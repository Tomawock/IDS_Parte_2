package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilita.Costanti;
import versione_5.model.*;

class Film_risorsa_test {
	
	private Film film;
	private Film film2;
	private Film film3;
	private Film film4;
	private Film film_stesso_id;
	private ArrayList<String> descrizione;
	private ArrayList<String> no_descrizione_film;
	private ArrayList<String> descrizione_no_ricerca_film;
	private ArrayList<String> descrizione_alternativa;
	
	/**
	 * inizzializzo oggetti per i test
	 */
	@BeforeEach
	public void setup() {
		film=new Film(1,1);
		film_stesso_id=new Film(1,0);
		film2=new Film(2,1);
		film3=new Film(3,1);
		film4=new Film(4,1);
		
		descrizione= new ArrayList<>(Arrays.asList(
				"Film 1",
				"Regista 1",
				"1",
				"1901",
				"Genere 1"));
		
		descrizione_alternativa= new ArrayList<>(Arrays.asList(
				"Film 2",
				"Regista 2",
				"2",
				"1902",
				"Genere 2"));
		
		no_descrizione_film= new ArrayList<>(Arrays.asList(
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE));
		
		descrizione_no_ricerca_film= new ArrayList<>(Arrays.asList(
				Costanti.NO_RICERCA,
				Costanti.NO_RICERCA,
				Costanti.NO_RICERCA,
				Costanti.NO_RICERCA,
				Costanti.NO_RICERCA));
	}
	
	/**
	 * Test di copertura del codice delle funzioni principali
	 */
	
	@Test
	public void vero_se_aggiunta_descrizione_corretta() {
		film.aggiungi_descrizione(descrizione);
		assertTrue(film.get_descrizione().equals(descrizione),"Descrizione aggiunta in modo corretto al filmn");
	}
	
	@Test
	public void vero_se_rimozione_descrizione_eseguita_correttamente() {
		film.aggiungi_descrizione(descrizione);
		film.rimuovi_descrizione();
		assertTrue(film.get_descrizione().equals(no_descrizione_film),"Descrizione rimossa in modo corretto rispetto alla logica applicativa");
	}
	
	@Test
	public void vero_se_due_film_hanno_la_stessa_descrizione() {
		film.aggiungi_descrizione(descrizione);
		film2.aggiungi_descrizione(descrizione);
		film3.aggiungi_descrizione(descrizione_no_ricerca_film);
		assertTrue(film.equals_by_descrizione(film2.get_descrizione()),"Due film con stessa descrizione sono uguali");
		
		assertTrue(film.equals_by_descrizione(film3.get_descrizione()),"Un film con descrizione e l'altro con NO_RICERCA come descrizione sono uguali");
	}
	
	@Test
	public void vero_se_due_film_non_hanno_la_stessa_descrizione() {
		film.aggiungi_descrizione(descrizione);
		film4.aggiungi_descrizione(descrizione_alternativa);
		assertFalse(film.equals_by_descrizione(film4.get_descrizione()),"Due film con descrizione diversa sono diversi");
	}
	
	@Test
	public void vero_se_due_film_hanno_lo_stesso_id() {
		assertTrue(film.equals(film_stesso_id),"Due film con lo stesso id sono uguali");
	}
	
	@Test
	public void vero_se_due_film_non_hanno_lo_stesso_id() {
		assertFalse(film.equals(film2),"Due film con id diverso sono diversi");
	}
	
	@Test
	public void vero_se_aggiunge_un_prestio() {
		film.set_in_prestito(0);
		film.add_prestito();
		assertEquals(1,film.get_in_prestito(),"Viene aggiunta una sola copia in prestito");
	}
	@Test
	public void vero_se_rimuove_un_prestio() {
		film.set_in_prestito(1);
		film.remove_prestito();
		assertEquals(0,film.get_in_prestito(),"Viene rimossa una sola copia dai prestiti");
	}
	@Test
	public void vero_se_calcola_il_numerodi_copie_disponibili() {
		film.set_n_licenze(2);
		film.set_in_prestito(1);
		assertEquals(1, film.get_disponibili(),"Calcola il numero di copie disponibili");
	}
	
}
