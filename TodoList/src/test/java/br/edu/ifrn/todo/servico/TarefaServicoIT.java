package br.edu.ifrn.todo.servico;

import br.edu.ifrn.todo.TodoApplication;
import br.edu.ifrn.todo.dominio.Atividade;
import br.edu.ifrn.todo.dominio.Projeto;
import br.edu.ifrn.todo.dominio.Tarefa;
import br.edu.ifrn.todo.persistencia.ProjetoFactory;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@Test(groups = "tarefa", dependsOnGroups = {"projeto"})
public class TarefaServicoIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private ProjetoFactory projetoFactory;
    
    @Inject
    private TarefaServico tarefaServico;
    
    @BeforeMethod
    void deletarTodos()
    {
        tarefaServico.deleteAll();
        assertThat(tarefaServico.findAll()).isEmpty();
    }
    
    private Tarefa tarefa(String nome, Projeto projeto){    
        Tarefa tarefa = (Tarefa)Atividade.builder()
                .nome(nome)
                .projeto(projeto)
                .build();
        
        return tarefa;
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(tarefaServico).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Tarefa tarefa = tarefa("rodar testes", projetoFactory.projeto());
        
        // executa a operacao a ser testada
        tarefaServico.save(tarefa);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(tarefaServico.findAll().iterator().next()).isEqualTo(tarefa);
    }
   
    public void deletarUm () {
        // cria o ambiente de teste
        Tarefa tarefa = tarefa("rodar testes", projetoFactory.projeto());
        
        tarefaServico.save(tarefa);
        
        // executa a operacao a ser testada
        tarefaServico.delete(tarefa);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(tarefaServico.findAll().iterator().hasNext()).isFalse();
    }
    
    public void transferir () {
        // cria o ambiente de teste
        Projeto p1 = projetoFactory.projeto("Testes");
        Projeto p2 = projetoFactory.projeto("Servicos");
        
        Tarefa tarefa = tarefa("rodar testes", p1);
        
        tarefaServico.save(tarefa);
        
        // executa a operacao a ser testada
        tarefaServico.transferir(p2, tarefa);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(tarefa.getProjeto()).isEqualTo(p2);
    }
}
