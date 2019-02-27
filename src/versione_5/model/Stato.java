package versione_5.model;

public abstract class Stato {
	
	private String output;
	
	public Stato() {
		inizializza_output();
	}
	/**
	 * usare set output all'interno per avere l'output corretto sulla view
	 */
	public abstract void inizializza_output();


	public String get_output() {
		return output;
	}
	public void set_output(String output){
		this.output= output;
	}
	
	public abstract Stato porssimo_stato(String dati_input);
}
