package main.process.xdotoolProcess;

import lombok.extern.slf4j.Slf4j;
import main.UI.menu.GraphicalMenus;
import main.utils.UtilsOS;

@Slf4j
public class GazePlayXdotoolProcessCreator implements XdotoolProcessCreator {

    ProcessBuilder processBuilder;

    @Override
    public void setUpProcessBuilder() {
        if (UtilsOS.isUnix()){
            processBuilder = new ProcessBuilder(
                    "sh",
                    "./scripts/gazeplay_windowId.sh"
            );
        }else {
            processBuilder = new ProcessBuilder("");
        }
    }

    @Override
    public Process start(GraphicalMenus graphicalMenus) {
        return XdotoolProcessCreator.getStartingProcess(processBuilder, graphicalMenus, "gazeplay");
    }

}
