/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.view.options;

import br.edu.ifrn.todo.dominio.Projeto;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class ProjetoOptions extends Options<Projeto, Long> {

	@Override
	public String label(Projeto e) {
		return e.getNome();
	}

	@Override
	protected Object key(Projeto e) {
		return e.getId();
	}

}
