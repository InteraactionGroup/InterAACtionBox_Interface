package main.UI.menu;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;
import main.Configuration;
import main.UI.DoubleClickedButton;
import main.process.GnomeControlCenterNamedProcessCreator;
import main.process.TeamviewerNamedProcessCreator;
import main.utils.StageUtils;
import main.utils.UtilsUI;

@Slf4j
public class OptionsMenu extends BorderPane {

    public String langage = "Francais";

    public OptionsMenu(GraphicalMenus graphicalMenus) {
        super();

        Rectangle r = new Rectangle();
        r.widthProperty().bind(graphicalMenus.primaryStage.widthProperty());
        r.heightProperty().bind(graphicalMenus.primaryStage.heightProperty());
        Stop[] stops = new Stop[]{new Stop(0, Color.web("#faeaed")), new Stop(1, Color.web("#cd2653"))};
        LinearGradient lg1 = new LinearGradient(0, 1, 1.5, 0, true, CycleMethod.NO_CYCLE, stops);
        r.setFill(lg1);

        this.getChildren().add(r);

        this.prefWidthProperty().bind(graphicalMenus.primaryStage.widthProperty());
        this.prefHeightProperty().bind(graphicalMenus.primaryStage.heightProperty());

        StackPane titlePane = UtilsUI.createTopBar(graphicalMenus,"Options");

        BorderPane.setAlignment(titlePane, Pos.CENTER);
        this.setTop(titlePane);


        GridPane settings = new GridPane();
        settings.setHgap(20);
        settings.setVgap(graphicalMenus.primaryStage.getHeight()/15);

        {
            Label useEyeTracker = new Label("Eye Tracker:");

            useEyeTracker.setFont(new Font(20));
            useEyeTracker.setStyle("-fx-font-weight: bold; -fx-font-family: Helvetica");
            useEyeTracker.setTextFill(Color.web("#cd2653"));

            CheckBox useEyeTrackerCheckBox = new CheckBox("Activ\u00e9");
            useEyeTrackerCheckBox.setStyle("-fx-font-weight: bold; -fx-font-family: Helvetica; -fx-font-size: 20");
            useEyeTrackerCheckBox.selectedProperty().addListener((obj, oldval, newval) -> {
                if (newval) {
                    useEyeTrackerCheckBox.setText("D\u00e9sactiv\u00e9");
                    graphicalMenus.getConfiguration().setMode(Configuration.MOUSE_INTERACTION);
                } else {
                    useEyeTrackerCheckBox.setText("Activ\u00e9");
                    graphicalMenus.getConfiguration().setMode(Configuration.GAZE_INTERACTION);
                }
            });

            useEyeTrackerCheckBox.setSelected(true);
            useEyeTrackerCheckBox.setTextFill(Color.web("#faeaed"));
            useEyeTrackerCheckBox.resize(100,100);

            settings.add(useEyeTracker, 0, 0);
            settings.add(useEyeTrackerCheckBox, 1, 0);
        }

        createGnomeControlCenterButton(graphicalMenus, settings, "Gestionnaire Wifi:", "images/wi-fi_white.png", "wifi", 1);
        createGnomeControlCenterButton(graphicalMenus, settings, "Gestionnaire Bluetooth:", "images/bluetooth.png", "bluetooth", 2);
        createGnomeControlCenterButton(graphicalMenus, settings, "Param\u00e8tres D'Affichage:", "images/notebook.png", "display", 3);
        createGnomeControlCenterButton(graphicalMenus, settings, "Param\u00e8tres de Batterie:", "images/battery.png", "power", 4);
        createGnomeControlCenterButtonLang(settings);

        {

            Label teamviewerLabel = new Label("Lancer TeamViewer:");

            teamviewerLabel.setFont(new Font(20));
            teamviewerLabel.setStyle("-fx-font-weight: bold; -fx-font-family: Helvetica");
            teamviewerLabel.setTextFill(Color.web("#cd2653"));

            Button teamViewerButton = createTopBarButton(
                    "Ouvrir>",
                    "images/teamviewer.png",
                    (e) -> {
                        StageUtils.killRunningProcess(graphicalMenus);
                        TeamviewerNamedProcessCreator teamviewerNamedProcessCreator = new TeamviewerNamedProcessCreator();
                        teamviewerNamedProcessCreator.setUpProcessBuilder();
                        graphicalMenus.process = teamviewerNamedProcessCreator.start(graphicalMenus);
                    }
            );

            teamViewerButton.setTextFill(Color.web("#faeaed"));

            settings.add(teamviewerLabel, 0, 5);
            settings.add(teamViewerButton, 1, 5);
        }

        settings.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(settings, Pos.CENTER);
        this.setCenter(settings);
    }

    void createGnomeControlCenterButton(GraphicalMenus graphicalMenus, GridPane settings, String label, String imageName, String panelToOpen, int row) {
        Label displayedLabel = new Label(label);
        displayedLabel.setFont(new Font(20));
        displayedLabel.setStyle("-fx-font-weight: bold; -fx-font-family: Helvetica");
        displayedLabel.setTextFill(Color.web("#cd2653"));

        Button button = createTopBarButton(
                "Ouvrir>",
                imageName,
                (e) -> {
                    StageUtils.killRunningProcess(graphicalMenus);
                    GnomeControlCenterNamedProcessCreator process = new GnomeControlCenterNamedProcessCreator(panelToOpen);
                    process.setUpProcessBuilder();
                    graphicalMenus.process = process.start(graphicalMenus);
                }
        );

        button.setTextFill(Color.web("#faeaed"));

        settings.add(displayedLabel, 0, row);
        settings.add(button, 1, row);
    }

    void createGnomeControlCenterButtonLang(GridPane settings) {
        Label displayedLabel = new Label("Langue");
        displayedLabel.setFont(new Font(20));
        displayedLabel.setStyle("-fx-font-weight: bold; -fx-font-family: Helvetica");
        displayedLabel.setTextFill(Color.web("#cd2653"));

        MenuItem menuItemFR = new MenuItem("Francais");
        MenuItem menuItemEN = new MenuItem("English");

        MenuButton menuButton = new MenuButton(langage);

        menuItemFR.setOnAction(eventMenuLanguages -> {
            langage = menuItemFR.getText();
            menuButton.setText(langage);
        });
        menuItemEN.setOnAction(eventMenuLanguages -> {
            langage = menuItemEN.getText();
            menuButton.setText(langage);
        });
        menuButton.getItems().addAll(menuItemEN,menuItemFR);


        /*

        Button button = createTopBarButton(
                "Ouvrir>",
                imageName,
                (e) -> {
                    StageUtils.killRunningProcess(graphicalMenus);
                    GnomeControlCenterNamedProcessCreator process = new GnomeControlCenterNamedProcessCreator(panelToOpen);
                    process.setUpProcessBuilder();
                    graphicalMenus.process = process.start(graphicalMenus);
                }
        );

         */

        // button.setTextFill(Color.web("#faeaed"));

        settings.add(displayedLabel, 0, 6);
        settings.add(menuButton,1, 6);
        // settings.add(button, 1, row);
    }

    Button createTopBarButton(String text, String imagePath, EventHandler eventhandler) {
        DoubleClickedButton optionButton = new DoubleClickedButton(text);
        optionButton.setPrefHeight(50);
        optionButton.setMaxHeight(50);
        optionButton.setStyle(
                "-fx-border-color: transparent; " +
                        "-fx-border-width: 0; " +
                        "-fx-background-radius: 0; " +
                        "-fx-background-color: transparent; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: Helvetica; " +
                        "-fx-text-fill: #faeaed;"+
                        "-fx-font-size: 20"
        );
        ImageView graphic = new ImageView(imagePath);
        graphic.setPreserveRatio(true);
        graphic.setFitHeight(30);
        optionButton.setGraphic(graphic);

        optionButton.assignHandler(eventhandler);
        return optionButton;
    }


}
