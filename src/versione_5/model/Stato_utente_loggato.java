package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;

public class Stato_utente_loggato extends Stato {

	public Stato_utente_loggato(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(Costanti.GRECA
				+"\n"
				+"              MENU' ACCESSO BENVENUTO "+((Utente)get_attore()).get_username()
				+"\n" 
				+Costanti.GRECA
				+"\n"
				+ "1)Logga come Fruitore\n"
				+ "2)Logga come Operatore\n"
				+ "3)Diventa Fruitore\n"
				+ "4)Diventa Operatore\n"
				+ "5)Esci"
				+ "\n"
				+ Costanti.GRECA3)));

	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {	
		switch (dati_input.get(0)) {
			case "1":{//Loggare come Fruitore
				 model.set_stato_attuale(new Stato_log_in_fruitore(get_attore()));
				 break;
			}
			case "2":{//Loggare come Operatore
				 model.set_stato_attuale(new Stato_log_in_operatore(get_attore()));
				 break;
			}
			case "3":{//Registra nuovo Fruitore
				 model.set_stato_attuale(new Stato_registra_nuovo_fruitore(get_attore()));
				 break;
			}
			case "4":{//Registra nuovo Operatore
				 model.set_stato_attuale(new Stato_registra_nuovo_operatore(get_attore()));
				 break;
			}
			case "5":{//Torna alla pagina di log in
				 model.set_stato_attuale(new Stato_iniziale(null));
				 break;
			}
			default:{
				 model.set_stato_attuale(new Stato_errore(model, new Stato_iniziale(null), this, "inseriti dati sbagliati", get_attore()));
				 break;
			}
		}	
	}

}
