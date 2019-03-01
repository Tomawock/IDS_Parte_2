package versione_5.model.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.model.Fruitore;
import versione_5.model.Model_context;
import versione_5.model.Operatore;
import versione_5.model.Utente;

public class Stato_utente_loggato extends Stato {

	public Stato_utente_loggato(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(Costanti.GRECA
				+"\n"
				+"              MENU' ACCESSO BENVENUTO "+((Utente)get_attore()).get_username() 
				+"\n" 
				+Costanti.GRECA
				+"\n"
				+ "1)Logga come Fruitore\n"
				+ "2)Logga come Operatore\n"
				+ "3)Diventa Fruitore\n"
				+ "4)Diventa Operatore\n"
				+ "5)Esci"
				+ "\n"
				+ Costanti.GRECA3)));

	}

	/**
	 * Consente di effetuare la scelta all'interno del menù dell'utente
	 */
	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {	
		Utente utente= model.get_database_file().carica_utente(((Utente)get_attore()).get_username(), ((Utente)get_attore()).get_password());
		Fruitore fruitore=model.get_database_file().carica_fruitore(((Utente)get_attore()).get_username(), ((Utente)get_attore()).get_password());
		Operatore operatore=model.get_database_file().carica_operatore(((Utente)get_attore()).get_username(), ((Utente)get_attore()).get_password());
		
		switch (dati_input.get(0)) {
			case "1":{//Loggare come Fruitore		
				if(fruitore==null) {
					model.set_stato_attuale(new Stato_notifica(this,"Non sei registrato come fruitore" , get_attore()));
				}
				else {
					if(!fruitore.is_valido()) {//controlla che sia 
						model.get_database_file().elimina_fruitore(fruitore);		
						model.set_stato_attuale(new Stato_notifica(this,"La registrazione come fruitore e' scaduta" , get_attore()));
					}
				model.set_stato_attuale(new Stato_fruitore_loggato(fruitore));
				}
				break;
			}
			case "2":{//Loggare come Operatore
				if(operatore==null) {
					model.set_stato_attuale(new Stato_notifica(this,"Non sei registrato come operatore" , get_attore()));
				}
				else {
					model.set_stato_attuale(new Stato_operatore_loggato(operatore));
				}
				break;
			}
			case "3":{//Registra nuovo Fruitore
				 if(fruitore!=null) {
					 model.set_stato_attuale(new Stato_notifica(this,"Sei gia registrato come fruitore" , get_attore()));
					}
					else if(utente.get_eta()>=18) {
						model.get_database_file().salva_fruitore(new Fruitore(utente));
						model.get_archivio().salva_fruitore(new Fruitore(utente));//salvo nell'archivio
						model.set_stato_attuale(new Stato_notifica(this,"Sei stato registrato come Fruitore" , get_attore()));
					}
					else {
						model.set_stato_attuale(new Stato_notifica(this,"Non puoi diventare fruitore in quanto non sei maggiorenne" , get_attore()));
					}
				 break;
			}
			case "4":{//Registra nuovo Operatore
				if(operatore!=null) {
					model.set_stato_attuale(new Stato_notifica(this,"Sei gia registrato come operatore" , get_attore()));
				}
				else {	
					model.get_database_file().salva_operatore(new Operatore(utente));
					model.get_archivio().salva_operatore(new Operatore(utente));//salvo l'operatore nell'archivio
					model.set_stato_attuale(new Stato_notifica(this,"Sei stato registrato come Operatore" , get_attore()));
				}
				break;
			}
			case "5":{//Torna alla pagina di log in
				 model.set_stato_attuale(new Stato_iniziale(null));
				 break;
			}
			default:{
				 model.set_stato_attuale(new Stato_errore(new Stato_iniziale(null), this, "inseriti dati sbagliati", get_attore()));
				 break;
			}
		}	
	}

}
