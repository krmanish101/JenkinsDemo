package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Atividade;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AtividadeFactory {
    public final static String ATIVIDADE = "Terminar testes";
    public Date prazo = retornaPrazo(2016, 07, 07);

    @Inject
    private ProjetoFactory projetoFactory;

    @Inject
    private IntervaloFactory intervaloFactory;

    @Inject
    private AtividadeRepository atividadeRepository;
    
    public Date retornaPrazo(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia, 0, 0, 0);
        return cal.getTime();
    }
    
    public Atividade atividade() {
        Atividade atividade = Atividade.builder()
                .nome(ATIVIDADE)
                .prazo(prazo)
                .projeto(projetoFactory.projeto())
                .intervalo(intervaloFactory.intervalo())
                .build();
        atividadeRepository.save(atividade);
        return atividade;
    }
}
