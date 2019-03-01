package versione_5.model.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.model.Categoria;
import versione_5.model.Fruitore;
import versione_5.model.Model_context;
import versione_5.model.Prestito;
import versione_5.model.Risorsa;

public class Stato_effettua_prestito extends Stato {

	public Stato_effettua_prestito(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
		Costanti.GRECA2
		+ "\n **** Inserire ID della Risorsa **** \n"
		+ Costanti.GRECA2)));
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		Fruitore fruitore = (Fruitore)get_attore();
		int id=-1;
		try {
			id=Integer.parseInt(dati_input.get(0));
		}
		catch(Exception exe) {
			model.set_stato_attuale(new Stato_errore(new Stato_fruitore_loggato(get_attore()), this,"Inserire un numero", get_attore()));
			return;
		}
		
		Categoria cat=model.get_database_file().carica_root_categorie();
		Risorsa res=cat.get_risorsa_by_id(cat, id);
		if(res!=null) {
			ArrayList<Prestito> prestiti_per_fruitore=model.get_database_file().get_prestiti_per_fruitore_risorsa(fruitore,res);
			//check se sforo il numero massimo di prestiti o se la risorsa selezionata è disponibile
				if(prestiti_per_fruitore.size()<res.get_max_n_prestiti() &&
						res.get_disponibili()>0) {
					Prestito p= new Prestito(res, fruitore);
					res.add_prestito(); 
					model.get_database_file().salva_categoria_root(cat);
					model.get_database_file().salva_prestito(p);
					model.get_archivio().salva_categoria_root(cat);//salvo nell'archivio le risorse e i l prestito
					model.get_archivio().salva_prestito(p);
					model.set_stato_attuale(new Stato_notifica(new Stato_fruitore_loggato(get_attore()), res.getClass().getSimpleName()+" Aggiunto con successo", get_attore()));
				}else {
					if(prestiti_per_fruitore.size()>=res.get_max_n_prestiti() &&
							res.get_disponibili()<=0) {
						model.set_stato_attuale(new Stato_notifica(new Stato_fruitore_loggato(get_attore()), "Massimo numero di prestiti raggiunto e Risorsa non disponibile", get_attore()));
					}else if(res.get_disponibili()<=0) {
						model.set_stato_attuale(new Stato_notifica(new Stato_fruitore_loggato(get_attore()), "Risorsa non disponibile", get_attore()));
					}else if(prestiti_per_fruitore.size()>=res.get_max_n_prestiti()) {
						model.set_stato_attuale(new Stato_notifica(new Stato_fruitore_loggato(get_attore()), "Massimo numero di prestiti raggiunto", get_attore()));
					}
				}
		}else {
			model.set_stato_attuale(new Stato_errore(new Stato_fruitore_loggato(get_attore()), this,"Risorsa non trovata", get_attore()));
		}
	}

}
