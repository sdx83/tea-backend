package com.tea.service;

import java.util.List;
import java.util.Optional;

import com.tea.model.Usuario;

public interface UsuarioService {

	public Usuario save(Usuario usuario);

	Optional<Usuario> findByUsuarioAndPassword(String usuario,String password);

	Optional<Usuario> findByUsuario(String usuario);

	public Usuario update(Usuario usuarioExistente, Usuario usuarioModificado);

	public Usuario delete(Usuario usuario);

	public Usuario activate(Usuario usuario);
	
	List<Usuario> findAll();

}
