package br.edu.ifrn.todo.servico;

import br.edu.ifrn.todo.dominio.Intervalo;
import br.edu.ifrn.todo.persistencia.IntervaloRepository;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

@Named
public class IntervaloServico  extends AbstratoServico<Intervalo, Long> {
    private IntervaloRepository intervaloRepository;
    
    @Inject
    public IntervaloServico(IntervaloRepository lancamentoRepository) {
        super();
        this.intervaloRepository = lancamentoRepository;
    }
    
    @Override
    @Transactional
    public Intervalo save(Intervalo objeto) {
        objeto.verificarAtributos();
        return super.save(objeto);
    }
}