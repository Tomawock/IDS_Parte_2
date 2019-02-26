package versione_5.model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import utilita.IO;

public class Database_file implements Salvataggio{
	//VERSIONE LOCALE DEI DATI
	public static final String PERCORSO_FILE_UTENTE="src/Local_database/db_utenti";
	public static final String PERCORSO_FILE_FRUITORE="src/Local_database/db_fruitori";
	public static final String PERCORSO_FILE_OPERATORE="src/Local_database/db_operatore";
	public static final String PERCORSO_FILE_CATEGORIE="src/Local_database/db_categorie";
	public static final String PERCORSO_FILE_PRESTITI="src/Local_database/db_prestiti";

	@Override
	public void salva_utente(Utente utente) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		//carica il db e poi aggiungo il singolo utente in coda ad esso e lo salvo
		ArrayList<Utente> utenti =this.carica_tutti_utenti();
		if (utenti==null) {
			utenti=new ArrayList<Utente>();
		}
		utenti.add(utente);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_UTENTE); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(utenti); 
              
            out.close(); 
            file.close(); 
              
            //System.out.println("utente serializzato"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught"); 
        } 
        
        //Salva l'utente anche sull'archivio
	}

	@Override
	public void salva_fruitore(Fruitore fruitore) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		//carica il db e poi aggiungo il singolo utente in coda ad esso e lo salvo
		ArrayList<Fruitore> fruitori =this.carica_tutti_fruitori();
		if (fruitori==null) {
			fruitori=new ArrayList<Fruitore>();
		}
		fruitori.add(fruitore);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_FRUITORE); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(fruitori); 
              
            out.close(); 
            file.close(); 
              
            //System.out.println("fruitore serializzato"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught"); 
        } 	
	}

	@Override
	public void salva_operatore(Operatore operatore) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		//carica il db e poi aggiungo il singolo utente in coda ad esso e lo salvo
		ArrayList<Operatore> operatori =this.carica_tutti_operatori();
		if (operatori==null) {
			operatori=new ArrayList<Operatore>();
		}
		operatori.add(operatore);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_OPERATORE); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(operatori); 
              
            out.close(); 
            file.close(); 
              
//            System.out.println("operatore serializzato"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught1"); 
        }
	}

	@Override
	public Utente carica_utente(String username, String psw) {
		ArrayList<Utente> utenti= new ArrayList<Utente>();
		utenti=this.carica_tutti_utenti();
		if (utenti==null) {
			return null;
		}
		else{
			for(Utente u:utenti) {
				if (u.get_username().equals(username)&& u.get_password().equals(psw))
					return u;
			}
			return null;
		}
	}

	@Override
	public Fruitore carica_fruitore(String username, String psw) {
		ArrayList<Fruitore> fruitori= new ArrayList<Fruitore>();
		fruitori=this.carica_tutti_fruitori();
		if (fruitori==null) {
			return null;
		}
		else{
			for(Fruitore f:fruitori) {
				Utente u=f.get_utente();
				if (u.get_username().equals(username)&& u.get_password().equals(psw))
					return f;
			}
			return null;
		}
	}
	
	@Override
	public Operatore carica_operatore(String username, String psw) {
		ArrayList<Operatore> operatori= new ArrayList<Operatore>();
		operatori=this.carica_tutti_operatori();
		if (operatori==null) {
			return null;
		}
		else{
			for(Operatore o:operatori) {
				Utente u=o.get_utente();
				if (u.get_username().equals(username)&& u.get_password().equals(psw))
					return o;
			}
			return null;
		}
	}

	@Override
	public ArrayList<Utente> carica_tutti_utenti() {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		//creo arraylist per gli utenti 
		ArrayList<Utente> utenti =new ArrayList<Utente>();
		// Deserialization 
        try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_UTENTE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            utenti = (ArrayList<Utente>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
//            System.out.println("Utenti deserializzati"); 
        }catch(EOFException ex) {
       	 	//System.out.println("EOFExceptionis caught"); // si verifica sempre qunado creo il file la prima volta ma non è fondamentale 
        }catch(IOException ex) {
        	System.err.println("IOException is caught"); 
        }catch(ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException is caught"); 
        } 	
		return utenti;
	}

	@Override
	public ArrayList<Fruitore> carica_tutti_fruitori() {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		//creo arraylist per gli utenti 
		ArrayList<Fruitore> fruitori =new ArrayList<Fruitore>();
		// Deserialization 
        try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_FRUITORE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            fruitori = (ArrayList<Fruitore>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
//            System.out.println("fruitori deserializzati"); 
        }catch(EOFException ex) {
       	 	//System.out.println("EOFExceptionis caught"); // si verifica sempre qunado creo il file la prima volta ma non è fondamentale 
        }catch(IOException ex) {
        	System.err.println("IOException is caught"); 
        }catch(ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException is caught"); 
        } 	
		return fruitori;
	}

	@Override
	public ArrayList<Operatore> carica_tutti_operatori() {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		//creo arraylist per gli utenti 
		ArrayList<Operatore> operatori =new ArrayList<Operatore>();
		// Deserialization 
        try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_OPERATORE); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            operatori = (ArrayList<Operatore>)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
//            System.out.println("operatori deserializzati"); 
        }catch(EOFException ex) {
       	 	//System.out.println("EOFExceptionis caught"); // si verifica sempre qunado creo il file la prima volta ma non è fondamentale 
        }catch(IOException ex) {
        	System.err.println("IOException is caught"); 
        }catch(ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException is caught"); 
        } 	
		return operatori;
	}
	
	@Override
	public void elimina_utente(Utente utente) {
		ArrayList<Utente> utenti=this.carica_tutti_utenti();
		if (!utenti.contains(utente)) {
			//impossibile eliminare utente poichè non è presente nel db
			
		}else {
			utenti.remove(utenti.indexOf(utente));
			this.reset_utenti(utenti);
			//System.out.println(utenti.indexOf(utente));
		}	
	}

	@Override
	public void elimina_fruitore(Fruitore fruitore) {
		ArrayList<Fruitore> fruitori=this.carica_tutti_fruitori();
		if (!fruitori.contains(fruitore)) {
			//impossibile eliminare utente poichè non è presente nel db
			
		}else {
			fruitori.remove(fruitori.indexOf(fruitore));
			this.reset_fruitori(fruitori);
		}	
	}

	@Override
	public void elimina_operatore(Operatore operatore) {
		ArrayList<Operatore> operatori=this.carica_tutti_operatori();
		if (!operatori.contains(operatore)) {
			//impossibile eliminare utente poichè non è presente nel db
			
		}else {
			operatori.remove(operatori.indexOf(operatore));
			this.reset_operatori(operatori);
		}
	}

	@Override
	public void reset_utenti(ArrayList<Utente> utenti) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_UTENTE);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_UTENTE); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(utenti); 
              
            out.close(); 
            file.close(); 
              
//            System.out.println("Utenti resettati"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught"); 
        }	
	}

	@Override
	public void reset_fruitori(ArrayList<Fruitore> fruitori) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_FRUITORE);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_FRUITORE); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(fruitori); 
              
            out.close(); 
            file.close(); 
              
//            System.out.println("Fruitori resettati"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught"); 
        }	
		
	}

	@Override
	public void reset_operatori(ArrayList<Operatore> operatori) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_OPERATORE);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_OPERATORE); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(operatori); 
              
            out.close(); 
            file.close(); 
              
//            System.out.println("Operatori resettati"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught"); 
        }
		
	}

	@Override
	public void aggiorna_validita_fruitori() {
		ArrayList<Fruitore> fruitori=this.carica_tutti_fruitori();
		
		if(fruitori!=null) {
			for(Fruitore f :fruitori) {
				if (!f.is_valido()){
					this.elimina_fruitore(f);
				}
			}
		}
	}
	
	@Override
	public boolean is_presente(Utente utente) {
		return (this.carica_tutti_utenti().contains(utente));
	}
	
	@Override
	public Categoria carica_root_categorie() {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		//creo arraylist per gli utenti 
		Categoria root =new Categoria("");
		// Deserialization 
	    try {    
	        // Reading the object from a file 
	        FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_CATEGORIE); 
	        ObjectInputStream in = new ObjectInputStream(file); 
	          
	        // root for deserialization of object 
	        root = (Categoria)in.readObject(); 
	          
	        in.close(); 
	        file.close(); 
	          
		    //System.out.println("operatori deserializzati"); 
	    }catch(EOFException ex) {
	   	 	//System.out.println("EOFExceptionis caught"); // si verifica sempre qunado creo il file la prima volta ma non è fondamentale 
	    }catch(IOException ex) {
	    	System.err.println("IOException is caught"); 
	    }catch(ClassNotFoundException ex) {
	        System.err.println("ClassNotFoundException is caught"); 
	    } 	
		return root;
	}

	@Override
	public void salva_categoria_root(Categoria root) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_CATEGORIE);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_CATEGORIE); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(root); 
              
            out.close(); 
            file.close(); 
              
            //System.out.println("utente serializzato"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught"); 
        } 
		
	}
		
	@Override
	public ArrayList<Prestito> get_prestiti_per_fruitore_risorsa(Fruitore fruitore, Risorsa res) {
		ArrayList<Prestito>prestiti=this.carica_tutti_prestiti();
		ArrayList<Prestito>risultato=new ArrayList<>();
		for(Prestito p:prestiti) {
			if(p.get_fruitore().equals(fruitore) && 
					res.getClass().getSimpleName().equals(p.get_risorsa().getClass().getSimpleName())){
				risultato.add(p);
			}
		}
		return risultato;	
	}

	@Override
	public void salva_prestito(Prestito prestito) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		//carica il db e poi aggiungo il singolo utente in coda ad esso e lo salvo
		ArrayList<Prestito> prestiti =this.carica_tutti_prestiti();
		if (prestiti==null) {
			prestiti=new ArrayList<Prestito>();
		}
		prestiti.add(prestito);
		// Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_PRESTITI); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(prestiti); 
              
            out.close(); 
            file.close(); 
              
            //System.out.println("utente serializzato"); 
  
        }catch(IOException ex) { 
            System.err.println("IOException is caught"); 
        }
	}

	@Override
	public ArrayList<Prestito> get_tutti_prestiti_per_fruitore(Fruitore fruitore) {
		ArrayList<Prestito>prestiti=this.carica_tutti_prestiti();
		ArrayList<Prestito>risultato=new ArrayList<>();
		for(Prestito p:prestiti) {
			if(p.get_fruitore().equals(fruitore)){
				risultato.add(p);
			}
		}
		return risultato;
	}
	
	@Override
	public ArrayList<Prestito> carica_tutti_prestiti() {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		//creo arraylist per gli utenti 
		ArrayList<Prestito> prestiti =new ArrayList<Prestito>();
		// Deserialization 
        try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(Database_file.PERCORSO_FILE_PRESTITI); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            prestiti = (ArrayList<Prestito>)in.readObject(); 
              
            in.close(); 
            file.close(); 
        }catch(EOFException ex) {
       	 	//System.out.println("EOFExceptionis caught"); // si verifica sempre qunado creo il file la prima volta ma non è fondamentale 
        }catch(IOException ex) {
        	System.err.println("IOException is caught"); 
        }catch(ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException is caught"); 
        } 	
		return prestiti;
	}
	
	@Override
	public void aggiorna_descrizione_prestiti() {
		ArrayList<Prestito> prestiti=this.carica_tutti_prestiti();
		Categoria cat=this.carica_root_categorie();
		ArrayList<Risorsa> risorse_db=new ArrayList<>();
		cat.carica_tutte_risorse(cat, risorse_db);
		for(Prestito p:prestiti) {
			for(Risorsa r:risorse_db) {
				if(p.get_risorsa().equals(r)) {
					p.set_risorsa(r);
				}
			}
		}
		this.reset_prestiti(prestiti);
	}
	
	@Override
	public void reset_prestiti(ArrayList<Prestito> prestiti) {
		//crea il file nel percorso se non e presente
		IO.CreaFile(Database_file.PERCORSO_FILE_PRESTITI);
		// Serialization  
	    try{    
	        //Saving of object in a file 
	        FileOutputStream file = new FileOutputStream(Database_file.PERCORSO_FILE_PRESTITI); 
	        ObjectOutputStream out = new ObjectOutputStream(file); 
	          
	        // Method for serialization of object 
	        out.writeObject(prestiti); 
	          
	        out.close(); 
	        file.close(); 
	          
            //System.out.println("Operatori resettati"); 
	  
	        }catch(IOException ex) { 
	            System.err.println("IOException is caught"); 
	    }	
	}

	@Override
	public void aggiorna_prestito(Prestito prestito) {
		ArrayList<Prestito> prestiti=this.carica_tutti_prestiti();
		for(Prestito p:prestiti) {
			if(p.equals(prestito)) {
				p.reset_dati(prestito);
			}
		}
		this.reset_prestiti(prestiti);
	}

	@Override
	public void aggiorna_fruitore(Fruitore fruitore) {
		ArrayList<Fruitore> fruitori=this.carica_tutti_fruitori();
		for(Fruitore p:fruitori) {
			if(p.equals(fruitore)) {
				p.reset_dati(fruitore);
			}
		}
		this.reset_fruitori(fruitori);
	}

	@Override
	public void aggiorna_validita_prestiti() {
		ArrayList<Prestito> prestiti=carica_tutti_prestiti();
		ArrayList<Prestito> risultato=new ArrayList<>();
		for(Prestito p:prestiti) {
			if(!p.is_terminato()) {
				risultato.add(p);
			}
		}
		this.reset_prestiti(risultato);		
	}

	@Override
	public ArrayList<Risorsa> ricerca_per_descrizione(Risorsa risorsa) {
		Categoria root=this.carica_root_categorie();
		ArrayList<Risorsa> risorse=new ArrayList<>();
		ArrayList<Risorsa> risultato=new ArrayList<>();
		root.carica_tutte_risorse(root, risorse);
		for(Risorsa r:risorse) {
			if(r.getClass().equals(risorsa.getClass()) && r.equals_by_descrizione(risorsa.get_descrizione())) {
				risultato.add(r);
			}
		}
		return risultato;
	}

	@Override
	public int get_n_copie_disponibili_by_id(int id) {
		Categoria root=this.carica_root_categorie();
		Risorsa ris=root.get_risorsa_by_id(root, id);
		if(ris==null)
			return -1;
		return ris.get_disponibili();
	}

	public static void main (String[] args) {
		Database_file db=new Database_file();
		
		Utente u=new Utente("test", "test", "test",  LocalDateTime.of(2012,12,12,0,0) ,"test", "test", "test");
		Utente u2=new Utente("test2", "test2", "test2",  LocalDateTime.of(2012,12,12,0,0) ,"test2", "test2", "test2");
		
		Fruitore f=new Fruitore(u);
		Fruitore f2=new Fruitore(u2);
		
		Operatore o=new Operatore(u);
		Operatore o2=new Operatore(u2);
		
		db.salva_utente(u);
		db.salva_utente(u2);
		
		db.salva_fruitore(f);
		db.salva_fruitore(f2);
		
		db.salva_operatore(o);
		db.salva_operatore(o2);
		
		System.out.println("**************UTENTI********************");
		ArrayList<Utente> utenti=db.carica_tutti_utenti();
		for(Utente ut:utenti) {
			System.out.println(ut.toString());
		}
		
		System.out.println("**************FRUITORI********************");
		ArrayList<Fruitore> fruitori=db.carica_tutti_fruitori();
		for(Fruitore fr:fruitori) {
			System.out.println(fr.toString());
		}
		
		System.out.println("**************OPERATORI********************");
		ArrayList<Operatore> operatrori=db.carica_tutti_operatori();
		for(Operatore op:operatrori) {
			System.out.println(op.toString());
		}
		
		System.out.println("SELECT UTENTE TRUE");
		System.out.println(db.carica_utente("test", "test").toString());
		System.out.println("SELECT UTENTE FALSE");
		String result=(db.carica_utente("test3", "test3")==null)?"Utente O Password Errati":"OK";
		System.out.println(result);
		
		System.out.println("SELECT FRUITORE TRUE");
		System.out.println(db.carica_fruitore("test", "test").toString());
		System.out.println("SELECT FRUITORE FALSE");
		String result1=(db.carica_fruitore("test3", "test3")==null)?"Utente O Password Errati":"OK";
		System.out.println(result1);
		
		System.out.println("SELECT OPERATORE TRUE");
		System.out.println(db.carica_operatore("test", "test").toString());
		System.out.println("SELECT OPERATORE FALSE");
		String result2=(db.carica_operatore("test3", "test3")==null)?"Utente O Password Errati":"OK";
		System.out.println(result2);
		
		System.out.println("ELIMINA UTENTE TRUE");
		db.elimina_utente(u);
		String result3=(db.carica_utente(u.get_username(),u.get_password())==null)?"Utente Eliminato":"OK";
		System.out.println(result3);
		
		System.out.println("ELIMINA FRUITORE TRUE");
		db.elimina_fruitore(f);
		String result4=(db.carica_fruitore(f.get_utente().get_username(),f.get_utente().get_password())==null)?"Fruitore Eliminato":"OK";
		System.out.println(result4);
		
		System.out.println("ELIMINA OPERATORE TRUE");
		db.elimina_operatore(o);
		String result5=(db.carica_operatore(o.get_utente().get_username(),o.get_utente().get_password())==null)?"Operatore Eliminato":"OK";
		System.out.println(result5);
		
	}
}
