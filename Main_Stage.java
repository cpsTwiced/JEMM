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
    public void start(Stage main_stage) {

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
      	browseB1.setLayoutX(300);
      	browseB1.setLayoutY(50);
      	Button browseB2 = new Button ("Browse");
      	browseB2.setLayoutX(300);
      	browseB2.setLayoutY(220);
      	//add text for file path1
      	Text pathText1 = new Text(10, 70, "file path of original data");
      	//add text for file path2
      	Text pathText2 = new Text(10, 240, "file path of shifted data");

        pane1.getChildren().addAll(browseB1, browseB2, originalText, shiftedText, pathText1, pathText2);
        /*load_block.getChildren().addAll(hbox1);
        load_block.getChildren().addAll(browseB1, browseB2);
      	load_block.getChildren().add(shiftedText);
      	load_block.getChildren().add(pathText1, pathText2);*/
        load_block.getChildren().add(pane1);


        raw_signal.setBackground(other_bg);
        raw_signal.setMinSize(1000, 300);
        raw_signal.setMaxSize(1000, 300);

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




    public static void main(String[] args) {

        launch(args);

    }

}
