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
    private CustomerDto customer;
    private UserDto user;
    private List<ItemDto> items = new ArrayList<>();
    private String description;
    private Date date;
    private double basicCost;
    private double additionalCost;
    private double totalCost;

    public OrderDto(CustomerDto customer, UserDto user, List<ItemDto> items, String description, Date date, double basicCost, double additionalCost, double totalCost) {
        this.customer = customer;
        this.user = user;
        this.items = items;
        this.description = description;
        this.date = date;
        this.basicCost = basicCost;
        this.additionalCost = additionalCost;
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OrderDto && ((OrderDto) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }
}
