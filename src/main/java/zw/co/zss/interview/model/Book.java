package zw.co.zss.interview.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.envers.Audited;
import zw.co.zss.interview.utils.Status;
import javax.persistence.*;
@Audited
@Entity
@Table(name = "books")
@Data
public class Book extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description" , unique = true)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "category_id" )
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Category category;


}


