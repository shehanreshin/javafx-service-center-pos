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
    private Integer userId;

    private String name;
    private String email;
    private String password;

    @Convert(converter = UserRoleConverter.class)
    private UserRole role;
}
