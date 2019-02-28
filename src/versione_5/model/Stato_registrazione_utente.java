package versione_5.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Stato_registrazione_utente extends Stato {

	public Stato_registrazione_utente(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inizializza_output() {
		// TODO Auto-generated method stub
		super.set_output(new ArrayList<>(Arrays.asList(" **** Inserire Nome **** ",
				" **** Inserire Cognome **** ",
				" **** Inserire Mail **** ",
				" **** Inserire Giorno di Nascita (compresa fra 1 e 31) **** ",
				" **** Inserire Mese di Nascita (compreso fra 1 e 12) **** ",
				" **** Inserire Anno di Nascita (compreso fra 1930 e "+LocalDate.now().getYear()+") **** ",
				" **** Inserire Username **** ",
				" **** Inserire Password **** ")));
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> view_input) {
		// TODO Auto-generated method stub
		//controllo dei dati dell'utente 
		Utente user= null;
		ArrayList<String> nuovo_utente=view_input;
		try {
			user= new Utente(nuovo_utente);
		}catch (Exception e) {
			model.set_stato_attuale(new Stato_errore(model, new Stato_iniziale(null), this, "Hai inserito dei dati sbagliati", get_attore()));
			return;
		}
		if(!model.get_database_file().is_presente(user)) {//controlla che l'utente non sia gia registrato(NON esistono due utenti con lo stesso username)
			model.get_database_file().salva_utente(user);
			model.get_archivio().salva_utente(user);//Aggiungo l'utente nell'archivio 
			//una volta completata l'iscrizione torna al log in
			model.set_stato_attuale(new Stato_iniziale(null));
		}else {
			model.set_stato_attuale(new Stato_errore(model, new Stato_iniziale(null), this, "Utete gia presente", get_attore()));
		}
	}


}
