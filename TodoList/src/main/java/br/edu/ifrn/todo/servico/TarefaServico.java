package br.edu.ifrn.todo.servico;

import br.edu.ifrn.todo.dominio.Projeto;
import br.edu.ifrn.todo.dominio.Tarefa;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

@Named
public class TarefaServico  extends AbstratoServico<Tarefa, Long> {
    
    @Transactional
    public void transferir(Projeto projetoFinal, Tarefa tarefa) {
        
        if (tarefa.getProjeto().equals(projetoFinal)) {
            throw new IllegalArgumentException("Projetos devem ser diferentes: "
                + projetoFinal);
        }
        
//        Tarefa tarefaFinal = (Tarefa)Atividade.builder()
//                .nome(tarefa.getNome())
//                .prazo(tarefa.getPrazo())
//                .projeto(projetoFinal)
//                .dataInicial(tarefa.getDataInicial())
//                .prioridade(tarefa.getPrioridade())
//                .build();
        
        tarefa.setProjeto(projetoFinal);
        
        save(tarefa);
    }
}
