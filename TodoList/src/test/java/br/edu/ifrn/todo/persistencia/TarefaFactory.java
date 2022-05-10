package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Atividade;
import br.edu.ifrn.todo.dominio.Tarefa;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TarefaFactory {
    public final static String TAREFA = "Terminar testes";
    public Date prazo = retornaPrazo(2016, 07, 07);

    @Inject
    private ProjetoFactory projetoFactory;

    @Inject
    private TarefaRepository tarefaRepository;
    
    public Date retornaPrazo(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia, 0, 0, 0);
        return cal.getTime();
    }
    
    public Tarefa tarefa() {
        Tarefa tarefa = (Tarefa)Atividade.builder()
                .nome(TAREFA)
                .prazo(prazo)
                .projeto(projetoFactory.projeto())
                .build();
        tarefaRepository.save(tarefa);
        return tarefa;
    }
}
