package versione_5.model;

import utilita.Costanti_output;

public class Stato_iniziale extends Stato{

	@Override
	public Stato porssimo_stato(String dati_input) {
		//codice controller old
		switch (dati_input) {
			case "1":{
				return new Stato_log_in_utente();
			}
			case "2":{
				return new Stato_registrazione_utente();
			}
			case "3":{
				return new Stato_terminato();
			}
			default:{
				return new Stato_errore();
			}
		}
	}

	@Override
	public void inizializza_output() {
		super.set_output(Costanti_output.OUTPUT_STATO_INIZIALE);
	}

}
