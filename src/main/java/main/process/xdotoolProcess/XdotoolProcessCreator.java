package main.process.xdotoolProcess;

import javafx.application.Platform;
import main.UI.menu.GraphicalMenus;

import java.io.File;
import java.io.IOException;

public interface XdotoolProcessCreator {

    static void startWindowIdSearcher(GraphicalMenus graphicalMenus, String name) {
        Thread t = new Thread(() -> {
            File file = new File(name + "_windowId.txt");
            while (!file.exists()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
            boolean deleteFile = file.delete();
            System.out.println(deleteFile);
            Platform.runLater(
                    () -> {
                        graphicalMenus.primaryStage.hide();
                        graphicalMenus.getHomeScreen().removeMenu();
                    }
            );
        });
        t.setDaemon(true);
        t.start();
    }

    static Process getStartingProcess(ProcessBuilder processBuilder, GraphicalMenus graphicalMenus, String name) {
        try {
            File file = new File(name + "_windowId.txt");
            boolean deleteFile = file.delete();
            System.out.println(deleteFile);
            XdotoolProcessCreator.startWindowIdSearcher(graphicalMenus, name);
            return processBuilder.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    void setUpProcessBuilder();

    Process start(GraphicalMenus graphicalMenus);

}
