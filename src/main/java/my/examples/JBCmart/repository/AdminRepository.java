package my.examples.JBCmart.repository;

import my.examples.JBCmart.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Product,String> {
}
