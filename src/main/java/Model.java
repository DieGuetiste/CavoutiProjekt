import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Comparator;

public class Model {
    ObservableList<Entry> entries = FXCollections.observableArrayList();

    public Model() throws Exception {
        loadData();
        entries.sort(Entry::compareTo);
    }

    private void saveData() throws Exception {
        entries.sort(Entry::compareTo);
        JSONArray array = new JSONArray();
        for (Entry entry : entries) {
            array.add(entry.toJson());
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("save.json")));
        writer.write(array.toJSONString());
        writer.close();

    }

    private void loadData() throws Exception {
        String filePath = "save.json";
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            line = reader.readLine();
        }
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(builder.toString());
        array.forEach(o -> {
            entries.removeAll();

            entries.add(Entry.fromJson((JSONObject) o));

        });

    }


}
