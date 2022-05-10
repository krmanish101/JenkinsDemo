package br.edu.ifrn.todo.servico;

import br.edu.ifrn.todo.dominio.Projeto;
import br.edu.ifrn.todo.persistencia.ProjetoRepository;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

@Named
public class ProjetoServico extends AbstratoServico<Projeto, Long> {
    private ProjetoRepository projetoRepository;
    
    @Inject
    public ProjetoServico(ProjetoRepository projetoRepository) {
        super();
        this.projetoRepository = projetoRepository;
    }
    
    @Override
    @Transactional
    public Projeto save(Projeto objeto) {
        objeto.verificarAtributos();
        return super.save(objeto);
    }
    
}
