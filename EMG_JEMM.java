import javafx.application.Application;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

//@SuppressWarnings("unchecked")
public class EMG_JEMM extends Application {

    private Scene mainScene;
    private AnchorPane positioning;
    private ScrollPane mainPane;

    private LoadBlock load;
    private Pane loadPane;

    private RawGraph raw;
    private Pane rawPane;

    private EuclidianDistance EDGraphic;
    private Pane EDPane;

    private FillerBlock filler;
    private Pane fillerPane;

    @Override
    public void start(Stage mainStage) throws Exception, FileNotFoundException {

        positioning = new AnchorPane();
        mainPane = new ScrollPane();

        //Raw Graph
        raw = new RawGraph();
        rawPane = raw.getChart();
        positioning.setTopAnchor(rawPane, 10.0);
        positioning.setRightAnchor(rawPane, 0.0);
        positioning.setLeftAnchor(rawPane, 500.0);
        positioning.getChildren().add(rawPane);

        //Euclidian Distance Graph
        EDGraphic = new EuclidianDistance(raw);
        EDPane = EDGraphic.getPane();
        positioning.setBottomAnchor(EDPane, 0.0);
        positioning.setLeftAnchor(EDPane, 0.0);
        positioning.setTopAnchor(EDPane, 500.0);
        positioning.getChildren().add(EDPane);

        //Filler Block
        filler = new FillerBlock(EDGraphic);
        fillerPane = filler.getPane();
        positioning.setBottomAnchor(fillerPane, 0.0);
        positioning.setRightAnchor(fillerPane, 0.0);
        positioning.setTopAnchor(fillerPane, 600.0);
        positioning.getChildren().add(fillerPane);

        //Load Block
        load = new LoadBlock(mainStage, EDGraphic, filler);
        load.initiateBlock();
        loadPane = load.getPane();
        positioning.setTopAnchor(loadPane, 100.0);
        positioning.setLeftAnchor(loadPane, 0.0);
        positioning.getChildren().add(loadPane);

        mainPane.setContent(positioning);
        mainScene = new Scene(mainPane, 1500, 900);
        mainStage.setScene(mainScene);
        mainStage.setMaximized(true);
        mainStage.setTitle("EMG JEMM");
        mainStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }

}
