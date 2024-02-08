package entity;

import entity.util.UserRole;
import entity.util.UserRoleConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    private boolean isActive;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && ((User) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }
}
