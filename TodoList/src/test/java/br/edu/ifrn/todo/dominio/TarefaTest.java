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
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Test
public class TarefaTest {
    
    private Tarefa tarefa; 
    private String nome1;
    private String nome2;
    private Date prazo;
    private Projeto projeto;
    
    @BeforeMethod
    public void inicializacao(){
        nome1 = "Testar classes";
        nome2 = "Rodar testes";
        prazo = retornaPrazo(2016, 5, 21);
        projeto = Projeto.builder().nome(nome1).usuario(Usuario.builder().email(nome1).build()).build();
        tarefa = retornaTarefa(nome1, prazo, projeto);
    }
    
    private Date retornaPrazo(int ano, int mes, int dia){
        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia);
        return cal.getTime();
    }
    
    private Projeto retornaProjeto(String name){
        Usuario user = Usuario.builder().email(name).build();
        return Projeto.builder().nome(name).usuario(user).build();
    }
    
    private Tarefa retornaTarefa(String name, Date prazo, Projeto projeto){
        return (Tarefa)Atividade.builder().nome(name).prazo(prazo).projeto(projeto).build();
    }

    public void nomesPrazosProjetosIguais() {
        assertThat(retornaTarefa(nome1, prazo, projeto)).isEqualTo(tarefa);
    }
    
    public void nomesPrazosIguaisProjetosDiferentes() {
        projeto = retornaProjeto(nome2);
        assertThat(retornaTarefa(nome1, prazo, projeto)).isNotEqualTo(tarefa);
    }
    
    public void nomesIguaisPrazosProjetosDiferentes() {
        prazo = retornaPrazo(2016, 5, 22);
        projeto = retornaProjeto(nome2);
        assertThat(retornaTarefa(nome1, prazo, projeto)).isNotEqualTo(tarefa);
    }
    
    public void nomesPrazosProjetosDiferentes() {
        prazo = retornaPrazo(2016, 5, 22);
        projeto = retornaProjeto(nome2);
        assertThat(retornaTarefa(nome2, prazo, projeto)).isNotEqualTo(tarefa);
    }

    public void compareTo() {
        Set<Tarefa> tarefas = new TreeSet<>();
        
        Tarefa tarefa2 = retornaTarefa(nome2, prazo, projeto);
        Projeto proj = retornaProjeto(nome2);
        Tarefa tarefa3 = retornaTarefa(nome2, prazo, proj);
                
        tarefas.add(tarefa);
        tarefas.add(tarefa2);
        tarefas.add(tarefa3);
        
        assertThat(tarefas.iterator().next()).isEqualTo(tarefa3);
    }
    
}
