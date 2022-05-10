package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.TodoApplication;
import br.edu.ifrn.todo.dominio.Intervalo;
import java.time.LocalTime;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@Test(groups = "intervalo", dependsOnGroups = {"atividade"})
public class IntervaloRepositoryIT extends AbstractTestNGSpringContextTests {
    @Inject
    private IntervaloRepository intervaloRepository;
    
    @Inject
    private IntervaloFactory intervaloFactory;
    
    @BeforeMethod
    void deletarTodos()
    {
        intervaloRepository.deleteAll();
        assertThat(intervaloRepository.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(intervaloRepository).isNotNull();
    }
    
    public void deletarUm () {
        // cria o ambiente de teste
        Intervalo intervalo = intervaloFactory.intervalo();
        
        // executa a operacao a ser testada
        intervaloRepository.delete(intervalo);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(intervaloRepository.findOne(intervalo.getId())).isNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Intervalo intervalo = Intervalo.builder()
                .horaInicio(intervaloFactory.retornarData(2016, 9, 9))
                .horaFim(intervaloFactory.retornarData(2016, 10, 10))
                .build();
        
        // executa a operacao a ser testada
        intervaloRepository.save(intervalo);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(intervaloRepository.findAll().iterator().next().getId()).isEqualTo(intervalo.getId());
    }
}
