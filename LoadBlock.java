import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.*;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.Scanner;

import java.io.File;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Desktop;


public class LoadBlock {

    private Desktop desktop;
    private Pane loadPane;

    private final FileChooser rawFileChooser;
    private File rawFile;
    private final DirectoryChooser shiftedFolderChooser;


    private Button rawButton, shiftButton;
    private Text originalText, shiftedText;
    private TextField rawFilePath, shiftedFolderPath;

    private Stage stage;

    private RawGraph rawGraph;
    private EuclidianDistance edGraph;
    private FillerBlock filler;

    public LoadBlock(Stage mainStage, EuclidianDistance ed, FillerBlock fil) {

        edGraph = ed;
        rawGraph = edGraph.getRawDataGraph();
        filler = fil;

        desktop = Desktop.getDesktop();

        loadPane = new Pane();

        stage = mainStage;

        rawFileChooser = new FileChooser();
        rawFile = null;

        shiftedFolderChooser = new DirectoryChooser();

        rawButton = new Button("Browse");
        shiftButton = new Button ("Browse");

        originalText = new Text(10, 40, "Original Data");
        shiftedText = new Text(10, 100, "Shifted Data");

        rawFilePath = new TextField();
        shiftedFolderPath = new TextField();

    }

    public void initiateBlock() throws FileNotFoundException {

        rawFilePath.setPromptText("Path of selected raw data");
        rawFilePath.setPrefWidth(400);
        rawFilePath.setPrefHeight(10);
        rawFilePath.setLayoutX(10);
        rawFilePath.setLayoutY(50);
        rawFilePath.setEditable(false);

        shiftedFolderPath.setPromptText("Path of selected shifted data folder");
        shiftedFolderPath.setPrefWidth(400);
        shiftedFolderPath.setPrefHeight(10);
        shiftedFolderPath.setLayoutX(10);
        shiftedFolderPath.setLayoutY(110);
        shiftedFolderPath.setEditable(false);

        rawButton.setLayoutX(415);
      	rawButton.setLayoutY(50);

        shiftButton.setLayoutX(415);
      	shiftButton.setLayoutY(110);

        rawButton.setOnAction( new EventHandler<ActionEvent>() {

            public void handle(final ActionEvent event) {
                rawFile = rawFileChooser.showOpenDialog(stage);
                if (rawFile != null) {
                    rawFilePath.setText(rawFile.getAbsolutePath());
                    rawGraph.GraphRawData(rawFile);
                    filler.loadData();
                }
            }

        });

        shiftButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(final ActionEvent event) {
                File tempFile = shiftedFolderChooser.showDialog(stage);
                File[] fileList;
                if (tempFile != null) {
                    shiftedFolderPath.setText(tempFile.getAbsolutePath());
                    if (tempFile.listFiles().length == 6) {
                        fileList = tempFile.listFiles();
                        for (File file : fileList) {
                            System.out.println(file.getName());
                        }
                        try {
                        edGraph.giveData(fileList);
                        edGraph.SeriesGraphing();
                        }
                        catch (FileNotFoundException ex) {
                            System.out.println("No.");
                        }
                    }
                }
            }
        });

        loadPane.getChildren().addAll(originalText, shiftedText, rawFilePath,
                                    shiftedFolderPath, rawButton, shiftButton);
    }

    public Pane getPane () {
        loadPane.setMinSize(500,500);
        return loadPane;

    }

    public File getFile() {

        return rawFile;

    }
}
