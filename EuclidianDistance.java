import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class EuclidianDistance {

    private XYChart.Series series0;
    private XYChart.Series series1;
    private XYChart.Series series2;
    private XYChart.Series series3;
    private XYChart.Series series4;
    private XYChart.Series series5;

    final NumberAxis EDDataYAxis = new NumberAxis();
    final CategoryAxis EDDataXAxis = new CategoryAxis();
    final BarChart<String, Number> EDDataGraph =
                new BarChart<String, Number>(EDDataXAxis, EDDataYAxis);
    private double[][][] EDData;
    private File inFile;
    private Pane EDPane;
    private RawGraph rawDataGraph;

    final static String channel1 = "Channel 1";
    final static String channel2 = "Channel 2";
    final static String channel3 = "Channel 3";
    final static String channel4 = "Channel 4";
    final static String channel5 = "Channel 5";
    final static String channel6 = "Channel 6";
    final static String channel7 = "Channel 7";
    final static String channel8 = "Channel 8";

    public EuclidianDistance(RawGraph raw) throws Exception, FileNotFoundException {

        series0 = new XYChart.Series();
        series0.setName("1.5 L");
        series1 = new XYChart.Series();
        series1.setName("1.0 L");
        series2 = new XYChart.Series();
        series2.setName("0.5 L");
        series3 = new XYChart.Series();
        series3.setName("0.5 R");
        series4 = new XYChart.Series();
        series4.setName("1.0 R");
        series5 = new XYChart.Series();
        series5.setName("1.5 R");

        rawDataGraph = raw;

        EDData = new double[7][6000][8];

        inFile = null;

        EDPane = new Pane();
        EDDataGraph.getData().addAll(  series0, series1, series2, series3,
                                    series4, series5);
        EDDataGraph.setMinSize(1000,500);
        EDPane.getChildren().add(EDDataGraph);
        EDDataGraph.setTitle("Euclidian Distance for each channel");
        EDDataYAxis.setLabel("Euclidian Distance");
        EDDataXAxis.setLabel("Channels");
        EDDataXAxis.setCategories(FXCollections.<String>observableArrayList(
                                    Arrays.asList(channel1, channel2, channel3,
                                    channel4, channel5, channel6,
                                    channel7, channel8)));

    }

    public void giveData(File[] fileList) throws FileNotFoundException {
        try {

            Scanner[] sc = new Scanner[6];

            for (int i = 0; i < sc.length; i++) {

                sc[i] = new Scanner(fileList[i]);
                sc[i].useDelimiter(",|\\s");
            }

            EDData[0] = rawDataGraph.getRawData();

            for (int i = 1; i < EDData.length; i++) {
                for (int j = 0; j < EDData[i].length; j++) {
                    for (int k = 0; k < EDData[i][j].length; k++) {

                        EDData[i][j][k] = Double.parseDouble(sc[i-1].next());
                    }
                }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(EuclidianDistance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SeriesGraphing() throws FileNotFoundException {

        double[][] mavData = new double[7][8];
        double[][] sscData = new double[7][8];
        double[][] wlData = new double[7][8];
        double[][] zcData = new double[7][8];

        double[][] barData = new double[8][6];

        for (int i = 0; i < 7; i++) {

            mavData[i] = MAV(EDData[i]);
            sscData[i] = SSC(EDData[i]);
            wlData[i] = Wavelength(EDData[i]);
            zcData[i] = Zero_C(EDData[i]);

        }

        double temp = 0;

        for (int i = 0; i < 6; i++) {
            temp = 0;

            for (int j = 0; j < 8; j++) {

                temp += Math.pow( (mavData[0][j] - mavData[i+1][j]) , 2 );
                temp += Math.pow( (sscData[0][j] - sscData[i+1][j]) , 2 );
                temp += Math.pow( (wlData[0][j] - wlData[i+1][j]) , 2 );
                temp += Math.pow( (zcData[0][j] - zcData[i+1][j]) , 2 );

                temp = Math.sqrt(temp);

                barData[j][i] = temp;
            }
        }

        series0.getData().add(new XYChart.Data(channel1, barData[0][0]));
        series0.getData().add(new XYChart.Data(channel2, barData[1][0]));
        series0.getData().add(new XYChart.Data(channel3, barData[2][0]));
        series0.getData().add(new XYChart.Data(channel4, barData[3][0]));
        series0.getData().add(new XYChart.Data(channel5, barData[4][0]));
        series0.getData().add(new XYChart.Data(channel6, barData[5][0]));
        series0.getData().add(new XYChart.Data(channel7, barData[6][0]));
        series0.getData().add(new XYChart.Data(channel8, barData[7][0]));

        series1.getData().add(new XYChart.Data(channel1, barData[0][1]));
        series1.getData().add(new XYChart.Data(channel2, barData[1][1]));
        series1.getData().add(new XYChart.Data(channel3, barData[2][1]));
        series1.getData().add(new XYChart.Data(channel4, barData[3][1]));
        series1.getData().add(new XYChart.Data(channel5, barData[4][1]));
        series1.getData().add(new XYChart.Data(channel6, barData[5][1]));
        series1.getData().add(new XYChart.Data(channel7, barData[6][1]));
        series1.getData().add(new XYChart.Data(channel8, barData[7][1]));

        series2.getData().add(new XYChart.Data(channel1, barData[0][2]));
        series2.getData().add(new XYChart.Data(channel2, barData[1][2]));
        series2.getData().add(new XYChart.Data(channel3, barData[2][2]));
        series2.getData().add(new XYChart.Data(channel4, barData[3][2]));
        series2.getData().add(new XYChart.Data(channel5, barData[4][2]));
        series2.getData().add(new XYChart.Data(channel6, barData[5][2]));
        series2.getData().add(new XYChart.Data(channel7, barData[6][2]));
        series2.getData().add(new XYChart.Data(channel8, barData[7][2]));

        series3.getData().add(new XYChart.Data(channel1, barData[0][3]));
        series3.getData().add(new XYChart.Data(channel2, barData[1][3]));
        series3.getData().add(new XYChart.Data(channel3, barData[2][3]));
        series3.getData().add(new XYChart.Data(channel4, barData[3][3]));
        series3.getData().add(new XYChart.Data(channel5, barData[4][3]));
        series3.getData().add(new XYChart.Data(channel6, barData[5][3]));
        series3.getData().add(new XYChart.Data(channel7, barData[6][3]));
        series3.getData().add(new XYChart.Data(channel8, barData[7][3]));

        series4.getData().add(new XYChart.Data(channel1, barData[0][4]));
        series4.getData().add(new XYChart.Data(channel2, barData[1][4]));
        series4.getData().add(new XYChart.Data(channel3, barData[2][4]));
        series4.getData().add(new XYChart.Data(channel4, barData[3][4]));
        series4.getData().add(new XYChart.Data(channel5, barData[4][4]));
        series4.getData().add(new XYChart.Data(channel6, barData[5][4]));
        series4.getData().add(new XYChart.Data(channel7, barData[6][4]));
        series4.getData().add(new XYChart.Data(channel8, barData[7][4]));

        series5.getData().add(new XYChart.Data(channel1, barData[0][5]));
        series5.getData().add(new XYChart.Data(channel2, barData[1][5]));
        series5.getData().add(new XYChart.Data(channel3, barData[2][5]));
        series5.getData().add(new XYChart.Data(channel4, barData[3][5]));
        series5.getData().add(new XYChart.Data(channel5, barData[4][5]));
        series5.getData().add(new XYChart.Data(channel6, barData[5][5]));
        series5.getData().add(new XYChart.Data(channel7, barData[6][5]));
        series5.getData().add(new XYChart.Data(channel8, barData[7][5]));

        EDDataGraph.setBarGap(5);
        EDDataGraph.setCategoryGap(20);
    }

    public double[] MAV(double[][] inData){

        double[] output = new double[8];
        double x = inData.length;


        for(int i = 0; i < inData[0].length; i++)
        {
          double counter = 0;
          for(int j = 0; j < inData.length; j++)
          {

            counter += Math.abs(inData[j][i]);
          }
          output[i] = (1/x)*counter;
        }

        return output;
    }

    public double[] SSC(double[][] inData) {

        double[] outArray = new double[8];
        double threshold = 0.0;

        for (int i = 0; i < inData[0].length; i++) {
            for (int j = 1; j < inData.length - 1; j++) {

                if (((inData[j][i] - inData[j-1][i]) * (inData[j][i] - inData[j+1][i])) >= threshold) {

                    outArray[i] += 1;
                }
            }
        }

        return outArray;

    }

    public double[] Wavelength(double[][] inData) {

        double[] outArray = new double[8];
        double temp = 0;

        for(int i = 0; i < inData[0].length; i++)
        {
          temp = 0;
          for(int j = 1; j < inData.length; j++)
          {
            temp += Math.abs(inData[j][i] - inData[j-1][i]);
          }
          outArray[i] = temp;
        }

        return outArray;
    }

    public double[] Zero_C(double[][] inData) {

        double[] zc = {0,0,0,0,0,0,0,0};

        double threshold = 0.1;

        for (int i = 0; i < inData[0].length; i++) {

            for (int j = 0; j < inData.length-1; j++) {

                if (inData[j][i] * inData[j+1][i] < threshold) {
                        zc[i] += 1;
                }
                if (Math.abs(inData[j+1][i] / inData[j][i]) <= 0) {
                        zc[i] += 1;
                }
            }
        }
        return zc;
    }

    public Pane getPane() {
        return EDPane;

    }

    public BarChart<String, Number> getBarChart() {

        return EDDataGraph;

    }

    public RawGraph getRawDataGraph() {

        return rawDataGraph;

    }
}
