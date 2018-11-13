import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class LineGraph extends Application {

  public void start (Stage stage) throws Exception{
    stage.setTitle("LineGraph");
    //set y-axis as the value collected
    final NumberAxis yAxis1 = new NumberAxis();
    //set x-axis as the time of when 1 value is collected (6000 points in 5 seconds; 1200 points in 1 second)
    final NumberAxis xAxis1 = new NumberAxis();
    //make a line chart with the created axis
    final LineChart<Number, Number> lc = new LineChart<>(xAxis1, yAxis1);
    yAxis1.setLabel("Values");
    xAxis1.setLabel("Time");
    lc.setTitle("RAW DATA");
    //hide the dots
    lc.setCreateSymbols(false);

    //make 8 series for the 8 channels
    XYChart.Series c1 = new XYChart.Series();
    XYChart.Series c2 = new XYChart.Series();
    XYChart.Series c3 = new XYChart.Series();
    XYChart.Series c4 = new XYChart.Series();
    XYChart.Series c5 = new XYChart.Series();
    XYChart.Series c6 = new XYChart.Series();
    XYChart.Series c7 = new XYChart.Series();
    XYChart.Series c8 = new XYChart.Series();
    c1.setName("Channel 1");
    c2.setName("Channel 2");
    c3.setName("Channel 3");
    c4.setName("Channel 4");
    c5.setName("Channel 5");
    c6.setName("Channel 6");
    c7.setName("Channel 7");
    c8.setName("Channel 8");

    //read file
    Scanner sc = new Scanner(new File("C:/Users/MS/Desktop/4/CS 2043/EMGDATA/wrist_ext_original.csv"));
    sc.useDelimiter(",|\\s");
    //data is sorted as an 6000x8 array
    double [][] rawArray = new double [6000][8];
    for(int i = 0; i < rawArray.length; i++) {
      for (int j = 0; j < rawArray[0].length; j++) {
        rawArray[i][j] = Double.parseDouble(sc.next());
      }
    }

    //all the values in each column goes in to each channel
    double y = 0;
    int offset = 200;
    //channel 1
    int xValue1 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][0];
      c1.getData().add(new XYChart.Data(xValue1, y));
      xValue1++;
    }
    //Channel 2
    int xValue2 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][1];
      c2.getData().add(new XYChart.Data(xValue2, y-offset));
      xValue2++;
    }
    //Channel 3
    int xValue3 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][2];
      c3.getData().add(new XYChart.Data(xValue3, y-(2*offset)));
      xValue3++;
    }
    //Channel 4
    int xValue4 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][3];
      c4.getData().add(new XYChart.Data(xValue4, y-(3*offset)));
      xValue4++;
    }
    //Channel 5
    int xValue5 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][4];
      c5.getData().add(new XYChart.Data(xValue5,y-(4*offset)));
      xValue5++;
    }
    //Channel 6
    int xValue6 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][5];
      c6.getData().add(new XYChart.Data(xValue6, y-(5*offset)));
      xValue6++;
    }
    //Channel 7
    int xValue7 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][6];
      c7.getData().add(new XYChart.Data(xValue7, y-(6*offset)));
      xValue7++;
    }
    //Channel 8
    int xValue8 = 1;
    for (int i = 0; i < rawArray.length; i++) {
      y = rawArray[i][7];
      c8.getData().add(new XYChart.Data(xValue8, y-(7*offset)));
      xValue8++;
    }

    Scene lineGraph =new Scene(lc, 800, 600);
    lc.getData().addAll(c1, c2, c3, c4, c5, c6, c7, c8);
    stage.setScene(lineGraph);
    stage.show();

  }
    public static void main (String [] args) {
      launch(args);
    }


}
