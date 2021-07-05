package zw.co.zss.interview.model;

public class Card {
    private String id;
    private String expiry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public Card(String id, String expiry) {
        this.id = id;
        this.expiry = expiry;
    }
}
