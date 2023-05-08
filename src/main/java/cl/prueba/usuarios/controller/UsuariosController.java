package cl.prueba.usuarios.controller;

import cl.prueba.usuarios.domain.ApplicationUser;
import cl.prueba.usuarios.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Slf4j
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Busca {@link ApplicationUser} por id.
     * @param id {@link Long}
     * @return {@link ApplicationUser}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> findById(@PathVariable Long id) {
        log.debug("findById :{}",id);
        Optional<ApplicationUser> usr = usuarioService.findById(id);
        if (usr.isPresent()) {
            return new ResponseEntity<ApplicationUser>(usr.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    /**
     * Lista {@link ApplicationUser} presentes en la base de datos.
     * @return respuesta {@link List}
     */
    @GetMapping("/")
    public ResponseEntity<List<ApplicationUser>> list() {
        log.debug("Listar Usuarios");
        return new ResponseEntity(usuarioService.list(), HttpStatus.OK);
    }

    /**
     * Crea {@link ApplicationUser} en la base de datos.
     * @param applicationUser {@link ApplicationUser}
     * @return respuesta {@link Map}
     */
    @PostMapping("/")
    public ResponseEntity<Map<String,String>> save(@RequestBody ApplicationUser applicationUser){
        log.debug("Save usuario");
        return usuarioService.save(applicationUser);
    }

    /**
     * Elimina un registro.
     * @param id {@link Long}
     * @return respuesta {@link Map}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        log.debug("delete usuarion con Id:{}",id);
        return usuarioService.delete(id);
    }

    /**
     * Actualiza un registro
     * @param id {@link Long}
     * @param applicationUser {@link ApplicationUser}
     * @return respuesta {@link Map}
     */
    @PutMapping("/{id}")
    public ResponseEntity <Map<String,String>> update(@PathVariable("id")Long id,@RequestBody ApplicationUser applicationUser){
        log.debug("Update usuario");
        return usuarioService.update(id,applicationUser);
    }

}
