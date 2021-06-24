package app.views.nd;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class NDController {

    public BorderPane nd;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            BackgroundImage myBI= new BackgroundImage(new Image("app/assets/images/bg.png", 600, 600,false,true), null, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            nd.setBackground(new Background(myBI));
        });
    }
}
