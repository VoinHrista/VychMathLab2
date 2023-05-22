import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

public class PrinterGraph {

    private void rawDraw(String title, XYSeriesCollection lineDataset) {


        JFreeChart lineChart = ChartFactory.createXYLineChart(
                title, "x",
                "y",
                lineDataset, PlotOrientation.VERTICAL,
                true, true, false
                );

        int width = 1920;
        int height = 1080;

        try {
            ChartUtils.saveChartAsJPEG(new File("src/main/res/Chart.jpeg"), lineChart, width, height);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lambdaDraw(double a, double b, double lambda) {
        XYSeriesCollection lineDataset = new XYSeriesCollection ();

        a = a - 1;
        b = b + 1;

        XYSeries function = new XYSeries("function");

        double len = b-a;
        for (double i = a; i <= b; i+=len/12) {
            function.add(i,i - lambda * Function.f(i));
        }

        XYSeries zero = new XYSeries("zero");
        zero.add(a,0);
        zero.add(b,0);


        lineDataset.addSeries(function);
        lineDataset.addSeries(zero);

        rawDraw("f(x)", lineDataset);
    }

    public void draw(double a, double b){
        XYSeriesCollection lineDataset = new XYSeriesCollection ();
        a--;
        b++;

        XYSeries function = new XYSeries("y = f(x)");
        double len = b-a;
        for (double i = a; i <= b; i += len / 12) {
            function.add(i, Function.f(i));
        }

        XYSeries zero = new XYSeries("y = 0");
        zero.add(a,0);
        zero.add(b,0);


        lineDataset.addSeries(function);
        lineDataset.addSeries(zero);

        rawDraw("f(x)", lineDataset);

    }

    public void sysDraw(int number, double ax, double bx, double ay, double by){
        XYSeriesCollection lineDataset = new XYSeriesCollection ();
        ax -= 0.5;
        bx += 0.5;
        ay -= 0.5;
        by += 0.5;

        XYSeries function1 = new XYSeries("f1(x, y) = 0");
        double delta_x = (bx - ax)/1000, delta_y = (by - ay)/1000;

        for (double x = ax; x <= bx; x += delta_x) {
            for (double y = ay; y <= by; y += delta_y){
                if (Math.abs(Function.f1(number, x, y)) < 0.001){
                    function1.add(x, y);
                }
            }
        }

        XYSeries function2 = new XYSeries("f2(x, y) = 0");

        for (double x = ax; x <= bx; x += delta_x) {
            for (double y = ay; y <= by; y += delta_y){
                if (Math.abs(Function.f2(number, x, y)) < 0.001){
                    function2.add(x, y);
                }
            }
        }

        lineDataset.addSeries(function1);
        lineDataset.addSeries(function2);

        rawDraw("f1(x, y), f2(x, y)", lineDataset);

        //function.getAutoSort(true);

    }
}
