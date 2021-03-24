package mk.finki.ukim.lab.database;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Manufacturer;
import mk.finki.ukim.lab.model.Order;
import mk.finki.ukim.lab.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Dataholder {
    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Order> ordersList = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<User> users = new ArrayList<>();


    @PostConstruct
    public void init() {
        balloons.add(new Balloon("pink", "pink balloon"));
        balloons.add(new Balloon("red", "red balloon"));
        balloons.add(new Balloon("orange", "orange balloon"));
        balloons.add(new Balloon("yellow", "yellow balloon"));
        balloons.add(new Balloon("green", "green balloon"));
        balloons.add(new Balloon("blue", "blue balloon"));
        manufacturers.add(new Manufacturer("D", "Germany", "DE"));
        manufacturers.add(new Manufacturer("I", "Italy", "IT"));
        manufacturers.add(new Manufacturer("E", "England", "EN"));
        users.add(new User("test", "test"));
    }

}
