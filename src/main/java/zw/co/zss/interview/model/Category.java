package zw.co.zss.interview.model;

import lombok.Data;
import org.hibernate.envers.Audited;
import zw.co.zss.interview.utils.Status;
import javax.persistence.*;
@Audited
@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
