import javafx.scene.control.TabPane;
import org.json.simple.JSONObject;

public class Entry {
    private String firstName;
    private String lastName;
    private String street;
    private int zip;
    private String city;
    private String email;

    public Entry(String firstName, String lastName, String street, int zip, String city, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.email = email;
    }

    public static Entry fromJson(JSONObject object) {
        Entry e = new Entry(
                (String) object.get("firstName"),
                (String) object.get("lastName"),
                (String) object.get("street"),
                Integer.parseInt(Long.toString((Long) object.get("zip"))),
                (String) object.get("city"),
                (String) object.get("email"));
        return e;
    }

    public void recreate(String firstName, String lastName, String street, int zip, String city, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.email = email;
    }

    public JSONObject toJsonObject() {
        JSONObject entry = new JSONObject();
        entry.put("firstName", firstName);
        entry.put("lastName", lastName);
        entry.put("street", street);
        entry.put("zip", zip);
        entry.put("city", city);
        entry.put("email", email);
        return entry;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public boolean matches(String filter) {
        String code = firstName +
                lastName +
                street +
                zip +
                city +
                email;
        return code.toLowerCase().contains(filter.toLowerCase());
    }

    public int compareTo(Entry o2) {
        String code = firstName +
                lastName +
                street +
                zip +
                city +
                email;
        String subcode = o2.firstName +
                o2.lastName +
                o2.street +
                o2.zip +
                o2.city +
                o2.email;
        return code.compareTo(subcode);
    }
}
