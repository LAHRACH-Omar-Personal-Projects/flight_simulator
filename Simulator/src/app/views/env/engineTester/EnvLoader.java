package app.views.env.engineTester;

import app.views.env.entities.*;
import app.views.env.models.RawModel;
import app.views.env.models.TexturedModel;
import app.views.env.renderEngine.DisplayManager;
import app.views.env.renderEngine.Loader;
import app.views.env.renderEngine.MasterRenderer;
import app.views.env.renderEngine.OBJLoader;
import app.views.env.terrains.Terrain;
import app.views.env.textures.ModelTexture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnvLoader {
    /************* Joystick buttons *************/
    public static final int DECELERATE = 4;
    public static final int ACCELERATE = 5;
    public static final int PITCH_UP = 2;
    public static final int PITCH_DOWN = 0;
    public static final int ROLL_LEFT = 3;
    public static final int ROLL_RIGHT = 1;
    public static final int GO_UP = 7;
    public static final int GO_DOWN = 6;
    /************* Joystick Controller *************/
    public static Controller joystickController;
    /************* Airplanes Model *************/
    public static Airplane tanker;
    public static Airplane receiver;

    public static void load() {
        DisplayManager.createDisplay();
        Loader loader = new Loader();


        RawModel model = OBJLoader.loadObjModel("tree", loader);

        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("tree", "PNG")));

        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            entities.add(new Entity(staticModel, new Vector3f(random.nextFloat() * 800 - 400, 0, random.nextFloat() * -600), 0, 0, 0, 3));
        }

        Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));

        Terrain terrain0 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain1 = new Terrain(-1, 0, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain2 = new Terrain(-1, 1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain3 = new Terrain(-1, 2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain4 = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain5 = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain6 = new Terrain(0, 1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain7 = new Terrain(0, 2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain8 = new Terrain(1, -1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain9 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain10 = new Terrain(1, 1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain11 = new Terrain(1, 2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain12 = new Terrain(-2, -1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain13 = new Terrain(-2, 0, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain14 = new Terrain(-2, 1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain15 = new Terrain(-2, 2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));

        Terrain terrain16 = new Terrain(-2, -1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain17 = new Terrain(-2, 0, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain18 = new Terrain(-2, 1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain19 = new Terrain(-2, 2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain20 = new Terrain(0, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain21 = new Terrain(0, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain22 = new Terrain(0, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain23 = new Terrain(0, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain24 = new Terrain(1, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain25 = new Terrain(1, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain26 = new Terrain(1, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain27 = new Terrain(1, -2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain28 = new Terrain(-3, -1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain29 = new Terrain(-3, 0, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain30 = new Terrain(-3, 1, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        Terrain terrain31 = new Terrain(-3, 2, loader, new ModelTexture(loader.loadTexture("grass", "PNG")));
        MasterRenderer renderer = new MasterRenderer();

        RawModel airplaneModel = OBJLoader.loadObjModel("Airplane", loader);
        TexturedModel airplaneTexture = new TexturedModel(airplaneModel, new ModelTexture(loader.loadTexture("Airplane", "PNG")));
        tanker = new Airplane(airplaneTexture, new Vector3f(100, 2, 0), 0, 0, 0, 0.01f);
        receiver = new Airplane(airplaneTexture, new Vector3f(150, 2, 0), 0, 0, 0, 0.01f);

        AirplaneController airplaneController = new AirplaneController();
        Thread thread1 = new Thread(airplaneController);
        thread1.start();
        PhoneInputsHandler phoneInputsHandler = new PhoneInputsHandler();
        Thread thread2 = new Thread(phoneInputsHandler);
        thread2.start();

        Camera camera = new Camera(receiver);

        initJoystick();

        while (!Display.isCloseRequested()) {
            handleJoystickInputs();
            handlePhoneInputs();
            camera.move();
            receiver.move(AirplaneController.myJoystickOrder);
            tanker.move(AirplaneController.otherJoystickOrder);
            renderer.processEntity(receiver);
            renderer.processEntity(tanker);
            renderer.processTerrain(terrain0);
            renderer.processTerrain(terrain1);
            renderer.processTerrain(terrain2);
            renderer.processTerrain(terrain3);
            renderer.processTerrain(terrain4);
            renderer.processTerrain(terrain5);
            renderer.processTerrain(terrain6);
            renderer.processTerrain(terrain7);
            renderer.processTerrain(terrain8);
            renderer.processTerrain(terrain9);
            renderer.processTerrain(terrain10);
            renderer.processTerrain(terrain11);
            renderer.processTerrain(terrain12);
            renderer.processTerrain(terrain13);
            renderer.processTerrain(terrain14);
            renderer.processTerrain(terrain15);
            renderer.processTerrain(terrain16);
            renderer.processTerrain(terrain17);
            renderer.processTerrain(terrain18);
            renderer.processTerrain(terrain19);
            renderer.processTerrain(terrain20);
            renderer.processTerrain(terrain21);
            renderer.processTerrain(terrain22);
            renderer.processTerrain(terrain23);
            renderer.processTerrain(terrain24);
            renderer.processTerrain(terrain25);
            renderer.processTerrain(terrain26);
            renderer.processTerrain(terrain27);
            renderer.processTerrain(terrain28);
            renderer.processTerrain(terrain29);
            renderer.processTerrain(terrain30);
            renderer.processTerrain(terrain31);
            for (Entity entity : entities) {
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }

    private static void initJoystick() {
        try {
            Controllers.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        if (Controllers.getControllerCount() != 0) {
            Controllers.poll();
            joystickController = Controllers.getController(0);
            joystickController.poll();
        }
    }

    private static synchronized void handleJoystickInputs() {
        List<String> orders = new ArrayList<>();
        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
            orders.add("ACCELERATE");
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            orders.add("DECELERATE");
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            orders.add("ROLL LEFT");
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            orders.add("ROLL RIGHT");
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            orders.add("PITCH UP");
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            orders.add("PITCH DOWN");
        }
        if(joystickController != null) {
            if (joystickController.isButtonPressed(ACCELERATE)) {
                orders.add("ACCELERATE");
            }
            if (joystickController.isButtonPressed(DECELERATE)) {
                orders.add("DECELERATE");
            }

            if (joystickController.isButtonPressed(ROLL_LEFT)) {
                orders.add("ROLL LEFT");
            }
            if (joystickController.isButtonPressed(ROLL_RIGHT)) {
                orders.add("ROLL RIGHT");
            }

            if (joystickController.isButtonPressed(PITCH_UP)) {
                orders.add("PITCH UP");
            }
            if (joystickController.isButtonPressed(PITCH_DOWN)) {
                orders.add("PITCH DOWN");
            }
        }

        ObservableList<String> observableList = FXCollections.observableList(orders);
        AirplaneController.myJoystickOrder.setValue(observableList);
    }

    private static void handlePhoneInputs() {
        List<String> orders = new ArrayList<>();
        String order = PhoneInputsHandler.movement.getValue();
        if(order != null) {
            orders.add(order);
            ObservableList<String> observableList = FXCollections.observableList(orders);
            AirplaneController.otherJoystickOrder.setValue(observableList);
        }
    }
}
