import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.event.ActionEvent;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main_Stage extends Application {

    //Main VBox containing the entire scene.
    VBox main_box;
    //HBox's that contain the division of scenes.
    HBox data_raw_box, displacement_visual_box, features;
    //Sub boxes for the raw pane.
    StackPane load_block, raw_signal;
    //Sub boxes for the feature/ visualization box;
    StackPane displacement_box, angle_box, visual_box;
    //Main scene of the stage.
    Scene main_scene;
    //Constructor variables for the displacement_visual_box region.
    CornerRadii displacement_visual_box_radii, other_radii, ad_radii, vis_radii;
    Insets displacement_visual_box_insets, other_insets, ad_insets, vis_insets;
    //Background for displacement_visual_box scene.
    BackgroundFill displacement_visual_box_bg_fill, other_fill, ad_fill, vis_fill;
    Background displacement_visual_box_bg, other_bg, ad_bg, vis_bg;

    @Override
    public void start(Stage main_stage) throws Exception {

        main_box = new VBox();
        data_raw_box = new HBox();
        features = new HBox();
        load_block = new StackPane();
        raw_signal = new StackPane();

        displacement_visual_box = new HBox(100);
        displacement_box = new StackPane();
        angle_box = new StackPane();
        visual_box = new StackPane();

        //Settings for raw data boxes.
        other_radii = new CornerRadii(20);
        other_insets = new Insets(5, 10, 5, 10);
        other_fill = new BackgroundFill(Color.RED, other_radii, other_insets);
        other_bg = new Background(other_fill);

        //Settings for displacement and angle boxes.
        ad_radii = new CornerRadii(5);
        ad_insets = new Insets(10, 10, 10, 10);
        ad_fill = new BackgroundFill(Color.ORANGE, ad_radii, ad_insets);
        ad_bg = new Background(ad_fill);

        //Settings for the visual box.
        vis_radii = new CornerRadii(5);
        vis_insets = new Insets(10, 10, 10, 10);
        vis_fill = new BackgroundFill(Color.PURPLE, vis_radii, vis_insets);
        vis_bg = new Background(vis_fill);

        //Settings for displacement_visual_box HBox.
        displacement_visual_box_radii = new CornerRadii(10);
        displacement_visual_box_insets = new Insets(10, 10, 10, 10);
        displacement_visual_box_bg_fill = new BackgroundFill(Color.AQUA,
        displacement_visual_box_radii, displacement_visual_box_insets);
        displacement_visual_box_bg = new Background(displacement_visual_box_bg_fill);

        //Applying values to the displacement_visual_box.
        displacement_visual_box.setBackground(displacement_visual_box_bg);
        displacement_visual_box.setMinSize(1500, 450);
        displacement_visual_box.setMaxSize(1500, 450);
        displacement_visual_box.setAlignment(Pos.CENTER);

        //set y-axis as the value collected
        final NumberAxis yAxis1 = new NumberAxis();
        //set x-axis as the time of when 1 value is collected (6000 points in 5 seconds; 1200 points in 1 second)
        final NumberAxis xAxis1 = new NumberAxis();
        //make a line chart with the created axis
        final LineChart<Number, Number> lc = new LineChart<>(xAxis1, yAxis1);
        yAxis1.setLabel("Values");
        xAxis1.setLabel("Time");
        lc.setTitle("NOT THE PLACE FOR RAW DATA BUT RAW DATA ANYWAYS");
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
        //channel 1
        int xValue1 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][0];
          c1.getData().add(new XYChart.Data(xValue1, y));
          xValue1++;
        }
        /*//Channel 2
        int xValue2 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][1];
          c2.getData().add(new XYChart.Data(xValue2, y));
          xValue2++;
        }
        //Channel 3
        int xValue3 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][2];
          c3.getData().add(new XYChart.Data(xValue3, y));
          xValue3++;
        }
        //Channel 4
        int xValue4 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][3];
          c4.getData().add(new XYChart.Data(xValue4, y));
          xValue4++;
        }
        //Channel 5
        int xValue5 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][4];
          c4.getData().add(new XYChart.Data(xValue5, y));
          xValue5++;
        }
        //Channel 6
        int xValue6 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][5];
          c4.getData().add(new XYChart.Data(xValue6, y));
          xValue6++;
        }
        //Channel 7
        int xValue7 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][6];
          c4.getData().add(new XYChart.Data(xValue7, y));
          xValue7++;
        }
        //Channel 8
        int xValue8 = 1;
        for (int i = 0; i < rawArray.length; i++) {
          y = rawArray[i][7];
          c4.getData().add(new XYChart.Data(xValue8, y));
          xValue8++;
        }*/

        lc.getData().addAll(c1, c2, c3, c4, c5, c6, c7, c8);
        Pane lcPane = new Pane();
        lcPane.getChildren().addAll(lc);
        lc.setPrefSize(400, 400);
        lc.setTranslateX(25);
        lc.setTranslateY(15);
        displacement_box.getChildren().addAll(lcPane);

        //Setting feature box data.
        angle_box.setBackground(ad_bg);
        angle_box.setMinSize(450, 430);
        angle_box.setMaxSize(450, 430);
        angle_box.setAlignment(Pos.CENTER);

        displacement_box.setBackground(ad_bg);
        displacement_box.setMinSize(450, 430);
        displacement_box.setMaxSize(450, 430);
        displacement_box.setAlignment(Pos.CENTER);


        //Setting visual box data.
        visual_box.setBackground(vis_bg);
        visual_box.setMinSize(430, 430);
        visual_box.setMaxSize(430, 430);
        visual_box.setAlignment(Pos.CENTER);

        //Setting raw data backgrounds.
        load_block.setBackground(other_bg);
        load_block.setMinSize(500, 300);
        load_block.setMaxSize(500, 300);

        Pane pane1 = new Pane();
        pane1.setTranslateX(30);
        pane1.setTranslateY(10);

        //add text for original file
      	Text originalText = new Text(10, 40, "Original Data");
      	//add text for shifted file
      	Text shiftedText = new Text(10, 210, "Shifted Data");
      	Button browseB1 = new Button ("Browse");
      	browseB1.setLayoutX(180);
      	browseB1.setLayoutY(50);
        /*FileChooser filechooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
          actionStatus.setText("File selected: " + selectedFile.getName());
        }
        else {
          actionStatus.setText("File selection cancelled.");
        }*/
        //browseB1.setOnAction(this::browseButtonPressed);
      	Button browseB2 = new Button ("Browse");
      	browseB2.setLayoutX(180);
      	browseB2.setLayoutY(220);
        //browseB2.setonAction(this::browseButtonPressed);
      	//add text for file path1
      	Text pathText1 = new Text(10, 70, "file path of original data");
        TextField path1 = new TextField();
        path1.setLayoutX(7);
        path1.setLayoutY(50);
      	//add text for file path2
      	Text pathText2 = new Text(10, 240, "file path of shifted data");
        TextField path2 = new TextField();
        path2.setLayoutX(7);
        path2.setLayoutY(220);

        pane1.getChildren().addAll(path1, path2);
        pane1.getChildren().addAll(browseB1, browseB2, originalText, shiftedText, pathText1, pathText2);
        load_block.getChildren().add(pane1);


        raw_signal.setBackground(other_bg);
        raw_signal.setMinSize(1000, 300);
        raw_signal.setMaxSize(1000, 300);

        //example graph
        String class1 = "PHYS 1081";
        String class2 = "ENGG 1003";
        String class3 = "ENGG 1015";
        String class4 = "CS 2043";

        GridPane buttons = new GridPane();
        GridPane graphGrid = new GridPane();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Number of students in cps' class");
        xAxis.setLabel("Class");
        yAxis.setLabel("Students");

        //Number of students of each class for Monday
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Monday");
        series1.getData().add(new XYChart.Data(class1, 300));
        series1.getData().add(new XYChart.Data(class2, 250));
        series1.getData().add(new XYChart.Data(class3, 0));
        series1.getData().add(new XYChart.Data(class4, 0));
        //Number of students of each class for Tuesday
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Tuesday");
        series2.getData().add(new XYChart.Data(class1, 0));
        series2.getData().add(new XYChart.Data(class2, 0));
        series2.getData().add(new XYChart.Data(class3, 200));
        series2.getData().add(new XYChart.Data(class4, 30));
        //Number of students of each class for Wednesday
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Wednesday");
        series3.getData().add(new XYChart.Data(class1, 300));
        series3.getData().add(new XYChart.Data(class2, 250));
        series3.getData().add(new XYChart.Data(class3, 0));
        series3.getData().add(new XYChart.Data(class4, 0));
        //Number of students of each class for Thursday
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Thursday");
        series4.getData().add(new XYChart.Data(class1, 0));
        series4.getData().add(new XYChart.Data(class2, 0));
        series4.getData().add(new XYChart.Data(class3, 0));
        series4.getData().add(new XYChart.Data(class4, 30));
        //Number of students of each class for Friday
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("Friday");
        series5.getData().add(new XYChart.Data(class1, 300));
        series5.getData().add(new XYChart.Data(class2, 250));
        series5.getData().add(new XYChart.Data(class3, 0));
        series5.getData().add(new XYChart.Data(class4, 0));

        Pane barChart = new Pane ();
        bc.getData().addAll(series1, series2, series3, series4, series5);
        bc.setBarGap(1);
        bc.setCategoryGap(40);
        bc.setPrefSize(800,250);
        bc.setTranslateX(100);
        bc.setTranslateY(25);
        barChart.getChildren().addAll(bc);
        raw_signal.getChildren().addAll(barChart);

        //Adding raw nodes.
        data_raw_box.setAlignment(Pos.CENTER);
        data_raw_box.getChildren().addAll(load_block, raw_signal);

        //Adding feature nodes.
        features.setAlignment(Pos.CENTER);
        features.getChildren().addAll(angle_box, displacement_box);
        displacement_visual_box.getChildren().addAll(features, visual_box);

        //Adding nodes to the main_box.
        main_box.setAlignment(Pos.CENTER);
        main_box.getChildren().addAll(data_raw_box, displacement_visual_box);

        //Adding the main_box to the main_scene.
        main_scene = new Scene(main_box);

        //Setting up and displaying the main_stage.
        main_stage.setTitle("Main Stage");
        main_stage.setScene(main_scene);
        main_stage.show();

    }

    /*public void browseButtonPressed(ActionEvent event) {
      Pane
    }*/




    public static void main(String[] args) {

        launch(args);

    }

}
