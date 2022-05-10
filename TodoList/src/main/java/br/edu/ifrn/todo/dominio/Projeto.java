/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

/**
 *
 * @author italo
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Builder
@ToString
@Entity
@SequenceGenerator(sequenceName = "seq_projeto", name = "ID_SEQUENCE", allocationSize = 1)
public class Projeto implements Serializable, Comparable<Projeto> {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuarioId",  nullable = false, foreignKey = @ForeignKey(name = "fk_projeto_usuario"))
    private Usuario usuario;
    
//    @OneToMany(mappedBy = "projeto")
//    private Set<Atividade> atividades;

//    @OneToMany(mappedBy = "projeto")
//    private Set<Tarefa> tarefas;
    
    private String nome;

    @Override
    public int compareTo(Projeto o) {
        int result = nome.compareTo(o.nome);
        if (result ==0)
        {
            result = usuario.compareTo(o.usuario);
        }
        return result;
    }
    
    public void verificarAtributos() {
//        if (tarefas instanceof Atividade ) {
//            throw new IllegalArgumentException("Tarefas não podem ser atividades: " + tarefas);     
//        }    
    }
    
}
