package entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private String description;
    private Date date;
    private double basicCost;
    private double additionalCost;

    public Orders(Customer customer, User user, String description, Date date, double basicCost, double additionalCost) {
        this.customer = customer;
        this.user = user;
        this.description = description;
        this.date = date;
        this.basicCost = basicCost;
        this.additionalCost = additionalCost;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Orders && ((Orders) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }
}
