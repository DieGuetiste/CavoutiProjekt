import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AlertController {
    public Button btn_yes;
    public Button btn_no;
    private EventHandler yesAction;
    private EventHandler noAction;
    public Label l_text;

    public void setOnYes(EventHandler action) {
        btn_yes.setOnAction(action);
    }

    public void setOnNo(EventHandler action) {
        btn_no.setOnAction(action);
    }

    public void setText(String text) {
        this.l_text.setText(text);
    }
}
