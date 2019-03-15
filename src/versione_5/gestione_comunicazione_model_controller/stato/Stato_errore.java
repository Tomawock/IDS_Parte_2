package versione_5.gestione_comunicazione_model_controller.stato;

import java.util.ArrayList;
import java.util.Arrays;

import versione_5.gestione_comunicazione_model_controller.Model_context;

public class Stato_errore extends Stato{

	private Stato back,undo;
	private String errore;
	
	/**
	 * Crea lo stato di errore 
	 * @param back				stato che consente di tornare indietro 
	 * @param undo				stato che consente di riprovare l'inserimento
	 * @param msg_errore		setta l'attirubuto che verrà passato alla view
	 * @param attore			attributo su cui si svolgono le operazioni durante lo stato
	 */
	public Stato_errore(Stato back, Stato undo, String msg_errore,Object attore) {
		super(attore);
		this.back=back;
		this.undo=undo;
		this.errore=msg_errore;
		this.inizializza_output();
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(this.errore +
				"\n1)Torna indietro\n2)Reinserire i dati")));
		
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
				model.set_stato_attuale(new Stato_errore(back, undo, "Opzione non disponibile", get_attore()));
			}
		}
	}

}
