package versione_5.model;

import java.util.ArrayList;

public class Stato_registra_nuovo_operatore extends Stato {

	public Stato_registra_nuovo_operatore(Object attore) {
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
		 * if(db.carica_operatore(utente.get_username(), utente.get_password())!=null) {
				view.scrivi("Sei gia registrato come operatore");
				this.user_loggato(utente);
			}
			else {
				this.view.scrivi(utente.get_username()+" sei diventato operatore");
				//new Database_file().salva_operatore(new Operatore(utente));		
				db.salva_operatore(new Operatore(utente));
				archivio.salva_operatore(new Operatore(utente));//salvo l'operatore nell'archivio
				this.user_loggato(utente);
			}
		 */

	}

}
