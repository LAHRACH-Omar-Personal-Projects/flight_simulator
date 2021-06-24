package app.components.pfdComponents;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import javafx.scene.paint.Color;

public class AccelerometerIndicator {
    private Gauge gauge;

    public AccelerometerIndicator(String skinType) {
        if(skinType.equals("N1")) {
            gauge = GaugeBuilder.create()
                    .skinType(Gauge.SkinType.TILE_KPI)
                    .backgroundPaint(Color.TRANSPARENT)
                    .foregroundBaseColor(Color.rgb(238, 238, 238))
                    .valueColor(Color.rgb(238, 238, 238))
                    .thresholdVisible(false)
                    .minValue(0)
                    .maxValue(120)
                    .thresholdColor(Color.WHITE)
                    .needleColor(Color.GREEN)
                    .angleRange(180)
                    .build();
        }
        if(skinType.equals("EGT")) {
            gauge = GaugeBuilder.create()
                    .skinType(Gauge.SkinType.TILE_KPI)
                    .backgroundPaint(Color.TRANSPARENT)
                    .foregroundBaseColor(Color.rgb(238, 238, 238))
                    .barColor(Color.WHITE)
                    .valueColor(Color.rgb(238, 238, 238))
                    .thresholdVisible(false)
                    .threshold(635)
                    .minValue(0)
                    .maxValue(1000)
                    .ledVisible(true)
                    .ledOn(true)
                    .ledColor(Color.RED)
                    .thresholdColor(Color.RED)
                    .needleColor(Color.GREEN)
                    .angleRange(180)
                    .build();
        }
        if(skinType.equals("FF")) {
            gauge = GaugeBuilder.create()
                    .skinType(Gauge.SkinType.TILE_KPI)
                    .decimals(0)
                    .backgroundPaint(Color.TRANSPARENT)
                    .foregroundBaseColor(Color.rgb(238, 238, 238))
                    .barColor(Color.WHITE)
                    .valueColor(Color.rgb(238, 238, 238))
                    .thresholdVisible(false)
                    .threshold(100)
                    .minValue(0)
                    .maxValue(1000)
                    .ledVisible(true)
                    .ledOn(true)
                    .ledColor(Color.RED)
                    .thresholdColor(Color.RED)
                    .threshold(700)
                    .needleColor(Color.GREEN)
                    .angleRange(180)
                    .build();
        }
    }

    public Gauge getGauge() {
        return gauge;
    }
}
