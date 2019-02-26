package versione_5.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Query {

	/**
	 * Classe che si occupa della gestione delle interrogazioni al database
	 */
	Salvataggio db;
	
	public Query(Salvataggio db) {
		this.db = db;
	}

	/**
	 * Conta il numero di prestiti per anno solare preso in ingresso
	 * 
	 * @param anno solare in cui contare i prestiti
	 * @return	il numero di prestiti effettuati in un anno solare 
	 */
	 public int count_numero_di_prestiti_per_anno_solare(LocalDateTime anno) {
		 ArrayList<Prestito> prestiti_archiviati=db.carica_tutti_prestiti();//sono tutti i prestiti ? perchè questi sarebbero quelli "locali e non dell'archivio"
		 int res=0;
		 for(Prestito p:prestiti_archiviati) {
			 if(p.get_data_inizio_prestito().getYear()==anno.getYear()) {
				 res++;
			 }
		 } 
		return res;
	 }
	 
	 /**
	  * Conta il numero di proroghe effettuate in un anno solare preso in ingresso
	  * 
	  * @param anno solare in cui contare le proroghe
	  * @return	il numero di proroghe effettaute in un anno solare
	  */
	 public int count_numero_di_proroghe_per_anno_solare(LocalDateTime anno) {
		 ArrayList<Prestito> prestiti_archiviati=db.carica_tutti_prestiti();//sono tutti i prestiti ? perchè questi sarebbero quelli "locali e non dell'archivio"
		 int res=0;
		 for(Prestito p:prestiti_archiviati) {
			 if(!p.is_mai_prorogato()) {//TODO
				 if(p.get_data_inizio_proroga().getYear()==anno.getYear()) {
					 res++;
				 } 
			 }
		 }
		return res; 
	 }
	 
	 /**
	  * Seleziona la risorsa che è stata prestata piu volte in un anno solare passato in ingresso,
	  * 
	  * @param anno solare dentro cui cercare
	  * @return	la risorsa con il massimo numero di prestiti associati o null altrimenti,
	  * 		nel caso di parità di massimo ne viene restituito uno solo
	  *			
	  */
	 public Risorsa select_risorsa_con_max_numero_prestiti(LocalDateTime anno) {
		ArrayList<Prestito> prestiti_archiviati=db.carica_tutti_prestiti();
		HashMap<Risorsa, Integer> risultato= new HashMap<Risorsa, Integer>();
		 for(Prestito p:prestiti_archiviati) {//prende tutti i prestiti
			Risorsa risorsa=p.get_risorsa();//seleziona una risorsa
			Integer num_ripetizioni_risorsa=0;//valore del conteggio delle ripetizioni della risorsa
			if(!risultato.containsKey(risorsa)) {//se non contiene la chiave allora è una nuova risorsa altrimenti calcolo gia fatto
				risultato.put(risorsa, num_ripetizioni_risorsa);
				for(Prestito p1:prestiti_archiviati){
					if(p1.get_risorsa().equals(risorsa) && p1.get_data_inizio_prestito().getYear()== anno.getYear()) {
						num_ripetizioni_risorsa++;
					}
				}
				risultato.put(risorsa, num_ripetizioni_risorsa);//aggiorna l'hash map con il valore delle ripetizioni corretto
			}
		 }
		 //ricerco il massimo nelle coppie risorsa
		 Map.Entry<Risorsa, Integer> massimo = null;

		 for (Map.Entry<Risorsa, Integer> e : risultato.entrySet())
		 {
		     if (massimo == null || e.getValue().compareTo(massimo.getValue()) > 0)
		     {
		         massimo = e;
		     }
		 }
		return massimo.getKey();
	 }
	 
	 /**
	  * Seleziona per ogni fruitore il quantitativo di prestiti effettuati in un anno solare passato in ingresso
	  * 
	  * @param anno solare dentro il cui cercare
	  * @return un'associazione fra un Fruitore e il numero di prestiti ad esso relativo
	  */
	 public HashMap<Fruitore, Integer> select_count_numero_di_prestiti_perogni_fruitore(LocalDateTime anno) {
		 ArrayList<Prestito> prestiti_archiviati=db.carica_tutti_prestiti();
		 
		 HashMap<Fruitore, Integer> risultato= new HashMap<Fruitore, Integer>();
		 for(Prestito p:prestiti_archiviati) {//prende tutti i prestiti
			Fruitore fruitore=p.get_fruitore();//seleziona un fruitore
			Integer num_ripetizioni_prestiti=0;//valore del conteggio delle ripetizioni della risorsa
			if(!risultato.containsKey(fruitore)) {//se non contiene la chiave allora è una nuova risorsa altrimenti calcolo gia fatto
				risultato.put(fruitore, num_ripetizioni_prestiti);
				for(Prestito p1:prestiti_archiviati){
					if(p1.get_fruitore().equals(fruitore) && p1.get_data_inizio_prestito().getYear()== anno.getYear()) {
						num_ripetizioni_prestiti++;
					}
				}
				risultato.put(fruitore, num_ripetizioni_prestiti);//aggiorna l'hash map con il valore delle ripetizioni corretto
			}
		 }
		 return risultato;
	 }
}
