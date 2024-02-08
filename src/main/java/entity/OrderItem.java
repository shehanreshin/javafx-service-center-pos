package entity;

import entity.util.ItemStatus;
import entity.util.ItemStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    public OrderItem(Orders order, Item item, ItemStatus itemStatus) {
        this.order = order;
        this.item = item;
        this.itemStatus = itemStatus;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OrderItem && ((OrderItem) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }
}
