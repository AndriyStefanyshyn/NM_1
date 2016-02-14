package com.restingrobots.nm_1;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project name: Trigonometric
 * @Class name: Trigonometric
 * @Author: Qiuzhping
 * @Time: 2014-1-18 am 7:40:37
 * @Effect: simulation using sin AChartEngine, cos trigonometric function, this is high school across the function graph, now through the code to draw is not the same, I now is the sin test,cos
 *     Others do not know AChartEngine can draw. 
 */
public class GraphActivity extends AppCompatActivity {
    private LinearLayout mLinear;// Layout
    private int step = 2;// Settings for each point of the span, shoulds not be too big, because these trigonometric function graph is composed of a series of points, too few points may lead to not smooth
    private int count = 360 / step + 1;// To the point
    private List<double[]> x = new ArrayList<double[]>();// The X axis data set
    private List<double[]> y = new ArrayList<double[]>();// The Y axis data set
    private String[] titles;// The caption to display
    private int[] colors;// Color
    private PointStyle[] styles;// The chart style
    private XYMultipleSeriesRenderer renderer;// Live plotter
    private GraphicalView mChartView;// Display objects on the screen

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
                            List<double[]> xValues, List<double[]> yValues, int scale) {// Set point set
        int length = titles.length;
        for (int i = 0; i <length; i++) {
            XYSeries series = new XYSeries(titles[i], scale);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k <seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
    }

    protected XYMultipleSeriesDataset buildDataset(String[] titles,
                                                   List<double[]> xValues, List<double[]> yValues) {// Data set
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        addXYSeries(dataset, titles, xValues, yValues, 0);
        return dataset;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        mLinear = (LinearLayout) findViewById(R.id.chart);// Gets layout
        mLinear.setBackgroundColor(Color.WHITE);// Set the background color

        titles = new String[] { "sin", "cos" };

        x.add(new double[count]);
        x.add(new double[count]);
        double[] sinValues = new double[count];
        double[] cosValues = new double[count];
        y.add(sinValues);// Add sin
        y.add(cosValues);// Add cos
        for (int i = 0; i <count; i++) {// Point
            int angle = i * step;
            x.get(0)[i] = angle;
            x.get(1)[i] = angle;
            double rAngle = Math.toRadians(angle);
            sinValues[i] = Math.sin(rAngle);// To obtain the corresponding sin value
            cosValues[i] = Math.cos(rAngle);// To obtain the corresponding cos value
        }
        colors = new int[] { Color.BLUE, Color.RED };// Set the color

        styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT };// Set the style
        renderer = buildRenderer(colors, styles);
        setChartSettings(renderer, "Graph", "X axis", " Y axis", 0, 360,
                -1, 1, Color.BLACK, Color.BLACK, Color.WHITE);

        renderer.setYLabelsAlign(Align.RIGHT, 0);
        renderer.setYAxisAlign(Align.LEFT, 0);

        renderer.setXLabelsAlign(Align.CENTER);
        renderer.setXLabels(0);// Numbers X axis showed the tick label

        renderer.setAntialiasing(true);
        renderer.setZoomEnabled(false, false);
        renderer.setPanEnabled(false, false);// Not allowed to drag around, not allowed to move up and down
        renderer.setShowCustomTextGrid(true);
        renderer.setShowLegend(false);
        for (int i = 0; i <8; i++) {// Customize the display of the X axis
            renderer.addXTextLabel((i + 1) * 45, "" + (i + 1) * 45);
        }
        renderer.setShowGrid(true);
        mChartView = ChartFactory.getLineChartView(getApplicationContext(),
                buildDataset(titles, x, y), renderer);
        mLinear.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }

    protected void setChartSettings(XYMultipleSeriesRenderer renderer,
                                    String title, String xTitle, String yTitle, double xMin,
                                    double xMax, double yMin, double yMax, int axesColor,
                                    int labelsColor, int marginColor) {// The chart style settings
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);// X axis title
        renderer.setYTitle(yTitle);// Y axis title
        renderer.setXAxisMin(xMin);// The minimum value of X
        renderer.setXAxisMax(xMax);// Maximum X
        renderer.setYAxisMin(yMin);// The minimum value of Y
        renderer.setYAxisMax(yMax);// The max value of Y
        renderer.setAxesColor(axesColor);// X axis
        renderer.setLabelsColor(labelsColor);// Y axis
        renderer.setYLabelsColor(0, labelsColor);
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setMarginsColor(marginColor);
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
                               PointStyle[] styles) {// Set the tracer properties
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPointSize(5f);
        renderer.setMargins(new int[] { 20, 30, 15, 20 });// On the left, down, right
        int length = colors.length;
        for (int i = 0; i <length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
    }

    protected XYMultipleSeriesRenderer buildRenderer(int[] colors,
                                                     PointStyle[] styles) {// The chart plotter
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, colors, styles);
        return renderer;
    }
}