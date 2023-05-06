package cl.prueba.usuarios.repository;

import cl.prueba.usuarios.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    public Optional<ApplicationUser> findByEmail(String email);
}
