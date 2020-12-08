import org.json.simple.JSONObject;

public class Entry implements Comparable<Entry> {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private int zip;
    private String email;

    public Entry(String firstName, String lastName, String street, String city, int zip, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
    }

    public Entry(String firstName, String lastName, String street, String city, String zip, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = Integer.parseInt(zip);
        this.email = email;
    }


    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("street", street);
        jsonObject.put("city", city);
        jsonObject.put("zip", zip);
        jsonObject.put("email", email);
        return jsonObject;
    }

    public static Entry fromJson(JSONObject jsonObject) {
        return new Entry(
                (String) jsonObject.get("firstName"),
                (String) jsonObject.get("lastName"),
                (String) jsonObject.get("street"),
                (String) jsonObject.get("city"),
                String.valueOf(jsonObject.get("zip")),
                (String) jsonObject.get("email")
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int compareTo(Entry entry) {
        String name = toString();
        return name.compareTo(entry.toString());
    }
}
