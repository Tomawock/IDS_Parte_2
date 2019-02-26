package versione_5.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utilita.*;
import versione_5.model.*;
import versione_5.view.*;

public class Controller {

	private View view;				//Parte Grafica
	private Salvataggio db;			//database locale
	private Salvataggio archivio;	//parte dell'archivio
	private Query query;			//parte delle query
	
	public Controller() {
		view=new View();
		db=new Database_file();
		archivio = new Archivio();
		query= new Query(archivio);	// le query si basano sull'archivio e non sui dati locali
	}
	
	/**
	 * Funzione principale che consente di accedere come utente o di registarrsi o di terminare l'esecuzione del programma	
	 */
	public void log_in(){
		int valore=view.log_in_scelta();//visualizza la view di log in
		db.aggiorna_validita_fruitori();//elimina eventuali fruitori non validi
		if (valore== 1) {//Effetto il log in come utente
			String utente=view.log();
			String utente_user=utente.split(IO.SEPARATORE_STRINGHE)[0];
			String utente_psw=utente.split(IO.SEPARATORE_STRINGHE)[1];
			//controlla che l'utente sia registrato nel Salvataggio
			if(db.carica_utente(utente_user, utente_psw)==null) {
				view.scrivi("Utente o passw errati");
				this.log_in();
			}
			else {
				view.scrivi("Hai effettuato correttamente l'accesso");
				this.user_loggato(db.carica_utente(utente_user, utente_psw));
			}
		}
		else if(valore == 2) {//Registro un nuovo utente
			ArrayList<String> nuovo_utente=view.nuova_registrazione();
			Utente user= new Utente(nuovo_utente);
			if(!db.is_presente(user)) {//controlla che l'utente non sia gia registrato(NON esistono due utenti con lo stesso username)
				db.salva_utente(user);
				archivio.salva_utente(user);//Aggiungo l'utente nell'archivio 
				this.view.scrivi("Ti sei iscritto correttamente "+user.get_username());
			}else {
				this.view.scrivi("Username già utilizzato Inserirne un'altro");
			}
			//una volta completata l'iscrizione torna al log in
			this.log_in();
		}
		else if (valore ==3) {//Caso di uscita 
			return;
		}
	}
	
	/**
	 * Parte che gestisce il post log in dato un Utente
	 * @param utente	utente che ha effettuato il log in
	 */
	public void user_loggato(Utente utente){
		int valore=view.log_fruitore_operatore(utente);//parte di view per le scelte
		if (valore== 1) {//Loggare come Fruitore
			Fruitore fruitore=db.carica_fruitore(utente.get_username(), utente.get_password());
			if(fruitore==null) {
				view.scrivi("Non sei registrato come fruitore");
				this.user_loggato(utente);
			}
			else {
				if(!fruitore.is_valido()) {//controlla che sia 
					this.view.scrivi("La registrazione come fruitore e' scaduta");
					db.elimina_fruitore(fruitore);//elimina il fruitore
					this.user_loggato(utente);
				}
			this.fruitore_loggato(fruitore);
			}
		}
		else if(valore == 2) {//Loggare come Operatore
			Operatore operatore=db.carica_operatore(utente.get_username(), utente.get_password());
			if(operatore==null) {
				view.scrivi("Non sei registrato come operatore");
				this.user_loggato(utente);
			}
			else {
				this.operatore_loggato(operatore);
			}
		}
		else if(valore==3) {//Registra nuovo Fruitore
			if(db.carica_fruitore(utente.get_username(), utente.get_password())!=null) {
				view.scrivi("Sei gia registrato come fruitore");
				this.user_loggato(utente);
			}
			else if(utente.get_eta()>=18) {
				this.view.scrivi(utente.get_username()+" sei diventato fruitore");
				//new Database_file().salva_fruitore(new Fruitore(utente));
				db.salva_fruitore(new Fruitore(utente));
				archivio.salva_fruitore(new Fruitore(utente));//salvo nell'archivio
				this.user_loggato(utente);
			}
			else {
				view.scrivi("Non puoi diventare fruitore in quanto non sei maggiorenne");
				this.user_loggato(utente);
			}
		}
		else if(valore==4) {//Registra nuovo Operatore
			if(db.carica_operatore(utente.get_username(), utente.get_password())!=null) {
				view.scrivi("Sei gia registrato come operatore");
				this.user_loggato(utente);
			}
			else {
				this.view.scrivi(utente.get_username()+" sei diventato operatore");
				//new Database_file().salva_operatore(new Operatore(utente));		
				db.salva_operatore(new Operatore(utente));
				archivio.salva_operatore(new Operatore(utente));//salvo l'operatore nell'archivio
				this.user_loggato(utente);
			}
		}
		else if(valore==5) {//Torna alla pagina di log in
			this.log_in();
		}
		else {//unused instruction 
			this.view.scrivi("Errore");
			this.user_loggato(utente);
		}
	}

	/**
	 * Gestione delle opzioni dell'Operatore
	 * @param operatore	operatore selezionato sul quale vengono svolti i casi d'uso
	 */
	private void operatore_loggato(Operatore operatore) {
		int scelta = view.operatore_view(operatore);
		if (scelta==1){//Visualizza tutti i fruitori presenti in locale
			this.view.stampa_fruitori(db.carica_tutti_fruitori());
			this.operatore_loggato(operatore);
		}
		else if(scelta == 2) {//aggiungi descrizione ad una risorsa
			int id=this.view.ricerca_risorsa_id();
			Categoria cat=db.carica_root_categorie();
			Risorsa res=cat.get_risorsa_by_id(cat, id);
			if(res!=null) {
				if(res instanceof Libro) {
					res.aggiungi_descrizione(this.view.nuova_descrizione_libro());
					db.salva_categoria_root(cat);
					archivio.salva_categoria_root(cat);//archivia le risorse
				}
				if(res instanceof Film) {
					res.aggiungi_descrizione(this.view.nuova_descrizione_film());
					db.salva_categoria_root(cat);
					archivio.salva_categoria_root(cat);//archivia le risorse
				}
			}else {
				this.view.scrivi("Risorsa non trovata");
			}
			this.operatore_loggato(operatore);//per continuare iterazioni
		}
		else if(scelta == 3) {//Rimozione descrizione ad una risorsa
			int id=this.view.ricerca_risorsa_id();
			Categoria cat=db.carica_root_categorie();
			Risorsa res=cat.get_risorsa_by_id(cat, id);
			if(res!=null) {
				res.rimuovi_descrizione();
				db.salva_categoria_root(cat);
				archivio.salva_categoria_root(cat);//aggiona la descrizione in modo corretto anche sull archivio
			}else {
				this.view.scrivi("Risorsa non trovata");
			}
			this.operatore_loggato(operatore);//per continuare iterazioni
		}
		else if(scelta == 4) {//visualizza tutte risorse
			Categoria c= db.carica_root_categorie();
			ArrayList<Risorsa> risultato=new ArrayList<>();
			c.carica_tutte_risorse(c, risultato);
			if(!risultato.isEmpty()) {
				for(Risorsa r:risultato) {
						this.view.scrivi(r.toString()+"\n");
				}
			}
			else {
				this.view.scrivi("Nulla trovato");
			}
			this.operatore_loggato(operatore);//per continuare iterazioni
		}
		else if(scelta == 5) {//ricerca o visualizza copie
			this.ricerca_o_disponibilita();	
			this.operatore_loggato(operatore);//per continuare iterazioni
		}else if(scelta == 6) {//Numero di prestiti per anno solare 
			this.view.scrivi("Il numero di prestiti per questo anno solare è di: "+query.count_numero_di_prestiti_per_anno_solare(LocalDateTime.now()));
			this.operatore_loggato(operatore);//per continuare iterazioni
		}else if(scelta == 7) {//Numero di proroghe per anno solare
			this.view.scrivi("Il numero di proroghe effettuate per quest'anno solare è di: "+query.count_numero_di_proroghe_per_anno_solare(LocalDateTime.now()));
			this.operatore_loggato(operatore);//per continuare iterazioni
		}else if(scelta == 8) {//Risorsa con il maggior numero di prestiti
			this.view.scrivi("La risorsa con piu Prestiti per quest'anno solare è:\n"+query.select_risorsa_con_max_numero_prestiti(LocalDateTime.now()).toString());
			this.operatore_loggato(operatore);//per continuare iterazioni
		}else if(scelta == 9) {//fruitore e prestiti
			HashMap<Fruitore, Integer> risultato=query.select_count_numero_di_prestiti_perogni_fruitore(LocalDateTime.now());
			for (Map.Entry<Fruitore, Integer> e : risultato.entrySet()) {
				this.view.scrivi("Il fruitore: "+e.getKey().get_utente().get_username()+" ha effettuato "+e.getValue()+" prestiti");
			}
			this.operatore_loggato(operatore);//per continuare iterazioni
		}else if (scelta==10) {//torna indietro
			this.user_loggato(operatore.get_utente());
		}
		else {
			this.user_loggato(operatore.get_utente());
		}		
	}
	
	/**
	 * Gestione delle opzioni di Fruitore
	 * @param fruitore Fruitore selezionato sul quale vengono svolti i casi d'uso
	 */
	private void fruitore_loggato(Fruitore fruitore) {
		db.aggiorna_descrizione_prestiti();//serve per avere i prestiti con la descrizione corretta
		db.aggiorna_validita_prestiti();// contolla ed elimina prestiti scaduti
		if(fruitore.is_rinnovabile()) {//controlla se il fruitore sta per scadere
			this.view.scrivi("Scadenza rinnovo vicina ti mancano " + fruitore.get_giorni_scadenza() + " giorni alla scadenza");
		}
		int scelta=view.fruitore_view(fruitore);
		if(scelta ==1) {//Rinnovo del fruitore
			if(fruitore.is_rinnovabile()) {
				 
				fruitore.rinnova_iscrizione();//rinnova oggetto ma non il db
				archivio.aggiorna_fruitore(fruitore);
				db.aggiorna_fruitore(fruitore);
			}
			else { 
				view.scrivi("Non puoi ancora rinnovare l'iscrizione");
			}
			this.fruitore_loggato(fruitore);
		}else if(scelta==2) {//aggiungi prestito
			int id=this.view.ricerca_risorsa_id();
			Categoria cat=db.carica_root_categorie();
			Risorsa res=cat.get_risorsa_by_id(cat, id);
			if(res!=null) {
				ArrayList<Prestito> prestiti_per_fruitore=db.get_prestiti_per_fruitore_risorsa(fruitore,res);
				//check se sforo il numero massimo di prestiti o se la risorsa selezionata è disponibile
					if(prestiti_per_fruitore.size()<res.get_max_n_prestiti() &&
							res.get_disponibili()>0) {
						Prestito p= new Prestito(res, fruitore);
						res.add_prestito(); 
						db.salva_categoria_root(cat);
						db.salva_prestito(p);//TODO
						archivio.salva_categoria_root(cat);//salvo nell'archivio le risorse e i l prestito
						archivio.salva_prestito(p);
						this.view.scrivi("Libro Aggiunto con successo");
					}else {
						if(prestiti_per_fruitore.size()>=res.get_max_n_prestiti() &&
								res.get_disponibili()<=0) {
							this.view.scrivi("Massimo numero di prestiti raggiunto e Risorsa non disponibile");
						}else if(res.get_disponibili()<=0) {
							this.view.scrivi("Risorsa non disponibile");
						}else if(prestiti_per_fruitore.size()>=res.get_max_n_prestiti()) {
							this.view.scrivi("Massimo numero di prestiti raggiunto");
						}
					}
			}else {
				this.view.scrivi("Risorsa non trovata");
			}
			this.fruitore_loggato(fruitore);
		}
		else if(scelta==3){//visualizza tutti i prestiti del fruitore 
			ArrayList<Prestito> prestiti=db.get_tutti_prestiti_per_fruitore(fruitore);
			if(!prestiti.isEmpty()) {
				for(Prestito p:prestiti) {
					Risorsa r=p.get_risorsa();
					this.view.scrivi(r.toString());
				}
			}else {
				this.view.scrivi("Non hai prestiti");
			}
			this.fruitore_loggato(fruitore);
			
		}else if(scelta==4){//proroga prestito
			String esito="Prestito non prorogato";
			ArrayList<Prestito> prestiti=db.get_tutti_prestiti_per_fruitore(fruitore);
			int num_prestito=this.view.seleziona_prestito_da_prorogare(prestiti);
			num_prestito--;
			for(int i=0;i<prestiti.size();i++) {
				if(i==num_prestito && prestiti.get(i).rinnova()) {
					db.aggiorna_prestito(prestiti.get(i));
					archivio.aggiorna_prestito(prestiti.get(i));
					esito = "Prestito prorogato";
					this.view.scrivi(esito);
				}
			}
			this.view.scrivi(esito);		
			this.fruitore_loggato(fruitore);
			
			
			
		}else if(scelta==5){//ricerca o visualizza disponibilità Risorsa
			this.ricerca_o_disponibilita();	
			this.fruitore_loggato(fruitore);
		}else if(scelta ==6) {//Torna indietro
			this.user_loggato(fruitore.get_utente());
		}else {
			this.user_loggato(fruitore.get_utente());
		}
	}

	/**
	 * Parte comune al Fruitore e all'Operatore che gestisce la ricerca per descrizione
	 * o la visualizzazione di disponibilità di una certa risorsa
	 */
	private void ricerca_o_disponibilita() {
		int risultato=this.view.ricerca_o_disponibilita();
		if(risultato==1) {//ricerca per descrizione
			//manca che prima venga scelta la categoria
			int ris=this.view.get_sottocategorie_principali();
			if(ris==1) {//caso dei libri
				this.view.scrivi("inserire "+Costanti.NO_RICERCA+" nel caso in cui non si voglia ricercare per quel parametro\n"
						+ "I valori inseriti nella ricerca sono case sensitive\n");
				ArrayList<String> ricerca=this.view.nuova_descrizione_libro();
				Libro l=new Libro(0, 0);
				l.aggiungi_descrizione(ricerca);
				ArrayList<Risorsa>test=db.ricerca_per_descrizione(l);
				this.view.stampa_risorse(test);
			}	
			if(ris==2) {//caso dei film
				this.view.scrivi("inserire "+Costanti.NO_RICERCA+" nel caso in cui non si voglia ricercare per quel parametro\n"
						+ "I valori inseriti nella ricerca sono case sensitive\n");
				ArrayList<String> ricerca=this.view.nuova_descrizione_film();
				Film f=new Film(0, 0);
				f.aggiungi_descrizione(ricerca);
				ArrayList<Risorsa>test=db.ricerca_per_descrizione(f);
				this.view.stampa_risorse(test);
			}
		}else if(risultato==2) {//valutazione disponibilita
			int id=this.view.ricerca_risorsa_id();
			int ris=db.get_n_copie_disponibili_by_id(id);
			if(ris==-1) {
				this.view.scrivi("Risorsa non trovata");
			}else {
				this.view.scrivi("Sono disponibili "+ris+" copie");
			}
		}
	}
}