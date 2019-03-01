package versione_5.model.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.model.Categoria;
import versione_5.model.Model_context;
import versione_5.model.Risorsa;

public class Stato_inserisci_id_risorsa_aggiungi_descrizione extends Stato {

	public Stato_inserisci_id_risorsa_aggiungi_descrizione(Object attore) {
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

	/**
	 * Consente di aggiungere la descrizione di una risorsa in base al suo id
	 */
	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		int id=-1;
		try {
			id=Integer.parseInt(dati_input.get(0));
		}catch(Exception e) {
			model.set_stato_attuale(new Stato_errore(new Stato_operatore_loggato(get_attore()), this, "Devi inserire un numero", get_attore()));
		}
		Categoria cat=model.get_database_file().carica_root_categorie();
		Risorsa res=cat.get_risorsa_by_id(cat, id);
		if(res!=null) {
			model.set_stato_attuale(new Stato_aggiungi_descrizione(new ArrayList<>(Arrays.asList(res,get_attore()))));
		}else {
			model.set_stato_attuale(new Stato_errore(new Stato_operatore_loggato(get_attore()), this, "Risorsa non trovata", get_attore()));
		}
	}

}
