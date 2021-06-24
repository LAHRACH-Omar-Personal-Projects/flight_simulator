package app.views.ewd;

import app.components.pfdComponents.AccelerometerIndicator;
import app.views.env.engineTester.EnvLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class EWDController {
    public HBox n11Container;
    public VBox n1UnitContainer;
    public HBox n12Container;
    public HBox egt1Container;
    public VBox egtUnitContainer;
    public HBox egt2Container;
    public HBox ffContainer;
    public VBox WarningsDisplay;
    public Label n1WarningLabel;
    public Label egtWarningLabel;
    public GridPane ewd;

    private AccelerometerIndicator n11;
    private AccelerometerIndicator n12;
    private AccelerometerIndicator egt1;
    private AccelerometerIndicator egt2;
    private AccelerometerIndicator ff;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            BackgroundImage myBI= new BackgroundImage(new Image("app/assets/images/bg.png", 600, 600,false,true), null, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            ewd.setBackground(new Background(myBI));

            n11 = new AccelerometerIndicator("N1");
            n12 = new AccelerometerIndicator("N1");
            egt1 = new AccelerometerIndicator("EGT");
            egt2 = new AccelerometerIndicator("EGT");
            ff = new AccelerometerIndicator("FF");

            n11.getGauge().setValue(36);
            n12.getGauge().setValue(36);
            egt1.getGauge().setValue(402);
            egt2.getGauge().setValue(402);

            n11Container.getChildren().add(n11.getGauge());
            n12Container.getChildren().add(n12.getGauge());
            egt1Container.getChildren().add(egt1.getGauge());
            egt2Container.getChildren().add(egt2.getGauge());
            ffContainer.getChildren().add(ff.getGauge());

            EnvLoader.receiver.pressureProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    if(observableValue.getValue().doubleValue() < n11.getGauge().getMaxValue() && observableValue.getValue().doubleValue() > n11.getGauge().getMinValue()) {
                        n11.getGauge().setValue(observableValue.getValue().doubleValue());
                        n12.getGauge().setValue(observableValue.getValue().doubleValue());
                    }
                    if(n11.getGauge().getValue() >= 94 || n12.getGauge().getValue() >= 94) {
                        n1WarningLabel.setText("N1 exceeds 94%");
                        n1WarningLabel.setStyle("-fx-text-fill: orange");
                        n11.getGauge().setNeedleColor(Color.ORANGE);
                        n12.getGauge().setNeedleColor(Color.ORANGE);
                    }
                    if(n11.getGauge().getValue() >= 100 || n12.getGauge().getValue() >= 100){
                        n1WarningLabel.setText("N1 exceeds 100%!!!");
                        n1WarningLabel.setStyle("-fx-text-fill: red");
                        n11.getGauge().setNeedleColor(Color.RED);
                        n12.getGauge().setNeedleColor(Color.RED);
                    }
                    if(n11.getGauge().getValue() < 94 && n12.getGauge().getValue() < 94){
                        n1WarningLabel.setText("");
                        n11.getGauge().setNeedleColor(Color.GREEN);
                        n12.getGauge().setNeedleColor(Color.GREEN);
                    }
                });
            });

            EnvLoader.receiver.temperatureProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    if(observableValue.getValue().doubleValue() < egt1.getGauge().getMaxValue() && observableValue.getValue().doubleValue() > egt2.getGauge().getMinValue()) {
                        egt1.getGauge().setValue(observableValue.getValue().doubleValue());
                        egt2.getGauge().setValue(observableValue.getValue().doubleValue());
                    }
                    if(egt1.getGauge().getValue() >= 610 || egt1.getGauge().getValue() >= 610) {
                        egtWarningLabel.setText("EGT exceeds 610°C");
                        egtWarningLabel.setStyle("-fx-text-fill: orange");
                        egt1.getGauge().setNeedleColor(Color.ORANGE);
                        egt2.getGauge().setNeedleColor(Color.ORANGE);
                    }
                    if(egt1.getGauge().getValue() >= 635 || egt2.getGauge().getValue() >= 635){
                        egtWarningLabel.setText("EGT exceeds 635°C!!!");
                        egtWarningLabel.setStyle("-fx-text-fill: red");
                        egt1.getGauge().setNeedleColor(Color.RED);
                        egt2.getGauge().setNeedleColor(Color.RED);
                    }
                    if(egt1.getGauge().getValue() < 610 && egt2.getGauge().getValue() < 610){
                        egtWarningLabel.setText("");
                        egt1.getGauge().setNeedleColor(Color.GREEN);
                        egt2.getGauge().setNeedleColor(Color.GREEN);
                    }
                });
            });

            EnvLoader.receiver.fuelProperty().addListener((observableValue, oldValue, t1) -> {
                Platform.runLater(() -> {
                    ff.getGauge().setValue(observableValue.getValue().doubleValue());
                });
            });
        });
    }
}