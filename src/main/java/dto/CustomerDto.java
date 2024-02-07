package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String contactNumber;
    private String email;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CustomerDto && ((CustomerDto) obj).getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
