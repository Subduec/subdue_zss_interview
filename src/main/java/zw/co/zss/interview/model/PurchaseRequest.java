package zw.co.zss.interview.model;

import lombok.Data;
import org.hibernate.envers.Audited;
import zw.co.zss.interview.utils.Status;

import javax.persistence.*;


public class PurchaseRequest {

    private Long bookId;

    private String cardNumber;

    private String expiryDate;

    public Long getBookId() {
        return bookId;
    }

    public PurchaseRequest(Long bookId, String cardNumber) {
        this.bookId = bookId;
        this.cardNumber = cardNumber;

    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
