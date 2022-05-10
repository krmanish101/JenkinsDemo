package br.edu.ifrn.todo.servico;

import br.edu.ifrn.todo.TodoApplication;
import br.edu.ifrn.todo.dominio.Atividade;
import br.edu.ifrn.todo.dominio.Projeto;
import br.edu.ifrn.todo.dominio.Tarefa;
import br.edu.ifrn.todo.persistencia.UsuarioFactory;
import java.util.Calendar;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@Test(groups = "projeto")
public class ProjetoServicoIT extends AbstractTestNGSpringContextTests {

    @Inject
    private ProjetoServico projetoServico;
    
    @Inject
    private UsuarioFactory usuarioFactory;
    
    @BeforeMethod
    void deletarTodos()
    {
        projetoServico.deleteAll();
        assertThat(projetoServico.findAll()).isEmpty();
    }
    
    private Tarefa tarefa(String nome, Projeto projeto){
        Calendar prazo = Calendar.getInstance();
        prazo.set(2016, 10, 10);
        
        Tarefa tarefa = (Tarefa)Atividade.builder()
                .nome(nome)
                .prazo(prazo.getTime())
                .projeto(projeto)
                .build();
        
        return tarefa;
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(projetoServico).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Projeto projeto = Projeto.builder()
                .nome("Testes")
                .usuario(usuarioFactory.usuario())
                .build();
        
        // executa a operacao a ser testada
        projetoServico.save(projeto);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(projetoServico.findAll().iterator().next()).isEqualTo(projeto);
    }
   
    public void deletarUm () {
        // cria o ambiente de teste
        Projeto projeto = Projeto.builder()
                .nome("Testes2")
                .usuario(usuarioFactory.usuario())
                .build();
        projetoServico.save(projeto);
        
        // executa a operacao a ser testada
        projetoServico.delete(projeto);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(projetoServico.findAll().iterator().hasNext()).isFalse();
    }
    
//    public void tarefasAbertas () {
//        // cria o ambiente de teste
//        Projeto projeto = Projeto.builder()
//                .nome("Abertas")
//                .usuario(usuarioFactory.usuario())
//                .build();
//        projetoServico.save(projeto);
//        
//        // executa a operacao a ser testada
//        Tarefa t1 = tarefa("Implementar testas", projeto);
//        Tarefa t2 = tarefa("Rodar testes", projeto);
//        
//        
//        // verifica o efeito da execucao da operacao a ser testada
//        assertThat(projetoServico.tarefasAbertas(projeto)).isEqualTo(2);
//    }
//    
//    public void tarefasFechadas () {
//        // cria o ambiente de teste
//        Projeto projeto = Projeto.builder()
//                .nome("Fechadas")
//                .usuario(usuarioFactory.usuario())
//                .build();
//        projetoServico.save(projeto);
//        
//        // executa a operacao a ser testada
//        Tarefa t1 = tarefa("Implementar testas", projeto);
//        Tarefa t2 = tarefa("Rodar testes", projeto);
//        
//        t1.concluida();
//        t2.concluida();
//        // verifica o efeito da execucao da operacao a ser testada
//        assertThat(projetoServico.tarefasFechadas(projeto)).isEqualTo(2);
//    }
}
   
