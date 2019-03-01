package versione_5.model.stato;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import utilita.Costanti;
import versione_5.model.Categoria;
import versione_5.model.Fruitore;
import versione_5.model.Model_context;
import versione_5.model.Operatore;
import versione_5.model.Risorsa;

public class Stato_operatore_loggato extends Stato {

	public Stato_operatore_loggato(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
				Costanti.GRECA
				+ "\n              MENU' OPERATORE         \n"
				+ Costanti.GRECA
				+ "\nCosa vuoi fare?\n"
				+ "1)Stampare i fruitore presenti nel database\n"
				+ "2)Aggiungi descrizione\n"
				+ "3)Rimuovi Descrizione\n"
				+ "4)Visualizza risorse\n"
				+ "5)Ricerca o Visualizza disponibilita Risorsa\n"
				+ "6)Numero di prestiti per anno solare\n"
				+ "7)Numero di proroghe per anno solare\n"
				+ "8)Risorsa con il maggior numero di prestiti\n"
				+ "9)Numero di prestiti per Fruitore\n"
				+ "10)Torna indietro\n"
				+ Costanti.GRECA3)));
	}

	/**
	 * Consente all'operatore presente nel sistema di andare nello stato successivo in base 
	 * all'input 
	 */
	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		switch(dati_input.get(0)) {
			case "1":{//Visualizza tutti i fruitori presenti in locale
				String output="";
				ArrayList<Fruitore> fruitori=model.get_database_file().carica_tutti_fruitori();
				for(Fruitore f:fruitori) {
					output+=f.toString();
				}
				if(output.equals("")) {
					output= "Non ci sono fruitori presenti nel databse";
				}
				model.set_stato_attuale(new Stato_notifica(this, output, get_attore()));
				break;
			}
			case "2":{//aggiungi descrizione ad una risorsa
				model.set_stato_attuale(new Stato_inserisci_id_risorsa_aggiungi_descrizione(get_attore()));
				break;
			}
			case "3":{//Rimozione descrizione ad una risorsa
				model.set_stato_attuale(new Stato_inserisci_id_risorsa_rimozione_descrizione(get_attore()));
				break;
			}
			case "4":{//visualizza tutte risorse
				String output="";
				Categoria c= model.get_database_file().carica_root_categorie();
				ArrayList<Risorsa> risultato=new ArrayList<>();
				c.carica_tutte_risorse(c, risultato);
				if(!risultato.isEmpty()) {
					for(Risorsa r:risultato) {
							output+=r.toString()+"\n";
					}
				}
				else {
					output="Non sono state trovate risorse";
				}
				model.set_stato_attuale(new Stato_notifica(this, output, get_attore()));
				break;
			}
			case "5":{//ricerca o visualizza copie
				model.set_stato_attuale(new Stato_ricerca_visualizza(get_attore()));
				break;
			}
			case "6":{//Numero di prestiti per anno solare 
				String output="Il numero di prestiti per questo anno solare è di: "+model.get_query().count_numero_di_prestiti_per_anno_solare(LocalDateTime.now());
				model.set_stato_attuale(new Stato_notifica(this, output, get_attore()));
				break;
			}
			case "7":{//Numero di proroghe per anno solare
				String output="Il numero di proroghe effettuate per quest'anno solare è di: "+model.get_query().count_numero_di_proroghe_per_anno_solare(LocalDateTime.now());
				model.set_stato_attuale(new Stato_notifica(this, output, get_attore()));
				break;
			}
			case "8":{//Risorsa con il maggior numero di prestiti
				String output="La risorsa con piu Prestiti per quest'anno solare è:\n"+model.get_query().select_risorsa_con_max_numero_prestiti(LocalDateTime.now()).toString();
				model.set_stato_attuale(new Stato_notifica(this, output, get_attore()));
				break;
			}
			case "9":{//fruitore e prestiti
				String output="";
				HashMap<Fruitore, Integer> risultato=model.get_query().select_count_numero_di_prestiti_perogni_fruitore(LocalDateTime.now());
				for (Map.Entry<Fruitore, Integer> e : risultato.entrySet()) {
					output+="Il fruitore: "+e.getKey().get_utente().get_username()+" ha effettuato "+e.getValue()+" prestiti\n";
				}
				model.set_stato_attuale(new Stato_notifica(this, output, get_attore()));
				break;
			}
			case "10":{//torna indietro
				 model.set_stato_attuale(new Stato_utente_loggato(((Operatore)get_attore()).get_utente()));
				 break;
			}
			default:{
				model.set_stato_attuale(new Stato_errore(new Stato_iniziale(null), this, "inseriti dati sbagliati", get_attore()));
				 break;
			}
		}
		/*
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
		}		*/
	}

}
