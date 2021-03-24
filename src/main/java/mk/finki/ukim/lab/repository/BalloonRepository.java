package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.Balloon;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon, Long> {
    List<Balloon> findByNameOrDescription(String Name, String Description);
}
