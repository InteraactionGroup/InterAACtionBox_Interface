package main.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.extern.slf4j.Slf4j;
import main.Configuration;

import java.time.Duration;

@Slf4j
public class UpdateManager {

    public BooleanProperty anyUpdateNeeded = new SimpleBooleanProperty(false);

    public UpdateService[] updateServices = {
            new UpdateService("Syst\u00e8me", ""),
            new UpdateService("AugCom", "https://api.github.com/repos/AFSR/AugCom-AFSR/releases/latest"),
            new UpdateService("InterAACtionScene", "https://api.github.com/repos/AFSR/InteraactionScene-AFSR/releases/latest"),
            new UpdateService("GazePlay", "https://api.github.com/repos/AFSR/GazePlay-AFSR/releases/latest"),
            new UpdateService("InterAACtionPlayer", "https://api.github.com/repos/AFSR/InteraactionPlayer-AFSR/releases/latest"),
            new UpdateService("InterAACtionGaze","https://api.github.com/repos/InteraactionGroup/interaactionGaze/releases/latest"),
            new UpdateService("InterAACtionBox_Interface-linux","https://api.github.com/repos/InteraactionGroup/InterAACtionBox_Interface/releases/latest")
    };

    public UpdateManager(Configuration configuration) {
        anyUpdateNeeded.bind(
                        updateServices[UpdateService.SYSTEME].getUpdateProperty().or(
                        updateServices[UpdateService.AUGCOM].getUpdateProperty()).or(
                        updateServices[UpdateService.INTERAACTION_SCENE].getUpdateProperty()).or(
                        updateServices[UpdateService.GAZEPLAY].getUpdateProperty()).or(
                        updateServices[UpdateService.INTERAACTION_PLAYER].getUpdateProperty().or(
                        updateServices[UpdateService.INTERAACTION_GAZE].getUpdateProperty()).or(
                        updateServices[UpdateService.INTERAACTION_INTERFACE].getUpdateProperty()
                        )
                ));
        Thread updateChecker = new Thread(()->{
            while (true) {
                try {
                    checkUpdates();
                    Thread.sleep(Duration.ofHours(1).toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateChecker.setDaemon(true);
        updateChecker.start();
    }

    public void checkUpdates() {
        for (UpdateService updateService : updateServices) {
            updateService.checkUpdate(true);
            updateService.checkExist();
        }
    }

}
