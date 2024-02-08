package dto.tm;

import com.jfoenix.controls.JFXComboBox;
import entity.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTm {
    private Long userId;
    private String name;
    private String email;
    private JFXComboBox<String> cmbxRole;
    private JFXComboBox<String> cmbxStatus;
}
