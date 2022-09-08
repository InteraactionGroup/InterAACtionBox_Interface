package main.process.xdotoolProcess;

import lombok.extern.slf4j.Slf4j;
import main.utils.UtilsOS;

import java.io.IOException;

@Slf4j
public class InteraactionGazeReOpenXdotoolProcessCreator {

    ProcessBuilder processBuilder;

    public InteraactionGazeReOpenXdotoolProcessCreator(){
        setUpProcessBuilder();
    }
    public void setUpProcessBuilder() {
        if (UtilsOS.isUnix()){
            processBuilder = new ProcessBuilder(
                    "sh",
                    "./scripts/interAACtionGaze_windowId.sh"
            );
        }else {
            processBuilder = new ProcessBuilder(
                    "C:\\Program Files (x86)\\InteraactionGaze\\bin\\interAACtionGaze-windows.bat"
            );
        }
    }

    public Process start() {
        try {
            return processBuilder.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
