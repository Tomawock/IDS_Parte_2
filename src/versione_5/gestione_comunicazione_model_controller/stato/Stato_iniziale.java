package versione_5.gestione_comunicazione_model_controller.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.gestione_comunicazione_model_controller.Model_context;

public class Stato_iniziale extends Stato{

	public Stato_iniziale(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
				Costanti.GRECA + 
				"\n" + 
				"              MENU' PRINCIPALE         " +
				"\n" +
				Costanti.GRECA + 
				"\n" + 
				"1)Accedere\n" +
				"2)Registrazione\n"+
				"3)Esci" +
				"\n" + 
				Costanti.GRECA)));
	}

	/**
	 * Consente di effettuare la prima scelta all'accessione del sistema ed 
	 * in base ai dati passati dalla view andare nello stato sucessivo
	 */
	@Override
	public void prossimo_stato(Model_context model,ArrayList<String> dati_input) {
		switch (dati_input.get(0)) {
			case "1":{
				 model.set_stato_attuale(new Stato_log_in_utente(get_attore()));
				 break;
			}
			case "2":{
				 model.set_stato_attuale(new Stato_registrazione_utente(get_attore()));
				 break;
			}
			case "3":{
				 model.set_stato_attuale(new Stato_terminato(get_attore()));
				 break;
			}
			default:{
				 model.set_stato_attuale(new Stato_errore(this, this, "inseriti dati sbagliati", get_attore()));
				 break;
			}
		}
	}

}
