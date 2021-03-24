package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.Order;
import mk.finki.ukim.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
