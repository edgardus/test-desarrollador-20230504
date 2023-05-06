package cl.prueba.usuarios.controller;

import cl.prueba.usuarios.domain.ApplicationUser;
import cl.prueba.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ApplicationUser findById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<ApplicationUser>> list(){
        return new ResponseEntity(usuarioService.list(), HttpStatus.OK);
    }

}
