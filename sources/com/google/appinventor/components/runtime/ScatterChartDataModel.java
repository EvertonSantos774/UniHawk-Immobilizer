package com.google.appinventor.components.runtime;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.google.appinventor.components.common.PointStyle;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;

public class ScatterChartDataModel extends PointChartDataModel<Entry, IScatterDataSet, ScatterData, ScatterChart, ScatterChartView> {
    public ScatterChartDataModel(ScatterData data, ScatterChartView view) {
        super(data, view);
        this.dataset = new ScatterDataSet(new ArrayList(), "");
        this.data.addDataSet(this.dataset);
        setDefaultStylingProperties();
    }

    public void addEntryFromTuple(YailList tuple) {
        Entry entry = getEntryFromTuple(tuple);
        if (entry != null) {
            this.entries.add(entry);
        }
    }

    /* access modifiers changed from: protected */
    public void setDefaultStylingProperties() {
        if (this.dataset instanceof ScatterDataSet) {
            this.dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        }
    }

    public void setPointShape(PointStyle shape) {
        if (this.dataset instanceof ScatterDataSet) {
            switch (shape) {
                case Circle:
                    this.dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
                    return;
                case Square:
                    this.dataset.setScatterShape(ScatterChart.ScatterShape.SQUARE);
                    return;
                case Triangle:
                    this.dataset.setScatterShape(ScatterChart.ScatterShape.TRIANGLE);
                    return;
                case Cross:
                    this.dataset.setScatterShape(ScatterChart.ScatterShape.CROSS);
                    return;
                case X:
                    this.dataset.setScatterShape(ScatterChart.ScatterShape.X);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown shape type: " + shape);
            }
        }
    }
}
