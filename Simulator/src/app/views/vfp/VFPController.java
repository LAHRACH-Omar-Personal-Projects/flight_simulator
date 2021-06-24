package app.views.vfp;

import app.components.vfpComponents.Trajectory;
import app.views.env.engineTester.EnvLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.concurrent.ScheduledExecutorService;

public class VFPController {
    public HBox trajectoryContainer;
    public StackPane vfp;
    private Trajectory trajectory;
    private double maxSize = 10000;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            BackgroundImage myBI= new BackgroundImage(new Image("app/assets/images/bg.png", 600, 600,false,true), null, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            vfp.setBackground(new Background(myBI));
            trajectory = new Trajectory();
            trajectoryContainer.getChildren().add(trajectory.getChart());

            EnvLoader.receiver.currentDistanceProperty().addListener((observableValue, number, t1) -> {
                Platform.runLater(() -> {
                    trajectory.getSeries().getData().add(new XYChart.Data<>(Math.abs(Math.round(EnvLoader.receiver.currentDistanceProperty().getValue())), EnvLoader.receiver.currentAltitudeProperty().getValue()));
                    if (trajectory.getSeries().getData().size() > 200)
                        maxSize++;
                        trajectory.getChart().getXAxis().setMaxSize(0, maxSize);
                });
            });
        });
    }
}
