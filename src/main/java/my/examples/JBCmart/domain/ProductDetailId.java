package my.examples.JBCmart.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class ProductDetailId implements Serializable {
    private String productSize;
    private String productColor;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

/*
| product_id    | varchar(255) | NO   | PRI | NULL    |       |
| product_size  | varchar(3)   | NO   | PRI | NULL    |       |
| product_color | varchar(50)  | NO   | PRI | NULL    |       |
 */