package versione_5.model;

import utilita.Costanti_output;

public class Stato_iniziale extends Stato{

	public Stato_iniziale(Object attore) {
		super(attore);
	}

	@Override
	public void prossimo_stato(Model_context model,String dati_input) {
		//codice controller old
		switch (dati_input) {
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
				 model.set_stato_attuale(new Stato_errore(model, this, this, "inseriti dati sbagliati", get_attore()));
				 break;
			}
		}
	}

	@Override
	public void inizializza_output() {
		super.set_output(Costanti_output.OUTPUT_STATO_INIZIALE);
	}

}
