package versione_5.model.stato;

import java.util.ArrayList;
import java.util.Arrays;

import versione_5.model.Model_context;

public class Stato_notifica extends Stato {

	Stato back;
	String notifica;	
	
	/**
	 * 
	 * @param back				stato a cui deve andare a seguito della notifica
	 * @param notifica			setta l'attributo corrispondente all'output della view
	 * @param attore			attributo su cui si svolgono le operazioni durante lo stato
	 */
	public Stato_notifica(Stato back, String notifica, Object attore) {
		super(attore);
		this.back=back;
		this.notifica=notifica;
		inizializza_output();
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(
				notifica
				+ "\n1)Torna indietro")));
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		if(dati_input.get(0).equals("1")) {
			model.set_stato_attuale(back);
		}
		else {
			model.set_stato_attuale(new Stato_notifica(back, "Hai inserito i dati sbagliati", get_attore()));
		}

	}

}
