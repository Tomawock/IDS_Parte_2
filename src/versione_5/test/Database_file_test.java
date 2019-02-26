package versione_5.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilita.IO;
import versione_5.model.*;

class Database_file_test {

	public static final String ROOT= "ROOT";
	
	private Database_file db;
	private Utente utente,utente_2;
	private Fruitore fruitore,fruitore_2;
	private Operatore operatore, operatore_2;
	private Prestito prestito,prestito_2;
	private Risorsa risorsa,risorsa_2,risorsa_tipo_diversa;
	private Categoria root;
	
	@BeforeEach
	public void setup() {

		db=new Database_file();
		
		utente=new Utente("test", "test", "test",  LocalDateTime.of(2012,12,12,0,0) ,"test", "test", "test");
		utente_2=new Utente("test2", "test2", "test2",  LocalDateTime.of(2012,12,12,0,0) ,"test2", "test2", "test2");
		
		fruitore=new Fruitore(utente);
		fruitore_2=new Fruitore(utente_2);
		
		operatore=new Operatore(utente);
		operatore_2=new Operatore(utente_2);
		
		root = new Categoria(Database_file_test.ROOT);
		
		risorsa = new Film(1,1);
		risorsa_2 = new Film(2,1);
		risorsa_tipo_diversa = new Libro(1,1);
		
		prestito =new Prestito(risorsa, fruitore);
		prestito_2 =new Prestito(risorsa_2, fruitore_2);
	}
	
	/**
	 * Test Per cammini di base
	 */	
	@Test 
	public void vero_se_salva_utente_salva_senza_utenti_gia_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);	
		
		ArrayList<Utente> utenti=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_UTENTE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            utenti = (ArrayList<Utente>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(utente,utenti.get(0),"Salvataggio di un utente come oggetto sul file,senza altri utenti precedentemente salvati");
	}
	
	@Test 
	public void vero_se_salva_utente_salva_con_utenti_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);
		db.salva_utente(utente_2);
		
		ArrayList<Utente> utenti=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_UTENTE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            utenti = (ArrayList<Utente>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(utente_2,utenti.get(utenti.size()-1),"Salvataggio di un utente come oggetto sul file,con utenti precedentemente salvati");
	}
	
	@Test 
	public void vero_se_salva_fruitore_salva_senza_fruitori_gia_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_fruitore(fruitore);	
		
		ArrayList<Fruitore> fruitori=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_FRUITORE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            fruitori = (ArrayList<Fruitore>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(fruitore,fruitori.get(0),"Salvataggio di un fruitore come oggetto sul file,senza altri fruitori precedentemente salvati");
	}
	
	@Test 
	public void vero_se_salva_fruitore_salva_con_fruitori_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_fruitore(fruitore);
		db.salva_fruitore(fruitore_2);
		
		ArrayList<Fruitore> fruitori=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_FRUITORE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            fruitori = (ArrayList<Fruitore>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(fruitore_2,fruitori.get(fruitori.size()-1),"Salvataggio di un fruitore come oggetto sul file,con fruitori precedentemente salvati");
	}

	@Test
	public void vero_se_salva_operatore_salva_senza_operatori_gia_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);	
		
		ArrayList<Operatore> operatori=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_OPERATORE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            operatori = (ArrayList<Operatore>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(operatore,operatori.get(0),"Salvataggio di un operatore come oggetto sul file,senza altri operatori precedentemente salvati");
	}
	
	@Test 
	public void vero_se_salva_operatore_salva_con_operatori_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);
		db.salva_operatore(operatore_2);
		
		ArrayList<Operatore> operatori=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_OPERATORE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            operatori = (ArrayList<Operatore>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(operatore_2,operatori.get(operatori.size()-1),"Salvataggio di un operatore come oggetto sul file,con operatori precedentemente salvati");
	}
	
	@Test
	public void vero_se_carica_tutti_utenti_ha_esito_popsitivo() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);
		db.salva_utente(utente_2);
		
		ArrayList<Utente> utenti= new ArrayList<>();
		utenti.add(utente);
		utenti.add(utente_2);
		
		assertEquals(utenti, db.carica_tutti_utenti(),"Caricamento di tutti gli utenti presenti sul file");
	}

	@Test
	public void vero_se_carica_tutti_fruitori_ha_esito_popsitivo() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_fruitore(fruitore);
		db.salva_fruitore(fruitore_2);
		
		ArrayList<Fruitore> fruitori= new ArrayList<>();
		fruitori.add(fruitore);
		fruitori.add(fruitore_2);
		
		assertEquals(fruitori, db.carica_tutti_fruitori(),"Caricamento di tutti i fruitori presenti sul file");
	}
	
	@Test
	public void vero_se_carica_tutti_operatori_ha_esito_popsitivo() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);
		db.salva_operatore(operatore_2);
		
		ArrayList<Operatore> operatori= new ArrayList<>();
		operatori.add(operatore);
		operatori.add(operatore_2);
		
		assertEquals(operatori, db.carica_tutti_operatori(),"Caricamento di tutti gli operatori presenti sul file");
	}
	
	@Test
	public void vero_se_carica_utente_non_trova_utente_poiche_file_non_ha_utenti_salvati() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		
		assertEquals(null,db.carica_utente(utente.get_username(), utente.get_password()),"Caricamento nullo in quanto non vi sono utenti nel file");
	}
	
	@Test
	public void vero_se_carica_utente_trova_utente_nel_file_con_utenti_gia_presenti_username_e_password_corrispondono() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);
		
		assertEquals(utente,db.carica_utente(utente.get_username(), utente.get_password()),"Caricamento dell'utente corrispondete a username e password combacianti con uno presente nel file");
	}
	
	@Test
	public void vero_se_carica_utente_non_trova_utente_nel_file_con_utenti_gia_presenti_non_corrisponde_ne_username_ne_password() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);
		
		assertEquals(null,db.carica_utente(utente_2.get_username(), utente_2.get_password()),"Caricamento nullo in qunto non è stato trovato l'utente corrispondete a username e password combaciante nel file");
	}

	@Test
	public void vero_se_carica_utente_non_trova_utente_nel_file_con_utenti_gia_presenti_solo_username_corrisponde() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);
		
		assertEquals(null,db.carica_utente(utente.get_username(), utente_2.get_password()),"Caricamento dell'utente nullo in quanto combacia sul file solo con l'username");
	}
	
	@Test
	public void vero_se_carica_utente_non_trova_utente_nel_file_con_utenti_gia_presenti_solo_password_corrisponde() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);
		
		assertEquals(null,db.carica_utente(utente_2.get_username(), utente.get_password()),"Caricamento dell'utente nullo in quanto combacia sul file solo con la password");
	}

	@Test
	public void vero_se_carica_fruitore_non_trova_fruitore_poiche_file_non_ha_fruitori_salvati() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		assertEquals(null,db.carica_fruitore(fruitore.get_utente().get_username(), fruitore.get_utente().get_password()),"Caricamento nullo in quanto non vi sono fruitori nel file");
	}
	
	@Test
	public void vero_se_carica_fruitore_trova_fruitore_nel_file_con_fruitori_gia_presenti_username_e_password_corrispondono() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_fruitore(fruitore);
		
		assertEquals(fruitore,db.carica_fruitore(fruitore.get_utente().get_username(), fruitore.get_utente().get_password()),"Caricamento del fruitore corrispondete a username e password combacianti con uno presente nel file");
	}
	
	@Test
	public void vero_se_carica_fruitore_non_trova_fruitore_nel_file_con_fruitori_gia_presenti_non_corrisponde_ne_username_ne_password() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_fruitore(fruitore);
		
		assertEquals(null,db.carica_fruitore(fruitore_2.get_utente().get_username(), fruitore_2.get_utente().get_password()),"Caricamento nullo in qunto non è stato trovato il fruitore corrispondete a username e password combaciante nel file");
	}
	
	@Test
	public void vero_se_carica_fruitore_non_trova_fruitore_nel_file_con_fruitorI_gia_presenti_solo_username_corrisponde() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_fruitore(fruitore);
		
		assertEquals(null,db.carica_fruitore(fruitore.get_utente().get_username(), fruitore_2.get_utente().get_password()),"Caricamento dell fruitore nullo in quanto combacia sul file solo con l'username");
	}
	
	@Test
	public void vero_se_carica_fruitore_non_trova_fruitore_nel_file_con_fruitori_gia_presenti_solo_password_corrisponde() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_utente(utente);
		
		assertEquals(null,db.carica_fruitore(fruitore_2.get_utente().get_username(), fruitore.get_utente().get_password()),"Caricamento del fruitore nullo in quanto combacia sul file solo con la password");
	}
	
	public void vero_se_carica_operatore_non_trova_operatore_poiche_file_non_ha_operatori_salvati() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		
		assertEquals(null,db.carica_operatore(operatore.get_utente().get_username(), operatore.get_utente().get_password()),"Caricamento nullo in quanto non vi sono operatori nel file");
	}
	
	@Test
	public void vero_se_carica_operatore_trova_operatore_nel_file_con_operatori_gia_presenti_username_e_password_corrispondono() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);
		
		assertEquals(operatore,db.carica_operatore(operatore.get_utente().get_username(), operatore.get_utente().get_password()),"Caricamento dell'operatore corrispondete a username e password combacianti con uno presente nel file");
	}
	
	@Test
	public void vero_se_carica_operatore_non_trova_operatore_nel_file_con_operatori_gia_presenti_non_corrisponde_ne_username_ne_password() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);
		
		assertEquals(null,db.carica_operatore(operatore_2.get_utente().get_username(), operatore_2.get_utente().get_password()),"Caricamento nullo in qunto non è stato trovato l'operatore corrispondete a username e password combaciante nel file");
	}
	
	@Test
	public void vero_se_carica_operatore_non_trova_operatore_nel_file_con_operatori_gia_presenti_solo_username_corrisponde() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);
		
		assertEquals(null,db.carica_operatore(operatore.get_utente().get_username(), operatore_2.get_utente().get_password()),"Caricamento dell'operatore nullo in quanto combacia sul file solo con l'username");
	}
	
	@Test
	public void vero_se_carica_operatore_non_trova_operatore_nel_file_con_operatori_gia_presenti_solo_password_corrisponde() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);
		
		assertEquals(null,db.carica_operatore(operatore_2.get_utente().get_username(), operatore.get_utente().get_password()),"Caricamento dell'operatore nullo in quanto combacia sul file solo con la password");
	}
	
	@Test 
	public void vero_se_reset_utenti_resetta_il_file_con_un_novo_set_di_utenti() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		db.salva_utente(utente);
		db.salva_utente(utente_2);
		
		ArrayList<Utente> resettato= new ArrayList<>();
		resettato.add(utente);
		
		db.reset_utenti(resettato);
		
		assertEquals(resettato, db.carica_tutti_utenti(),"Il file è stato resettato con gli utenti desiderati");
	}
	
	@Test 
	public void vero_se_reset_fruitori_resetta_il_file_con_un_novo_set_di_fruitori() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		db.salva_fruitore(fruitore);
		db.salva_fruitore(fruitore_2);
		
		ArrayList<Fruitore> resettato= new ArrayList<>();
		resettato.add(fruitore);
		
		db.reset_fruitori(resettato);
		
		assertEquals(resettato, db.carica_tutti_fruitori(),"Il file è stato resettato con i fruitori desiderati");
	}
	
	@Test 
	public void vero_se_reset_operatori_resetta_il_file_con_un_novo_set_di_operatori() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		db.salva_operatore(operatore);
		db.salva_operatore(operatore_2);
		
		ArrayList<Operatore> resettato= new ArrayList<>();
		resettato.add(operatore);
		
		db.reset_operatori(resettato);
		
		assertEquals(resettato, db.carica_tutti_operatori(),"Il file è stato resettato con gli operatori desiderati");
	}

	@Test
	public void vero_se_elimina_utente_non_modifica_il_file_se_non_trova_utente_da_eliminare() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		
		db.salva_utente(utente);
		ArrayList<Utente> prima= new ArrayList<>();
		prima =db.carica_tutti_utenti();
		
		db.elimina_utente(utente_2);
		ArrayList<Utente> dopo= new ArrayList<>();
		dopo=db.carica_tutti_utenti();
		
		assertEquals(prima,dopo,"Il File non è stato modificato in qunato l'utente da elimanre non era presente");
	}
	
	@Test
	public void vero_se_elimina_utente_elimina_utente_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		
		db.salva_utente(utente);
		
		db.elimina_utente(utente);
		ArrayList<Utente> dopo= new ArrayList<>();
		dopo=db.carica_tutti_utenti();
		
		assertTrue(dopo.isEmpty(),"Il File è stato modificato eliminado l'utente selezionato");
	}
	
	@Test
	public void vero_se_elimina_fruitore_non_modifica_il_file_se_non_trova_fruitore_da_eliminare() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		db.salva_utente(utente);
		ArrayList<Fruitore> prima= new ArrayList<>();
		prima =db.carica_tutti_fruitori();
		
		db.elimina_fruitore(fruitore_2);
		ArrayList<Fruitore> dopo= new ArrayList<>();
		dopo=db.carica_tutti_fruitori();
		
		assertEquals(prima,dopo,"Il File non è stato modificato in qunato il fruitore da elimanre non era presente");
	}
	
	@Test
	public void vero_se_elimina_fruitore_elimina_fruitore_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		db.salva_fruitore(fruitore);
		
		db.elimina_fruitore(fruitore);
		ArrayList<Fruitore> dopo= new ArrayList<>();
		dopo=db.carica_tutti_fruitori();
		
		assertTrue(dopo.isEmpty(),"Il File è stato modificato eliminado il fruitore selezionato");
	}
	
	@Test
	public void vero_se_elimina_operatore_non_modifica_il_file_se_non_trova_fruitore_da_eliminare() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		
		db.salva_operatore(operatore);
		ArrayList<Operatore> prima= new ArrayList<>();
		prima =db.carica_tutti_operatori();
		
		db.elimina_operatore(operatore_2);
		ArrayList<Operatore> dopo= new ArrayList<>();
		dopo=db.carica_tutti_operatori();
		
		assertEquals(prima,dopo,"Il File non è stato modificato in qunato l'operatore da elimanre non era presente");
	}
	
	@Test
	public void vero_se_elimina_operatore_elimina_operatore_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_OPERATORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		
		db.salva_operatore(operatore);
		
		db.elimina_operatore(operatore);
		ArrayList<Operatore> dopo= new ArrayList<>();
		dopo=db.carica_tutti_operatori();
		
		assertTrue(dopo.isEmpty(),"Il File è stato modificato eliminado l'operatore selezionato");
	}
	
	@Test
	public void vero_se_aggiorna_validita_fruitori_non_elimina_fruitori_non_piu_validi_poiche_non_vi_sono_fruitori_sul_file() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		db.aggiorna_validita_fruitori();
		
		assertTrue(db.carica_tutti_fruitori().isEmpty(),"Aggiornare la valida dei fruitori non modifica il file in qunato non vi erano fruitori gia presenti sul file");
	}
	
	@Test
	public void vero_se_aggiorna_validita_fruitori_non_elimina_fruitori_non_piu_validi_poiche_sono_tutti_validi() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		fruitore.set_data_fine_iscrizione(LocalDateTime.now().plusDays(1));
			
		db.salva_fruitore(fruitore);
		ArrayList<Fruitore> prima= new ArrayList<>();
		prima.add(fruitore);
		
		db.aggiorna_validita_fruitori();
		
		assertEquals(prima,db.carica_tutti_fruitori(),"Aggiornare la valida dei fruitori non modifica il file in qunato i fruitoti presenti erano tutti validi");
	}
	
	@Test
	public void vero_se_aggiorna_validita_fruitori_elimina_fruitori_non_piu_validi() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		fruitore.set_data_fine_iscrizione(LocalDateTime.now().minusDays(1));//se si mette solo il setvalidita il codice lo ripristina al valore corretto secondo la logica, quindi bisogna avere un oggetto effetivamnete scaduto 
		
		db.salva_fruitore(fruitore);
		ArrayList<Fruitore> prima= new ArrayList<>();
		prima.add(fruitore);
		
		db.aggiorna_validita_fruitori();
		
		assertTrue(db.carica_tutti_fruitori().isEmpty(),"Aggiornare la valida dei fruitori modifica il file eliminando i fruitori non piu validi");
	}

	@Test
	public void vero_se_is_presente_trova_utente() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		
		db.salva_utente(utente);
		
		assertTrue(db.is_presente(utente));
	}

	@Test
	public void vero_se_is_presente_non_trova_utente() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_UTENTE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		
		db.salva_utente(utente);
		
		assertFalse(db.is_presente(utente_2));
	}

	@Test 
	public void vero_se_salva_categoria_root_salva_categoria_sul_file() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		db.salva_categoria_root(root);	
		
		Categoria base= new Categoria("");
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_CATEGORIE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            base = (Categoria)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertTrue(root.equals(base),"Salvataggio di una categoria come oggetto sul file");
	}
	
	@Test
	public void vero_se_carica_categoria_root_carica_la_categoria_root_dal_file() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		db.salva_categoria_root(root);	
		
		assertEquals(root, db.carica_root_categorie(),"La categoria redice è stata caricata in modo corretto dal file");
	}
	
	public void vero_se_salva_prestito_salva_senza_prestitii_gia_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		db.salva_prestito(prestito);	
		
		ArrayList<Prestito> prestiti=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_PRESTITI); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            prestiti = (ArrayList<Prestito>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(prestito,prestiti.get(0),"Salvataggio di un prestito come oggetto sul file,senza altri prestiti precedentemente salvati");
	}
	
	@Test 
	public void vero_se_salva_prestito_salva_con_prestiti_presenti(){
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		db.salva_prestito(prestito);
		db.salva_prestito(prestito_2);
		
		ArrayList<Prestito> prestiti=new ArrayList<>();
		try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_PRESTITI); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            prestiti = (ArrayList<Prestito>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
		assertEquals(prestito_2,prestiti.get(prestiti.size()-1),"Salvataggio di un prestito come oggetto sul file,con prestiti precedentemente salvati");
	}
	
	@Test
	public void vero_se_carica_tutti_prestiti_ha_esito_popsitivo() {
		//elimino il file per esere sicuro di non avere altri utenti salvati sullo stesso file
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		db.salva_prestito(prestito);
		db.salva_prestito(prestito_2);
		
		ArrayList<Prestito> prestiti= new ArrayList<>();
		prestiti.add(prestito);
		prestiti.add(prestito_2);
		
		assertEquals(prestiti, db.carica_tutti_prestiti(),"Caricamento di tutti i prestiti presenti sul file");
	}

	@Test
	public void vero_se_get_prestiti_per_fruitore_risorsa_ritorna_un_array_vuoto_poiche_non_trova_fruitore_corrispondente_ma_esistono_risorse_compatibili()
	{
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		db.salva_prestito(prestito);
		
		assertTrue(db.get_prestiti_per_fruitore_risorsa(fruitore_2, risorsa).isEmpty(),"Array vuoto in qunato non è presente il fruitore desiderato");
		
	}
	
	@Test
	public void vero_se_get_prestiti_per_fruitore_risorsa_ritorna_un_array_vuoto_poiche_trova_fruitore_corrispondente_ma_non_esistono_risorse_compatibili()
	{
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		db.salva_prestito(prestito);
		
		assertTrue(db.get_prestiti_per_fruitore_risorsa(fruitore, risorsa_tipo_diversa).isEmpty(),"Array vuoto in qunato è presente il fruitore desiderato ma non il tipo di risorsa");
		
	}
	
	@Test
	public void vero_se_get_prestiti_per_fruitore_risorsa_ritorna_un_array_vuoto_poiche_non_fruitore_corrispondente_e_non_esistono_risorse_compatibili()
	{
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		db.salva_prestito(prestito);
		
		assertTrue(db.get_prestiti_per_fruitore_risorsa(fruitore_2, risorsa_tipo_diversa).isEmpty(),"Array vuoto in qunato non è presente il fruitore desiderato e neanche il tipo di risorsa");
		
	}
	
	@Test
	public void vero_se_get_prestiti_per_fruitore_risorsa_ritorna_un_array_non_vuoto_poiche_trova_fruitore_corrispondente_e_esistono_risorse_compatibili()
	{
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		db.salva_prestito(prestito);
		
		assertFalse(db.get_prestiti_per_fruitore_risorsa(fruitore, risorsa).isEmpty(),"Array non vuoto in qunato è presente il fruitore desiderato e il tipo di risorsa");
		
	}
	
	@Test
	public void vero_se_get_tutti_prestiti_per_fruitore_ritorna_un_array_vuoto_poiche_non_trova_fruitore_corrispondente()
	{
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		db.salva_prestito(prestito);
		
		assertTrue(db.get_tutti_prestiti_per_fruitore(fruitore_2).isEmpty(),"Array vuoto in qunato non è presente il fruitore desiderato");
		
	}
	
	@Test
	public void vero_se_get_prestiti_per_fruitore_risorsa_ritorna_un_array_poiche_trova_fruitore_corrispondente()
	{
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		db.salva_prestito(prestito);
		
		assertFalse(db.get_tutti_prestiti_per_fruitore(fruitore).isEmpty(),"Array non vuoto in qunato è presente il fruitore desiderato");
		
	}
	
	@Test 
	public void vero_se_reset_prestiti_resetta_il_file_con_un_novo_set_di_prestiti() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		db.salva_prestito(prestito);
		db.salva_prestito(prestito_2);
		
		ArrayList<Prestito> resettato= new ArrayList<>();
		resettato.add(prestito);
		
		db.reset_prestiti(resettato);
		
		assertEquals(resettato, db.carica_tutti_prestiti(),"Il file è stato resettato con i prestiti desiderati");
	}
	
	@Test
	public void vero_se_aggiorna_descrizione_prestiti_non_aggiorna_il_prestito_in_qunato_non_è_cambiato_il_suo_stato_sul_file() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		db.salva_prestito(prestito);
		
		db.aggiorna_descrizione_prestiti();
		
		assertEquals(prestito, db.carica_tutti_prestiti().get(0),"Il prestito non è stato modificato percio l'aggionramento non muta i suio dati");
	}
	
	@Test
	public void vero_se_aggiorna_descrizione_prestiti_aggiorna_il_prestito_in_qunato_è_cambiato_il_suo_stato_sul_file() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		db.salva_prestito(prestito);
		
		risorsa.aggiungi_descrizione(new ArrayList<>(Arrays.asList("1","2","3","4","5")));
		
		db.aggiorna_prestito(prestito);
		
		db.aggiorna_descrizione_prestiti();
		
		assertEquals(prestito, db.carica_tutti_prestiti().get(0),"Il prestito è stato modificato percio l'aggionramento muta i suio dati");
	}
	
	@Test
	public void vero_se_aggiorna_prestito_non_aggiorna_il_prestito_poiche_non_presente_sul_file() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		db.salva_prestito(prestito);
		
		
		db.aggiorna_prestito(prestito_2);
		
		assertEquals(prestito,db.carica_tutti_prestiti().get(0),"Aggiornane un prestito non presente sul file non modifica il file" );
	}
	
	@Test
	public void vero_se_aggiorna_prestito_aggiorna_il_prestito_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		db.salva_prestito(prestito);
		prestito.set_data_inizio_proroga(LocalDateTime.now().plusDays(1));
		
		db.aggiorna_prestito(prestito);
		
		assertEquals(prestito,db.carica_tutti_prestiti().get(0),"Aggiornane un prestito non presente sul file modifica il file" );
	}
	
	@Test
	public void vero_se_aggiorna_fruitore_non_aggiorna_il_fruitore_poiche_non_presente_sul_file() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		db.salva_fruitore(fruitore);
		
		db.aggiorna_fruitore(fruitore_2);
		
		assertEquals(fruitore,db.carica_tutti_fruitori().get(0),"Aggiornane un fruitore non presente sul file non modifica il file" );
	}
	
	@Test
	public void vero_se_aggiorna_fruitore_aggiorna_il_fruitore_selezionato() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		db.salva_fruitore(fruitore);
		fruitore.set_data_iscrizione(LocalDateTime.now().plusDays(1));
		
		db.aggiorna_fruitore(fruitore);
		
		assertEquals(fruitore,db.carica_tutti_fruitori().get(0),"Aggiornane un fruitore non presente sul file modifica il file" );
	}
	
	@Test
	public void vero_se_aggiorna_validia_prestiti_non_aggiorna_i_prestiti_poiche_non_vi_sono_prestiti_presenti_sul_file() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_PRESTITI);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		
		db.aggiorna_validita_prestiti();
		
		assertTrue(db.carica_tutti_prestiti().isEmpty(),"Aggiornare la validita dei prestiti non modifica il file se non ci sono prestiti presenti sul file");
	}
	
	@Test
	public void vero_se_aggiorna_validia_prestiti_rimuove_i_prestiti_terminati() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		prestito.set_data_fine_prestito(LocalDateTime.now());
		db.salva_prestito(prestito);
		
		db.aggiorna_validita_prestiti();
		
		assertTrue(db.carica_tutti_prestiti().isEmpty(),"Aggiornare la valida dei prestiti modifica il file se vengono trovati dei prestiti terminati" );
	}
	@Test
	public void vero_se_aggiorna_validia_prestiti_non_rimuove_i_prestiti_non_terminati() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_FRUITORE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		
		prestito.set_data_fine_prestito(LocalDateTime.now().plusDays(1));
		db.salva_prestito(prestito);
		
		db.aggiorna_validita_prestiti();
		
		assertFalse(db.carica_tutti_prestiti().isEmpty(),"Aggiornare la valida dei prestiti non modifica il file poiche non ci sono prestiti terminati" );
	}
	
	@Test
	public void vero_se_ricerca_per_descrizione_non_trova_nessuna_risorsa_poiche_quelle_presenti_sono_di_un_altro_tipo() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		
		root.add_risorsa(risorsa_tipo_diversa);
		
		db.salva_categoria_root(root);
		
		assertTrue(db.ricerca_per_descrizione(risorsa).isEmpty(),"Non trova risorse corripondenti alla risorsa in qunato il tipo din risorsa non è presente");
	}
	
	@Test
	public void vero_se_ricerca_per_descrizione_non_trova_nessuna_risorsa_poiche_la_descrizione_non_combacia() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		
		root.add_risorsa(risorsa);
		risorsa_2.aggiungi_descrizione(new ArrayList<>(Arrays.asList("1","2","3","4","5")));
		
		db.salva_categoria_root(root);
		
		assertTrue(db.ricerca_per_descrizione(risorsa_2).isEmpty(),"Non trova risorse corrispondenti in qunato la descrizione è diversa");
	}
	
	@Test
	public void vero_se_ricerca_per_descrizione_non_trova_nessuna_risorsa_poiche_la_descrizione_non_combacia_e_le_descrizioni_presenti_sono_di_un_altro_tipo() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		
		root.add_risorsa(risorsa_tipo_diversa);
		risorsa_2.aggiungi_descrizione(new ArrayList<>(Arrays.asList("1","2","3","4","5")));
		
		db.salva_categoria_root(root);
		
		assertTrue(db.ricerca_per_descrizione(risorsa_2).isEmpty(),"Non trova risorse corrispondenti in qunato la descrizione è diversae anche il tipo di risorsa ricercata");
	}
	
	@Test
	public void vero_se_ricerca_per_descrizione_trova_la_risorsa_poiche_la_descrizione_combacia() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		
		root.add_risorsa(risorsa);
		
		db.salva_categoria_root(root);
		
		assertFalse(db.ricerca_per_descrizione(risorsa).isEmpty(),"Trova risorse corrispondenti alla descrizione");
	}
	
	@Test
	public void vero_se_get_n_copie_disponibili_by_id_non_trova_la_risorsa_selezionata() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		
		root.add_risorsa(risorsa);
		
		db.salva_categoria_root(root);
		
		assertEquals(-1,db.get_n_copie_disponibili_by_id(risorsa_2.get_id()),"Non trova la risorsa con l'id selezionato");
	}
	@Test
	public void vero_se_get_n_copie_disponibili_by_id_trova_la_risorsa_selezionata() {
		File file_eliminato= new File(Database_file.PERCORSO_FILE_CATEGORIE);
		file_eliminato.delete();
		
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		
		root.add_risorsa(risorsa);
		
		db.salva_categoria_root(root);
		
		assertEquals(risorsa.get_id(),db.get_n_copie_disponibili_by_id(risorsa.get_id()),"Non trova la risorsa con l'id selezionato");
	}
}
