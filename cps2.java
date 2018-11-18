import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Desktop;

@SuppressWarnings("unchecked")
public class cps2 extends Application {

  //Make Raw Data Graph
  final NumberAxis rawDataXAxis = new NumberAxis();
  final NumberAxis rawDataYAxis = new NumberAxis();
  final LineChart<Number, Number> rawDataGraph = new LineChart<>(rawDataXAxis, rawDataYAxis);

  final XYChart.Series c1 = new XYChart.Series();
  final XYChart.Series c2 = new XYChart.Series();
  final XYChart.Series c3 = new XYChart.Series();
  final XYChart.Series c4 = new XYChart.Series();
  final XYChart.Series c5 = new XYChart.Series();
  final XYChart.Series c6 = new XYChart.Series();
  final XYChart.Series c7 = new XYChart.Series();
  final XYChart.Series c8 = new XYChart.Series();

  private Desktop desktop = Desktop.getDesktop();

  static double [] mavArray = new double[8];

  @Override
  public void start (Stage stage) throws Exception {
    //AnchorPane
    AnchorPane anchorPane = new AnchorPane();
    Pane pane1 = new Pane(); //pane1 = buttons + file directory display
    AnchorPane.setTopAnchor(pane1, 0.0);
    AnchorPane.setLeftAnchor(pane1, 0.0);
    Pane pane2 = new Pane(); //pane2 = raw data graph display
    AnchorPane.setTopAnchor(pane2, 0.0);
    AnchorPane.setRightAnchor(pane2, 0.0);
    AnchorPane.setLeftAnchor(pane2, 350.0);
    //ScrollPane
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(anchorPane);
    Scene scene1 = new Scene(scrollPane, 880, 500);

    //Add text for each original and shifted file chooser section
    Text originalText = new Text(10, 40, "Original Data");
    Text shiftedText = new Text(10, 100, "Shifted Data");
    pane1.getChildren().addAll(originalText, shiftedText);

    //TextField - Display File Directory
    TextField rawFilePath = new TextField();
    TextField shiftedFilePath = new TextField();
    rawFilePath.setPromptText("Path of selected raw data");
    rawFilePath.setPrefWidth(285);
    rawFilePath.setPrefHeight(10);
    rawFilePath.setLayoutX(10);
    rawFilePath.setLayoutY(50);
    rawFilePath.setEditable(false);
    shiftedFilePath.setPromptText("Path of selected shifted data");
    shiftedFilePath.setPrefWidth(285);
    shiftedFilePath.setPrefHeight(10);
    shiftedFilePath.setLayoutX(10);
    shiftedFilePath.setLayoutY(110);
    shiftedFilePath.setEditable(false);
    pane1.getChildren().addAll(rawFilePath, shiftedFilePath);

    //Raw Data Graph
    rawDataXAxis.setLabel("Time");
    rawDataYAxis.setLabel("Values");
    rawDataGraph.setTitle("RAW DATA");
    rawDataGraph.setCreateSymbols(false); //hide data point dots on the graph
    c1.setName("Channel 1");
    c2.setName("Channel 2");
    c3.setName("Channel 3");
    c4.setName("Channel 4");
    c5.setName("Channel 5");
    c6.setName("Channel 6");
    c7.setName("Channel 7");
    c8.setName("Channel 8");
    rawDataGraph.getData().addAll(c1, c2, c3, c4, c5, c6, c7, c8);

    //Browse Button - select original and shifted data file
    Button browseB1 = new Button ("Browse");
  	browseB1.setLayoutX(300);
  	browseB1.setLayoutY(50);
  	Button browseB2 = new Button ("Browse");
  	browseB2.setLayoutX(300);
  	browseB2.setLayoutY(110);
    pane1.getChildren().addAll(browseB1, browseB2);

    final FileChooser rawFileChooser = new FileChooser();
    rawFileChooser.setTitle("Open Raw Data File");
    FileChooser.ExtensionFilter csvExtensionFilter = new FileChooser.ExtensionFilter("CSV", "*.csv");
    FileChooser.ExtensionFilter allFilesFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
    rawFileChooser.getExtensionFilters().addAll(allFilesFilter, csvExtensionFilter);
    rawFileChooser.setSelectedExtensionFilter(csvExtensionFilter);

    final FileChooser shiftedFileChooser = new FileChooser();
    shiftedFileChooser.setTitle("Open Shifted Data File");
    shiftedFileChooser.getExtensionFilters().addAll(allFilesFilter, csvExtensionFilter);
    shiftedFileChooser.setSelectedExtensionFilter(csvExtensionFilter);


    browseB1.setOnAction(
      new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
          File rawFile = rawFileChooser.showOpenDialog(stage);
          if (rawFile != null) {
            rawFilePath.setText(rawFile.getAbsolutePath());
            graphRawData(rawFile, 0, 200);
            mav(rawFile, mavArray);
          }
        }
      });

    browseB2.setOnAction(
      new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
          File shiftedFile = shiftedFileChooser.showOpenDialog(stage);
          if (shiftedFile != null) {
            openFile(shiftedFile);
            shiftedFilePath.setText(shiftedFile.getAbsolutePath());
          }
        }
      });


    pane2.getChildren().addAll(rawDataGraph);
    anchorPane.getChildren().addAll(pane1, pane2, pane3);
  	stage.setScene(scene1);
    stage.setTitle("JEMM");
  	stage.show();
    }

  private void openFile(File file) {
        try {
            desktop.open(file);
        }
        catch (IOException ex) {
            Logger.getLogger(cps2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  //Draw line graph with raw data
  public void graphRawData (File file, double y, int offset) {
      try {
        //Read raw data file selected
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",|\\s");
        double [][] rawArray = new double [6000][8];
        for(int i = 0; i < rawArray.length; i++) {
          for (int j = 0; j < rawArray[0].length; j++) {
            rawArray[i][j] = Double.parseDouble(sc.next());
          }
        }
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
      }
      catch (IOException ex) {
          Logger.getLogger(cps2.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  //mav
  //creates a mavArray[8] (1x8)
  public void mav(File file, double [] returnArray) {
    try {
      Scanner sc = new Scanner(file);
      sc.useDelimiter(",|\\s");
      double[][] array = new double[6000][8];
  		for(int i = 0; i < array.length; i++) {
        for(int j = 0; j < array[0].length; j++) {
          array[i][j] = Double.parseDouble(sc.next());
        }
      }
    	double [] mavArray = new double [array[0].length];
    	double [] arrayCol = new double [array.length];
    	//get each column
  		for (int b = 0; b < array[0].length; b++) {
  			for (int a = 0; a < array.length; a++) {
    			arrayCol [a] = array[a][b];
    		}
    		returnArray [b] = getMAV(arrayCol);
    	}
      //System.out.println(Arrays.toString(returnArray));

    }
    catch (IOException ex) {
        Logger.getLogger(cps2.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  //getMAV method; calculates mav for each Channel
  public static double getMAV(double [] array) {
		double x = 0;
		for (int n = 0; n < array.length; n++) {
			x = x + array [n];
		}
		double y = (Math.abs(x))/array.length;
		return y;
	}

  public static void main (String [] args) {
    Application.launch(args);
  }
}
