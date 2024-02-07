package entity.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ItemTypeConverter implements AttributeConverter<ItemType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ItemType itemType) {
        switch (itemType) {
            case ELECTRONIC:
                return 1;
            case ELECTRICAL:
                return 2;
            default:
                return -1;
        }
    }

    @Override
    public ItemType convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return ItemType.ELECTRONIC;
            case 2:
                return ItemType.ELECTRICAL;
            default:
                return null;
        }
    }
}
