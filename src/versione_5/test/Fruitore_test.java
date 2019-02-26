package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilita.Costanti;
import versione_5.model.*;


class Fruitore_test {

	private Utente user;
	private Utente user_diverso;
	private Utente user_uguale;
	private Fruitore fruitore;
	private Fruitore fruitore_uguale;
	private Fruitore fruitore_diverso;
	
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
		
		fruitore =new Fruitore(user);
		fruitore_uguale =new Fruitore(user_uguale);
		fruitore_diverso =new Fruitore(user_diverso);
	}
	/**
	 * Test Coverange
	 */
	@Test
	public void get_giorni_scadenza_dovrebbe_ritornare_meno_uno_se_fruitore_non_rinnovabile() {
		fruitore.set_rinnovabile(false);//vuol dire che non si è nel periodo in cui si puo rinnovare la scadenza
		assertEquals(-1, fruitore.get_giorni_scadenza(),"Fruitore non rinnvovabile, implica che non posso sapere i giorni alla scdenza");
	}
	
	@Test
	public void get_giorni_scadenza_dovrebbe_ritornare_intero_se_fruitore_rinnovabile() {
		fruitore.set_rinnovabile(true);//vuol dire che non si è nel periodo in cui si puo rinnovare la scadenza
		fruitore.set_data_fine_iscrizione(LocalDateTime.now().plusDays(1));
		assertEquals(1, fruitore.get_giorni_scadenza(),"Fruitore rinnvovabile, implica che posso sapere i giorni alla scdenza");
	}
	
	@Test
	public void controllo_validia_dovrebbe_settare_valido_e_rinnovabile_true_se_data_fine_iscrizione_successiva_ad_oggi_e_se_data_rinnovo_iscrizione_precedente_ad_oggi() {
		fruitore.set_data_fine_iscrizione(LocalDateTime.now().plusDays(1));
		fruitore.set_data_rinnovo_iscrizione(LocalDateTime.now().minusDays(1));
		assertTrue(fruitore.is_valido(),"Fruitore con validita a true");
		assertTrue(fruitore.is_rinnovabile(),"Fruitore con rinnovabilia a true");
	}
	
	@Test
	public void controllo_validia_dovrebbe_settare_valido_true_se_data_fine_iscrizione_successiva_ad_oggi_e_settarerinnovabile_false_se_data_rinnovo_iscrizione_successiva_ad_oggi() {
		fruitore.set_data_fine_iscrizione(LocalDateTime.now().plusDays(1));
		fruitore.set_data_rinnovo_iscrizione(LocalDateTime.now().plusDays(1));
		assertTrue(fruitore.is_valido(),"Fruitore con validita a true");
		assertFalse(fruitore.is_rinnovabile(),"Fruitore con rinnovabilia a false");	
	}
	
	@Test
	public void controllo_validia_dovrebbe_settare_valido_e_rinnovabile_false_se_data_fine_iscrizione_precedente_ad_oggi() {
		fruitore.set_data_fine_iscrizione(LocalDateTime.now().minusDays(1));
		assertFalse(fruitore.is_valido(),"Fruitore con validita a false");
		assertFalse(fruitore.is_rinnovabile(),"Fruitore con rinnovabilia a false");	
	}

	@Test
	public void vero_se_due_fruitori_sono_uguali() {
		assertTrue(fruitore.equals(fruitore_uguale),"I due Fruitori sono uguali");
	}
	
	@Test
	public void vero_se_due_fruitori_non_sono_uguali() {
		assertFalse(fruitore.equals(fruitore_diverso),"I due Fruitori sono diversi");
	}
	
	@Test
	public void vero_se_reset_dei_dati_con_nuovo_fruitore_corretto() {
		fruitore.reset_dati(fruitore_diverso);
		assertTrue(fruitore.get_data_fine_iscrizione().equals(fruitore_diverso.get_data_fine_iscrizione()),"Il Fruitore è resettato in modo corretto,Data fine iscrizione coincidenti");
		assertTrue(fruitore.get_data_iscrizione().equals(fruitore_diverso.get_data_iscrizione()),"Il Fruitore è resettato in modo corretto,Data Iscrizione coincidente");
		assertTrue(fruitore.get_data_rinnovo_iscrizione().equals(fruitore_diverso.get_data_rinnovo_iscrizione()),"Il Fruitore è resettato in modo corretto,Data rinnovo coincidente");
		assertTrue(fruitore.is_rinnovabile()==fruitore_diverso.is_rinnovabile(),"Il Fruitore è resettato in modo corretto,rinnovabilità coincidente");
		assertTrue(fruitore.get_utente().equals(fruitore_diverso.get_utente()),"Il Fruitore è resettato in modo corretto,Utente coincidente");
		assertTrue(fruitore.is_valido()==fruitore_diverso.is_valido(),"Il Fruitore è resettato in modo corretto,validità coincidente");
	}

	@Test
	public void vero_se_rinnova_iscrizione_rinnova_iscrizione_del_fruitore() {
		LocalDateTime oggi= LocalDateTime.now();
		fruitore.rinnova_iscrizione();
		assertTrue(fruitore.get_data_iscrizione().getDayOfYear()==oggi.getDayOfYear(),"Data Iscrizione Fissata Correttamente");
		assertTrue(fruitore.get_data_fine_iscrizione().equals(oggi.plusYears(Costanti.SCADENZA_TERMINE_FRUITORE)),"Scadenza fissata Correttamente");
		assertTrue(fruitore.get_data_rinnovo_iscrizione().equals(oggi.plusYears(Costanti.SCADENZA_TERMINE_FRUITORE).minusDays(Costanti.GIORNI_RINNOVO_ISCRIZIONE)),"Inizio periodo rinnovabilita fissato Correttamente");
	}
	
}
