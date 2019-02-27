package versione_5.model;

import utilita.IO;

public class Stato_log_in_utente extends Stato {

	public Stato_log_in_utente(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output("utente e password");
		
	}

	@Override
	public void prossimo_stato(Model_context model, String dati_input) {
		String utente=dati_input;
		if(utente.contains(IO.SEPARATORE_STRINGHE)){
			String utente_user=utente.split(IO.SEPARATORE_STRINGHE)[0];
			String utente_psw=utente.split(IO.SEPARATORE_STRINGHE)[1];
			//controlla che l'utente sia registrato nel Salvataggio
			Utente user = model.get_database_file().carica_utente(utente_user, utente_psw);
			
			if(user==null) {
				model.set_stato_attuale(new Stato_errore(model, new Stato_iniziale(super.get_attore()),this,"msg error",get_attore()));
			}
			else {
				model.set_stato_attuale(new Stato_utente_loggato(get_attore()));
			}	
		}
		model.set_stato_attuale(new Stato_errore(model, new Stato_iniziale(super.get_attore()),this,"msg error",get_attore()));
	}
}
