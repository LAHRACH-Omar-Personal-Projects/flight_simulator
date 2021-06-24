package app.views.pfd;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PFDLoader {
    public static void load() throws IOException {
        FXMLLoader loader = new FXMLLoader(PFDLoader.class.getResource("pfd.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 500, 380));
        stage.setX(-8);
        stage.setY(0);
        stage.show();
    }
}
