package app.views.ewd;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EWDLoader {
    public static void load() throws IOException {
        FXMLLoader loader = new FXMLLoader(EWDLoader.class.getResource("ewd.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 542, 425));
        stage.setX(492);
        stage.setY(380);
        stage.show();
    }
}
