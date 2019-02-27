package versione_5.view;

import java.util.Observable;

import utilita.IO;

public class View_ms extends Observable{

	private String input;
	private String output;
	
	
	public String get_input() {	
		return input;
	}
	
	public void set_output(String get_output) {
		this.output= get_output;
	}
	
	public void aggiorna() {
		System.out.println(this.output);
		this.input=IO.insertString();
		
		setChanged();
		notifyObservers();
	}
	
}
