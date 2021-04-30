package com.tea.controller;
import java.util.Optional;

import com.tea.model.Usuario;
import com.tea.service.UsuarioServiceImpl;
import com.tea.utils.UserEnum;
import com.tea.utils.UserValidator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/Usuarios")
@Api(value = "User REST Endpoint", description = "Shows the user info")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @Autowired
	public UsuarioController(UsuarioServiceImpl usuarioService) {
		this.usuarioService = usuarioService;
	}

	// GET: http://localhost:8080/Usuarios/user/pass
    @GetMapping(value="/{usuario}/{pass}")
	public ResponseEntity<Usuario> getUsuarioByUserAndPass(@PathVariable("usuario") String user, @PathVariable("pass") String pass) throws Exception{

 		try {
 			Optional<Usuario> usuario;
 			usuario = usuarioService.findByUsuarioAndPassword(user, pass);

 			if(usuario.isPresent() && usuario.get().isEnable()) {
 	 			return ResponseEntity.ok(usuario.get());
 	 		}else {
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario y/o Password incorrectos");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}

    //GET: http://localhost:8080/Usuarios/1
    @GetMapping(value="/{idUsuario}")
 	public ResponseEntity<Usuario> getUsuarioByID(@PathVariable("idUsuario") Long id) throws Exception{

  		try {
  			Optional<Usuario> usuario = usuarioService.findById(id);
  	 		if(usuario.isPresent()) {
  	 			return ResponseEntity.ok(usuario.get());
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (Exception e) {
			throw new Exception("Error al obtener el usuario");
		}
 	}


  	// POST: http://localhost:8080/Usuarios
 	@PostMapping
 	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) throws Exception {

 		try {
 			Optional<Usuario> usuarioExistente = usuarioService.findByUsuario(usuario.getUsuario().trim());
 			if (usuarioExistente.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario existente");
 			}
 			UserValidator.validarUsuario(usuario, UserEnum.ALTA);

 			return ResponseEntity.ok(usuarioService.save(usuario));
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear el usuario");
 	 	}
 	}

     //PUT: http://localhost:8080/Usuarios/1
  	 @RequestMapping(value = "/{usuario}", method = RequestMethod.PUT)
     public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("usuario") String usuario,
    		 															@RequestBody Usuario nuevoUsuario) throws Exception {

  		try {
  			Optional<Usuario> usuarioExistente = usuarioService.findByUsuario(usuario.trim());
  			if (!usuarioExistente.isPresent()) {
  				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario no encontrado");
  			}
  			UserValidator.validarUsuario(nuevoUsuario, UserEnum.MODIFICACION);
			return ResponseEntity.ok(usuarioService.update(usuarioExistente.get(), nuevoUsuario));
  		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al actualizar el usuario");
		}
     }

    // PUT: http://localhost:8080/Usuarios/Eliminar/usuario
 	@RequestMapping(value = "/Eliminar/{usuario}", method = RequestMethod.PUT)
 	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("usuario") String user) throws Exception {


 		try {
 			Optional<Usuario> usuario = usuarioService.findByUsuario(user);
 			if (!usuario.isPresent()) {
 				throw new Exception("Usuario no encontrado");
 			}
 			return ResponseEntity.ok(usuarioService.delete(usuario.get()));
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al eliminar el usuario");
		}
 	}
}
