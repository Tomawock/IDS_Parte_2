package versione_5.gestione_comunicazione_model_controller.stato;

import java.util.ArrayList;
import java.util.Arrays;

import versione_5.gestione_comunicazione_model_controller.Model_context;

public class Stato_terminato extends Stato {

	public Stato_terminato(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList(" **** ARRIVEDERCI **** ")));
		
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> view_input) {
		return;	
	}

}
