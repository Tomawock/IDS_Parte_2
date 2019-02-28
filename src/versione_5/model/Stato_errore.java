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
		super.set_output(new ArrayList<>(Arrays.asList(this.errore+
				"\n"+"premere 1 per tornare indietro premere 2 per reiserire i dati")));
		
	}

	@Override
	public void prossimo_stato(Model_context model, ArrayList<String> dati_input) {
		// TODO Auto-generated method stub
		if(dati_input.get(0).equals("1")) {
			model.set_stato_attuale(back);
		}else if(dati_input.get(0).equals("2")) {
			model.set_stato_attuale(undo);
		}//todo
	}

}
