package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Stato_log_in_utente extends Stato {

	public Stato_log_in_utente(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
				" **** Inserire Username **** ",
				" **** Inserire Password **** ")));
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		ArrayList<String> utente=dati_input;
		String utente_user=utente.get(0);
		String utente_psw=utente.get(1);
		//controlla che l'utente sia registrato nel Salvataggio
		Utente user = model.get_database_file().carica_utente(utente_user, utente_psw);
		
		if(user==null) {
			model.set_stato_attuale(new Stato_errore(new Stato_iniziale(super.get_attore()), this,"Username o password errati,o utente non registrato",get_attore()));
		}
		else {
			model.set_stato_attuale(new Stato_utente_loggato(user));//passo come parametro allo stato successivo l'utente corretto
		}			
	}
}
