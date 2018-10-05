package novoUsuario;

import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;


public class NovoUsuarioController{

	@FXML
	private TextField emailNewUser;
	@FXML
	private TextField nameNewUser;
	@FXML
	private PasswordField passNewUser;
	@FXML
	private Label alertNewUser;


	public void saveNewUser(Event newUser){
		Usuario user = new Usuario();
		UsuarioDAO newUserDAO = new UsuarioDAO();



		if (nameNewUser.getText().isEmpty()) {
			alertNewUser.setText("Campo nome vazio!");

		} else if (emailNewUser.getText().isEmpty()){
			alertNewUser.setText("Campo email vazio!");

		} else if (passNewUser.getText().isEmpty()){
			alertNewUser.setText("Campo senha vazio!");
		} else {

			String nome = nameNewUser.getText();
			String email = emailNewUser.getText();
			String senha = passNewUser.getText();

			user.setName(nome);
			user.setEmail(email);
			user.setSenha(senha);

			newUserDAO.novoUsuario(user);

			try {

				LoginController.openInicial();
	            Stage primaryStage = (Stage)((Node)newUser.getSource()).getScene().getWindow();
	            primaryStage.hide();
	        }catch (Exception e){
	        	alertNewUser.setText(e.getMessage());
	        }
		}

	}

	public void backIni(Event backIni){
		try {
			LoginController.openInicial();
            Stage primaryStage = (Stage)((Node)backIni.getSource()).getScene().getWindow();
            primaryStage.hide();
        }catch (Exception e){
        	alertNewUser.setText(e.getMessage());
        }
	}

	public static void openNewUser() throws IOException{

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(Objects.requireNonNull(NovoUsuarioController.class.getClassLoader().getResource("novoUsuario/novoUsuario.fxml")));

		primaryStage.setTitle("Minhas tarefas");

		primaryStage.setScene(new Scene(root));

		primaryStage.show();
	}
}