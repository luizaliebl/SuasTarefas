package novoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.ConnectionFactory;

public class UsuarioDAO extends ConnectionFactory{


	public void novoUsuario(Usuario user){
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("insert usuarios(nomeUsuario, emailUsuario, senhaUsuario) values(?, ?, ?)");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getSenha());

			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }
	}

	public void alteraUsuario(Usuario user){
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("update usuarios set nomeUsuario = ?, emailUsuario = ?, senhaUsuario = ? where idUsuarios = ?");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getSenha());
			stmt.setInt(4, user.getIdUser());

			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }
	}

	public void excluiUsuario(int ident){
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("delete from usuarios where idUsuarios = ?");
			stmt.setInt(1, ident);

			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }


	}


}
