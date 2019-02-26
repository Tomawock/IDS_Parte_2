package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import versione_5.model.*;

class Utente_test {
	
	private Utente user;
	private Utente user_diverso;
	private Utente user_uguale;
	
	/**
	 * inizzializzo oggetti per i test
	 */
	@BeforeEach
	public void setup() {
		user= new Utente("Nome",
				"Cognome",
				"mail",
				LocalDateTime.now(),
				"CodiceFiscale",
				"Username", 
				"password");
		user_diverso= new Utente("Nome",
				"Cognome",
				"mail",
				LocalDateTime.now(),
				"CodiceFiscale",
				"Username_diverso", 
				"password");
		user_uguale= new Utente("Nome",
				"Cognome",
				"mail",
				LocalDateTime.now(),
				"CodiceFiscale",
				"Username", 
				"password");
	}
	
	/**
	 * Test di copertura del codice delle funzioni principali
	 */
	@Test
	public void vero_se_due_utenti_sono_uguali() {
		assertTrue(user.equals(user_uguale),"I due utenti sono uguali");
	}
	
	@Test
	public void vero_se_due_utenti_non_sono_uguali() {
		assertFalse(user.equals(user_diverso),"I due utenti sono diversi");
	}
	/**
	 * FIXARE DURANTE REFACTORING 
	 * creare una funzione di set up non all'interno del costruttore per gli array list
	 */
	@Test
	public void costruttore_nullo_genera_NullPointerException() {
		assertThrows(NullPointerException.class,
	            ()->{
	            Utente u =new Utente(null);
            	});
	}
}
