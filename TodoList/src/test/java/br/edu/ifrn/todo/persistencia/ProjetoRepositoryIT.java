package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.TodoApplication;
import br.edu.ifrn.todo.dominio.Projeto;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@Test(groups = "projeto", dependsOnGroups = {"usuario"})
public class ProjetoRepositoryIT extends AbstractTestNGSpringContextTests {
    @Inject
    private ProjetoRepository projetoRepository;

    @Inject
    private ProjetoFactory projetoFactory;
    
    @Inject
    private UsuarioFactory usuarioFactory;

    @BeforeMethod
    void deletarTodos() {
        projetoRepository.deleteAll();
        assertThat(projetoRepository.findAll()).isEmpty();
    }

    public void repositorioNaoEhNulo() {
        assertThat(projetoRepository).isNotNull();
    }

    public void deletarUm() {
        // cria o ambiente de teste
        //throw new RuntimeException("Erro no projeto");
        Projeto projeto = projetoFactory.projeto();

        // executa a operacao a ser testada
        projetoRepository.delete(projeto);

        // verifica o efeito da execucao da operacao a ser testada
        assertThat(projetoRepository.findOne(projeto.getId())).isNull();
    }

    public void salvarUm() {
        // cria o ambiente de teste
        Projeto projeto = Projeto.builder()
                .nome("Testes")
                .usuario(usuarioFactory.usuario())
                .build();

        // executa a operacao a ser testada
        projetoRepository.save(projeto);

        // verifica o efeito da execucao da operacao a ser testada
        assertThat(projetoRepository.findAll().iterator().next()).isEqualTo(projeto);
    }
    
}
