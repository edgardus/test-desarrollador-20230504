package cl.prueba.usuarios.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Comparable<Phone> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private ApplicationUser applicationUser;
    @JsonProperty
    private String number;

    @Override
    public int compareTo(Phone o) {

        return this.applicationUser.compareTo(o.applicationUser) * this.number.compareTo(o.number);
    }
}
