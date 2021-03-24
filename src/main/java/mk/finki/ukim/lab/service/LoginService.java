package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.User;

public interface LoginService {
    User login(String username, String password);
}
