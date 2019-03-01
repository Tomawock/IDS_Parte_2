package versione_5.model.stato;

import java.util.ArrayList;

import versione_5.model.Model_context;

public abstract class Stato {
	
	private ArrayList<String> output;
	private Object attore;
	
	public Stato(Object attore) {
		this.attore=attore;
		inizializza_output();
	}
	/**
	 * usare set output all'interno per avere l'output corretto sulla view
	 */
	public abstract void inizializza_output();

	public Object get_attore() {
		return attore;
	}

	public ArrayList<String> get_output() {
		return output;
	}
	
	public void set_output(ArrayList<String> output){
		this.output= output;
	}
	
	public abstract void prossimo_stato(Model_context model,ArrayList<String> view_input);
}
