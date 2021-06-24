package app.views.radar;

import app.components.pfdComponents.AirspeedIndicator;
import app.components.radarComponents.AirCompass;
import app.views.env.engineTester.EnvLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class RadarController {
    public VBox airCompassContainer;
    public AirCompass airCompass;
    public StackPane radar;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            BackgroundImage myBI= new BackgroundImage(new Image("app/assets/images/bg.png", 600, 600,false,true), null,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            radar.setBackground(new Background(myBI));
            airCompass = new AirCompass();
            airCompassContainer.getChildren().add(airCompass);
            EnvLoader.receiver.currentYawProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    airCompass.setBearing(-EnvLoader.receiver.currentYawProperty().getValue());
                });
            });
        });
    }
}
