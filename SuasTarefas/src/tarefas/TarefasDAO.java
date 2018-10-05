package tarefas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.ConnectionFactory;

public class TarefasDAO extends ConnectionFactory{


	public void novaTarefa(Tarefa tarefa){
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("insert tarefas(descricao, usuario) values(?, ?)");
			stmt.setString(1, tarefa.getTarefa());
			stmt.setInt(2, tarefa.getUsuario());

			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }
	}

	public void alteraTarefa(Tarefa tarefa){
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("update tarefas set descricao = ? where idTarefas = ?");
			stmt.setString(1, tarefa.getTarefa());
			stmt.setInt(2, tarefa.getIdTarefa());

			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }
	}

	public ArrayList<Tarefa> getTarefas(int usuario){
		ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("select idTarefas, descricao, usuario from tarefas where usuario = ?");
			stmt.setInt(1, usuario);

			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next()){
				Tarefa task = new Tarefa();

				task.setIdTarefa(resultSet.getInt(1));
				task.setTarefa(resultSet.getString(2));
				task.setUsuario(resultSet.getInt(3));//?

				tarefas.add(task);

			}


		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }

		return tarefas;
	}


	public void excluiTarefa(int tarefa){
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("delete from tarefas where idTarefas = ?");
			stmt.setInt(1, tarefa);

			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }
	}

	public void excluiTarefaUsuario(int ident){
		try{

			Connection connection = getConnection();

			PreparedStatement stmt = connection.prepareStatement("delete from tarefas where usuario = ?");
			stmt.setInt(1, ident);

			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
            throw new RuntimeException();
        }
	}



}
