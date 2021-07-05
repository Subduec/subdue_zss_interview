package zw.co.zss.interview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.envers.Audited;
import zw.co.zss.interview.utils.Status;

import javax.persistence.*;

@Audited
@Entity
@Table(name = "purchases")
@Data
public class Purchases extends BaseEntity {


    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "amount" )
    private Double amount;

    @Column(name = "payment_reference", nullable = false)
    private String paymentReference;

  @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "debit_reference", nullable = false)
    private String debitReference;


}


