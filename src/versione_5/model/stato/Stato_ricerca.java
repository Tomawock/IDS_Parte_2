package versione_5.model.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.model.Model_context;

public class Stato_ricerca extends Stato {

	public Stato_ricerca(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
				Costanti.GRECA2
				+ "\n"
				+ "Quale tipo di risorsa vuoi cercare?\n"
				+ "1)Libri\n"
				+ "2)Film\n"
				+ Costanti.GRECA2)));

	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		switch(dati_input.get(0)) {
			case "1":{
				model.set_stato_attuale(new Stato_inserisci_descrizione_libro_ricerca(get_attore()));
				break;
			}
			case "2":{
				model.set_stato_attuale(new Stato_inserisci_descrizione_film_ricerca(get_attore()));
				break;
			}
			default:{
				model.set_stato_attuale(new Stato_errore(new Stato_ricerca_visualizza(get_attore()), this, "inserisci un valore corretto", get_attore()));
				break;
			}
		}
	}

}
