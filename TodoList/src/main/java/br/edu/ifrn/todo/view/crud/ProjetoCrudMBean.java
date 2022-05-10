/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.view.crud;

import br.edu.ifrn.todo.dominio.Projeto;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class ProjetoCrudMBean extends CrudMBean<Projeto, Long> {
        
        @Override
	protected Projeto createBean() {
		return Projeto.builder().build();
	}
}


