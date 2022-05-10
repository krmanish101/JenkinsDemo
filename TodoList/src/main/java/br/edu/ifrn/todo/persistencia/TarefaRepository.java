package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Tarefa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TarefaRepository extends CrudRepository<Tarefa, Long>, QueryByExampleExecutor<Tarefa> {
    
}