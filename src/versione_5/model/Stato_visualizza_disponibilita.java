package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;

public class Stato_visualizza_disponibilita extends Stato {

	public Stato_visualizza_disponibilita(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
				Costanti.GRECA2
				+ "\n **** Inserire ID della Risorsa **** \n"
				+ Costanti.GRECA2)));

	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		
		int id=-1;
		try {
			id=Integer.parseInt(dati_input.get(0));
		}catch(Exception e) {
			model.set_stato_attuale(new Stato_errore(new Stato_ricerca_visualizza(get_attore()), this, "Devi inserire un numero", get_attore()));
		}
		int ris=model.get_database_file().get_n_copie_disponibili_by_id(id);
		if(ris==-1) {
			model.set_stato_attuale(new Stato_notifica(new Stato_ricerca_visualizza(get_attore()), "Risorsa non trovata", get_attore()));
		}else {
			model.set_stato_attuale(new Stato_notifica(new Stato_ricerca_visualizza(get_attore()), "Sono disponibili "+ris+" copie", get_attore()));
		}
	}

}
