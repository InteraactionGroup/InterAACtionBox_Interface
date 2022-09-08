package main.process;

import lombok.extern.slf4j.Slf4j;
import main.utils.UtilsOS;

import java.io.File;
import java.io.IOException;

@Slf4j
public class CloseGoogleChromeProcessCreator {

    ProcessBuilder processBuilder;

    public void setUpProcessBuilder() {
        if (UtilsOS.isUnix()){
            processBuilder = new ProcessBuilder(
                    "sh",
                    "./scripts/close_chrome.sh");
        }
    }

    Process waitForCloseRequest() {
        if (UtilsOS.isUnix()){
            try {
                File fileFR = new File("~/Téléchargements/close161918.txt");
                File fileEN = new File("~/Downloads/close161918.txt");
                if(fileFR.exists()) {
                    boolean deleteFrFile = fileFR.delete();
                    log.info("File delete = " + deleteFrFile);
                }
                if(fileEN.exists()) {
                    boolean deleteEnFile = fileEN.delete();
                    log.info("File delete = " + deleteEnFile);
                }
                return this.processBuilder.inheritIO().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
