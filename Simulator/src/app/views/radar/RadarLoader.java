package app.views.radar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RadarLoader {
    public static void load() throws IOException {
        FXMLLoader loader = new FXMLLoader(RadarLoader.class.getResource("radar.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 500, 425));
        stage.setX(-8);
        stage.setY(380);
        stage.show();
    }
}
