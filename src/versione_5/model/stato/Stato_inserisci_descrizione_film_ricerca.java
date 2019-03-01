package versione_5.model.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.model.Film;
import versione_5.model.Model_context;
import versione_5.model.Risorsa;

public class Stato_inserisci_descrizione_film_ricerca extends Stato {

	public Stato_inserisci_descrizione_film_ricerca(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
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
				" **** Inserire Regista **** ",
				" **** Inserire Durata in minuti **** ",
				" **** Inserire Anno di pubblicazione **** ",
				" **** Inserire Genere **** ")));

	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		Film f=new Film(0, 0);
		f.aggiungi_descrizione(dati_input);
		ArrayList<Risorsa>risorse_trovate =model.get_database_file().ricerca_per_descrizione(f);
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
