package dto;

import entity.Customer;
import entity.OrderItem;
import entity.Orders;
import entity.User;
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
public class OrderDto {
    private Long id;
    private Customer customer;
    private User user;
    private List<OrderItem> orderItems = new ArrayList<>();
    private String description;
    private Date date;
    private double basicCost;
    private double additionalCost;
    private double totalCost;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OrderDto && ((OrderDto) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }
}
