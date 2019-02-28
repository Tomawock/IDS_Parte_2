package versione_5.view;

import java.util.ArrayList;
import java.util.Observable;

import utilita.IO;

public class View_ms extends Observable{

	private ArrayList<String> input;
	private ArrayList<String> output;
	
	public View_ms(){
		input= new ArrayList<>();
		output = new ArrayList<>();
	}
	
	public ArrayList<String> get_input() {	
		return input;
	}
	
	public void set_output(ArrayList<String> get_output) {
		this.output= get_output;
		input.clear();
	}
	
	public void aggiorna() {
		for(int i=0; i< this.output.size();i++) {
			System.out.println(this.output.get(i));
			this.input.add(IO.insertString());
		}
		setChanged();
		notifyObservers();
	}
	
}
