package main.utils;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class UpdateService {

    public static final int SYSTEME = 0;
    public static final int AUGCOM = 1;
    public static final int INTERAACTION_SCENE = 2;
    public static final int GAZEPLAY = 3;
    public static final int INTERAACTION_PLAYER = 4;
    public static final int INTERAACTION_GAZE = 5;
    public static final int INTERAACTION_INTERFACE = 6;
    @Getter
    final BooleanProperty updateProperty;
    @Getter
    final BooleanProperty existProperty;
    @Getter
    public final String name;
    @Getter
    private final String updateURL;
    @Getter
    String version;

    @Getter
    StringProperty output = new SimpleStringProperty("");

    public UpdateService(String name, String updateURL) {
        this.name = name;
        this.updateURL = updateURL;
        this.updateProperty = new SimpleBooleanProperty(false);
        this.existProperty = new SimpleBooleanProperty(false);
    }

    public void checkUpdate(boolean testBool){
        if (UtilsOS.isUnix()){
            this.checkUpdateUnix(testBool);
        }else {
            this.checkUpdateWindows(testBool);
        }
    }

    public void checkUpdateWindows(boolean testBool){
        if (!updateURL.equals("") && testBool) {
            try {
                JSONObject softwareJson = JsonReader.readJsonFromUrl(updateURL);
                if (softwareJson != null) {
                    this.version = "" + softwareJson.get("name");
                    updateProperty.set(!(softwareJson.get("name").equals(this.readVersion(name))));
                    System.out.println("Name json -> " + softwareJson.get("name"));
                    System.out.println("Name version -> " + this.readVersion(name));
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        } else {
            updateProperty.set(false);
        }
    }

    public void checkUpdateUnix(boolean testBool) {
        if (!updateURL.equals("") && testBool) {
            try {
                if (name.equals("GazePlay")) {
                    JSONObject softwareJson = JsonReader.readJsonFromUrl(updateURL);
                    if (softwareJson != null) {
                        File directory = new File(System.getProperty("user.home") + "/" + softwareJson.get("name"));
                        File directoryspace = new File(System.getProperty("user.home") + "/ " + softwareJson.get("name"));
                        this.version = "" + softwareJson.get("name");
                        log.info(directory.getAbsolutePath());
                        Platform.runLater(() -> {
                            updateProperty.set(!((directory.exists() && directory.isDirectory()) || (directoryspace.exists() && directoryspace.isDirectory())));
                        });
                    }
                }else if (name.equals("InterAACtionGaze")) {
                    JSONObject softwareJson = JsonReader.readJsonFromUrl(updateURL);
                    if (softwareJson != null) {
                        File directory = new File(System.getProperty("user.home") + "/InterAACtionGaze");
                        File directoryspace = new File(System.getProperty("user.home") + "/ InterAACtionGaze");
                        this.version = "" + softwareJson.get("name");
                        log.info(directory.getAbsolutePath());
                        Platform.runLater(() -> {
                            updateProperty.set(!((directory.exists() && directory.isDirectory()) || (directoryspace.exists() && directoryspace.isDirectory())));
                        });
                    }
                }else if (name.equals("InterAACtionBox_Interface")){
                    JSONObject softwareJson = JsonReader.readJsonFromUrl(updateURL);
                    if (softwareJson != null) {
                        File directory = new File(System.getProperty("user.home") + "/InterAACtionBox_Interface-linux");
                        File directoryspace = new File(System.getProperty("user.home") + "/ InterAACtionBox_Interface-linux");
                        this.version = "" + softwareJson.get("name");
                        log.info(directory.getAbsolutePath());
                        Platform.runLater(() -> {
                            updateProperty.set(!((directory.exists() && directory.isDirectory()) || (directoryspace.exists() && directoryspace.isDirectory())));
                        });
                    }
                } else {
                    JSONObject softwareJson = JsonReader.readJsonFromUrl(updateURL);
                    if (softwareJson != null) {
                        File directory = new File(System.getProperty("user.home") + "/dist/" + softwareJson.get("name"));
                        File directoryspace = new File(System.getProperty("user.home") + "/dist/ " + softwareJson.get("name"));
                        this.version = "" + softwareJson.get("name");
                        Platform.runLater(() -> {
                            updateProperty.set(!((directory.exists() && directory.isDirectory()) || (directoryspace.exists() && directoryspace.isDirectory())));
                        });
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } else {
            updateProperty.set(false);
        }
    }

    public void checkExist() {
        if (UtilsOS.isUnix()){
            if (name.equals("GazePlay")) {
                File[] directories = new File(System.getProperty("user.home")).listFiles(file -> file.isDirectory() && file.getName().toLowerCase().contains(name.toLowerCase() + "-afsr"));
                existProperty.set(directories == null || directories.length == 0);
            } else if (name.equals("InterAACtionGaze")) {
                File[] directories = new File(System.getProperty("user.home")).listFiles(file -> file.isDirectory() && file.getName().toLowerCase().contains(name.toLowerCase()));
                existProperty.set(directories == null || directories.length == 0);
            }else  if (name.equals("InterAACtionBox_Interface")){
                    String nameFolder = name + "-linux";
                    File[] directories = new File(System.getProperty("user.home")).listFiles(file -> file.isDirectory() && file.getName().toLowerCase().contains(nameFolder.toLowerCase()));
                    existProperty.set(directories == null || directories.length == 0);
            } else {
                File[] directories = new File(System.getProperty("user.home") + "/dist").listFiles(file -> file.isDirectory() && file.getName().toLowerCase().contains(name.toLowerCase()));
                existProperty.set(directories == null || directories.length == 0);
            }
        }else {
            switch (name){
                case "InterAACtionScene":
                    File sceneFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\InterAACtionScene");
                    existProperty.set(!sceneFolder.exists());
                    break;
                case "InterAACtionPlayer":
                    File playerFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\InterAACtionPlayer");
                    existProperty.set(!playerFolder.exists());
                    break;
                case "AugCom":
                    File augcomFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\AugCom");
                    existProperty.set(!augcomFolder.exists());
                    break;
                case "GazePlay":
                    File gazeplayFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\GazePlay");
                    existProperty.set(!gazeplayFolder.exists());
                    break;
                case "InterAACtionGaze":
                    File gazeFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\interAACtionGaze");
                    existProperty.set(!gazeFolder.exists());
                    break;
                default:
                    break;
            }
        }
    }

    public String readVersion(String name){
        String version = "";
        try{
            File file = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version\\"+ name + "Version.txt");
            Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
            version = scanner.nextLine();
            scanner.close();
        } catch (IOException e) {
            System.out.println("No version found !");
        }
        return version;
    }
}
