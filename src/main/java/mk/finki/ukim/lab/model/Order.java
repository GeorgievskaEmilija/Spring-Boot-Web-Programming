package mk.finki.ukim.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shop_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String balloonColor;

    private String balloonSize;

    @ManyToOne
    private User user;

    public Order() {
    }

    public Order(String balloonColor, String balloonSize, User user) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user = user;
    }
}
