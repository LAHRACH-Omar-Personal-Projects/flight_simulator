package app.views.env.entities;

import app.views.env.models.TexturedModel;
import app.views.env.renderEngine.DisplayManager;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;

public class Airplane extends Entity {
    /************* Airplane current values *************/
    public DoubleProperty currentPitch = new SimpleDoubleProperty();
    public DoubleProperty currentAltitude = new SimpleDoubleProperty();
    public DoubleProperty currentRoll = new SimpleDoubleProperty();
    public DoubleProperty currentDistance = new SimpleDoubleProperty();
    public DoubleProperty currentYaw = new SimpleDoubleProperty();
    /************* Airspeed *************/
    public static final double MIN_AIRSPEED = 0;
    public static final double MAX_AIRSPEED = 840;
    public static final double TAKE_OFF_SPEED = 285;
    public DoubleProperty airspeed = new SimpleDoubleProperty(MIN_AIRSPEED);
    public DoubleProperty pitchSpeed = new SimpleDoubleProperty();
    /************* Altitude *************/
    public static final double MIN_ALTITUDE = 2;
    public static final double MAX_ALTITUDE = 11900;
    /************* Roll and Yaw *************/
    public DoubleProperty yawSpeed = new SimpleDoubleProperty();
    public DoubleProperty rollSpeed = new SimpleDoubleProperty();
    /************* Pressure state *************/
    public static final double WARNING_PRESSURE = 94;
    public static final double DANGER_PRESSURE = 100;
    public final DoubleProperty pressure = new SimpleDoubleProperty(36);
    /************* Pressure state *************/
    public final DoubleProperty temperature = new SimpleDoubleProperty(402);
    /************* Fuel state *************/
    public final DoubleProperty fuel = new SimpleDoubleProperty();
    /************* Engine Warnings *************/
    public final IntegerProperty pressureState = new SimpleIntegerProperty();
    /************* Joystick Orders *************/
    public ListProperty<String> orders = new SimpleListProperty<>();
    private final MediaPlayer mediaPlayer;

    public Airplane(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
        Media sound = new Media(new File("engineSounds/warning.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    public void move(ListProperty<String> orders) {
        setOrders(orders);
        initAirplaneCurrentValues();
        handleJoystickInputs();
        applyChanges();
        setPressureState();
        setTemperatureState();
    }

    private void handleJoystickInputs() {
        if (orders.contains("ACCELERATE")) {
            accelerate();
        } else if (orders.contains("DECELERATE")) {
            decelerate();
        }

        if (orders.contains("PITCH UP")) {
            pitchUp();
        } else if (orders.contains("PITCH DOWN")) {
            pitchDown();
        } else  {
            pitchSpeed.setValue(0);
        }

        if (airspeed.getValue() >= 600 || currentAltitude.getValue() >= 9000 || temperature.getValue() >= 635 || pressure.getValue() >= 100) {
            playSound();
        }
        else {
            stopSound();
        }

        if(currentRoll.getValue() > 20 || currentRoll.getValue() < -20) {
            pitchSpeed.setValue(0);
        }

        if(currentPitch.getValue() != 0) {
            rollSpeed.setValue(0);
        }

        if (currentAltitude.getValue() != MIN_ALTITUDE && airspeed.getValue() == 0) {
            pitchDown();
        }

        if (orders.contains("ROLL LEFT")) {
            rollLeft();
        } else if (orders.contains("ROLL RIGHT")) {
            rollRight();
        } else {
            rollSpeed.setValue(0);
            if (currentAltitude.getValue() == MIN_ALTITUDE) {
                yawSpeed.setValue(0);
            }
        }
    }

    private void initAirplaneCurrentValues() {
        currentPitch.setValue(super.getRotX());
        currentAltitude.setValue(super.getPosition().y);
        currentRoll.setValue(super.getRotZ());
        currentDistance.setValue(super.getPosition().z);
        currentYaw.setValue(super.getRotY());
    }

    public void accelerate() {
        if (airspeed.getValue() < MAX_AIRSPEED) {
            airspeed.setValue(airspeed.getValue() + 1);
        }
    }

    public void decelerate() {
        if (currentAltitude.getValue() == MIN_ALTITUDE) {
            if (airspeed.getValue() > -MAX_AIRSPEED) {
                airspeed.setValue(airspeed.getValue() - 1);
            }
        }
        if (currentAltitude.getValue() > MIN_ALTITUDE) {
            if (airspeed.getValue() > MIN_AIRSPEED) {
                airspeed.setValue(airspeed.getValue() - 1);
            }
        }
    }

    public void pitchUp() {
        pitchSpeed.setValue(-10);
        yawSpeed.setValue(0);
    }

    public void pitchDown() {
        pitchSpeed.setValue(10);
        yawSpeed.setValue(0);
    }

    public void rollLeft() {
        if (currentAltitude.getValue() > MIN_ALTITUDE) {
            rollSpeed.setValue(-10);
        }
        if (currentRoll.getValue() < 0 || currentAltitude.getValue() == MIN_ALTITUDE) {
            yawSpeed.setValue(10);
        }
    }

    public void rollRight() {
        if (currentAltitude.getValue() > MIN_ALTITUDE) {
            rollSpeed.setValue(10);
        }
        if (currentRoll.getValue() > 0 || currentAltitude.getValue() == MIN_ALTITUDE) {
            yawSpeed.setValue(-10);
        }
    }

    public void setPressureState() {
        int state = 0;
        if(currentAltitude.getValue() > 9000) {
            pressure.setValue(0.3 * currentAltitude.getValue() + 0.04 * airspeed.getValue());
        }
        else {
            pressure.setValue(0.05 * currentAltitude.getValue() + 0.04 * airspeed.getValue());
        }
        if (pressure.getValue() >= WARNING_PRESSURE) {
            state = 1;
        }
        if (pressure.getValue() >= DANGER_PRESSURE) {
            state = 2;
        }
        pressureState.setValue(state);
    }

    public void setTemperatureState() {
        if(airspeed.getValue() >= 400) {
            temperatureProperty().setValue(temperature.getValue() + airspeed.getValue() / 20000);
        }
        else {
            temperatureProperty().setValue(temperature.getValue() - airspeed.getValue() / 20000);
        }
    }

    private void applyChanges() {
        super.increaseRotation(
                pitchSpeed.floatValue() * DisplayManager.getFrameTimeSeconds(),
                yawSpeed.floatValue() * DisplayManager.getFrameTimeSeconds(),
                rollSpeed.floatValue() * DisplayManager.getFrameTimeSeconds());
        double distance = airspeed.getValue() * (DisplayManager.getFrameTimeSeconds() / 5);
        double dx = distance * Math.sin(Math.toRadians(super.getRotY()));
        double dy = - distance * Math.tan(Math.toRadians(super.getRotX()));
        double dz = distance * Math.cos(Math.toRadians(super.getRotY()));

        if(dy > -1) {
            super.increasePosition(dx, dy, dz);
        }
    }

    public void playSound() {
        if(!mediaPlayer.getStatus().name().equals("PLAYING")) {
            mediaPlayer.setCycleCount(1000);
            new Thread(mediaPlayer::play).start();
        }
    }

    public void stopSound() {
        if(mediaPlayer.getStatus().name().equals("PLAYING")) {
            new Thread(mediaPlayer::stop).start();;
        }
    }

    public DoubleProperty currentPitchProperty() {
        return currentPitch;
    }

    public DoubleProperty currentAltitudeProperty() {
        return currentAltitude;
    }

    public DoubleProperty currentRollProperty() {
        return currentRoll;
    }

    public DoubleProperty currentDistanceProperty() {
        return currentDistance;
    }

    public DoubleProperty airspeedProperty() {
        return airspeed;
    }

    public DoubleProperty pressureProperty() {
        return pressure;
    }

    public DoubleProperty temperatureProperty() {
        return temperature;
    }

    public DoubleProperty fuelProperty() {
        return fuel;
    }

    public void setOrders(ObservableList<String> orders) {
        this.orders.set(orders);
    }

    public DoubleProperty currentYawProperty() {
        return currentYaw;
    }
}
