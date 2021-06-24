package app.views.pfd;

import app.components.pfdComponents.AirspeedIndicator;
import app.components.pfdComponents.Altimeter;
import app.components.pfdComponents.horizon.Horizon;
import app.views.env.engineTester.EnvLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PFDController {
    public VBox airspeedIndicatorContainer;
    public AirspeedIndicator airspeedIndicator;
    public VBox horizonContainer;
    public Horizon horizon;
    public VBox altimeterContainer;
    public Altimeter altimeter;
    public BorderPane pfd;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            BackgroundImage myBI = new BackgroundImage(new Image("app/assets/images/bg.png", 600, 600, false, true), null, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            pfd.setBackground(new Background(myBI));

            airspeedIndicator = new AirspeedIndicator();
            airspeedIndicatorContainer.getChildren().add(airspeedIndicator.getGauge());
            EnvLoader.receiver.airspeedProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    if (observableValue.getValue().doubleValue() < airspeedIndicator.getGauge().getMaxValue() && observableValue.getValue().doubleValue() > airspeedIndicator.getGauge().getMinValue()) {
                        airspeedIndicator.getGauge().setValue(Math.abs(observableValue.getValue().doubleValue()));
                    }
                    if (airspeedIndicator.getGauge().getValue() < 600) {
                        airspeedIndicator.getGauge().setLedVisible(false);
                        airspeedIndicator.getGauge().setBarColor(Color.GREEN);
                        airspeedIndicator.getGauge().setLedColor(Color.GREEN);
                    }
                    if (airspeedIndicator.getGauge().getValue() >= 600 && airspeedIndicator.getGauge().getValue() < 650) {
                        airspeedIndicator.getGauge().setLedVisible(true);
                        airspeedIndicator.getGauge().setLedBlinking(true);
                        airspeedIndicator.getGauge().setBarColor(Color.ORANGE);
                        airspeedIndicator.getGauge().setLedColor(Color.ORANGE);
                    }
                    if (airspeedIndicator.getGauge().getValue() > 650) {
                        airspeedIndicator.getGauge().setLedVisible(true);
                        airspeedIndicator.getGauge().setLedBlinking(true);
                        airspeedIndicator.getGauge().setBarColor(Color.RED);
                        airspeedIndicator.getGauge().setLedColor(Color.RED);
                    }
                });
            });

            horizon = new Horizon();
            horizonContainer.getChildren().add(horizon);
            EnvLoader.receiver.currentPitchProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    horizon.pitchProperty().set(-EnvLoader.receiver.currentPitchProperty().getValue());
                });
            });
            EnvLoader.receiver.currentRollProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    horizon.rollProperty().set(-EnvLoader.receiver.currentRollProperty().getValue());
                });
            });

            altimeter = new Altimeter();
            altimeterContainer.getChildren().add(altimeter.getGauge());
            EnvLoader.receiver.currentAltitudeProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    if(observableValue.getValue().doubleValue() < altimeter.getGauge().getMaxValue() && observableValue.getValue().doubleValue() > altimeter.getGauge().getMinValue()) {
                        altimeter.getGauge().setValue(Math.abs(observableValue.getValue().doubleValue()));
                    }
                    if(altimeter.getGauge().getValue() < 9000) {
                        altimeter.getGauge().setLedVisible(false);
                        altimeter.getGauge().setLedBlinking(false);
                        altimeter.getGauge().setBarColor(Color.GREEN);
                        altimeter.getGauge().setLedColor(Color.GREEN);
                    }
                    if(altimeter.getGauge().getValue() >= 9000 && altimeter.getGauge().getValue() < 11000){
                        altimeter.getGauge().setLedVisible(true);
                        altimeter.getGauge().setBarColor(Color.ORANGE);
                        altimeter.getGauge().setLedColor(Color.ORANGE);
                    }
                    if (altimeter.getGauge().getValue() > 11000) {
                        altimeter.getGauge().setLedVisible(true);
                        altimeter.getGauge().setBarColor(Color.RED);
                        altimeter.getGauge().setLedColor(Color.RED);
                    }
                });
            });
        });
    }
}
