package versione_5.gestione_comunicazione_model_controller.stato;

import java.util.ArrayList;

import versione_5.gestione_comunicazione_model_controller.Model_context;

public abstract class Stato {
	
	private ArrayList<String> output;
	private Object attore;
	
	/**
	 * Crea un nuovo Stato ed inizilizza l'output
	 * @param attore 		attributo su cui si svolgono le operazioni durante lo stato
	 */
	public Stato(Object attore) {
		this.attore=attore;
		inizializza_output();
	}
	
	/**
	 * funzione che setterà nell'attributo output i dati relativi allo stato
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
	
	/**
	 * Funzione che consente il passaggio allo stato successivo
	 * @param model 		context del pattern state.
	 * @param dati_input	dati che la logica prende dalla parte grafica
	 */
	public abstract void prossimo_stato(Model_context model,ArrayList<String> dati_input);
}
