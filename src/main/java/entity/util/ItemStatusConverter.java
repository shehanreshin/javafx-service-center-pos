package entity.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ItemStatusConverter implements AttributeConverter<ItemStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ItemStatus itemStatus) {
        switch (itemStatus) {
            case ORANGE:
                return 1;
            case RED:
                return 2;
            case YELLOW:
                return 3;
            case GREEN:
                return 4;
            default:
                return -1;
        }
    }

    @Override
    public ItemStatus convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return ItemStatus.ORANGE;
            case 2:
                return ItemStatus.RED;
            case 3:
                return ItemStatus.YELLOW;
            case 4:
                return ItemStatus.GREEN;
            default:
                return null;
        }
    }
}
