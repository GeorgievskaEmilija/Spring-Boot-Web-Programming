package mk.finki.ukim.lab.service.implementations;

import mk.finki.ukim.lab.model.Order;
import mk.finki.ukim.lab.model.User;
import mk.finki.ukim.lab.model.exceptions.WrongCredentialsException;
import mk.finki.ukim.lab.repository.OrderRepository;
import mk.finki.ukim.lab.repository.UserRepository;
import mk.finki.ukim.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImplementation(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order placeOrder(String balloonColor, String clientName, String address) {
        User user = userRepository.findByUsername(clientName).orElseThrow(()-> new WrongCredentialsException());
        return orderRepository.save(new Order(balloonColor, "", user));
    }

    @Override
    public List<Order> searchByClient(String name, String address) {
        User user = userRepository.findAllByUsernameLike(name).stream().findFirst().orElseThrow(() -> new WrongCredentialsException());
        return orderRepository.findAllByUser(user);
    }
}
