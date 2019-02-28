package my.examples.JBCmart.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    private String productId;
    private String productName;
    private int price;
    private Date regdate;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ImageFile> imageFiles;

    @OneToMany(mappedBy = "productDetailId.product",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ProductDetail> productDetails;

    public Product(){
        regdate = new Date();
        imageFiles = new HashSet<>();
        productDetails = new HashSet<>();
    }

    public void addImageFile(ImageFile imageFile) {
        if(imageFiles == null)
            imageFiles = new HashSet<>();
        imageFile.setProduct(this); // 쌍방향이기 때문에 this를 참조하도록 한다.
        imageFiles.add(imageFile);
    }
}

/*
| product_id   | varchar(255) | NO   | PRI | NULL    |       |
| product_name | varchar(255) | NO   |     | NULL    |       |
| price        | int(11)      | NO   |     | NULL    |       |
 */