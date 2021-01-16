import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Controller {
    public ListView lv_entries;
    public Label l_firstName;
    public Label l_lastName;
    public Label l_street;
    public Label l_city;
    public Label l_zip;
    public Label l_email;
    private Model model;

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
        System.out.println("Not Implemented yet!");
    }

    public void cancel(ActionEvent actionEvent) {
        System.out.println("Not Implemented yet!");
    }

    public void saveEdit(ActionEvent actionEvent) {
        System.out.println("Not Implemented yet!");
    }

    public void delete(ActionEvent actionEvent) {
        System.out.println("Not Implemented yet!");
    }
}
