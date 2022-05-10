/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.dominio;

import java.util.Set;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author julia
 */
@Test
public class ProjetoTest {
    
    private String nome1;
    private String nome2;
    private String email1;
    private String email2;
    private Usuario usuario;
    
    @BeforeMethod
    public void inicializacao(){
        nome1 = "Compras";
        nome2 = "Estudos";
        email1 = "tads@todo.com";
        email2 = "euvou@todo.com";
    }
    
    private Usuario retornaUsuario(String email){
        return Usuario.builder().email(email).build();
    }
    
    private Projeto retornaProjeto(String name, Usuario user){
        return Projeto.builder().nome(name).usuario(user).build();
    }
    
    public void nomesIguaisUsuariosDiferentes() {
        assertThat(retornaProjeto(nome1, retornaUsuario(email1)))
                .isNotEqualTo(retornaProjeto(nome1, retornaUsuario(email2)));
    }

    public void nomesUsuariosIguais() {
        usuario = retornaUsuario(email1);
        assertThat(retornaProjeto(nome1, usuario)).isEqualTo(retornaProjeto(
                nome1, usuario));
    }
    
    public void nomesDiferentesUsuariosIguais() {
        usuario = retornaUsuario(email1);
        assertThat(retornaProjeto(nome1, usuario)).isNotEqualTo(retornaProjeto(
                nome2, usuario));
    }

    public void nomesUsuariosDiferentes() {
        assertThat(retornaProjeto(nome1, retornaUsuario(email1)))
                .isNotEqualTo(retornaProjeto(nome2, retornaUsuario(email2)));
    }
    
    public void compareTo() {
        Set<Projeto> projetos = new TreeSet<>();
        
        Projeto projeto1 = retornaProjeto(nome2, retornaUsuario(email1));
        Projeto projeto2 = retornaProjeto(nome1, retornaUsuario(email1));
        Projeto projeto3 = retornaProjeto(nome1, retornaUsuario(email2));
        
        projetos.add(projeto1);
        projetos.add(projeto2);
        projetos.add(projeto3);
        
        assertThat(projetos.iterator().next()).isEqualTo(projeto3);
    }
    
}
