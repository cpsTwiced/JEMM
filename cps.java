import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class cps extends Application {

  public void start (Stage stage) {
    Pane pane1 = new Pane();
    Scene scene1 = new Scene(pane1, 400, 300);
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
	
	pane1.getChildren().add(browseB1);
	pane1.getChildren().add(browseB2);
	pane1.getChildren().add(originalText);
	pane1.getChildren().add(shiftedText);	
	pane1.getChildren().add(pathText1);
	pane1.getChildren().add(pathText2);
	stage.setScene(scene1);
	stage.show();
  }
  

  
  public static void main (String [] args) {
    Application.launch(args);
  }
}