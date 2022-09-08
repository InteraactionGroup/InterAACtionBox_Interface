package main.process;

import lombok.extern.slf4j.Slf4j;
import main.UI.menu.GraphicalMenus;
import main.process.xdotoolProcess.GnomeControlCenterXdotoolProcessCreator;
import main.utils.NamedProcess;
import main.utils.UtilsOS;

import java.io.IOException;

@Slf4j
public class GnomeControlCenterNamedProcessCreator implements AppNamedProcessCreator {

    ProcessBuilder processBuilder;
    String panelToOpen = "";

    public GnomeControlCenterNamedProcessCreator(String panelToOpen) {
        this.panelToOpen = panelToOpen;
    }

    @Override
    public void setUpProcessBuilder() {
        processBuilder = new ProcessBuilder("gnome-control-center", panelToOpen);
    }

    @Override
    public NamedProcess start(GraphicalMenus graphicalMenus) {
        if (UtilsOS.isUnix()) {
            return AppNamedProcessCreator.createProcress(new GnomeControlCenterXdotoolProcessCreator(), processBuilder, graphicalMenus, "Gnome Control Center");
        } else {

            String cmd = "";
            switch (this.panelToOpen){

                case "wifi" :
                    cmd = "control /name Microsoft.NetworkAndSharingCenter";
                    break;

                case "display" :
                    cmd = "control desk.cpl";
                    break;

                case "power" :
                    cmd = "control powercfg.cpl";
                    break;

                default:
                    cmd = "";
                    break;
            }

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmd);
            try{
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
