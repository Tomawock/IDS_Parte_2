package versione_5.gestione_comunicazione_model_controller;

import java.util.ArrayList;

import versione_5.gestione_comunicazione_model_controller.stato.Stato;
import versione_5.model.Archivio;
import versione_5.model.Database_file;
import versione_5.model.Query;
import versione_5.model.Salvataggio;

public class Model_context {
	
	private Stato stato_attuale;
	private Salvataggio db;
	private Salvataggio archivio;
	private Query query;
	
	/**
	 * Crea il model
	 */
	public Model_context() {
		db= new Database_file();
		archivio= new Archivio();
		query = new Query(archivio);
	}
	
	public Salvataggio get_database_file() {
		return db;
	}
	
	public Salvataggio get_archivio() {
		return archivio;
	}
	
	public Stato get_stato_attuale() {
		return stato_attuale;
	}

	public void set_stato_attuale(Stato stato_attuale) {
		this.stato_attuale = stato_attuale;
	}
	
	/**
	 * Funzione che consente il passaggio allo stato successivo
	 * @param dati_input	dati che la logica prende dalla parte grafica
	 */
	public void prossimo_stato(ArrayList<String> dati_input) {
		stato_attuale.prossimo_stato(this,dati_input);
	}

	public Query get_query() {
		return query;
	}
}
