package my.examples.JBCmart.repository;

import my.examples.JBCmart.domain.Cart;
import my.examples.JBCmart.domain.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CartRepository extends JpaRepository<Cart, CartId> {
    @Query("SELECT c FROM Cart c WHERE  c.cartId.user.userId = :userId")
    public List<Cart> getCarts(@Param("userId") String userId);

    @Query("SELECT max(c.cartId.seq) FROM Cart c WHERE c.cartId.user.userId = :userId")
    public Long getSeq(@Param("userId") String userId);
}
