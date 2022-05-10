/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.todo.dominio;

import java.util.Set;
import java.util.TreeSet;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author julia
 */
@Test
public class UsuarioTest {
    
    private String email;
    private String nome;
    private String senha;
    private int numero;
            
    @BeforeMethod
    public void inicializacao(){
        nome = "Luan";
        senha = "luan";
        numero = 2;
    }
    
    private Usuario retornaUsuario(){
        email = "tads@todo.com";
        return Usuario.builder().email(email).build();
    }

    public void emailsIguais() {
        email = "tads@todo.com";
        assertThat(Usuario.builder().email(email).build()).isEqualTo(retornaUsuario());
    }

    public void emailsDiferentes() {
        email = "euvou@todo.com";
        assertThat(Usuario.builder().email(email).build()).isNotEqualTo(retornaUsuario());
    }

    public void emailsIguaisRestoDiferente() {
        email = "tads@todo.com";
        assertThat(Usuario.builder().email(email).nivel(numero).nome(nome)
                .senha(senha).combo(numero).build()).isEqualTo(retornaUsuario());
    }
  
    public void compareTo() {
        email = "euvou@todo.com";
        
        Set<Usuario> usuarios = new TreeSet<>();
        
        Usuario user1 = retornaUsuario();
        Usuario user2 = retornaUsuario();
        user2.setEmail(email);
        
        usuarios.add(user1);
        usuarios.add(user2);
        
        assertThat(usuarios.iterator().next()).isEqualTo(user2);
    }
}