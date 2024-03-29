package main.utils;

import javafx.stage.Stage;
import main.UI.menu.GraphicalMenus;

public class StageUtils {

    public static void makesUnclosable(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);

        /*primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB ||
                    event.getCode() == KeyCode.ALT) {
                event.consume();
                try {
                    Robot robot = new Robot();
                    robot.keyRelease(java.awt.event.KeyEvent.VK_ALT);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                } catch (AWTException e) {
                    e.printStackTrace();
                }

            } else if (event.getCode() == KeyCode.ESCAPE ||
                    event.getCode() == KeyCode.ALT_GRAPH) {
                event.consume();
            }
        });


        primaryStage.setAlwaysOnTop(true);

        primaryStage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setIconified(false);
        });

        Platform.setImplicitExit(false);
        primaryStage.setOnCloseRequest(Event::consume);*/
    }

    public static void displayUnclosable(Stage primaryStage) {
        makesUnclosable(primaryStage);
        primaryStage.toFront();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    /*---------------Code For forcing the primaryStage to be always on front using thread---------------*/
//
//    public void onFrontThread(Stage primaryStage) {
//        Thread t = new Thread(() -> {
//            while (true) {
//                Platform.runLater(() -> {
//                    primaryStage.toFront();
//                });
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ignored) {
//
//                }
//            }
//        });
//        t.start();
//    }
//
    /*------------------------------------------------------------------------------------------------*/

    public static void killRunningProcess(GraphicalMenus graphicalMenus) {
        if (graphicalMenus.process != null && graphicalMenus.process.get() != null) {
            graphicalMenus.process.exitAskedByUser = true;
            graphicalMenus.process.destroy();
            graphicalMenus.process.set(null);
        }
    }

}
