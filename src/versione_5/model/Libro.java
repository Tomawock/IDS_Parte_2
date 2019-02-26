package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;

public class Libro  extends Risorsa{	

	/**
	 * Numero seriale per la serializzazione dei dati su File
	 */
	private static final long serialVersionUID = 9L;
	
	/**
	 * Crea una nuova risorsa di tipo Libro con un id univoco e un numero di copie fissato
	 * 		Viene aggiunta la descrizione della risorsa con tutti i campi settati con NO_DESCRIZIONE
	 * 
	 * @param id			id univoco della risorsa
	 * @param n_licenze		numero di copie massimo diponibile
	 */
	public Libro(int id, int n_licenze) {
		super(id, n_licenze);
		super.aggiungi_descrizione(new ArrayList<>(Arrays.asList(Costanti.NO_DESCRIZIONE,Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,Costanti.NO_DESCRIZIONE,Costanti.NO_DESCRIZIONE,Costanti.NO_DESCRIZIONE,Costanti.NO_DESCRIZIONE)));
	}
	
	@Override
	public String toString() {
		return " ***** Titolo Libro: " + super.get_descrizione().get(0) + ", Autore: " + super.get_descrizione().get(1) + 
				", Numero di Pagine: " + super.get_descrizione().get(2) + ", Anno di Pubblicazione: " + super.get_descrizione().get(3)+ 
				", Casa Editrice: " + super.get_descrizione().get(4)+ ", Lingua: "	+ super.get_descrizione().get(5) + 
				", Genere: " + super.get_descrizione().get(6) +", Numero Identificativo: "+ super.get_id() +", Numero di Copie: "+ super.get_n_licenze()
				+ ", Numero Attualmente Disponibili: " + (super.get_n_licenze() - super.get_in_prestito()) + "  ***** ";
	}

	@Override
	public void inizialize_max_n_prestiti() {
		super.set_max_n_prestiti(Costanti.MAX_NUMERO_DI_LIBRI_FRUITORE);		
	}

}
