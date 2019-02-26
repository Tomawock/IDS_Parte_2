package versione_5.model;

import java.util.ArrayList;

public interface Salvataggio {
	/**
	 * NB: 	Tutte le classi che implemantano questa interfaccia di salvataggio devono essere
	 * 		in grado anche di archivare in modo permanenete I FRUITORI, I PRESTITI e LE RISORSE
	 */
	
	
	/**
	 * Dato un utente lo salva sul file locale degli utenti
	 * 
	 * @param utente 
	 */
	void salva_utente(Utente utente);
	
	/**
	 * Dato un fruitore lo salva sul file locale dei fruitori
	 * 
	 * @param fruitore
	 */
	void salva_fruitore(Fruitore fruitore);
	
	/**
	 * Dato un operatore lo salva sul file locale degli operatori
	 * 
	 * @param operatore
	 */
	void salva_operatore(Operatore operatore);
	
	/**
	 * Aggiorna il fruitore presente in locale con i valori del fruitore passato in ingresso 
	 * nel caso in cui non sia presente non effettua variazioni
	 * 
	 * @param fruitore a cui bisogna aggiornare i valori
	 */
	void aggiorna_fruitore(Fruitore fruitore);
	
	/**
	 * Dato un insieme di Utenti sovrascrive tutti i dati locali con quelli passati in ingresso
	 * 
	 * @param utenti	Insieme di utenti che saranno presenti in locale alla terminazione della funzione
	 */
	void reset_utenti(ArrayList<Utente> utenti);
	
	/**
	 * Dato un insieme di Fruitori sovrascrive tutti i dati locali con quelli passati in ingresso
	 * 
	 * @param fruitori	Insieme di fruitori che saranno presenti in locale alla terminazione della funzione
	 */
	void reset_fruitori(ArrayList<Fruitore> fruitori);
	
	/**
	 * Dato un insieme di Operatori	 sovrascrive tutti i dati locali con quelli passati in ingresso
	 * 
	 * @param operatori	Insieme di Operatori che saranno presenti in locale alla terminazione della funzione
	 */
	void reset_operatori(ArrayList<Operatore> operatori);
	
	/**
	 * Dati username e password restituisce l'eventuale utente associato
	 * 
	 * @param username	
	 * @param psw
	 * @return utente se trovato o null
	 */
	Utente carica_utente(String username, String psw);
	
	/**
	 * Dati username e password restituisce l'eventuale Fruitore associato
	 * 
	 * @param username	
	 * @param psw
	 * @return fruitore se trovato o null
	 */
	Fruitore carica_fruitore(String username, String psw);
	
	/**
	 * Dati username e password restituisce l'eventuale Operatore associato
	 * 
	 * @param username	
	 * @param psw
	 * @return operatore se trovato o null 
	 */
	Operatore carica_operatore(String username, String psw);
	
	/**
	 * Vengono restituiti tutti gli utenti presenti nel salvataggio
	 * 
	 * @return utenti
	 */
	ArrayList<Utente> carica_tutti_utenti();
	
	/**
	 * Vengono restituiti tutti i friutori presenti nel salvataggio
	 * 
	 * @return fruitori
	 */
	ArrayList<Fruitore> carica_tutti_fruitori();
	
	/**
	 * Vengono restituiti tutti gli operatori presenti nel salvataggio
	 * 
	 * @return operatori
	 */
	ArrayList<Operatore> carica_tutti_operatori();

	/**
	 * Elimina l'utente che viene passanto in ingresso dal salvataggio degli utenti,
	 * nel caso in cui non sia presente non ci sono variazioni
	 * 
	 * @param utente da eliminare
	 */
	void elimina_utente(Utente utente);
	
	/**
	 * Elimina il fruitore che viene passanto in ingresso dal salvataggio dei fruitori,
	 * nel caso in cui non sia presente non ci sono variazioni
	 * 
	 * @param fruitire da eliminare
	 */
	void elimina_fruitore(Fruitore fruitore);
	
	/**
	 * Elimina l'operatore che viene passanto in ingresso dal salvataggio degli operatori,
	 * nel caso in cui non sia presente non ci sono variazioni
	 * 
	 * @param operatore da eliminare
	 */
	void elimina_operatore(Operatore operatore);
	
	/**
	 * Effettua il controllo che i fruitori non abbiano superato la data di validità, nel caso 
	 * in cui l'abbiano superata, vengono eliminati dal salvatagio
	 * 
	 */
	void aggiorna_validita_fruitori();
	
	/**
	 * Viene controllato che nel salvataggio non sia già presente un utente uguale (stesso username)
	 * a quello passato in ingresso
	 * 
	 * @param utente
	 * @return true se è già presente, false nel caso non lo sia
	 */
	boolean is_presente(Utente utente);
	
	/**
	 * Viene restituita la categoria principale del salvataggio
	 * 
	 * @return categoria principale
	 */
	Categoria carica_root_categorie();
	
	/**
	 * Viene salvata la categoria principale (intesa come radice delle categoria)
	 * 
	 * @param root categoria principale da salvare
	 */
	void salva_categoria_root(Categoria root);
	
	/**
	 * Dato un fruitore ed una risorsa restituisce un arraylist contentente tutti i prestiti relativi alla
	 * categoria della risorsa ed al fruitore
	 * 
	 * @param fruitore	sul quale effettuare la ricerca
	 * @param res		dalla quale viene estratta la categoria
	 * @return			tutti i prestiti tra fruitore e categoria della risorsa oppure una arraylist vuoto 
	 */
	ArrayList<Prestito> get_prestiti_per_fruitore_risorsa(Fruitore fruitore,Risorsa res);
	
	/**
	 * Viene aggiunto in presti all'interno del salvataggio 
	 * 
	 * @param prestito da inserire nel salvataggio
	 */
	void salva_prestito(Prestito prestito);
	
	/**
	 * Dato un insieme di Prestiti sovrascrive tutti i dati locali con quelli passati in ingresso
	 * 
	 * @param prestiti 	da sovrascrivere
	 */
	void reset_prestiti(ArrayList<Prestito> prestiti);
	
	/**
	 * Vengono restituiti tutti i prestiti presenti nel salvataggio
	 * 
	 * @return
	 */
	ArrayList<Prestito> carica_tutti_prestiti();
	
	/**
	 * Vengono restituiti tutti i prestiti relativi ad un determinato fuitore
	 * 
	 * @param fruitore	del quale vogliamo avere i prestiti
	 * @return prestiti del fruiture oppure arraylist vuoto
	 */
	ArrayList<Prestito> get_tutti_prestiti_per_fruitore(Fruitore fruitore);
	
	/**
	 * Effettua il controllo che i prestiti non abbiano superato la data di validità, nel caso 
	 * in cui l'abbiano superata, vengono eliminati dal salvatagio
	 * 
	 */
	void aggiorna_validita_prestiti();
	
	/**
	 * Effettua il controllo che la descrizione delle risorse presente all'interno del salvataggio sia uguale 
	 * a quella associata al prestito
	 * 
	 */
	void aggiorna_descrizione_prestiti();
	
	/**
	 * Viene modificato il prestito presente nel salvattio con lo stesso prestito passato come parametro
	 * 
	 * @param p		parametro che vogliamo sostituire all'interno del salvataggio
	 */
	void aggiorna_prestito(Prestito p);
	
	/**
	 * Viene creata una risorsa (poi passata come parametro) che contiene l'array list dei dati che viene utilizzato
	 * per ricercare una o più risorse della stessa categoria che abbiano gli stessi dati 
	 * 
	 * @param risorsa	contenitore dei dati per il quale effettuare la ricerca
	 * @return			una o più risorse che matchano con i dati passati in ingresso oppure arraylist vuoto
	 */
	ArrayList<Risorsa> ricerca_per_descrizione(Risorsa risorsa);
	
	/**
	 * Ritorna il numero di copie disponibili di una certa risorsa dato un id in ingresso
	 * @param id	risorsa su cui verificare in numero di copie disponibili
	 * @return		numero copie disponibili risorsa, -1 in caso l'id non sia presente 
	 */
	int get_n_copie_disponibili_by_id(int id);	
}
