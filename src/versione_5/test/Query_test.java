package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilita.IO;
import versione_5.model.*;

class Query_test {

	public static final String ROOT= "ROOT";

	private Query query;
	private Database_file db;
	private Utente utente,utente_2;
	private Fruitore fruitore,fruitore_2;
	private Prestito prestito,prestito_2;
	private Risorsa risorsa,risorsa_2;
	
	@BeforeEach
	public void setup() {		
		db=new Database_file();
		
		query = new Query(db);
		
		utente=new Utente("test", "test", "test",  LocalDateTime.of(2012,12,12,0,0) ,"test", "test", "test");
		utente_2=new Utente("test2", "test2", "test2",  LocalDateTime.of(2012,12,12,0,0) ,"test2", "test2", "test2");
		
		fruitore=new Fruitore(utente);
		fruitore_2=new Fruitore(utente_2);
		
		risorsa = new Film(1,1);
		risorsa_2 = new Film(2,1);
		
		prestito =new Prestito(risorsa, fruitore);
		prestito_2 =new Prestito(risorsa_2, fruitore_2);
	}
	
	@Test
	public void vero_se_count_numero_di_prestiti_per_anno_solare_ritorna_zero_se_non_sono_presenti_prestiti_nell_anno_solare_prescelto() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
				
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		db.salva_prestito(prestito);
		
		assertEquals(0,query.count_numero_di_prestiti_per_anno_solare(LocalDateTime.of(2012,12,12,0,0)),"Il numero di prestiti nell'anno solare corrente è 0 poiche non vi sono salvataggi nell'anno selezionato");
	}
	
	@Test
	public void vero_se_count_numero_di_prestiti_per_anno_solare_ritorna_il_numero_di_prestiti_effettuati_nell_anno_solare_prescelto() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		db.salva_prestito(prestito);
		
		assertEquals(1,query.count_numero_di_prestiti_per_anno_solare(LocalDateTime.now()),"Il numero di prestiti nell'anno solare corrente è 0 poiche non vi sono salvataggi nell'anno selezionato");
	}
	
	@Test
	public void vero_se_count_numero_di_proroghe_per_anno_solare_ritorna_zero_se_non_sono_presenti_prestiti_che_sono_stati_prorogati_nell_anno_soloare_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		prestito.set_mai_prorogato(true);
		
		db.salva_prestito(prestito);
		
		assertEquals(0,query.count_numero_di_proroghe_per_anno_solare(LocalDateTime.of(2012,12,12,0,0)),"Il numero di proroghe nell'anno solare corrente è 0 poiche non vi sono prestiti nell'anno selezionato che non sono mai stati prorogati");
	}

	@Test
	public void vero_se_count_numero_di_proroghe_per_anno_solare_ritorna_zero_se_sono_presenti_prestiti_che_sono_stati_prorogati_ma_non_nell_anno_soloare_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		prestito.set_mai_prorogato(false);
		prestito.set_data_inizio_proroga(LocalDateTime.now());
		
		db.salva_prestito(prestito);
		
		assertEquals(0,query.count_numero_di_proroghe_per_anno_solare(LocalDateTime.of(2012,12,12,0,0)),"Il numero di proroghe nell'anno solare corrente è 0 poiche vi sono prestiti che sono stati prorogati ma non nell'anno selezionato");
	}
	
	@Test
	public void vero_se_count_numero_di_proroghe_per_anno_solare_ritorna_il_numero_dei_prestiti_che_sono_stati_prorogati_nell_anno_soloare_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		prestito.set_mai_prorogato(false);
		prestito.set_data_inizio_proroga(LocalDateTime.now());
		
		db.salva_prestito(prestito);
		
		assertEquals(1,query.count_numero_di_proroghe_per_anno_solare(LocalDateTime.now()),"Il numero di proroghe nell'anno solare selezionato");
	}

	@Test
	public void vero_se_select_count_numero_di_prestiti_perogni_fruitore_risuta_zero_perchè_non_vi_sono_prestiti_nell_anno_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		prestito.set_data_inizio_prestito(LocalDateTime.now());
		
		db.salva_prestito(prestito);
		
		assertEquals(0,query.select_count_numero_di_prestiti_perogni_fruitore(LocalDateTime.of(2012, 1, 1, 12, 12, 12)).get(fruitore).intValue());
	}
	
	@Test
	public void vero_se_select_count_numero_di_prestiti_perogni_fruitore_risulta_non_zero_perchè_vi_sono_prestiti_nell_anno_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		prestito.set_data_inizio_prestito(LocalDateTime.now());
		
		db.salva_prestito(prestito);
		
		assertEquals(1,query.select_count_numero_di_prestiti_perogni_fruitore(LocalDateTime.now()).get(fruitore).intValue());
	}
	
	@Test
	public void vero_se_select_risorsa_con_max_numero_prestiti_con_risorsa_massima_presente_nell_anno_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		prestito.set_data_inizio_prestito(LocalDateTime.now());
		db.salva_prestito(prestito);
		
		assertEquals(prestito.get_risorsa(),query.select_risorsa_con_max_numero_prestiti(LocalDateTime.now()));
	}
	
	@Test
	public void vero_se_select_risorsa_con_max_numero_prestiti_con_risorsa_massima_non_presente_nell_anno_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		prestito.set_data_inizio_prestito(LocalDateTime.of(2012, 1, 1, 1, 1, 1));
		prestito_2.set_data_inizio_prestito(LocalDateTime.now());
		db.salva_prestito(prestito);
		db.salva_prestito(prestito_2);
		
		assertEquals(prestito_2.get_risorsa(),query.select_risorsa_con_max_numero_prestiti(LocalDateTime.now()));
	}
	
}
