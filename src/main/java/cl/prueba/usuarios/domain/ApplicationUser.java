package cl.prueba.usuarios.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser implements Comparable<ApplicationUser>{
    @Id
    private String email;
    private String password;
    private LocalDateTime modificationDate;
    private LocalDateTime creationDate;
    private String accessToken;
    @OneToMany
    private Set<Phone> phones = new TreeSet<>();

    @Override
    public int compareTo(ApplicationUser o) {
        return this.email.compareTo(o.email);
    }
}
