package com.tea.repository;

import java.util.Optional;

import com.tea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Usuario, Long>  {

	Optional<Usuario> findByUsuarioAndPassword(String usuario,String password);

	Optional<Usuario> findByUsuario(String usuario);

}
