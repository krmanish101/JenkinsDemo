/*
*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "dataInicial", "prioridade", "concluida"})
//@Data => Substitui os 4 acima, mas dá erro com getData
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
@Entity
@SequenceGenerator(sequenceName = "seq_tarefa", name = "ID_SEQUENCE", allocationSize = 1)
public class Tarefa implements Serializable, Comparable<Tarefa> {
   
   private static final long serialVersionUID = 1L;
    
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
   private Long id;
   
   @Temporal(TemporalType.TIMESTAMP)
   private Date prazo;
   
   
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataInicial;
   
   @ManyToOne
   @JoinColumn(name = "projetoId", nullable = false, foreignKey = @ForeignKey(name = "fk_tarefa_projeto"))
   private Projeto projeto;
   
   private String nome;
   
   private int prioridade;
   private boolean concluida = false;  

    @Override
    public int compareTo(Tarefa o) {
        int result = nome.compareTo(o.nome);
        if (result ==0)
        {
            result = projeto.compareTo(o.projeto);
        }
        return result;
    }
    
    public void concluir(){
        this.concluida = true;
    }
    
}
