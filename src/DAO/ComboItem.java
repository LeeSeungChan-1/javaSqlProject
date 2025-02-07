package DAO;

public class ComboItem {
    private Long key;
    private String value;

    public ComboItem(Long key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }
}
