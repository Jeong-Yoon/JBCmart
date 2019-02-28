package my.examples.JBCmart.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderDetailId implements Serializable {
    private String orderId;
    private String userId;
    private String productId;
    private String productSize;
    private String productColor;
}
/*
| order_id      | varchar(255) | NO   | PRI | NULL    |       |
| user_id       | varchar(255) | NO   | PRI | NULL    |       |
| product_id    | varchar(255) | NO   | PRI | NULL    |       |
| product_size  | varchar(3)   | NO   | PRI | NULL    |       |
| product_color | varchar(50)  | NO   | PRI | NULL    |       |
 */