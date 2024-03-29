package main.UI.menu;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;
import main.Configuration;
import main.Main;
import main.UI.I18NButton;
import main.UI.I18NLabel;
import main.UI.I18NText;
import main.UI.Translator;
import main.utils.UtilsMail;
import main.utils.UtilsUI;

@Slf4j
public class ContactUs extends BorderPane {


    Label errorLabel = new Label();

    public ContactUs(GraphicalMenus graphicalMenus, Main main, Configuration configuration) {

        Translator translator = main.getTranslator();

        this.getChildren().add(UtilsUI.createBackground(graphicalMenus));

        this.prefWidthProperty().bind(graphicalMenus.primaryStage.widthProperty());
        this.prefHeightProperty().bind(graphicalMenus.primaryStage.heightProperty());

        this.setTop(UtilsUI.createTopBar(translator, graphicalMenus.getOptionsMenu(), graphicalMenus, "Contact"));

        GridPane settings = new GridPane();
        settings.setHgap(20);
        settings.setVgap(graphicalMenus.primaryStage.getHeight() / 20);
        settings.prefWidthProperty().bind(this.prefWidthProperty());


        Label firstNameLabel = new I18NLabel(translator,"Nom*:");
        TextField firstName = new TextField();
        firstName.setPromptText("Dupont");
        firstName.minWidthProperty().bind(settings.prefWidthProperty().divide(8));
        firstName.prefWidthProperty().bind(settings.prefWidthProperty().divide(8));
        firstName.maxWidthProperty().bind(settings.prefWidthProperty().divide(8));


        Label lastNameLabel = new I18NLabel(translator,"Pr\u00e9nom*:");
        TextField lastName = new TextField();
        lastName.setPromptText("Martin");
        lastName.minWidthProperty().bind(settings.prefWidthProperty().divide(8));
        lastName.prefWidthProperty().bind(settings.prefWidthProperty().divide(8));
        lastName.maxWidthProperty().bind(settings.prefWidthProperty().divide(8));


        Label mailLabel = new I18NLabel(translator,"E-mail*:");
        TextField email = new TextField();
        email.setPromptText("Martin.Dupont@email.fr");
        email.minWidthProperty().bind(settings.prefWidthProperty().divide(4));
        email.prefWidthProperty().bind(settings.prefWidthProperty().divide(4));
        email.maxWidthProperty().bind(settings.prefWidthProperty().divide(4));


        Label objectLabel = new I18NLabel(translator,"Objet*:");
        TextField object = new TextField();
        I18NText objectPromptText = new I18NText(translator,"Objet du message");
        object.setPromptText(objectPromptText.textProperty().getValue());
        object.minWidthProperty().bind(settings.prefWidthProperty().divide(4));
        object.prefWidthProperty().bind(settings.prefWidthProperty().divide(4));
        object.maxWidthProperty().bind(settings.prefWidthProperty().divide(4));

        Label messageLabel = new I18NLabel(translator,"Message*:");
        TextArea message = new TextArea();
        I18NText messagePromptText = new I18NText(translator,"Un probl\u00e8me, Une id\u00e9e, vous voulez travailler avec nous ? Dites-nous tout ! ");
        message.setPromptText(messagePromptText.textProperty().getValue());
        message.setWrapText(true);
        message.minWidthProperty().bind(settings.prefWidthProperty().divide(3));
        message.prefWidthProperty().bind(settings.prefWidthProperty().divide(3));
        message.maxWidthProperty().bind(settings.prefWidthProperty().divide(3));

        message.minHeightProperty().bind(this.prefHeightProperty().divide(3));
        message.prefHeightProperty().bind(this.prefHeightProperty().divide(3));
        message.maxHeightProperty().bind(this.prefHeightProperty().divide(3));

        Button ok = new I18NButton(translator,"Envoyer");
        ok.setOnAction((event) -> {
            send(errorLabel, firstName.getText(), lastName.getText(), email.getText(), object.getText(), message.getText());
        });

        GridPane.setHalignment(firstName, HPos.CENTER);
        GridPane.setHalignment(lastName, HPos.CENTER);
        GridPane.setHalignment(email, HPos.CENTER);

        GridPane.setHalignment(object, HPos.CENTER);
        GridPane.setHalignment(message, HPos.CENTER);
        GridPane.setHalignment(errorLabel, HPos.CENTER);
        GridPane.setHalignment(ok, HPos.CENTER);

        settings.add(firstNameLabel, 0, 0);
        settings.add(firstName, 1, 0);
        settings.add(lastNameLabel, 2, 0);
        settings.add(lastName, 3, 0);
        settings.add(mailLabel, 0, 1);
        settings.add(email, 1, 1, 3, 1);
        settings.add(objectLabel, 0, 2);
        settings.add(object, 1, 2, 3, 1);
        settings.add(messageLabel, 0, 3);
        settings.add(message, 0, 4, 4, 1);
        settings.add(errorLabel, 0, 5, 4, 1);
        settings.add(ok, 0, 6, 4, 1);

        settings.setAlignment(Pos.CENTER);
        this.setCenter(settings);
    }

    public static void send(Label errorLabel, String firstname, String lastname, String email, String object, String text) {
        UtilsMail.send(errorLabel,
                firstname,
                lastname,
                email,
                object,
                text
        );
    }

}
