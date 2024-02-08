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

    public UserDto(String name, String email, String password, UserRole role, boolean isActive) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
}
