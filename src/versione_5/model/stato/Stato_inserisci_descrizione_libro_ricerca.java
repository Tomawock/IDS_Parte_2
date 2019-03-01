package versione_5.model.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.model.Libro;
import versione_5.model.Model_context;
import versione_5.model.Risorsa;

public class Stato_inserisci_descrizione_libro_ricerca extends Stato {

	public Stato_inserisci_descrizione_libro_ricerca(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
				Costanti.GRECA
				+"\nInserire "+Costanti.NO_RICERCA+" nel caso in cui non si voglia ricercare per quel parametro\n"
				+ "I valori inseriti nella ricerca sono case sensitive\n"
				+ Costanti.GRECA
				+ "\n"
				+ " **** Inserire Titolo **** ",
				" **** Inserire Autore **** ",
				" **** Inserire Numero di Pagine **** ",
				" **** Inserire Anno di pubblicazione **** ",
				" **** Inserire Casa Editrice **** ",
				" **** Inserire Lingua **** ",
				" **** Inserire Genere **** ")));
	}

	/**
	 * Consente di cercare un film con una determinata descrizione
	 */
	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		Libro l=new Libro(0, 0);
		l.aggiungi_descrizione(dati_input);
		ArrayList<Risorsa>risorse_trovate =model.get_database_file().ricerca_per_descrizione(l);
		String output="";
		if(risorse_trovate.isEmpty()) {
			output="Non sono state trovate risorse corrispondenti alla ricerca";
		}
		else {
			for(Risorsa r:risorse_trovate) {
				output+=r.toString()+"\n";
			}
		}
		model.set_stato_attuale(new Stato_notifica(new Stato_ricerca_visualizza(get_attore()), output, get_attore()));
	}

}
