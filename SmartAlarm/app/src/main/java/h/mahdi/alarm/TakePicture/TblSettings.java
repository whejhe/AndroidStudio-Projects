package h.mahdi.alarm.TakePicture;

public class TblSettings {
    int id;
    String name;
    String value;

    public TblSettings(int id, String name, String value) {
        this.name = "";
        this.value = "";
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public TblSettings() {
        this.name = "";
        this.value = "";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
