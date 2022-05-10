/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.dominio;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author julia
 */
@Test
public class AtividadeTest {
    
    private Atividade atividade; 
    private String nome1;
    private String nome2;
    private Date prazo;
    private Projeto projeto;
    private Date inicio;
    private Date fim;
    private Intervalo intervalo;
    
    @BeforeMethod
    public void inicializacao(){
        nome1 = "Testar classes";
        nome2 = "Rodar testes";
        prazo = retornaData(2016, 5, 21);
        projeto = retornaProjeto(nome1);
        intervalo = Intervalo.builder().horaInicio(inicio).horaFim(fim).build();
        atividade = retornaAtividade(nome1, prazo, projeto, intervalo);
        inicio = retornaData(2016, 10, 10);
        fim = retornaData(2016, 10, 16); 
        
    }
    
    private Intervalo retornaIntervalo(Date inicio, Date fim){        
        return Intervalo.builder().horaInicio(inicio).horaFim(fim).build();
    }
    
    private Date retornaData(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia);
        return cal.getTime();
    }
    
    private Projeto retornaProjeto(String name){        
        Usuario user = Usuario.builder().email(name).build();
        return Projeto.builder().nome(name).usuario(user).build();
    }
    
    private Atividade retornaAtividade(String name, Date prazo, Projeto projeto, Intervalo inter){
        return Atividade.builder().nome(name).prazo(prazo).projeto(projeto)
                .intervalo(inter).build();
    }

    public void nomesPrazosProjetosIntervalosIguais() {
        assertThat(retornaAtividade(nome1, prazo, projeto, intervalo)).isEqualTo(atividade);
    }
    
    public void nomesPrazosProjetosIguaisIntervalosDiferentes() {
        intervalo = retornaIntervalo(inicio, retornaData(2016, 12, 12));
        assertThat(retornaAtividade(nome1, prazo, projeto, intervalo)).isNotEqualTo(atividade);
    }
    
    public void nomesPrazosIguaisProjetosIntervalosDiferentes() {
        intervalo = retornaIntervalo(inicio, retornaData(2016, 12, 12));
        projeto = retornaProjeto(nome2);
        assertThat(retornaAtividade(nome1, prazo, projeto, intervalo)).isNotEqualTo(atividade);
    }
    
    public void nomesIguaisPrazosProjetosIntervalosDiferentes() {
        intervalo = retornaIntervalo(inicio, retornaData(2016, 12, 12));
        projeto = retornaProjeto(nome2);
        prazo = retornaData(2016, 5, 22);
        assertThat(retornaAtividade(nome1, prazo, projeto, intervalo)).isNotEqualTo(atividade);
    }
    
    public void nomesPrazosProjetosIntervalosDiferentes() {
        intervalo = retornaIntervalo(inicio, retornaData(2016, 12, 12));
        projeto = retornaProjeto(nome2);
        prazo = retornaData(2016, 5, 22);
        assertThat(retornaAtividade(nome2, prazo, projeto, intervalo)).isNotEqualTo(atividade);
    }

    public void compareTo() {
        Set<Atividade> atividades = new TreeSet<>();
        
        Atividade atividade2 = retornaAtividade(nome2, prazo, projeto, intervalo);
        prazo = retornaData(2016, 5, 2);
        Atividade atividade3 = retornaAtividade(nome2, prazo, projeto, intervalo);
        projeto = retornaProjeto(nome2);
        Atividade atividade4 = retornaAtividade(nome2, prazo, projeto, intervalo);
                
        //atividades.add(atividade);
        atividades.add(atividade2);
        atividades.add(atividade3);
        atividades.add(atividade4);
        
        assertThat(atividades.iterator().next()).isEqualTo(atividade4);
    }
}
