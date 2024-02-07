package entity;

import entity.util.ItemStatus;
import entity.util.ItemStatusConverter;
import entity.util.ItemType;
import entity.util.ItemTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Convert(converter = ItemTypeConverter.class)
    private ItemType type;

    private String name;
    private double startingPrice;

    private String img;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Item && ((Item) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }
}
