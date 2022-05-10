package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.TodoApplication;
import br.edu.ifrn.todo.dominio.Atividade;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@Test(groups = "atividade", dependsOnGroups = {"usuario", "projeto"})
public class AtividadeRepositoryIT extends AbstractTestNGSpringContextTests {
    @Inject
    private AtividadeRepository atividadeRepository;
    
    @Inject
    private AtividadeFactory atividadeFactory;
    
    @Inject
    private ProjetoFactory projetoFactory;
    
    @Inject
    private IntervaloFactory intervaloFactory;
    
    @BeforeMethod
    void deletarTodos()
    {
        atividadeRepository.deleteAll();
        assertThat(atividadeRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(atividadeRepository).isNotNull();
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Atividade atividade = atividadeFactory.atividade();
        
        // executa a operacao a ser testada
        atividadeRepository.delete(atividade);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(atividadeRepository.findOne(atividade.getId())).isNull();
    }
    
    public void salvarUm () {
        //verifica se não tem nenhuma atividade
        assertThat(atividadeRepository.findAll()).hasSize(0);
        
        // cria o ambiente de teste
        Atividade atividade = Atividade.builder()
                .nome("Testar salvar um")
                .projeto(projetoFactory.projeto())
                .intervalo(intervaloFactory.intervalo())
                .build();
        
        // executa a operacao a ser testada
        atividadeRepository.save(atividade);
        
        // verifica se a atividade foi adicionada
        //assertThat(atividadeRepository.findAll()).hasSize(1); 
        //assertThat(atividadeRepository.findAll()).contains(atividade);
        assertThat(atividadeRepository.findAll().iterator().next().getId()).isEqualTo(atividade.getId());
    }
}
