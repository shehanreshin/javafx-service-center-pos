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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
