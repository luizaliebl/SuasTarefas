package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	String conexao = "jdbc:mysql://localhost:3306/tasks?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
	String userBanco = "root";
	String senhaBanco = "univille";

	 public Connection getConnection() {
	        try {
	            return DriverManager
	                    .getConnection(conexao, userBanco, senhaBanco);

	        }catch (Exception e) {
	        	e.printStackTrace();
	            throw new RuntimeException();
	        }
	    }
}
