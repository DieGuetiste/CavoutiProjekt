import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class Controller {
    public ListView lv_entries;
    private Model myModel;

    public void setMyModel(Model myModel) {
        this.myModel = myModel;
        lv_entries.setItems(myModel.entries);
    }

    public void saveAdd(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void saveEdit(ActionEvent actionEvent) {
    }

    public void delete(ActionEvent actionEvent) {
    }
}
