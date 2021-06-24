package app.views.vfp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class VFPLoader {
    public static void load() throws IOException {
        FXMLLoader loader = new FXMLLoader(VFPLoader.class.getResource("vfp.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 500, 425));
        stage.setX(1035);
        stage.setY(380);
        stage.show();
    }
}
