package br.edu.ifrn.todo.view.options;

import br.edu.ifrn.todo.dominio.Usuario;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class UsuarioOptions extends Options<Usuario, Long> {

	@Override
	public String label(Usuario e) {
		return e.getNome();
	}

	@Override
	protected Object key(Usuario e) {
		return e.getId();
	}

}
