package versione_5.gestione_comunicazione_model_controller.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.gestione_comunicazione_model_controller.Model_context;
import versione_5.model.Fruitore;
import versione_5.model.Prestito;

public class Stato_fruitore_loggato extends Stato {

	public Stato_fruitore_loggato(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		Fruitore fruitore = (Fruitore)get_attore();
		if(fruitore.is_rinnovabile()) {//controlla se il fruitore sta per scadere
			super.set_output(new ArrayList<>(Arrays.asList(
					Costanti.GRECA
					+ "\n              MENU' FRUITORE        \n "
					+ Costanti.GRECA
					+ "\nCosa vuoi fare?\n"
					+ "1)Rinnova Iscrizione\n"
					+ "2)Effettua Prestito\n"
					+ "3)Visualizza tutti i prestiti\n"
					+ "4)Prolunga prestito\n"
					+ "5)Ricerca o Visualizza disponibilita Risorsa\n"
					+ "6)Torna indietro\n"
					+"Scadenza rinnovo vicina ti mancano " + fruitore.get_giorni_scadenza() + " giorni alla scadenza"
					+ Costanti.GRECA3)));
		}
		else {
			super.set_output(new ArrayList<>(Arrays.asList(
				Costanti.GRECA
				+ "\n              MENU' FRUITORE        \n "
				+ Costanti.GRECA
				+ "\nCosa vuoi fare?\n"
				+ "1)Rinnova Iscrizione\n"
				+ "2)Effettua Prestito\n"
				+ "3)Visualizza tutti i prestiti\n"
				+ "4)Prolunga prestito\n"
				+ "5)Ricerca o Visualizza disponibilita Risorsa\n"
				+ "6)Torna indietro\n"
				+ Costanti.GRECA3)));
			}

	}

	/**
	 * Consente di gestire le scelte che possono essere fatte dal fruitore 
	 */
	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		Fruitore fruitore= (Fruitore)get_attore();
		
		model.get_database_file().aggiorna_descrizione_prestiti();//serve per avere i prestiti con la descrizione corretta
		model.get_database_file().aggiorna_validita_prestiti();// contolla ed elimina prestiti scaduti
		
		switch (dati_input.get(0)) {
		case "1":{//Rinnova Iscrizione
			if(fruitore.is_rinnovabile()) {		 
				fruitore.rinnova_iscrizione();//rinnova oggetto ma non il db
				model.get_archivio().aggiorna_fruitore(fruitore);
				model.get_database_file().aggiorna_fruitore(fruitore);
				model.set_stato_attuale(new Stato_notifica(this,"Sei stato rinnovato come fruitore",get_attore()));
			}
			else { 
				model.set_stato_attuale(new Stato_notifica(this,"Non puoi ancora rinnovare l'iscrizione",get_attore()));
			}
			break;
		}
		case "2":{//Effettua Prestito
			model.set_stato_attuale(new Stato_effettua_prestito(get_attore()));
			break;
		}
		case "3":{//Visualizza tutti i prestiti
			String output="";
			ArrayList<Prestito> prestiti=model.get_database_file().get_tutti_prestiti_per_fruitore(fruitore);
			if(!prestiti.isEmpty()) {
				for(int i=0;i< prestiti.size();i++) {
					int temp=i+1;
					output+=temp+")"+prestiti.get(i).toString()+"\n";
				}
			}else {
				output="Non hai prestiti";
			}	
			model.set_stato_attuale(new Stato_notifica(this,output,get_attore()));
			break;
		}
		case "4":{//Prolunga prestito
			ArrayList<Prestito> prestiti=model.get_database_file().get_tutti_prestiti_per_fruitore(fruitore);
			if(!prestiti.isEmpty()) {
				model.set_stato_attuale(new Stato_prolunga_prestito(prestiti));
			}
			else {
				model.set_stato_attuale(new Stato_notifica(this,"Non hai prestiti" ,fruitore));
			}	
			break;
		}
		case "5":{//Ricerca o Visualizza disponibilita Risorsa
			model.set_stato_attuale(new Stato_ricerca_visualizza(get_attore()));
			break;
		}
		case "6":{//Torna indietro
			 model.set_stato_attuale(new Stato_utente_loggato(((Fruitore)get_attore()).get_utente()));
			 break;
		}
		default:{
			 model.set_stato_attuale(new Stato_errore(new Stato_iniziale(null), this, "inseriti dati sbagliati", get_attore()));
			 break;
		}
	}	

	}

}
