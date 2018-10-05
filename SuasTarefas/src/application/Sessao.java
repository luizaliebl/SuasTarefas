package application;

import novoUsuario.Usuario;

public class Sessao {

	private Usuario usuarioAtual;

    private final static Sessao sessao = new Sessao();

    private Sessao(){

    }

    public static Sessao getInstance(){
        return sessao;
    }

    public boolean isUsuarioLogado(){
        return usuarioAtual != null;
    }

    public void setUsuarioAtual(Usuario usuario){
        usuarioAtual = usuario;
    }

    public void removerUsuarioAtual(){
        usuarioAtual = null;
    }

    public Usuario getUsuarioAtual(){
        return  usuarioAtual;
    }




}
