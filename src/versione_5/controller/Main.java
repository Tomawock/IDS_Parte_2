package versione_5.controller;

import utilita.Emulazione_Server;
import versione_5.gestione_comunicazione_model_controller.Model_context;
import versione_5.gestione_comunicazione_model_controller.stato.Stato;
import versione_5.gestione_comunicazione_model_controller.stato.Stato_iniziale;
import versione_5.view.View_ms;

public class Main {

	public static void main(String[] args) {
		Emulazione_Server es= new Emulazione_Server();
		es.inizializza();
		View_ms view = new View_ms();
		Model_context model= new Model_context();
		Controller_ms controller= new Controller_ms(model, view);
		controller.start();
	}
}
