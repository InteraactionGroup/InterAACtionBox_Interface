package main.UI.menu;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.UI.ProgressButton;
import main.process.*;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class QuickMenu extends Pane {


    public Process process;

    final LinkedList<ProgressButton> buttons;

    final ImageView backgroundBlured;
    final Button closeMenuButton;

    final GraphicalMenus graphicalMenus;

    public QuickMenu(GraphicalMenus graphicalMenus) {
        super();
        this.graphicalMenus = graphicalMenus;
        this.setStyle("-fx-background-color: transparent;");

        backgroundBlured = new ImageView(new Image("images/blured.jpg"));

        backgroundBlured.fitWidthProperty().bind(graphicalMenus.primaryStage.widthProperty());
        backgroundBlured.fitHeightProperty().bind(graphicalMenus.primaryStage.heightProperty());

        getChildren().add(backgroundBlured);
        setBackgroundListener(backgroundBlured);
        backgroundBlured.setOpacity(0.1);
        backgroundBlured.setOnMouseClicked((e)-> {
            graphicalMenus.primaryStage.hide();
        });

        closeMenuButton = createCloseMenuButton();
        getChildren().add(closeMenuButton);

        buttons = setButtons(graphicalMenus.primaryStage, graphicalMenus.getGazePlayInstallationRepo());
        createCircularButtons();
    }

    public Button createCloseMenuButton() {
        Button closeButton = new Button();
        closeButton.setPrefWidth(50);
        closeButton.setPrefHeight(50);

        closeButton.setShape(new Circle(50));

        closeButton.layoutXProperty().bind(graphicalMenus.primaryStage.widthProperty().divide(2).subtract(25));
        closeButton.layoutYProperty().bind(graphicalMenus.primaryStage.heightProperty().divide(2).subtract(25));

        ImageView logo = new ImageView(new Image("images/cross.png"));
        logo.setFitWidth(20);
        logo.setFitHeight(20);
        logo.setPreserveRatio(true);
        closeButton.setGraphic(logo);

        closeButton.setOnMouseClicked((e)-> {
            graphicalMenus.primaryStage.hide();
        });


        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(0);
        shadow.setOffsetY(10);
        shadow.setRadius(50);
        closeButton.setEffect(shadow);

        return closeButton;
    }

    public void setBackgroundListener(ImageView backgroundBlured) {
        backgroundBlured.setOnMouseMoved(event -> {

            for (ProgressButton button : buttons) {
                if (button != null) {
                    double buttonOrigin = Point2D.distance(
                            Screen.getPrimary().getBounds().getWidth() / 2,
                            Screen.getPrimary().getBounds().getHeight() / 2,
                            button.getLayoutX() + button.getPrefWidth() / 2,
                            button.getLayoutY() + button.getPrefHeight() / 2);
                    double mouseOrigin = Point2D.distance(
                            Screen.getPrimary().getBounds().getWidth() / 2,
                            Screen.getPrimary().getBounds().getHeight() / 2,
                            event.getX(),
                            event.getY());
                    double mouseButton = Point2D.distance(
                            button.getLayoutX() + button.getPrefWidth() / 2,
                            button.getLayoutY() + button.getPrefHeight() / 2,
                            event.getX(),
                            event.getY());

                    if (mouseButton < buttonOrigin) {
                        double factor = (mouseOrigin / buttonOrigin) > 1 ? 1 : (mouseOrigin / buttonOrigin);
                        button.setPrefWidth(150 + 50 * factor);
                        button.setPrefHeight(150 + 50 * factor);
                        button.getButton().setRadius(75 + 25 * factor);
                    } else {
                        button.setPrefWidth(150);
                        button.setPrefHeight(150);
                        button.getButton().setRadius(75);
                    }
                }
            }
        });
    }

    public void createCircularButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setPrefWidth(150);
            buttons.get(i).setPrefHeight(150);
            buttons.get(i).getButton().setRadius(75);
            buttons.get(i).layoutXProperty().bind(closeMenuButton.layoutXProperty().add(250 * Math.cos(Math.toRadians(i * 360d / buttons.size()))).subtract(50));
            buttons.get(i).layoutYProperty().bind(closeMenuButton.layoutYProperty().add(250 * Math.sin(Math.toRadians(i * 360d / buttons.size()))).subtract(50));
            this.getChildren().add(buttons.get(i));

            int index = i;
            buttons.get(i).setOnMouseEntered(event -> {
                buttons.get(index).requestFocus();
            });
        }
    }


    public LinkedList<ProgressButton> setButtons(Stage primaryStage, String gazePlayInstallationRepo) {
        EventHandler<Event> eventhandler = null;
        ImageView logo;
        LinkedList<ProgressButton> buttons = new LinkedList<ProgressButton>();
        for (int i = 0; i < 6; i++) {
            ProgressButton progressButton = new ProgressButton();
            progressButton.getButton().setStroke(Color.DARKGRAY);
            progressButton.getButton().setStrokeWidth(3);
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(0);
            shadow.setOffsetY(10);
            shadow.setRadius(50);
            progressButton.setEffect(shadow);
            buttons.add(progressButton);
            switch (i) {
                case 0:
                    buttons.get(i).getLabel().setText("Exit");
                    eventhandler = e -> {
                        if (process != null) {
                            process.destroy();
                            process = null;
                        }
                        Platform.exit();
                        System.exit(0);
                    };
                    break;
                case 1:
                    buttons.get(i).getLabel().setText("Menu");
                    eventhandler = (event) -> {
                        if (process != null) {
                            process.destroy();
                            process = null;
                        }
                        primaryStage.show();
                        primaryStage.toFront();
                        primaryStage.getScene().setRoot(graphicalMenus.getHomeScreen());
                    };
                    break;
                case 2:
                    buttons.get(i).getLabel().setText("AugCom");
                    logo = new ImageView(new Image("images/angular.png"));
                    logo.setPreserveRatio(true);
                    logo.setFitWidth(100);
                    logo.setFitHeight(100);
                    buttons.get(i).setImage(logo);
                    eventhandler = e -> {
                        if (process != null) {
                            process.destroy();
                            process = null;
                        }
                        AugComProcess augComProcess = new AugComProcess();
                        initAndStartProcess(augComProcess);
                    };
                    break;
                case 3:
                    buttons.get(i).getLabel().setText("InteraactionScene");
                    logo = new ImageView(new Image("images/angular.png"));
                    logo.setPreserveRatio(true);
                    logo.setFitWidth(100);
                    logo.setFitHeight(100);
                    buttons.get(i).setImage(logo);
                    eventhandler = e -> {
                        if (process != null) {
                            process.destroy();
                            process = null;
                        }
                        InteraactionSceneProcess interaactionSceneProcess = new InteraactionSceneProcess();
                        initAndStartProcess(interaactionSceneProcess);
                    };
                    break;
                case 4:
                    buttons.get(i).getLabel().setText("Youtube");
                    logo = new ImageView(new Image("images/yt.png"));
                    logo.setPreserveRatio(true);
                    logo.setFitWidth(100);
                    logo.setFitHeight(100);
                    buttons.get(i).setImage(logo);
                    eventhandler = e -> {
                        if (process != null) {
                            process.destroy();
                            process = null;
                        }
                        YoutubeProcess youtubeProcess = new YoutubeProcess();
                        initAndStartProcess(youtubeProcess);
                    };
                    break;
                case 5:
                    buttons.get(i).getLabel().setText("GazePlay");
                    logo = new ImageView(new Image("images/gazeplayicon.png"));
                    logo.setPreserveRatio(true);
                    logo.setFitWidth(100);
                    logo.setFitHeight(100);
                    buttons.get(i).setImage(logo);
                    eventhandler = e -> {
                        if (process != null) {
                            process.destroy();
                            process = null;
                        }
                        GazePlayProcess gazePlayProcess = new GazePlayProcess(gazePlayInstallationRepo);
                        initAndStartProcess(gazePlayProcess);
                    };
                    break;

                default:
                    break;
            }

            buttons.get(i).setOnMouseClicked(eventhandler);
            buttons.get(i).assignIndicator(eventhandler);
            buttons.get(i).start();
            graphicalMenus.getGazeDeviceManager().addEventFilter(buttons.get(i));
        }
        return buttons;
    }


    public void initAndStartProcess(AppProcess process) {
        process.setUpProcessBuilder();
        this.process = process.start();
        this.process.onExit().thenRunAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        graphicalMenus.primaryStage.show();
                        graphicalMenus.primaryStage.toFront();
                    }
                }
        );
    }

}
