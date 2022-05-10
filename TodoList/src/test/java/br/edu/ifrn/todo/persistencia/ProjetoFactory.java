package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Projeto;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ProjetoFactory {
    public final static String PROJETO = "Testes";
    
    @Inject
    private UsuarioFactory usuarioFactory;

    @Inject
    private ProjetoRepository projetoRepository;

    public Projeto projeto() {
        Projeto projeto = Projeto.builder()
                .nome(PROJETO)
                .usuario(usuarioFactory.usuario())
                .build();
        projetoRepository.save(projeto);
        return projeto;
    } 
    
    public Projeto projeto(String nome) {
        Projeto projeto = Projeto.builder()
                .nome(nome)
                .usuario(usuarioFactory.usuario())
                .build();
        projetoRepository.save(projeto);
        return projeto;
    } 
}
