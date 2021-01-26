import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import java.util.List;

public class Controller {
    public ListView lv_entries;
    public Label l_firstName, l_lastName, l_street, l_city, l_zip, l_email,l_del_firstName, l_del_lastName, l_del_email, l_del_street, l_del_zip, l_del_city;
    public TextField tf_firstname, tf_lastname, tf_street, tf_city, tf_zip, tf_email,tf_edit_firstname, tf_edit_lastname, tf_edit_street, tf_edit_zip, tf_edit_city, tf_edit_email;
    public ChoiceBox cb_edit;
    public ChoiceBox cb_delete;
    public TextField tf_search;
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
    private ChangeListener onDeleteSelected = new ChangeListener() {
        @Override
        public void changed(ObservableValue observableValue, Object oldEntry, Object newEntry) {
            l_del_firstName.setText(((Entry)newEntry).getFirstName());
            l_del_lastName.setText(((Entry)newEntry).getLastName());
            l_del_street.setText(((Entry)newEntry).getStreet());
            l_del_zip.setText(((Entry)newEntry).getZip() + "");
            l_del_city.setText(((Entry)newEntry).getCity());
            l_del_email.setText(((Entry)newEntry).getEmail());
        }
    };

    public void setMyModel(Model model) {
        this.model = model;
        //Randomizer.getRandomEntries(50).forEach(model::addEntry);
        try {
            model.loadEntries("Entries.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            model.saveEntries("Entries.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv_entries.setItems(model.entries);
        lv_entries.getSelectionModel().selectedItemProperty().addListener((observableValue, oldEntry, newEntry) -> showEntry((Entry) newEntry));
        cb_edit.setItems(model.entries);
        cb_delete.setItems(model.entries);
        cb_edit.getSelectionModel().selectedItemProperty().addListener(onEditSelected);
        cb_delete.getSelectionModel().selectedItemProperty().addListener(onDeleteSelected);
        tf_search.textProperty().addListener((observableValue, old, _new) -> {
            if (_new.equals("")){
                lv_entries.setItems(model.entries);
                return;
            }
            ObservableList<Entry> filtered = model.filter(_new);
            lv_entries.setItems(filtered);
        });
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
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("alert.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Speichern");
            stage.setScene(new Scene(root));
            stage.show();
            AlertController alertController = loader.getController();
            alertController.setText("Willst du die Änderungen speichern?");
            alertController.setOnYes(event -> {
                ((Entry) cb_edit.getSelectionModel().getSelectedItem()).recreate(tf_edit_firstname.getText(), tf_edit_lastname.getText(), tf_edit_street.getText(), Integer.valueOf(tf_edit_zip.getText()), tf_edit_city.getText(), tf_edit_email.getText());
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

    public void delete(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("alert.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Löschen");
            stage.setScene(new Scene(root));
            stage.show();
            AlertController alertController = loader.getController();
            alertController.setText("Willst du den Eintrag löschen?");
            alertController.setOnYes(event -> {
                model.removeEntry((Entry)cb_delete.getSelectionModel().getSelectedItem());
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
}
