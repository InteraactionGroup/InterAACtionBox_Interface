package main.process;

import lombok.extern.slf4j.Slf4j;
import main.UI.menu.GraphicalMenus;
import main.process.xdotoolProcess.TobiiXdotoolProcessCreator;
import main.utils.NamedProcess;
import main.utils.UtilsOS;

@Slf4j
public class TobiiManagerNamedProcessCreator implements AppNamedProcessCreator {

    ProcessBuilder processBuilder;

    @Override
    public void setUpProcessBuilder() {
        if (UtilsOS.isWindows()) {
            processBuilder = new ProcessBuilder(
                    "cmd",
                    "/c",
                    "start ",
                    "/max ",
                    System.getProperty("user.home") + "/AppData/Local/Programs/TobiiProEyeTrackerManager/TobiiProEyeTrackerManager.exe");
        } else {
            processBuilder = new ProcessBuilder("tobii_config");
        }
    }

    @Override
    public NamedProcess start(GraphicalMenus graphicalMenus) {
        return AppNamedProcessCreator.createProcress(new TobiiXdotoolProcessCreator(), processBuilder, graphicalMenus, "Eye-Tracker Manager");

    }

}
