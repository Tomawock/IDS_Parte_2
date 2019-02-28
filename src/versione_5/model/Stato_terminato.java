package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

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
