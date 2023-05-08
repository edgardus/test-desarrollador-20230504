package cl.prueba.usuarios.domain;

import cl.prueba.usuarios.constants.UsuariosConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser implements Comparable<ApplicationUser> {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @Pattern(regexp =  UsuariosConstants.EMAIL, message ="Email no válido")
    private String email;
    private String password;
    @JsonIgnore
    private LocalDateTime modificationDate = LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime creationDate = LocalDateTime.now();
    @JsonIgnore
    private String accessToken;
    @OneToMany
    private Set<Phone> phones = new TreeSet<>();
    @Override
    public int compareTo(ApplicationUser o) {
        return this.id.compareTo(o.id);
    }
}
