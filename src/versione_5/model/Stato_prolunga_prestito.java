package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;

public class Stato_prolunga_prestito extends Stato {

	public Stato_prolunga_prestito(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
		Costanti.GRECA2
		+ "\n **** Inserire numero del prestito da prorogare **** \n"
		+ Costanti.GRECA2)));	
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		Fruitore fruitore = (Fruitore)get_attore();
		
		ArrayList<Prestito> prestiti=model.get_database_file().get_tutti_prestiti_per_fruitore(fruitore);
		int num_prestito=-1;
		try {
			num_prestito=Integer.parseInt(dati_input.get(0));
		}
		catch(Exception exe) {
			model.set_stato_attuale(new Stato_errore(new Stato_fruitore_loggato(get_attore()), this,"Inserire un numero", get_attore()));
			return;
		}
		
		num_prestito--;
		for(int i=0;i<prestiti.size();i++) {
			if(i==num_prestito && prestiti.get(i).rinnova()) {
				model.get_database_file().aggiorna_prestito(prestiti.get(i));
				model.get_archivio().aggiorna_prestito(prestiti.get(i));
				model.set_stato_attuale(new Stato_notifica(new Stato_fruitore_loggato(get_attore()), "Prestito prorogato", get_attore()));
			}
		}
		model.set_stato_attuale(new Stato_errore(new Stato_fruitore_loggato(get_attore()), this, "Prestio non preorogato perchè non è stato trovato", get_attore()));
	}

}
