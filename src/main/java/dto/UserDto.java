package dto;

import entity.util.UserRole;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private int userId;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
