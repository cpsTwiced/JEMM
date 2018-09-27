import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
public class Main extends Application
{
  Button settingsButton;
  Stage settingsStage;
  VBox checkboxesVBox;
  CheckBox mavCheckBox;
  public void start(Stage primaryStage) throws Exception
  {
    primaryStage.setTitle("JEMM");
    settingsButton = new Button("Settings");
    mavCheckBox = new CheckBox("Mean Absolute Value");
    checkboxesVBox = new VBox(mavCheckBox);
    settingsButton.setOnAction(this::settingsButtonPressed);
    FlowPane pane = new FlowPane(checkboxesVBox, settingsButton);
    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public void settingsButtonPressed(ActionEvent event)
  {
    settingsStage = new Stage();
    settingsStage.setTitle("Settings");
    settingsStage.show();
  }
}
