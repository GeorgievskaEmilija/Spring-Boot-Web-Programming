package mk.finki.ukim.lab.repository.InMemoryRepository;

import mk.finki.ukim.lab.database.Dataholder;
import mk.finki.ukim.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public Optional<User> tryLogin(String username, String password) {
        return Dataholder.users.stream().filter(i->i.getUsername().equals(username)
                && i.getPassword().equals(password)).findFirst();
    }
    public Optional<User> findByUsername(String username) {
        return Dataholder.users.stream().filter(i->i.getUsername().equals(username)).findFirst();
    }
}


