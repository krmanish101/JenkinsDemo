package br.edu.ifrn.todo.persistencia;

import br.edu.ifrn.todo.dominio.Intervalo;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class IntervaloFactory {
    public final Date inicio = retornarData(2016, 10, 11);
    public final Date fim = retornarData(2016, 10, 12);

    @Inject
    private IntervaloRepository intervaloRepository;
    
    public Date retornarData(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia);
        return cal.getTime();
    }
    
    public Intervalo intervalo() {
        Intervalo intervalo = Intervalo.builder()
                .horaInicio(inicio)
                .horaFim(fim)
                .build();
        intervaloRepository.save(intervalo);
        return intervalo;
    }
}
