package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.ConnectionFactory;
import application.Sessao;
import novoUsuario.Usuario;

public class LoginDAO extends ConnectionFactory{

	public boolean Login(String email, String senha) throws Exception{
		boolean retorno = false;
		Usuario usuario = null;
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("select idUsuarios, nomeUsuario, emailUsuario, senhaUsuario from usuarios where emailUsuario like ?");

			stmt.setString(1, email);

			ResultSet resultSet = stmt.executeQuery();

			if(resultSet.next()){

                usuario= new Usuario();

                usuario.setIdUser(resultSet.getInt(1));
                usuario.setName(resultSet.getString(2));
                usuario.setEmail(resultSet.getString(3));
                usuario.setSenha(resultSet.getString(4));

                Sessao.getInstance().setUsuarioAtual(usuario);

            }

		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }

		if(usuario == null){
            retorno = false;
        } else if (usuario.getSenha().equals(senha)){
        	retorno = true;
        }

        return retorno;
	}
}
