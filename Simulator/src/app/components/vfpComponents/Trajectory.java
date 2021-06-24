package app.components.vfpComponents;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;

public class Trajectory {
    final NumberAxis xAxis = new NumberAxis(0, 10000, 100);
    final NumberAxis yAxis = new NumberAxis(0, 11900, 10);
    final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    private Series series;

    public Trajectory() {
        xAxis.setLabel("Distance");
        xAxis.tickLabelFillProperty().setValue(Color.WHITE);
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Altitude");
        yAxis.tickLabelFillProperty().setValue(Color.WHITE);
        yAxis.setAnimated(false); // axis animations are removed

        lineChart.setAnimated(false); // disable animations
        lineChart.horizontalGridLinesVisibleProperty().setValue(false);
        lineChart.verticalGridLinesVisibleProperty().setValue(false);
        lineChart.legendVisibleProperty().setValue(false);


        //defining a series to display data
        series = new XYChart.Series<>();
        series.setName("Data Series");

        // add series to chart
        lineChart.getData().add(series);
    }

    public LineChart getChart() {
        return lineChart;
    }

    public Series getSeries() {
        return series;
    }
}
