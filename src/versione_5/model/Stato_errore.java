package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Stato_errore extends Stato{

	private Stato back,undo;
	private String errore;
	
	public Stato_errore(Model_context model, Stato back, Stato undo,String msg_errore,Object attore) {
		super(attore);
		this.back=back;
		this.undo=undo;
		this.errore=msg_errore;
		this.inizializza_output();
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(this.errore +
				"\nPremere 1 per tornare indietro \nPremere 2 per reiserire i dati")));
		
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		// TODO Auto-generated method stub
		switch(dati_input.get(0)) {
			case "1":{
				model.set_stato_attuale(back);
				break;
			}
			case "2":{
				model.set_stato_attuale(undo);
				break;
			}
			default:{
				model.set_stato_attuale(new Stato_errore(model, back, undo, "Opzione non disponibile", get_attore()));
			}
		}
	}

}
