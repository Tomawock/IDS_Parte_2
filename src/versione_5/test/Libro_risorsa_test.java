package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilita.Costanti;
import versione_5.model.*;

class Libro_risorsa_test {

	private Libro libro;
	private Libro libro2;
	private Libro libro3;
	private Libro libro4;
	private Libro libro_stesso_id;
	private ArrayList<String> descrizione;
	private ArrayList<String> no_descrizione_libro;
	private ArrayList<String> descrizione_no_ricerca_libro;
	private ArrayList<String> descrizione_alternativa;
	
	/**
	 * inizzializzo oggetti per i test
	 */
	@BeforeEach
	public void setup() {
		libro=new Libro(1,1);
		libro_stesso_id=new Libro(1,0);
		libro2=new Libro(2,1);
		libro3=new Libro(3,1);
		libro4=new Libro(4,1);
		
		descrizione= new ArrayList<>(Arrays.asList(
				"Il diario di zen",
				"Zen",
				"1000",
				"1999",
				"Zanichelli",
				"Arabo",
				"Fantasy"));
		
		descrizione_alternativa= new ArrayList<>(Arrays.asList(
				"Bob lo scavatore",
				"ZioSam",
				"10",
				"2000",
				"Orelly",
				"Cinese",
				"Horror"));
		
		no_descrizione_libro= new ArrayList<>(Arrays.asList(
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE,
				Costanti.NO_DESCRIZIONE));
		
		descrizione_no_ricerca_libro= new ArrayList<>(Arrays.asList(
				Costanti.NO_RICERCA,
				Costanti.NO_RICERCA,
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
		libro.aggiungi_descrizione(descrizione);
		assertTrue(libro.get_descrizione().equals(descrizione),"Descrizione aggiunta in modo corretto al libro");
	}
	
	@Test
	public void vero_se_rimozione_descrizione_eseguita_correttamente() {
		libro.aggiungi_descrizione(descrizione);
		libro.rimuovi_descrizione();
		assertTrue(libro.get_descrizione().equals(no_descrizione_libro),"Descrizione rimossa in modo corretto rispetto alla logica applicativa");
	}
	
	@Test
	public void vero_se_due_libri_hanno_la_stessa_descrizione() {
		libro.aggiungi_descrizione(descrizione);
		libro2.aggiungi_descrizione(descrizione);
		libro3.aggiungi_descrizione(descrizione_no_ricerca_libro);
		assertTrue(libro.equals_by_descrizione(libro2.get_descrizione()),"Due libro con stessa descrizione sono uguali");
		
		assertTrue(libro.equals_by_descrizione(libro3.get_descrizione()),"Un libro con descrizione e l'altro con NO_RICERCA come descrizione sono uguali");
	}
	
	@Test
	public void vero_se_due_libri_non_hanno_la_stessa_descrizione() {
		libro.aggiungi_descrizione(descrizione);
		libro4.aggiungi_descrizione(descrizione_alternativa);
		assertFalse(libro.equals_by_descrizione(libro4.get_descrizione()),"Due libro con descrizione diversa sono diversi");
	}
	
	@Test
	public void vero_se_due_libri_hanno_lo_stesso_id() {
		assertTrue(libro.equals(libro_stesso_id),"Due libro con lo stesso id sono uguali");
	}
	
	@Test
	public void vero_se_due_libri_non_hanno_lo_stesso_id() {
		assertFalse(libro.equals(libro2),"Due libro con id diverso sono diversi");
	}
	
	@Test
	public void vero_se_aggiunge_un_prestio() {
		libro.set_in_prestito(0);
		libro.add_prestito();
		assertEquals(1,libro.get_in_prestito(),"Viene aggiunta una sola copia in prestito");
	}
	@Test
	public void vero_se_rimuove_un_prestio() {
		libro.set_in_prestito(1);
		libro.remove_prestito();
		assertEquals(0,libro.get_in_prestito(),"Viene rimossa una sola copia dai prestiti");
	}
	@Test
	public void vero_se_calcola_il_numerodi_copie_disponibili() {
		libro.set_n_licenze(2);
		libro.set_in_prestito(1);
		assertEquals(1, libro.get_disponibili(),"Calcola il numero di copie disponibili");
	}
}
