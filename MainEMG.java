import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainEMG extends Application {

    FlowPane initialPane;

    Scene initialScene;

    public static void main(String[] args) {

        launch(args);

    }

    public void start(Stage mainWindow) {

        initialPane = new FlowPane();


    }

}
