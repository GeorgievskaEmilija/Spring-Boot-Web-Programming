package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.Order;
import mk.finki.ukim.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    List<User> findAllByUsernameLike(String name);
}
