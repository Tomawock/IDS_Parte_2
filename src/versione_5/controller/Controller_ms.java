package versione_5.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import versione_5.model.Model_context;
import versione_5.model.stato.Stato;
import versione_5.model.stato.Stato_iniziale;
import versione_5.model.stato.Stato_terminato;
import versione_5.view.View_ms;

public class Controller_ms implements Observer {

	private Model_context model;
	private View_ms view;
	
	public Controller_ms(Model_context model, View_ms view) {
		this.model= model;
		this.view=view;
		view.addObserver(this);
	}
	
	public void start() {
		model.set_stato_attuale(new Stato_iniziale(null));
		view.set_output(model.get_stato_attuale().get_output());
		view.aggiorna();
	}

	@Override
	public void update(Observable o, Object arg) {
		ArrayList<String> view_input=view.get_input();		
		model.prossimo_stato(view_input);
		view.set_output(model.get_stato_attuale().get_output());
		//TODO
		if(!model.get_stato_attuale().getClass().getSimpleName().equals(Stato_terminato.class.getSimpleName()))
			view.aggiorna();
	}
	
}
