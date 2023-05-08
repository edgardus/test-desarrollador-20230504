package cl.prueba.usuarios.service;

import cl.prueba.usuarios.constants.UsuariosConstants;
import cl.prueba.usuarios.domain.ApplicationUser;
import cl.prueba.usuarios.repository.ApplicationUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Clase encargada de realizar las
 * operaciones de logica de negocios.
 */
@Service
@Slf4j
public class UsuarioService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    /**
     * Metodo encargado de Obtener un registro dado su id
     *
     * @param id {@link Long}
     * @return instancia de {@link ApplicationUser}
     */
    public Optional<ApplicationUser> findById(Long id) {
        return applicationUserRepository.findById(id);
    }

    /**
     * Metodo utilizado para buscar usuarios por email
     *
     * @param email {@link String}
     * @return instancia de {@link ApplicationUser}
     */
    public Optional<ApplicationUser> findByEmail(String email) {
        return applicationUserRepository.findByEmail(email);
    }

    /**
     * Metodo encargado de listar todos los
     * elementos de {@link ApplicationUser}
     *
     * @return Lista de elementos {@link List} &lt; {@link ApplicationUser} &gt;
     */
    public List<ApplicationUser> list() {
        return applicationUserRepository.findAll();
    }

    /**
     * Metodo encargado de la logica de guardado.
     *
     * @param applicationUser {@link ApplicationUser}
     * @return respuesta {@link ResponseEntity}
     */
    public ResponseEntity<Map<String, String>> save(ApplicationUser applicationUser) {
        Map<String, String> responseHashMap = new HashMap<>();
        ResponseEntity<Map<String, String>> respuesta = respuesta = new ResponseEntity<>(responseHashMap, HttpStatus.UNPROCESSABLE_ENTITY);
        if (validaCreacionUsuario(applicationUser, responseHashMap).isEmpty()) {
            try {
                applicationUser = applicationUserRepository.save(applicationUser);
            } catch (Exception e) {
                responseHashMap.put("ERROR", e.getMessage());
                respuesta = new ResponseEntity<>(responseHashMap, HttpStatus.UNPROCESSABLE_ENTITY);
                return respuesta;
            }
            responseHashMap = creaMapRespuesta(applicationUser, responseHashMap);
            respuesta = new ResponseEntity<>(responseHashMap, HttpStatus.OK);
        }
        return respuesta;
    }

    /**
     * Metodo encargado de generar la salida de una operacion exitosa
     *
     * @param applicationUser {@link ApplicationUser}
     * @param responseHashMap
     * @return {@link Map} &lt; {@link String}, {@link String} &gt;
     */
    private Map<String, String> creaMapRespuesta(ApplicationUser applicationUser, Map<String, String> responseHashMap) {
        responseHashMap.put("id", applicationUser.getId().toString());
        responseHashMap.put("creationDate", applicationUser.getCreationDate().toString());
        responseHashMap.put("modificationDate", applicationUser.getModificationDate().toString());
        responseHashMap.put("token", applicationUser.getAccessToken());
        return responseHashMap;
    }

    /**
     * Metodo encargado de eliminar un registro.
     *
     * @param id {@link Long}
     * @return {@link ResponseEntity}
     */
    public ResponseEntity<String> delete(Long id) {
        try {
            applicationUserRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<String>("Eliminado registro:" + id.toString(), HttpStatus.OK);
    }

    /**
     * Metodo encargado de actualizar un registro en la bd.
     *
     * @param id              {@link Long}
     * @param applicationUser {@link ApplicationUser}
     * @return {@link ResponseEntity}
     */
    public ResponseEntity<Map<String, String>> update(Long id, ApplicationUser applicationUser) {
        Optional<ApplicationUser> originalUser = applicationUserRepository.findById(id);
        Map<String, String> responseHashMap = new HashMap<>();
        ResponseEntity<Map<String, String>> respuesta = new ResponseEntity<>(responseHashMap, HttpStatus.UNPROCESSABLE_ENTITY);
        if (originalUser.isEmpty()) {
            responseHashMap.put(UsuariosConstants.MESSAGE, "Nada que actualizar");
            return respuesta;
        }
        responseHashMap = validaCreacionUsuario(applicationUser, responseHashMap);
        if (responseHashMap.containsKey("id")) {
            if (Long.getLong(responseHashMap.get("id")).equals(id)) {
                responseHashMap.clear();
            }
        }
        if (responseHashMap.isEmpty()) {
            applicationUser.setId(id);
            applicationUser.setModificationDate(LocalDateTime.now());
            ApplicationUser a = applicationUserRepository.save(applicationUser);
            responseHashMap = creaMapRespuesta(a, responseHashMap);
        }
        return respuesta;
    }

    /**
     * Metodo encargado de validar el Password
     *
     * @param applicationUser {@link ApplicationUser}
     * @return true valido boolean
     */
    private boolean validatePassword(ApplicationUser applicationUser) {
        return applicationUser.getEmail() != null &&
                !applicationUser.getPassword().isBlank() &&
                !applicationUser.getPassword().matches(UsuariosConstants.INVALID_PASSWORD);
    }

    /**
     * Metodo encargado de validar reglas del negocio
     *
     * @param applicationUser {@link ApplicationUser}
     * @param responseHashMap {@link Map} &lt; {@link String}, {@link String} &gt;
     * @return {@link Map} &lt; {@link String}, {@link String} &gt; con mensajes
     */
    private Map<String, String> validaCreacionUsuario(ApplicationUser applicationUser, Map<String, String> responseHashMap) {
        if (!validatePassword(applicationUser)) {
            responseHashMap.put(UsuariosConstants.MESSAGE, UsuariosConstants.INVALID_PASSWORD_MESSAGE);
        } else {
            Optional<ApplicationUser> byEmail = applicationUserRepository.findByEmail(applicationUser.getEmail());
            if (byEmail.isPresent()) {
                responseHashMap.put("id", byEmail.get().getId().toString());
                responseHashMap.put(UsuariosConstants.MESSAGE, UsuariosConstants.MAIL_MESSAGE);
            }
        }
        return responseHashMap;
    }

}
