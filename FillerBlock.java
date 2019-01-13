import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.ComboBoxListCell;

public class FillerBlock {

    private String[] titleNames;
    private Text[] titles;

    private ObservableList<Number> mavValues;
    private ObservableList<Number> sscValues;
    private ObservableList<Number> wlValues;
    private ObservableList<Number> zcValues;

    private ListView<Number> mavList;
    private ListView<Number> sscList;
    private ListView<Number> wlList;
    private ListView<Number> zcList;

    private HBox subtitleBox;
    private HBox horizontalBox;
    private HBox horizontalBox0;
    private VBox verticalBox;
    private Pane pane;

    private Text title;

    private EuclidianDistance edGraph;

    private RawGraph rawGraphThingy;

    private double[][] rawData;

    public FillerBlock(EuclidianDistance ed) {

        rawData = new double[6000][8];

        edGraph = ed;

        rawGraphThingy = edGraph.getRawDataGraph();

        titleNames = new String[4];

        titleNames[0] = "MAV";
        titleNames[1] = "SSC";
        titleNames[2] = "WL";
        titleNames[3] = "ZC";

        titles = new Text[4];

        titles[0] = new Text(titleNames[0]);
        titles[1] = new Text(titleNames[1]);
        titles[2] = new Text(titleNames[2]);
        titles[3] = new Text(titleNames[3]);

        mavValues = null;
        sscValues = null;
        wlValues = null;
        zcValues = null;

        mavList = new ListView<Number>();
        mavList.setPrefWidth(100);
        mavList.setPrefHeight(200);
        sscList = new ListView<Number>();
        sscList.setPrefWidth(100);
        sscList.setPrefHeight(200);
        wlList = new ListView<Number>();
        wlList.setPrefWidth(100);
        wlList.setPrefHeight(200);
        zcList = new ListView<Number>();
        zcList.setPrefWidth(100);
        zcList.setPrefHeight(200);

        subtitleBox = new HBox(titles[0], titles[1], titles[2], titles[3]);
        subtitleBox.setAlignment(Pos.CENTER);
        subtitleBox.setSpacing(80);

        horizontalBox = new HBox(mavList, sscList, wlList, zcList);
        horizontalBox.setAlignment(Pos.CENTER);

        title = new Text("Feature Values");

        horizontalBox0 = new HBox();
        horizontalBox0.setAlignment(Pos.CENTER);
        horizontalBox0.setSpacing(3);

        verticalBox = new VBox(title, subtitleBox,
                        horizontalBox, horizontalBox0);
        verticalBox.setAlignment(Pos.CENTER);
        verticalBox.setSpacing(3);

        pane = new Pane(verticalBox);

    }

    public void loadData() {

        rawData = rawGraphThingy.getRawData();

        double[] temp = new double[8];

        temp = edGraph.MAV(rawData);
        System.out.println(temp[0]);
        System.out.println(temp[1]);
        System.out.println(temp[2]);
        System.out.println(temp[3]);
        System.out.println(temp[4]);
        System.out.println(temp[5]);
        System.out.println(temp[6]);
        System.out.println(temp[7]);

        mavValues = FXCollections.observableArrayList(temp[0], temp[1],
                    temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);

        temp = edGraph.SSC(rawData);

        sscValues = FXCollections.observableArrayList(temp[0], temp[1],
                    temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);

        temp = edGraph.Wavelength(rawData);

        wlValues = FXCollections.observableArrayList(temp[0], temp[1],
                    temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);

        temp = edGraph.Zero_C(rawData);

        zcValues = FXCollections.observableArrayList(temp[0], temp[1],
                    temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);

        mavList.setItems(mavValues);
        sscList.setItems(sscValues);
        wlList.setItems(wlValues);
        zcList.setItems(zcValues);

    }

    public Pane getPane() {

        return pane;

    }

}
