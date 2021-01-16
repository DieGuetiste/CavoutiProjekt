import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class Controller {
    public ListView lv_entries;
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
