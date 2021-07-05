package zw.co.zss.interview.model;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ZssPurchaseRequest {

    private String type;
    private String extendedType;
    private Double amount;
    private String created;  //String
    private Card card;
    private String reference;
    private String narration;
    Map<String,String> additionalData =new HashMap<>();

    public ZssPurchaseRequest(String type, String extendedType, Double amount, String created, Card card, String reference, String narration,  Map<String, String> additionalData) {
        this.type = type;
        this.extendedType = extendedType;
        this.amount = amount;
        this.created = created;
        this.card = card;
        this.reference = reference;
        this.narration = narration;
        this.additionalData = additionalData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtendedType() {
        return extendedType;
    }

    public void setExtendedType(String extendedType) {
        this.extendedType = extendedType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Map<String, String> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, String> additionalData) {
        this.additionalData = additionalData;
    }
}


