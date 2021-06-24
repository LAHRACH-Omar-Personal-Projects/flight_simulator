package app.views.nd;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NDLoader {
    public static void load() throws IOException {
        FXMLLoader loader = new FXMLLoader(NDLoader.class.getResource("nd.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 500, 400));
        stage.setX(1035);
        stage.setY(0);
        stage.show();
    }
}
