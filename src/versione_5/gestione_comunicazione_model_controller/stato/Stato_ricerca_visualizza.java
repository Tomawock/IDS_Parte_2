package versione_5.gestione_comunicazione_model_controller.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.gestione_comunicazione_model_controller.Model_context;
import versione_5.model.Fruitore;

public class Stato_ricerca_visualizza extends Stato {

	public Stato_ricerca_visualizza(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
		Costanti.GRECA
		+ "\n"
		+"Cosa vuoi fare?\n"
		+ "1)Ricerca per descrizione\n"
		+ "2)Visualizza quatità disponibili\n"
		+ "3)Torna indietro"
		+ "\n"
		+ Costanti.GRECA)));

	}

	/**
	 * Consente di scegliere se effettuare la ricerca o la visualizzazione delle risorse
	 */
	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		switch(dati_input.get(0)) {
			case "1":{
				model.set_stato_attuale(new Stato_ricerca(get_attore()));
				break;
			}
			case "2":{
				model.set_stato_attuale(new Stato_visualizza_disponibilita(get_attore()));
				break;
			}
			case "3":{
				if(get_attore().getClass().getSimpleName().equals(Fruitore.class.getSimpleName()))
					model.set_stato_attuale(new Stato_fruitore_loggato(get_attore()));
				else
					model.set_stato_attuale(new Stato_operatore_loggato(get_attore()));
				break;
			}
			default:{
				if(get_attore().getClass().getSimpleName().equals(Fruitore.class.getSimpleName()))
					model.set_stato_attuale(new Stato_errore(new Stato_fruitore_loggato(get_attore()), this, "inseriti dati sbagliati", get_attore()));
				else
					model.set_stato_attuale(new Stato_errore(new Stato_operatore_loggato(get_attore()), this, "inseriti dati sbagliati", get_attore()));
				break;
			}
		}
	}

}
