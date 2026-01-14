import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Projekt extends Application {

    private List<PasswordEntry> entries = new ArrayList<>();
    private TableView<PasswordEntry> table = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Daten beim Start laden
        entries = LoadManager.load();

        // ---------------- LOGIN FENSTER ----------------
        TextField field = new TextField();
        field.setPromptText("Master Passwort");
        Button login = new Button("Login");
        login.getStyleClass().add("login-button");

        VBox loginBox = new VBox(10, field, login);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.getStyleClass().add("login-bg");

        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(loginBox, 250, 150));
        loginStage.setTitle("Login");
        loginStage.show();

        login.setOnAction(e -> {
            if (field.getText().equals("1234")) {
                loginStage.close();
                primaryStage.show();
            }
        });

        try {
            // ---------------- Passwort Generator Tab ----------------
            VBox genField = new VBox(10);
            genField.setAlignment(Pos.CENTER);

            TextField txtField = new TextField();
            txtField.setPromptText("Generated password");
            txtField.setPrefWidth(150);

            Button button1 = new Button("Passwort Generieren");
            button1.setPrefSize(150, 25);

            button1.setOnAction(event -> txtField.setText(Passwort_Generierung.Generierung()));
            genField.getChildren().addAll(txtField, button1);

            // ---------------- Eingabefelder Tab ----------------
            VBox tabContent = new VBox(10);
            tabContent.setAlignment(Pos.CENTER_LEFT);

            TextField loginField = new TextField();
            loginField.setPromptText("Login");

            TextField passField = new TextField();
            passField.setPromptText("Passwort");

            TextField websiteField = new TextField();
            websiteField.setPromptText("Website");

            Button speichern = new Button("Speichern");
            speichern.setOnAction(event -> {
                PasswordEntry entry = new PasswordEntry(
                        websiteField.getText(),
                        loginField.getText(),
                        passField.getText()
                );

                entries.add(entry);
                SaveManager.save(entries);
                table.getItems().add(entry); // -> Tabelle aktualisieren

                websiteField.clear();
                loginField.clear();
                passField.clear();
            });

            tabContent.getChildren().addAll(
                    new Label("Login:"), loginField,
                    new Label("Passwort:"), passField,
                    new Label("Website:"), websiteField,
                    speichern
            );

            Tab tab1 = new Tab("Daten Eingabe", tabContent);
            tab1.setClosable(false);

            // ---------------- Passwort Anzeige Tab ----------------
            TableColumn<PasswordEntry, String> colWeb = new TableColumn<>("Website");
            colWeb.setCellValueFactory(new PropertyValueFactory<>("website"));

            TableColumn<PasswordEntry, String> colLogin = new TableColumn<>("Login");
            colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));

            TableColumn<PasswordEntry, String> colPass = new TableColumn<>("Passwort");
            colPass.setCellValueFactory(new PropertyValueFactory<>("password"));

            table.getColumns().addAll(colWeb, colLogin, colPass);
            table.getItems().addAll(entries); // bereits gespeicherte anzeigen

            Tab tab2 = new Tab("Gespeicherte Passwörter", table);
            tab2.setClosable(false);

            // ---------------- Tabs ----------------
            TabPane tabPane = new TabPane();
            tabPane.getTabs().addAll(tab1, tab2);

            // ---------------- Root & Scene ----------------
            VBox root = new VBox(20);
            root.setAlignment(Pos.TOP_CENTER);
            root.getChildren().addAll(tabPane, genField);

            Scene scene = new Scene(root, 400, 400);
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

            primaryStage.setTitle("Passwort Manager");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
