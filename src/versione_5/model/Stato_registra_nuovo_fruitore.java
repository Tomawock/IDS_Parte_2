package versione_5.model;

import java.util.ArrayList;

public class Stato_registra_nuovo_fruitore extends Stato {

	public Stato_registra_nuovo_fruitore(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inizializza_output() {
		// TODO Auto-generated method stub

	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> view_input) {
		/*
		 * if(db.carica_fruitore(utente.get_username(), utente.get_password())!=null) {
				view.scrivi("Sei gia registrato come fruitore");
				this.user_loggato(utente);
			}
			else if(utente.get_eta()>=18) {
				this.view.scrivi(utente.get_username()+" sei diventato fruitore");
				//new Database_file().salva_fruitore(new Fruitore(utente));
				db.salva_fruitore(new Fruitore(utente));
				archivio.salva_fruitore(new Fruitore(utente));//salvo nell'archivio
				this.user_loggato(utente);
			}
			else {
				view.scrivi("Non puoi diventare fruitore in quanto non sei maggiorenne");
				this.user_loggato(utente);
			}
		 */

	}

}
