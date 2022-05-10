package br.edu.ifrn.todo.servico;

import br.edu.ifrn.todo.TodoApplication;
import br.edu.ifrn.todo.dominio.Intervalo;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@Test(groups = "intervalo")
public class IntervaloServicoIT extends AbstractTestNGSpringContextTests {
    
    public final Date inicio = retornarData(2016, 10, 11);
    public final Date fim = retornarData(2016, 10, 12);
    
    @Inject
    private IntervaloServico intervaloServico;
    
    private Date retornarData(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia);
        return cal.getTime();
    }
    
    @BeforeMethod
    void deletarTodos()
    {
        intervaloServico.deleteAll();
        assertThat(intervaloServico.findAll()).isEmpty();
    }
    
    public void repositorioNaoEhNulo () {
        assertThat(intervaloServico).isNotNull();
    }
    
    public void salvarUm () {
        // cria o ambiente de teste
        Intervalo intervalo = Intervalo.builder()
                .horaInicio(inicio)
                .horaFim(fim)
                .build();
        
        // executa a operacao a ser testada
        intervaloServico.save(intervalo);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(intervaloServico.findAll().iterator().next().getId()).isEqualTo(intervalo.getId());
    }
   
    public void deletarUm () {
        // cria o ambiente de teste
        Intervalo intervalo = Intervalo.builder()
                .horaInicio(inicio)
                .horaFim(fim)
                .build();
        
        intervaloServico.save(intervalo);
        
        // executa a operacao a ser testada
        intervaloServico.delete(intervalo);
        
        // verifica o efeito da execucao da operacao a ser testada
        assertThat(intervaloServico.findAll().iterator().hasNext()).isFalse();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void horaFinalIgualHoraInicial () {
        // cria o ambiente de teste
        Intervalo intervalo = Intervalo.builder()
                .horaInicio(inicio)
                .horaFim(inicio)
                .build();
        
        intervaloServico.save(intervalo);
    }    
}
