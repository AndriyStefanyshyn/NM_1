package com.restingrobots.nm_1;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * @Project name: Trigonometric
 * @Class name: Trigonometric
 * @Author: Qiuzhping
 * @Time: 2014-1-18 am 7:40:37
 * @Effect: simulation using sin AChartEngine, cos trigonometric function, this is high school across the function graph, now through the code to draw is not the same, I now is the sin test,cos
 *     Others do not know AChartEngine can draw. 
 */
public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LinearLayout mLinear = (LinearLayout) findViewById(R.id.chart);
        mLinear.setBackgroundColor(Color.WHITE);

        Bundle extras = getIntent().getExtras();
        double fromX = extras.getInt("fromX", 0);
        double toX = extras.getInt("toX", 1);
        double fromY = extras.getInt("fromY", 0);
        double toY = extras.getInt("toY", 1);

        double amount;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            amount = width/2;
        }
        else {
            amount = height/2;
        }
        double step = (toX - fromX)/amount;

        XYSeries series = new XYSeries("Graph", 0);
        for (double i = fromX; i < toX; i += step) {
            series.add(i, Finder.getY(i));
        }

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeriesRenderer renderer = new XYSeriesRenderer();

        dataset.addSeries(series);
        mRenderer.addSeriesRenderer(renderer);

        renderer.setColor(Color.rgb(0, 50, 0));

        mRenderer.setXTitle("X axis");
        mRenderer.setYTitle("Y axis");
        mRenderer.setAxisTitleTextSize((float) (amount / 15));
        mRenderer.setLabelsTextSize((float) (amount / 15));
        mRenderer.setMarginsColor(Color.WHITE);
        mRenderer.setAxesColor(Color.BLACK);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setXLabelsColor(Color.BLACK);
        mRenderer.setYLabelsColor(0, Color.BLACK);
        mRenderer.setYLabelsAlign(Align.RIGHT);
        mRenderer.setShowLegend(false);
        mRenderer.setAntialiasing(true);
        mRenderer.setZoomEnabled(true, true);
        mRenderer.setShowGrid(true);
        mRenderer.setYLabelsPadding(5);
        mRenderer.setMargins(new int[]{40, (int)(amount / 6), 15, 20});
        mRenderer.setPanEnabled(true, true);
        mRenderer.setXAxisMin(fromX);
        mRenderer.setXAxisMax(toX);
        mRenderer.setYAxisMin(fromY);
        mRenderer.setYAxisMax(toY);

        GraphicalView mChartView = ChartFactory.getLineChartView(getApplicationContext(), dataset, mRenderer);
        mLinear.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }
}