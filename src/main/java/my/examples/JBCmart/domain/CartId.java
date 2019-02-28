package my.examples.JBCmart.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class CartId implements Serializable {
    private long seq;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

/*
| user_id       | varchar(255) | NO   | PRI | NULL    |       |
| seq           | int(11)      | NO   | PRI | NULL    |       |
 */