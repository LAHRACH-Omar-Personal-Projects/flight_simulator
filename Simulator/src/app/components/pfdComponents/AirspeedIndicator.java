package app.components.pfdComponents;

import app.views.env.entities.Airplane;
import eu.hansolo.medusa.*;
import javafx.scene.paint.Color;

public class AirspeedIndicator {
    private final Gauge gauge;

    public AirspeedIndicator() {
        gauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.LINEAR)
                .foregroundBaseColor(Color.WHITE)
                .title("Airspeed")
                .titleColor(Color.WHITE)
                .subTitle("")
                .subTitleColor(Color.WHITE)
                .unit("km/h")
                .unitColor(Color.WHITE)
                .valueColor(Color.WHITE)
                .decimals(0)
                .minValue(Airplane.MIN_AIRSPEED)
                .maxValue(Airplane.MAX_AIRSPEED)
                .angleRange(280)
                .barColor(Color.GREEN)
                .tickLabelDecimals(0)
                .tickLabelLocation(TickLabelLocation.INSIDE)
                .tickLabelOrientation(TickLabelOrientation.HORIZONTAL)
                .onlyFirstAndLastTickLabelVisible(false)
                .majorTickMarksVisible(true)
                .majorTickMarkType(TickMarkType.LINE)
                .majorTickMarkColor(Color.WHITE)
                .mediumTickMarksVisible(true)
                .mediumTickMarkType(TickMarkType.LINE)
                .mediumTickMarkColor(Color.WHITE)
                .minorTickMarksVisible(true)
                .minorTickMarkType(TickMarkType.LINE)
                .minorTickMarkColor(Color.WHITE)
                .ledType(Gauge.LedType.STANDARD)
                .ledBlinking(true)
                .needleShape(Gauge.NeedleShape.ANGLED)
                .needleSize(Gauge.NeedleSize.STANDARD)
                .needleColor(Color.CRIMSON)
                .startFromZero(false)
                .returnToZero(false)
                .knobType(Gauge.KnobType.STANDARD)
                .knobColor(Color.LIGHTGRAY)
                .interactive(false)
                .ledOn(true)
                .thresholdVisible(false)
                .checkThreshold(false)
                .checkSectionsForValue(false)
                .markersVisible(false)
                .animated(false)
                .build();
    }

    public Gauge getGauge() {
        return gauge;
    }
}
