package app;

import app.views.env.engineTester.EnvLoader;
import app.views.ewd.EWDLoader;
import app.views.pfd.PFDLoader;
import app.views.radar.RadarLoader;
import app.views.vfp.VFPLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Runnable envRunnable = EnvLoader::load;
        Thread envThread = new Thread(envRunnable);
        envThread.start();
        EWDLoader.load();
        PFDLoader.load();
        RadarLoader.load();
        VFPLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
