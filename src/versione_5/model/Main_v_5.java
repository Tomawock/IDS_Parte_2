package versione_5.model;

import utilita.Emulazione_Server;
import versione_5.controller.Controller;
import versione_5.view.View;

public class Main_v_5 {
	public static void main(String[] args) {
		Emulazione_Server.inizializza();//emeula il server il quale cointiene dei dati preimpostati
		//Controller c= new Controller();//crea il controller del sw e inizia il programma
		View view = new View();
		Controller c= new Controller();
		c.log_in();	
	}
}