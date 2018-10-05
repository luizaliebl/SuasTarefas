package configConta;

import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginController;
import novoUsuario.NovoUsuarioController;
import novoUsuario.Usuario;
import novoUsuario.UsuarioDAO;
import tarefas.TarefasController;
import tarefas.TarefasDAO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import application.Sessao;


public class ConfigContaController implements Initializable{

	@FXML
	private TextField nameUserAlt;
	@FXML
	private TextField emailUserAlt;
	@FXML
	private PasswordField passAtual;
	@FXML
	private PasswordField newPass;
	@FXML
	private Label erroConfig;



	public static void openConfigConta() throws IOException{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(Objects.requireNonNull(NovoUsuarioController.class.getClassLoader().getResource("configConta/configConta.fxml")));

		primaryStage.setTitle("Minhas tarefas");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}


	public void backTask(Event backTask){
		try {
			TarefasController.openTarefas();
            Stage primaryStage = (Stage)((Node)backTask.getSource()).getScene().getWindow();
            primaryStage.hide();
        }catch (Exception e){
        	System.out.print(e.getMessage());
        }
	}

	public void deleteConta(Event deleteConta){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		TarefasDAO tarefasDAO = new TarefasDAO();

		tarefasDAO.excluiTarefaUsuario(Sessao.getInstance().getUsuarioAtual().getIdUser());
		usuarioDAO.excluiUsuario(Sessao.getInstance().getUsuarioAtual().getIdUser());

		try {
			LoginController.openInicial();
            Stage primaryStage = (Stage)((Node)deleteConta.getSource()).getScene().getWindow();
            primaryStage.hide();
        }catch (Exception e){
        	System.out.print(e.getMessage());
        }
	}


	public void sairConta(Event sairConta){
		try {
			LoginController.openInicial();
            Stage primaryStage = (Stage)((Node)sairConta.getSource()).getScene().getWindow();
            primaryStage.hide();
        }catch (Exception e){
        	System.out.print(e.getMessage());
        }
	}

	public void atualizaUsuario(Event atualizaUsuario){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario user = new Usuario();

		if(nameUserAlt.getText().isEmpty()){
			erroConfig.setText("Nome vazio!");
		} else if (emailUserAlt.getText().isEmpty()){
			erroConfig.setText("Email vazio!");
		} else if(passAtual.getText().isEmpty()){
			erroConfig.setText("Senha atual vazio!");
		} else if(newPass.getText().isEmpty()){
			erroConfig.setText("Nova atual vazio!");
		} else{
			user.setIdUser(Sessao.getInstance().getUsuarioAtual().getIdUser());
			user.setName(nameUserAlt.getText());
			user.setEmail(emailUserAlt.getText());
			user.setSenha(newPass.getText());

			usuarioDAO.alteraUsuario(user);

			Sessao.getInstance().setUsuarioAtual(user);

			try {
				TarefasController.openTarefas();
	            Stage primaryStage = (Stage)((Node)atualizaUsuario.getSource()).getScene().getWindow();
	            primaryStage.hide();
	        }catch (Exception e){
	        	System.out.print(e.getMessage());
	        }
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameUserAlt.setText(Sessao.getInstance().getUsuarioAtual().getName());
		emailUserAlt.setText(Sessao.getInstance().getUsuarioAtual().getEmail());

	}


}