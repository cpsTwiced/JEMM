import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.converter.*;

public class LineGraph extends Application {

    public void start(Stage primaryStage) throws Exception, FileNotFoundException {

        primaryStage.setTitle("Line Graph");

        NumberAxis xAxis = new NumberAxis(0, 6000, 500);
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Data");

        LineChart<Number, Number> lineChart =
                                new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Original Wrist Extention Data");
        lineChart.setCreateSymbols(false);

        XYChart.Series[] rawSeries = new XYChart.Series[8];



        for (int i = 0; i < rawSeries.length; i++) {
            rawSeries[i] = new XYChart.Series();
            rawSeries[i].setName("Channel " + (i + 1));
        }

        Scanner sc = new Scanner(new File("C:/Users/jtkau/OneDrive/Desktop/EMGDATA/wrist_flex_original.csv"));
        sc.useDelimiter(",|\\s");

        double[][] rawData = new double[6000][8];

        for (int i = 0; i < rawData.length; i++) {

            for (int j = 0; j < rawData[i].length; j++) {

                rawData[i][j] = Double.parseDouble(sc.next());
            }
        }

        for (int j = 0; j < rawData[0].length; j++) {

            for (int i = 0; i < rawData.length; i ++) {

                rawSeries[j].getData().add(new XYChart.Data(i + 1, (rawData[i][j]) + 900 + (j * -200)));
            }
        }

        for (int i = 0; i < rawSeries.length; i++) {
            lineChart.getData().add(rawSeries[i]);
        }

        Scene mainScene = new Scene(lineChart, 2000, 1000);

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

    }

}
