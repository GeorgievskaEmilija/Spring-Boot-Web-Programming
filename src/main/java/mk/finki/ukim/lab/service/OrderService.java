package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String balloonColor, String clientName, String address);
    List<Order> searchByClient(String name, String address);

}
