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

    public JSONObject toJsonObject(){
        JSONObject entry = new JSONObject();
        entry.put("firstName",firstName);
        entry.put("lastName",lastName);
        entry.put("street",street);
        entry.put("zip",zip);
        entry.put("city",city);
        entry.put("email",email);
        return entry;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
