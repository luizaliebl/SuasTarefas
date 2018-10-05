package tarefas;

import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import novoUsuario.NovoUsuarioController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import application.Sessao;
import configConta.ConfigContaController;


public class TarefasController implements Initializable{

	@FXML
	private TextField novaTarefa;
	@FXML
	private Label error;
	@FXML
	private Label tituloTarefa;
	@FXML
	private ListView<Tarefa> listaTarefas;

	private Tarefa tarefaEditar;

	final ObservableList<Tarefa> tarefas = FXCollections.observableArrayList();


	public static void openTarefas() throws IOException{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(Objects.requireNonNull(NovoUsuarioController.class.getClassLoader().getResource("tarefas/tarefas.fxml")));

		primaryStage.setTitle("Minhas tarefas");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}


	public void salvaTarefa(Event salvaTarefa){
		error.setText("");
		TarefasDAO tarefasDAO = new TarefasDAO();

		if(tarefaEditar != null){
			tarefaEditar.setTarefa(novaTarefa.getText());

			tarefasDAO.alteraTarefa(tarefaEditar);

			tarefaEditar = null;
		} else{

			Tarefa task = new Tarefa();

			if (novaTarefa.getText().isEmpty()){
				error.setText("Texto inválido!");
			} else {
				String tarefa = novaTarefa.getText();

				task.setTarefa(tarefa);
				task.setUsuario(Sessao.getInstance().getUsuarioAtual().getIdUser());

				tarefasDAO.novaTarefa(task);
			}
		}
		novaTarefa.setText("");
		populaLista();
	}


	public void configConta(Event configConta){
		try {
			ConfigContaController.openConfigConta();
            Stage primaryStage = (Stage)((Node)configConta.getSource()).getScene().getWindow();
            primaryStage.hide();
        }catch (Exception e){
        	System.out.print(e.getMessage());
        }
	}


	public void excluiTarefa(Event excluiTarefa){
		TarefasDAO tarefasDAO = new TarefasDAO();
		ArrayList<Tarefa> task = new ArrayList<Tarefa>();

		task = retornaTarefas();

		tarefasDAO.excluiTarefa(task.get(listaTarefas.getSelectionModel().getSelectedIndex()).getIdTarefa());

		populaLista();
	}


	public void alteraTarefa(Event alteraTarefa){
		tarefaEditar = tarefas.get(listaTarefas.getSelectionModel().getSelectedIndex());
		novaTarefa.setText(tarefaEditar.getTarefa());

		populaLista();
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tituloTarefa.setText("SUAS TAREFAS " + Sessao.getInstance().getUsuarioAtual().getName().toUpperCase());
		populaLista();
	}


	private void populaLista(){
		tarefas.clear();
		tarefas.addAll(retornaTarefas());
		listaTarefas.setItems(tarefas);
	}


	private ArrayList<Tarefa> retornaTarefas(){
		TarefasDAO tarefasDAO = new TarefasDAO();

		ArrayList<Tarefa> task = new ArrayList<Tarefa>();
		task = tarefasDAO.getTarefas(Sessao.getInstance().getUsuarioAtual().getIdUser());

		return task;
	}
}