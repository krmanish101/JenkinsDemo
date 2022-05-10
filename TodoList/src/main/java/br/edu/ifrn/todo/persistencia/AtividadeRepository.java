package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Atividade;
import org.springframework.data.repository.CrudRepository;

public interface AtividadeRepository extends CrudRepository<Atividade, Long> {
    
}