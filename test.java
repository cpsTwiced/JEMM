import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//test extends javafx application
public class test extends Application
{
  Button button;
/*
  //launch() goes into javafx application which goes into start()
  public static void main(String[] args)
  {
    launch(args);
  }
*/

  //Main frame is called stage, inside stage has scene
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    primaryStage.setTitle("test window");
    button = new Button("test click");
    //button.setText("test click");

    StackPane pane = new StackPane();
    pane.getChildren().add(button);

    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
