package cl.prueba.usuarios.repository;


import cl.prueba.usuarios.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "/api/phones", path = "phones")
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
