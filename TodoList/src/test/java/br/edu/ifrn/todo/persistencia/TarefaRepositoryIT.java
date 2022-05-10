package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.TodoApplication;
import br.edu.ifrn.todo.dominio.Atividade;
import br.edu.ifrn.todo.dominio.Tarefa;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@Test(groups = "tarefa", dependsOnGroups = {"usuario", "projeto"})
public class TarefaRepositoryIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private TarefaRepository tarefaRepository;
    
    @Inject
    private TarefaFactory tarefaFactory;
    
    @Inject
    private ProjetoFactory projetoFactory;
    
    @BeforeMethod
    void deletarTodos()
    {
        tarefaRepository.deleteAll();
        assertThat(tarefaRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(tarefaRepository).isNotNull();
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Tarefa tarefa = tarefaFactory.tarefa();
        
        // executa a operacao a ser testada
        tarefaRepository.delete(tarefa);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(tarefaRepository.findOne(tarefa.getId())).isNull();
    }
    
    public void salvarUm () {
        //verifica se não tem nenhuma tarefa
        assertThat(tarefaRepository.findAll()).hasSize(0); 
        
        // cria o ambiente de teste
        Tarefa tarefa =  (Tarefa)Atividade.builder()
                .nome("Testar salvar um")
                .projeto(projetoFactory.projeto())
                .build();
        
        // executa a operacao a ser testada
        tarefaRepository.save(tarefa);
        
        // verifica o efeito da execucao da operacao a ser testada
        //assertThat(tarefaRepository.findAll()).hasSize(1); 
        assertThat(tarefaRepository.findAll().iterator().next()).isEqualTo(tarefa);
    }
}
