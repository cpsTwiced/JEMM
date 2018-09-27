import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
public class settingsButton extends Application
{
  Button settingsButton;
  Stage settingStage;
  Button button1;
  Button button2;
  Button button3;
  Button button4;
  Button button5;
  FlowPane mainPane;
  FlowPane pane;
  Scene mainScene;
  VBox vbox;
  Scene scene;
  public void start(Stage primaryStage) throws Exception
  {
    settingsButton = new Button("Settings");
    settingsButton.setOnAction(this::processSettingsButtonPressed);
    mainPane = new FlowPane(settingsButton);
    mainScene = new Scene(mainPane, 500, 500);
    primaryStage.setTitle("Main Window");
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }
  public void processSettingsButtonPressed(ActionEvent event)
  {
    settingStage = new Stage();
    settingStage.setTitle("Settings Window");
    button1 = new Button("Button 1");
    button2 = new Button("Button 2");
    button3 = new Button("Button 3");
    button4 = new Button("Button 4");
    button5 = new Button("Button 5");
    vbox = new VBox(button1, button2, button3, button4, button5);
    pane = new FlowPane(vbox);
    scene = new Scene(pane, 300, 300);
    settingStage.setScene(scene);
    settingStage.show();
  }
}
