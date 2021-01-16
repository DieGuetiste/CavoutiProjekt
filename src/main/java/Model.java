import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Comparator;

public class Model {

    public ObservableList<Entry> entries;

    public Model() {
        entries = FXCollections.observableArrayList();
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public void saveEntries(String path) throws Exception {
        JSONArray array = new JSONArray();
        entries.forEach(e -> {
            array.add(e.toJsonObject());
        });
        String jsonString = array.toJSONString();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)));
        writer.write(jsonString);
        writer.close();
    }

    public void loadEntries(String path) throws Exception {
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        String jsonString = reader.readLine();
        JSONArray array = (JSONArray) parser.parse(jsonString);
        array.forEach(e -> {
            entries.add(Entry.fromJson((JSONObject)e));
        });
    }

    public void removeEntry(Entry selectedItem) {
        entries.remove(selectedItem);
    }
}
