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
import java.io.IOException;
import java.util.List;
import java.awt.Desktop;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cps extends Application {

  private Desktop desktop = Desktop.getDesktop();

  @Override
  public void start (Stage stage) {
    Pane pane1 = new Pane();
    Scene scene1 = new Scene(pane1, 400, 300);
  	//add text for original file
  	Text originalText = new Text(10, 40, "Original Data");
  	//add text for shifted file
  	Text shiftedText = new Text(10, 210, "Shifted Data");

  	//add text for file path1
  	Text pathText1 = new Text(10, 70, "file path of original data");
  	//add text for file path2
  	Text pathText2 = new Text(10, 240, "file path of shifted data");

  	pane1.getChildren().add(originalText);
  	pane1.getChildren().add(shiftedText);
  	pane1.getChildren().add(pathText1);
  	pane1.getChildren().add(pathText2);

    //BUTTONS
    Button browseB1 = new Button ("Browse");
  	browseB1.setLayoutX(300);
  	browseB1.setLayoutY(50);

  	Button browseB2 = new Button ("Browse");
  	browseB2.setLayoutX(300);
  	browseB2.setLayoutY(220);

    pane1.getChildren().add(browseB1);
  	pane1.getChildren().add(browseB2);



    final FileChooser fileChooser = new FileChooser();

    browseB1.setOnAction(
      new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
          configureFileChooser(fileChooser);
          File file = fileChooser.showOpenDialog(stage);
          if (file != null) {
            openFile(file);
          }
        }
      });


  	stage.setScene(scene1);
  	stage.show();
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
      //fileChooser.setTitle("View Pictures");
      //fileChooser.setInitialDirectory(newFile(System.getProperty("user.home")));
      fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All Files", "*.*"),
        new FileChooser.ExtensionFilter("CSV", "*.csv")z
      );
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                FileChooserSample.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }

  /*public void browseButtonPressed(ActionEvent event) {
    Pane pane2 = new Pane();
    Stage browseStage = new Stage();
    Scene scene2 = new Scene(pane2, 200, 400);
    browseStage.setTitle("Browse");
    browseStage.setScene(scene2);
    browseStage.show();
    FileChooser fc = new FileChooser();
    File file = fileChooser.showOpenDialog(stage);
    fc.setTitle("Open File");
    fc.showOpenDialog(event);
  }*/


  public static void main (String [] args) {
    Application.launch(args);
  }
}
