package dto.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDtoWrapper {
    private String email;
    private String password;
}
