package cl.prueba.usuarios.repository;

import cl.prueba.usuarios.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "users",
        path = "users")
public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, String>,
        CrudRepository<ApplicationUser, String> {
}
