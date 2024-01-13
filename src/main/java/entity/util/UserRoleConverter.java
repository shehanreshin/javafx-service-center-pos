package entity.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRole userRole) {
        switch (userRole) {
            case ADMIN:
                return 1;
            case STAFF:
                return 2;
            default:
                return null;
        }
    }

    @Override
    public UserRole convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return UserRole.ADMIN;
            case 2:
                return UserRole.STAFF;
            default:
                return null;
        }
    }
}
