package main.UI.menu;

import javafx.stage.Stage;
import lombok.Getter;
import main.Configuration;
import main.gaze.devicemanager.AbstractGazeDeviceManager;
import main.gaze.devicemanager.TobiiGazeDeviceManager;
import main.utils.NamedProcess;
import main.utils.UpdateManager;

public class GraphicalMenus {

    final public Stage primaryStage;
    @Getter
    private final AbstractGazeDeviceManager gazeDeviceManager = new TobiiGazeDeviceManager();
    @Getter
    private final Configuration configuration = new Configuration();
    @Getter
    private final HomeScreen homeScreen;
    @Getter
    private final OptionsMenu optionsMenu;
    @Getter
    private final UpdateMenu updateMenu;
    @Getter
    private final UserPageMenu userPageMenu;

    public NamedProcess process = new NamedProcess();

    public GraphicalMenus(Stage primaryStage) {
        this.primaryStage = primaryStage;
        UpdateManager updateManager = new UpdateManager();
        this.homeScreen = new HomeScreen(this, updateManager);
        this.optionsMenu = new OptionsMenu(this);
        this.updateMenu = new UpdateMenu(this, updateManager);
        this.userPageMenu = new UserPageMenu(this);
    }

}

