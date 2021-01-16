import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public ListView lv_entries;
    public Label l_firstName;
    public Label l_lastName;
    public Label l_street;
    public Label l_city;
    public Label l_zip;
    public Label l_email;
    public TextField tf_firstname;
    public TextField tf_lastname;
    public TextField tf_street;
    public TextField tf_city;
    public TextField tf_zip;
    public TextField tf_email;
    public ChoiceBox cb_edit;
    public TabPane tabPane;
    public Tab edit_tab;
    public TextField tf_edit_firstname;
    public TextField tf_edit_lastname;
    public TextField tf_edit_street;
    public TextField tf_edit_zip;
    public TextField tf_edit_city;
    public TextField tf_edit_email;
    private Model model;
    private ChangeListener onEditSelected = new ChangeListener() {
        @Override
        public void changed(ObservableValue observableValue, Object oldEntry, Object newEntry) {
            tf_edit_firstname.setText(((Entry) newEntry).getFirstName());
            tf_edit_lastname.setText(((Entry) newEntry).getLastName());
            tf_edit_street.setText(((Entry) newEntry).getStreet());
            tf_edit_zip.setText("" + ((Entry) newEntry).getZip());
            tf_edit_city.setText(((Entry) newEntry).getCity());
            tf_edit_email.setText(((Entry) newEntry).getEmail());
        }

    };

    public void setMyModel(Model model) {
        this.model = model;
        Randomizer.getRandomEntries(10).forEach(model::addEntry);
        try {
            model.saveEntries("Entries.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv_entries.setItems(model.entries);
        lv_entries.getSelectionModel().selectedItemProperty().addListener((observableValue, oldEntry, newEntry) -> showEntry((Entry) newEntry));
        cb_edit.setItems(model.entries);
        cb_edit.getSelectionModel().selectedItemProperty().addListener(onEditSelected);
    }

    public void showEntry(Entry entry) {
        l_firstName.setText(entry.getFirstName());
        l_lastName.setText(entry.getLastName());
        l_city.setText(entry.getCity());
        l_email.setText(entry.getEmail());
        l_street.setText(entry.getStreet());
        l_zip.setText(entry.getZip() + "");
    }

    public void saveAdd(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("alert.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Speichern");
            stage.setScene(new Scene(root));
            stage.show();
            AlertController alertController = loader.getController();
            alertController.setText("Willst du den Eintrag Speichern?");
            alertController.setOnYes(event -> {
                Entry entry = new Entry(tf_firstname.getText(), tf_lastname.getText(), tf_street.getText(), Integer.valueOf(tf_zip.getText()), tf_city.getText(), tf_email.getText());
                model.addEntry(entry);
                tf_firstname.clear();
                tf_lastname.clear();
                tf_street.clear();
                tf_zip.clear();
                tf_city.clear();
                tf_email.clear();
                stage.close();
                try {
                    model.saveEntries("Entries.json");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            alertController.setOnNo(event -> {
                stage.close();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void saveEdit(ActionEvent actionEvent) {
        ((Entry) cb_edit.getSelectionModel().getSelectedItem()).recreate(tf_edit_firstname.getText(), tf_edit_lastname.getText(), tf_edit_street.getText(), Integer.valueOf(tf_edit_zip.getText()), tf_edit_city.getText(), tf_edit_email.getText());
        try {
            model.saveEntries("Entries.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ActionEvent actionEvent) {

    }
}
