package versione_5.model;

public class Model_context {
	
	private Stato stato_attuale;
	private Database_file db;
	
	public Model_context() {
		db= new Database_file();
	}
	
	public Database_file get_database_file() {
		return db;
	}
	
	
	public Stato get_stato_attuale() {
		return stato_attuale;
	}

	public void set_stato_attuale(Stato stato_attuale) {
		this.stato_attuale = stato_attuale;
	}
	
	public void prossimo_stato(String input_utente) {
		stato_attuale.prossimo_stato(this,input_utente);
	}
}
