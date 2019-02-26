package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import versione_5.model.*;

class Operatore_test {
	
	private Operatore o_pass;
	private Operatore o_pass2;
	private Operatore o_fail;
	
	@BeforeEach
	public void setup() {
		Utente user= new Utente("Nome",
				"Cognome",
				"mail",
				LocalDateTime.now(),
				"CodiceFiscale",
				"Username", 
				"password");
		Utente user_diverso= new Utente("Nome",
				"Cognome",
				"mail",
				LocalDateTime.now(),
				"CodiceFiscale",
				"Username_diverso", 
				"password");
		Utente user_uguale= new Utente("Nome",
				"Cognome",
				"mail",
				LocalDateTime.now(),
				"CodiceFiscale",
				"Username", 
				"password");

	 o_pass=new Operatore(user);
	 o_pass2=new Operatore(user_uguale);
	 o_fail = new Operatore(user_diverso);
	}
	/**
	 * Test Per cammini di base
	 */	
	@Test
	public void vero_se_due_operatori_sono_uguali() {	
		assertTrue(o_pass.equals(o_pass2),"I due operatori con lo stesso username sono uguai");
		

	}
	
	@Test
	public void vero_se_due_operatori_non_sono_uguali() {		
		assertFalse(o_pass.equals(o_fail),"Operatori con diverso username sono diversi");
	}
	
	/**
	 * Test boundary value analysis
	 */
	@Test
	public void equals_con_oggetto_nullo_genera_NullPointerException() {
		Operatore op = new Operatore(null);
		assertThrows(NullPointerException.class,
	            ()->{
	            op.equals(op);
            	});
	}
}
