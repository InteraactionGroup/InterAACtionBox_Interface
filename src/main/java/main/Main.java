package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import main.UI.DefaultTranslator;
import main.UI.Translator;
import main.UI.menu.GraphicalMenus;
import main.utils.Setup;
import main.utils.StageUtils;
import main.utils.UtilsOS;
import main.utils.multilinguism.Multilinguism;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Getter
    private static Main instance;

    @Getter
    private Translator translator;

    @Getter
    private final Setup setup = new Setup();

    public static void main(String[] args) {instance = getInstance();
        launch(args);}

    @Override
    public void start(Stage primaryStage) {

        this.initWindows();

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("InteraactionBox-AFSR");
        primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight());
        Scene scene = new Scene(new Pane(new Rectangle(0,0,Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight())), Color.TRANSPARENT);

        final Configuration config = ConfigurationBuilder.createFromPropertiesResource().build();
        config.language = ConfigurationBuilder.createFromPropertiesResource().language;
        final Multilinguism multilinguism = Multilinguism.getSingleton();

        translator = new DefaultTranslator(config, multilinguism);

        GraphicalMenus graphicalMenus = new GraphicalMenus(primaryStage, this);
        scene.setRoot(graphicalMenus.getHomeScreen());
        graphicalMenus.getConfiguration().setScene(scene);

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> {
            if (UtilsOS.isWindows()){
                try {
                    Runtime.getRuntime().exec("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\close_ports.bat");
                    System.exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        graphicalMenus.getHomeScreen().showCloseProcessButtonIfProcessNotNull();
        StageUtils.displayUnclosable(primaryStage);

        scene.setOnMouseMoved((e) -> {
            if (graphicalMenus.getConfiguration().isGazeInteraction()) {
                graphicalMenus.getConfiguration().analyse(e.getScreenX(), e.getScreenY());
            }
        });
    }

    public void initWindows(){
        if (UtilsOS.isWindows()){
            File appFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR");
            if (!appFolder.exists()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Setup Application");
                alert.setHeaderText(null);
                alert.setContentText("Completing the installation of InterAACtionBoxAFSR ! \n It may take several minutes.");
                alert.showAndWait();
            }
            this.setup.setup();
            this.setup.openPorts();
        }
    }
}
