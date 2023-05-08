package cl.prueba.usuarios.controller;

import cl.prueba.usuarios.domain.ApplicationUser;
import cl.prueba.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> findById(@PathVariable Long id) {
        Optional<ApplicationUser> usr = usuarioService.findById(id);
        if (usr.isPresent()) {
            return new ResponseEntity<ApplicationUser>(usr.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<ApplicationUser>> list() {
        return new ResponseEntity(usuarioService.list(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Map<String,String>> save(ApplicationUser applicationUser){
        return usuarioService.save(applicationUser);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(Long id){
        return usuarioService.delete(id);
    }

}
