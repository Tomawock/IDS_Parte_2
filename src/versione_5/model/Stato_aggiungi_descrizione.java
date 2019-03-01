package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import utilita.IO;

public class Stato_aggiungi_descrizione extends Stato {

	/**
	 * 
	 * @param attore è un array lsit con come primo elemento la risorsa selezionata dall'utente e l'operatore
	 */
	public Stato_aggiungi_descrizione(Object attore) {
		super(attore);
		
	}

	@Override
	public void inizializza_output() {
		Risorsa risorsa = (Risorsa)((ArrayList<Object>)get_attore()).get(0);
		if(risorsa instanceof Libro) {
			super.set_output(new ArrayList<>(Arrays.asList(
			" **** Inserire Titolo **** ",
			" **** Inserire Autore **** ",
			" **** Inserire Numero di Pagine **** ",
			" **** Inserire Anno di pubblicazione **** ",
			" **** Inserire Casa Editrice **** ",
			" **** Inserire Lingua **** ",
			" **** Inserire Genere **** ")));
		}
		else if(risorsa instanceof Film) {
			super.set_output(new ArrayList<>(Arrays.asList(
			" **** Inserire Titolo **** ",
			" **** Inserire Regista **** ",
			" **** Inserire Durata in minuti **** ",
			" **** Inserire Anno di pubblicazione **** ",
			" **** Inserire Genere **** ")));
		}
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		Risorsa risorsa = (Risorsa)((ArrayList<Object>)get_attore()).get(0);
		Categoria cat=model.get_database_file().carica_root_categorie();
		risorsa=cat.get_risorsa_by_id(cat, risorsa.get_id());
		risorsa.aggiungi_descrizione(dati_input);
		model.get_database_file().salva_categoria_root(cat);
		model.get_archivio().salva_categoria_root(cat);
		
		model.set_stato_attuale(new Stato_operatore_loggato(((ArrayList<Object>)get_attore()).get(1)));
	}

}
