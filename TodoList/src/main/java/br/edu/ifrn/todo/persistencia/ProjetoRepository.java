package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Projeto;
import org.springframework.data.repository.CrudRepository;

public interface ProjetoRepository extends CrudRepository<Projeto, Long> {
    
}