package versione_5.model;

import java.util.ArrayList;

public class Model_context {
	
	private Stato stato_attuale;
	private Database_file db;
	private Archivio archivio;
	private Query query;
	
	public Model_context() {
		db= new Database_file();
		archivio= new Archivio();
		query = new Query(archivio);
	}
	
	public Database_file get_database_file() {
		return db;
	}
	
	public Archivio get_archivio() {
		return archivio;
	}
	
	public Stato get_stato_attuale() {
		return stato_attuale;
	}

	public void set_stato_attuale(Stato stato_attuale) {
		this.stato_attuale = stato_attuale;
	}
	
	public void prossimo_stato(ArrayList<String> view_input) {
		stato_attuale.prossimo_stato(this,view_input);
	}

	public Query get_query() {
		return query;
	}
}
