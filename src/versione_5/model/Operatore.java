package versione_5.model;

import java.io.Serializable;

public class Operatore implements Serializable{

	/**
	 * Numero seriale per la serializzazione dei dati su File
	 */
	private static final long serialVersionUID = 3L;
	
	private Utente utente;
	
	public Operatore(Utente utente) {
		this.utente=utente;
		
	}
	
	public Utente get_utente() {
		return utente;
	}

	@Override
	public String toString() {
		return "Operatore " + this.utente.get_username();
	}
	/**
	 * Due operatori sono ugali quando il loro utente associato ha lo stesso username
	 */
	@Override
	public boolean equals(Object obj) {
		Operatore o=(Operatore) obj;
		if(this.get_utente().get_username().equals(o.get_utente().get_username()))
		{
			return true;
		}else
			return false;
	}
}
