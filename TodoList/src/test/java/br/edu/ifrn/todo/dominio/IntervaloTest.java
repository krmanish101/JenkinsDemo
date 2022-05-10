/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author julia
 */
@Test
public class IntervaloTest {
    
    private Intervalo int1;
    private Intervalo int2;
    private Date inicio1;
    private Date inicio2;
    private Date fim1;
    private Date fim2;
    
    @BeforeMethod
    public void inicializacao(){
        inicio1 = retornarData(2016, 10, 10);
        inicio2 = retornarData(2016, 10, 11);
        fim1 = retornarData(2016, 11, 10);
        fim2 = retornarData(2016, 11, 11);
        int1 = Intervalo.builder().horaInicio(inicio1).horaFim(fim1).build();
        int2 = Intervalo.builder().horaInicio(inicio2).horaFim(fim2).build();
    }
    
    private Date retornarData(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia);
        return cal.getTime();
    }
    
    public void inicioFimIguais() {
        assertThat(Intervalo.builder().horaInicio(inicio1).horaFim(fim1).build())
                .isEqualTo(int1);
    }
    
    public void inicioIgualFimDiferente() {
        assertThat(Intervalo.builder().horaInicio(inicio1).horaFim(fim2).build())
                .isNotEqualTo(int1);
    }

    public void inicioDiferenteFimIgual() {
        assertThat(Intervalo.builder().horaInicio(inicio2).horaFim(fim1).build())
                .isNotEqualTo(int1);
    }

    public void inicioFimDiferentes() {
        assertThat(Intervalo.builder().horaInicio(inicio2).horaFim(fim2).build())
                .isNotEqualTo(int1);
    }

    public void compareTo() {
        Set<Intervalo> intervalos = new TreeSet<>();
        
        intervalos.add(int2);
        intervalos.add(int1);
        
        assertThat(intervalos.iterator().next()).isEqualTo(int1);
    }
    
}
