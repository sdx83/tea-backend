package com.tea.service;

import java.util.Optional;

import com.tea.model.Usuario;
import com.tea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class UsuarioServiceImpl implements UsuarioService {

	@Qualifier("userRepository")
	@Autowired
	UserRepository userRepository;

	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {

		usuario.setEnable(true);
		return userRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findByUsuarioAndPassword(String usuario, String password) {
		return userRepository.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public Optional<Usuario> findByUsuario(String usuario) {
		return userRepository.findByUsuario(usuario);
	}

	@Override
	public Usuario update(Usuario usuarioExistente, Usuario usuarioModificado) {

		usuarioExistente.setNombre(usuarioModificado.getNombre().trim());
		usuarioExistente.setApellido(usuarioModificado.getApellido().trim());
		usuarioExistente.setPassword(usuarioModificado.getPassword().trim());
		usuarioExistente.setMail(usuarioModificado.getMail().trim());
		usuarioExistente.setTelefono(usuarioModificado.getTelefono().trim());
		usuarioExistente.setNroDocumento(usuarioModificado.getNroDocumento().trim());
		usuarioExistente.setDireccion(usuarioModificado.getDireccion().trim());
		usuarioExistente.setLocalidad(usuarioModificado.getLocalidad().trim());
		usuarioExistente.setProvincia(usuarioModificado.getProvincia().trim());

		return this.save(usuarioExistente);
	}

	@Override
	public Usuario delete(Usuario usuario) {

		usuario.setEnable(false);

		return userRepository.save(usuario);
	}

	@Override
	public Usuario activate(Usuario usuario) {

		usuario.setEnable(true);

		Usuario user = userRepository.save(usuario);

		return user;
	}
}
