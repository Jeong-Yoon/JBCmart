package my.examples.JBCmart.repository;

import my.examples.JBCmart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.userId = :id")
    public User getUserById(@Param("id") String id);
}
