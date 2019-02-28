package my.examples.JBCmart.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderId implements Serializable {
    private String orderId;
    private String userId;
}

/*
| order_id         | varchar(255) | NO   | PRI | NULL    |       |
| user_id          | varchar(255) | NO   | PRI | NULL    |       |
 */