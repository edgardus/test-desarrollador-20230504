package cl.prueba.usuarios.service;

import cl.prueba.usuarios.domain.ApplicationUser;
import cl.prueba.usuarios.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Clase encargada de realizar las
 * operaciones de logica de negocios.
 */
@Service
public class UsuarioService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    /**
     * Metodo encargado de Obtener un registro dado su id
     * @param id {@link Long}
     * @return instancia de {@link ApplicationUser}
     */
    public ApplicationUser findById( Long id){
        Optional<ApplicationUser> byId = applicationUserRepository.findById(id);
        return byId.get();
    }

    /**
     * Metodo utilizado para buscar {@link ApplicationUser#getEmail()}
     * @param email {@link String}
     * @return instancia de {@link ApplicationUser}
     */
    public Optional<ApplicationUser> findByEmail( String email){
        return applicationUserRepository.findByEmail(email);
    }

    /**
     * Metodo encargado de listar todos los
     * elementos de {@link ApplicationUser}
     * @return Lista de elementos {@link List}< {@link ApplicationUser}>
     */
    public List<ApplicationUser> list(){
        return applicationUserRepository.findAll();
    }
}
