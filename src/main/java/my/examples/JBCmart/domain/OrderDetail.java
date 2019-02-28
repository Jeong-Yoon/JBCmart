package my.examples.JBCmart.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId orderDetailId;
    private int quantity;
}

/*
| order_id      | varchar(255) | NO   | PRI | NULL    |       |
| user_id       | varchar(255) | NO   | PRI | NULL    |       |
| product_id    | varchar(255) | NO   | PRI | NULL    |       |
| product_size  | varchar(3)   | NO   | PRI | NULL    |       |
| product_color | varchar(50)  | NO   | PRI | NULL    |       |
| quantity      | int(11)      | NO   |     | NULL    |       |
 */