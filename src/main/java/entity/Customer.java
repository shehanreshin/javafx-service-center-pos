package entity;

import dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String contactNumber;
    private String email;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Customer && ((Customer) obj).getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
