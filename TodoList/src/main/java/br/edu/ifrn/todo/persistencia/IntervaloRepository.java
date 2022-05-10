package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Intervalo;
import org.springframework.data.repository.CrudRepository;

public interface IntervaloRepository extends CrudRepository <Intervalo, Long>{

}