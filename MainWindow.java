import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class MainWindow extends Application {

  public void start (Stage stage) {
    Pane pane1 = new Pane();
    Scene scene1 = new Scene(pane1, 400, 300);
    Button button1  = new Button ("Settings");
    button1.setLayoutX(330);
    button1.setLayoutY(265);
    pane1.getChildren().add(button1);
    button1.setOnAction(this::settingsButtonPressed);
    stage.setTitle("JEMM");
    stage.setScene(scene1);
    stage.show();
  }

  public void settingsButtonPressed(ActionEvent event) {
    Pane pane2 = new Pane();
    Stage settingsStage = new Stage();
    Scene scene2 = new Scene(pane2, 200, 400);
    settingsStage.setTitle("Settings");
    settingsStage.setScene(scene2);
    settingsStage.show();
  }

  public static void main (String [] args) {
    Application.launch(args);
  }

}
