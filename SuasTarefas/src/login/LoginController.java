package login;

//import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import novoUsuario.NovoUsuarioController;
import tarefas.TarefasController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.util.Objects;

//import javafx.stage.Stage;
import javafx.event.Event;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;

public class LoginController{

	@FXML
	public TextField campoTexto;
	@FXML
	public TextField password;
	@FXML
	public Label errorLogin;



	//Função para validar campos de login e atribuir usuário para a execução
	public void clickLogin(Event clickLogin){
		LoginDAO loginDAO = new LoginDAO();
		boolean log = false;

		if (campoTexto.getText().isEmpty()) {
			errorLogin.setText("Campo de usuário vazio!");
		} else if (password.getText().isEmpty()){
			errorLogin.setText("Campo de senha vazio!");
		} else{

			String usuario = campoTexto.getText();
			String senha = password.getText();

			try {
				log = loginDAO.Login(usuario, senha);

			} catch (Exception e) {
				System.out.print(e.getMessage());
				errorLogin.setText(e.getMessage());
			}

			if (!log){
				errorLogin.setText("Usuário não autenticado!");
			} else {
				try {
					TarefasController.openTarefas();
		            Stage primaryStage = (Stage)((Node)clickLogin.getSource()).getScene().getWindow();
		            primaryStage.hide();
		        }catch (Exception e){
		        	System.out.print(e.getMessage());
		        	errorLogin.setText(e.getMessage());
		        }
			}
		}
	}


	//Função para abrir a tela inicial
	public static void openInicial() throws Exception{

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getClassLoader().getResource("login/login.fxml")));

		primaryStage.setTitle("Minhas tarefas");

		primaryStage.setScene(new Scene(root));

		primaryStage.show();
	}


	//Função para abrir a tela de novo usuário
	public void clickNewUser(Event clickNewUser){
		try {
			NovoUsuarioController.openNewUser();
            Stage primaryStage = (Stage)((Node)clickNewUser.getSource()).getScene().getWindow();
            primaryStage.hide();
        }catch (Exception e){
        	errorLogin.setText(e.getMessage());
        }

	}

}