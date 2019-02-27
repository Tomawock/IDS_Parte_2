package versione_5.controller;

import java.util.Observable;
import java.util.Observer;

import versione_5.model.Stato;
import versione_5.view.View_ms;

public class Controller_ms implements Observer {

	private Stato stato_attuale;
	private View_ms view;
	
	public Controller_ms(Stato stato_attuale, View_ms view) {
		this.stato_attuale= stato_attuale;
		this.view=view;
		view.addObserver(this);
	}
	
	public void start() {
		view.set_output(stato_attuale.get_output());
		view.aggiorna();
	}

	@Override
	public void update(Observable o, Object arg) {
		String view_input=view.get_input();
		Stato stato_futuro=stato_attuale.porssimo_stato(view_input);
		view.set_output(stato_futuro.get_output());
		view.aggiorna();
	}
	
}
