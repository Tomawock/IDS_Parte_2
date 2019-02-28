package versione_5.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Stato_utente_loggato extends Stato {

	public Stato_utente_loggato(Object attore) {
		super(attore);
	}

	@Override
	public void inizializza_output() {
		super.set_output(new ArrayList<>(Arrays.asList("Buongiorno "+((Utente)get_attore()).get_username())));

	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> view_input) {
		// TODO Auto-generated method stub
		
	}

}
