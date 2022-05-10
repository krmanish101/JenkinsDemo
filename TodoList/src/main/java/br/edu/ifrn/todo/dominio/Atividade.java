/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.dominio;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author italo
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, of = {"intervalo"})
@Entity
public class Atividade extends Tarefa{
    
    @OneToOne
    private Intervalo intervalo;
  
    @Builder
    public Atividade(Long id, Date prazo, Date dataInicial, Projeto projeto, String nome, int prioridade, boolean concluida, Intervalo intervalo) {
        super(id, prazo, dataInicial, projeto, nome, prioridade, concluida);
        this.intervalo = intervalo;
    } 
    
}
