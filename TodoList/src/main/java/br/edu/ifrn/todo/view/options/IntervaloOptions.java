package br.edu.ifrn.todo.view.options;

import br.edu.ifrn.todo.dominio.Intervalo;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class IntervaloOptions extends Options<Intervalo, Long> {

	@Override
	public String label(Intervalo e) {
                return e.descricao();
	}

	@Override
	protected Object key(Intervalo e) {
		return e.getId();
	}

}