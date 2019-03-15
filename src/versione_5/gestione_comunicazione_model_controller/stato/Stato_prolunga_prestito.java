package versione_5.gestione_comunicazione_model_controller.stato;

import java.util.ArrayList;
import java.util.Arrays;

import utilita.Costanti;
import versione_5.gestione_comunicazione_model_controller.Model_context;
import versione_5.model.Fruitore;
import versione_5.model.Prestito;

public class Stato_prolunga_prestito extends Stato {

	public Stato_prolunga_prestito(Object attore) {
		super(attore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inizializza_output() {
		String output="";
		ArrayList<Prestito> prestiti= (ArrayList<Prestito>)get_attore();
		if(!prestiti.isEmpty()) {
			for(int i=0;i< prestiti.size();i++) {
				int temp=i+1;
				output+=temp+")"+prestiti.get(i).toString()+"\n";
			}
		}
			
		super.set_output(new ArrayList<>(Arrays.asList(
		output		
		+Costanti.GRECA2
		+ "\n **** Inserire numero del prestito da prorogare **** \n"
		+ Costanti.GRECA2)));	
	}

	/**
	 * Consente di prolungare un prestito 
	 */
	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		
		Fruitore fruitore = ((ArrayList<Prestito>)get_attore()).get(0).get_fruitore();
		
		ArrayList<Prestito> prestiti=model.get_database_file().get_tutti_prestiti_per_fruitore(fruitore);
		int num_prestito=-1;
		try {
			num_prestito=Integer.parseInt(dati_input.get(0));
		}
		catch(Exception exe) {
			model.set_stato_attuale(new Stato_errore(new Stato_fruitore_loggato(fruitore), this,"Inserire un numero", fruitore));
			return;
		}
		
		num_prestito--;
		for(int i=0;i<prestiti.size();i++) {
			if(i==num_prestito && prestiti.get(i).rinnova()) {
				model.get_database_file().aggiorna_prestito(prestiti.get(i));
				model.get_archivio().aggiorna_prestito(prestiti.get(i));
				model.set_stato_attuale(new Stato_notifica(new Stato_fruitore_loggato(fruitore), "Prestito prorogato", fruitore));
				return;
			}
		}
		model.set_stato_attuale(new Stato_errore(new Stato_fruitore_loggato(fruitore), this, "Prestio non prorogato", fruitore));
	}

}
