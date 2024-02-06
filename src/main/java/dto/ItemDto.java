package dto;

import entity.Item;
import entity.util.ItemType;
import entity.util.ItemTypeConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {
    private Long id;
    private ItemType type;
    private String name;
    private double startingPrice;
    private Date collectionDate;
    private String img;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ItemDto && ((ItemDto) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }
}
