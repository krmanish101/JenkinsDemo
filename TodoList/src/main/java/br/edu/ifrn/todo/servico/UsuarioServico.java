package br.edu.ifrn.todo.servico;

import br.edu.ifrn.todo.dominio.Usuario;
import br.edu.ifrn.todo.persistencia.UsuarioRepository;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UsuarioServico  extends AbstratoServico<Usuario, Long> {
    private UsuarioRepository usuarioRepository;
    
    @Inject
    public UsuarioServico(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }
    
    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
}
