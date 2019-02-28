package my.examples.JBCmart.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {
    @EmbeddedId
    private CartId cartId;
    private String productId;
    private String productSize;
    private String productColor;
    private int quantity;
}
/*
| user_id       | varchar(255) | NO   | PRI | NULL    |       |
| seq           | int(11)      | NO   | PRI | NULL    |       |
| product_id    | varchar(255) | NO   |     | NULL    |       |
| product_size  | varchar(3)   | NO   |     | NULL    |       |
| product_color | varchar(50)  | NO   |     | NULL    |       |
| quantity      | int(11)      | NO   |     | NULL    |       |
 */