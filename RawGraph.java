import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class RawGraph {

    private XYChart.Series[] rawSeries;
    private NumberAxis rawDataXAxis, rawDataYAxis;
    private LineChart<Number, Number> rawDataGraph;
    private double[][] rawData;
    private File inFile;
    private Pane rawPane;

    public RawGraph() throws Exception {

        rawPane = new Pane();
        inFile = null;
        rawSeries = new XYChart.Series[8];

        for (int i = 0; i < rawSeries.length; i++) {

            rawSeries[i] = new XYChart.Series();
            rawSeries[i].setName("Channel " + (i + 1));
        }


        rawDataXAxis = new NumberAxis(0,6000,1000);
        rawDataXAxis.setLabel("Time");


        rawDataYAxis = new NumberAxis(-2000,250,250);
        rawDataYAxis.setLabel("Values");

        rawDataGraph = new LineChart<>(rawDataXAxis, rawDataYAxis);
        rawDataGraph.setTitle("RAW DATA");
        rawDataGraph.setMinSize(1000,500);
        rawDataGraph.setCreateSymbols(false);

        for (int i = 0; i < rawSeries.length; i++) {
            rawDataGraph.getData().add(rawSeries[i]);
        }

        rawData = new double[6000][8];

        rawPane.getChildren().add(rawDataGraph);

    }

    public void GraphRawData(File newFile) {
        if (newFile != null) {
            try {
                inFile = newFile;
                Scanner sc = new Scanner(inFile);
                //Read raw data file selected
                sc.useDelimiter(",|\\s");
                for(int i = 0; i < rawData.length; i++) {
                    for (int j = 0; j < rawData[0].length; j++) {
                        rawData[i][j] = Double.parseDouble(sc.next());
                        rawSeries[j].getData().add(
                            new XYChart.Data((i+1),
                            (rawData[i][j] - (250 * j))));
                    }
                }
            }
            catch (IOException ex) {
                Logger.getLogger(RawGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Pane getChart() {

        return rawPane;
    }

    public LineChart<Number, Number> getGraph() {

        return rawDataGraph;

    }

    public double[][] getRawData() {

        return rawData;

    }

}
