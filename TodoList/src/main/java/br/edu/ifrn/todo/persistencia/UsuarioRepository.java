package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    
}