package my.examples.JBCmart.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
public class ProductDetail {
    @EmbeddedId
    private ProductDetailId productDetailId;
    private int quantity;

}

/*
| product_id    | varchar(255) | NO   | PRI | NULL    |       |
| product_size  | varchar(3)   | NO   | PRI | NULL    |       |
| product_color | varchar(50)  | NO   | PRI | NULL    |       |
| quantity      | int(11)      | NO   |     | NULL    |       |
 */
