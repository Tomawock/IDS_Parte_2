package versione_5.model;

import utilita.Emulazione_Server;
import versione_5.controller.Controller;
import versione_5.controller.Controller_versione_2;
import versione_5.view.View_versione_2;

public class Main_v_5 {
	public static void main(String[] args) {
		Emulazione_Server.inizializza();//emeula il server il quale cointiene dei dati preimpostati
		//Controller c= new Controller();//crea il controller del sw e inizia il programma
		View_versione_2 view = new View_versione_2();
		Controller_versione_2 c= new Controller_versione_2(view);
		c.log_in();	
	}
}