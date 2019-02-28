package my.examples.JBCmart.repository;

import my.examples.JBCmart.domain.Product;
import my.examples.JBCmart.repository.custom.ProductRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> , ProductRepositoryCustom {

    @Query(value = "SELECT p FROM Product p INNER JOIN FETCH p.category ORDER BY p.regdate DESC",
            countQuery = "SELECT count(p) FROM Product p")
    public Page<Product> getProducts(Pageable pageable);

    @Query("SELECT distinct p FROM Product p INNER JOIN FETCH p.category WHERE p.productId = :id")
    public Product getProduct(@Param("id") String id);
//    @Query("SELECT distinct p FROM Product p INNER JOIN FETCH p.category LEFT JOIN FETCH p.imageFiles WHERE p.productId = :id")
//    public Product getProduct(@Param("id") String id);
}
