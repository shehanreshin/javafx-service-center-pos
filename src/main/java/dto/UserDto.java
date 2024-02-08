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
    private Long userId;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private boolean isActive;
}
