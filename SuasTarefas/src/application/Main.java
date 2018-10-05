package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{


	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception{
		//Carregar a tela
		Parent root = FXMLLoader.load(getClass()
				.getClassLoader()
				.getResource("login/login.fxml"));

		//Atribui o título da janela
		primaryStage.setTitle("Minhas tarefas");

		//Atribui o tamanho da janela
		primaryStage.setScene(new Scene(root));

		//Exibe a janela
		primaryStage.show();
	}

}
