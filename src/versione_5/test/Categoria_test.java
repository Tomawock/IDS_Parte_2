package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import versione_5.model.*;

class Categoria_test {
	
	private static final String NOT_FOUND="NON trovato";
	
	private Risorsa risorsa,risorsa2;
	private Categoria categoria,sotto_categoria,categoria_match,categoria_uguale,categoria_diversa;
	private ArrayList<Risorsa> risorse,risorse_con_elementi;
	private ArrayList<Categoria> sotto_categorie,sottocategorie_con_elementi;
	
	/**
	 * inizzializzo oggetti per i test
	 */
	@BeforeEach
	public void setup() {
		//per i test di code coverange uso Film come classe concreta
		risorsa=new Film(1,1);
		risorsa2=new Film(2,1);
		
		categoria= new Categoria("Test");
		categoria_match= new Categoria("match");
		categoria_uguale =new Categoria("Test");
		categoria_diversa =new Categoria("Diverso");
		
		sotto_categoria= new Categoria("Test_sottocategoria");
		
		risorse=new ArrayList<>();
		sotto_categorie = new ArrayList<>();
		
		risorse_con_elementi= new ArrayList<>();
		risorse_con_elementi.add(risorsa);
		risorse_con_elementi.add(risorsa2);
		
		sottocategorie_con_elementi= new ArrayList<>();
		sottocategorie_con_elementi.add(categoria_match);
	}
	
	/**
	 * Test di copertura del codice delle funzioni principali
	 */
	@Test
	public void vero_se_add_risorsa_aggiunge_una_risorsa_arraylist_era_null() {
		categoria.set_risorse(null);
		categoria.add_risorsa(risorsa);
		assertFalse(categoria.get_risorse().isEmpty(),"Aggiunta una risorsa alla Categoria, il vettore di base era nullo");
	}
	
	@Test
	public void vero_se_add_risorsa_aggiunge_una_risorsa_arraylist_non_era_null() {
		categoria.set_risorse(risorse);
		categoria.add_risorsa(risorsa);
		assertFalse(categoria.get_risorse().isEmpty(),"Aggiunta una risorsa alla Categoria, il vettore di base non era nullo");
	}
	
	@Test
	public void vero_se_add_sottocategoria_aggiunge_una_categoria__arraylist_era_null() {
		categoria.set_sottocategorie(null);
		categoria.add_sottocategoria(sotto_categoria);
		assertFalse(categoria.get_sottocategorie().isEmpty(),"Aggiunge una sottocategoria, il vettore di base era nullo");
	}
	
	@Test
	public void vero_se_add_sottocategoria_aggiunge_una_categoria__arraylist_non_era_null() {
		categoria.set_sottocategorie(sotto_categorie);
		categoria.add_sottocategoria(sotto_categoria);
		assertFalse(categoria.get_sottocategorie().isEmpty(),"Aggiunge una sottocategoria, il vettore di base non era nullo");
	}
	
	@Test
	public void vero_se_data_una_categoria_e_se_stessa_get_sottocategoria_by_name_tova_se_stessa(){
		Categoria risultato=categoria.get_sottocategoria_by_name(categoria, categoria.get_nome());
		assertTrue(risultato.equals(categoria), "Sottocategoria trovata, la categoria cercata era quella base della ricerca");
	}
	
	@Test
	public void vero_se_categoria_non_ha_sottocategorie_e_non_viene_trovato_match_in_get_sottocategoria_by_name_(){
		categoria.set_sottocategorie(null);
		Categoria risultato=categoria.get_sottocategoria_by_name(categoria,Categoria_test.NOT_FOUND );
		assertEquals(null,risultato,"Sottocategoria non trovata perchè non ha sottocategorie");
	}
	
	@Test
	public void vero_se_data_una_categoria_e_una_presente_nelle_sue_sottocategorie_trova_il_match_in_get_sottocategoria_by_name(){
		categoria.set_sottocategorie(sottocategorie_con_elementi);
		Categoria risultato=categoria.get_sottocategoria_by_name(categoria, categoria_match.get_nome());
		assertEquals(categoria_match,risultato,"Sottocategoria trovata all'interno delle sotto categorie");
	}
	
	@Test
	public void vero_se_categoria_ha_sottocategorie_e_non_viene_trovato_match_in_get_sottocategoria_by_name_(){
		categoria.set_sottocategorie(sottocategorie_con_elementi);
		Categoria risultato=categoria.get_sottocategoria_by_name(categoria, Categoria_test.NOT_FOUND);
		assertEquals(null,risultato,"Sottocategoria non trovata all'interno delle sotto categorie");
	}
	
	@Test
	public void vero_se_due_categorie_sono_uguali() {
		assertTrue(categoria.equals(categoria_uguale),"Due categorie con lo stesso nome sono uguali");
	}
	
	@Test
	public void vero_se_due_categorie_non_sono_uguali() {
		assertFalse(categoria.equals(categoria_diversa),"Due categorie con nomi diversi sono diverse");
	}
	
	@Test
	public void vero_se_carica_tutte_risorse_trova_le_risorse_direttamente_nella_categoria_di_base() {
		ArrayList<Risorsa> risultato= new ArrayList<>();
		categoria.add_risorsa(risorsa);
		categoria.add_risorsa(risorsa2);
		categoria.carica_tutte_risorse(categoria, risultato);
		assertEquals(risultato,risorse_con_elementi,"Trovati tutte le risorse nella categoria di base");
	}
	
	@Test
	public void vero_se_carica_tutte_risorse_trova_le_risorse_nelle_sottocategorie_nella_categoria_di_base() {
		ArrayList<Risorsa> risultato= new ArrayList<>();
		categoria.set_risorse(null);
		categoria.add_sottocategoria(sotto_categoria);
		sotto_categoria.add_risorsa(risorsa);
		sotto_categoria.add_risorsa(risorsa2);
		categoria.carica_tutte_risorse(categoria, risultato);
		assertEquals(risultato,risorse_con_elementi,"Trovati tutte le risorse nella sottocategoria");
	}
	
	@Test
	public void vero_se_carica_tutte_risorse_non_carica_risorse_in_qunato_non_presenti_ne_nelle_sottocategorie_ne_nella_categoria_di_base() {
		ArrayList<Risorsa> risultato= new ArrayList<>();
		categoria.set_risorse(null);
		categoria.add_sottocategoria(sotto_categoria);
		sotto_categoria.set_risorse(null);
		categoria.carica_tutte_risorse(categoria, risultato);
		assertTrue(risultato.isEmpty(),"Non sono presenti Risorse nelle categorie");
	}
	
	@Test
	public void vero_se_get_risorsa_by_id_trova_la_risorsa_corrispondente_dentro_categoria_base_non_avendo_sottocategorie() {
		categoria.set_sottocategorie(null);
		categoria.add_risorsa(risorsa);
		Risorsa risultato= categoria.get_risorsa_by_id(categoria, risorsa.get_id());
		assertEquals(risorsa,risultato,"TRovata la risorsa corrispondente all'id dentro la categoria di base");
	}
	
	@Test
	public void vero_se_get_risorsa_by_id_non_trova_la_risorsa_corrispondente_dentro_categoria_base_non_avendo_sottocategorie() {
		categoria.set_sottocategorie(null);
		categoria.add_risorsa(risorsa);
		Risorsa risultato= categoria.get_risorsa_by_id(categoria, risorsa2.get_id());
		assertEquals(null,risultato,"Risorsa non trovata in base all'id dentro la categoria di base");
	}
	
	@Test
	public void vero_se_get_risorsa_by_id_non_trova_la_risorsa_poiche_risorse_sono_null_dentro_categoria_base_non_avendo_sottocategorie() {
		categoria.set_sottocategorie(null);
		categoria.set_risorse(null);
		Risorsa risultato= categoria.get_risorsa_by_id(categoria, risorsa2.get_id());
		assertEquals(null,risultato,"Risorsa non trovata perche risorse erano nulle nella categoria base");
	}
	
	@Test
	public void vero_se_get_risorsa_by_id_trova_la_risorsa_dentro_sotto_categoria() {
		categoria.add_sottocategoria(sotto_categoria);
		sotto_categoria.add_risorsa(risorsa);
		Risorsa risultato= categoria.get_risorsa_by_id(categoria, risorsa.get_id());
		assertEquals(risorsa,risultato,"Risorsa trovata nelle sotto categorie");
	}
	
	@Test
	public void vero_se_get_risorsa_by_id_non_trova_la_risorsa_dentro_sotto_categoria() {
		categoria.add_sottocategoria(sotto_categoria);
		sotto_categoria.add_risorsa(risorsa);
		Risorsa risultato= categoria.get_risorsa_by_id(categoria, risorsa2.get_id());
		assertEquals(null,risultato,"Risorsa non trovata nelle sotto categorie");
	}
}
