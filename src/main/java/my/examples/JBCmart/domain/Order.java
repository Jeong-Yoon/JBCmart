package my.examples.JBCmart.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {
    @EmbeddedId
    private OrderId orderId;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String receiverRequest;

}

/*
| order_id         | varchar(255) | NO   | PRI | NULL    |       |
| user_id          | varchar(255) | NO   | PRI | NULL    |       |
| receiver_name    | varchar(45)  | NO   |     | NULL    |       |
| receiver_address | varchar(255) | NO   |     | NULL    |       |
| receiver_phone   | varchar(11)  | NO   |     | NULL    |       |
| receiver_request | varchar(255) | NO   |     | NULL    |       |
 */