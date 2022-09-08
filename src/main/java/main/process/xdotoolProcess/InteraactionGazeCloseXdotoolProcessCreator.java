package main.process.xdotoolProcess;

import lombok.extern.slf4j.Slf4j;
import main.utils.UtilsOS;

import java.io.IOException;

@Slf4j
public class InteraactionGazeCloseXdotoolProcessCreator {

    ProcessBuilder processBuilder;

    public InteraactionGazeCloseXdotoolProcessCreator(){
        setUpProcessBuilder();
    }
    public void setUpProcessBuilder() {
        if (UtilsOS.isUnix()){
            processBuilder = new ProcessBuilder(
                    "sh",
                    "./scripts/closeGaze.sh"
            );
        }else {
            processBuilder = new ProcessBuilder(
                    "cmd.exe",
                    "/c",
                    "(for /f \"tokens=2 delims=,\" %F in ('tasklist /v /fo csv /nh /fi \"imagename eq java.exe\" /fi \"windowtitle eq InteraactionGaze\"') do taskkill /pid %~F)"
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
