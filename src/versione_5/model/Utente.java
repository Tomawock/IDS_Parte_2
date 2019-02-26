package versione_5.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Utente implements Serializable{
	
	/**
	 *	Numero seriale per la serializzazione dei dati su File
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String mail;
	private LocalDateTime data_di_nascita;
	private String username;
	private String password;
	
	/**
	 * Viene creato un nuovo utente i cui dati sono salvati dell'arrayList in ingresso
	 * @param dati	valori degli attributi del nuovo utente
	 */
	public Utente (ArrayList<String> dati) {
		this.nome = dati.get(0);
		this.cognome = dati.get(1);
		this.mail = dati.get(2);
		//data di nascita in formato (ANNO, MESE, GIORNO, ORA, MINUTI) NB: gli ultimi due "0,0" sono per ora e minuti
		this.data_di_nascita = LocalDateTime.of(Integer.parseInt(dati.get(5)), Integer.parseInt(dati.get(4)), Integer.parseInt(dati.get(3)), 0 , 0);
		this.username = dati.get(6);
		this.password = dati.get(7);
		
	}
	
	public Utente(String nome, String cognome, String mail, LocalDateTime data_di_nascita, String c_f,
			String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.data_di_nascita = data_di_nascita;
		this.username = username;
		this.password = password;
	}
	
	public void set_nome(String nome) {
		this.nome = nome;
	}
	
	public void set_cognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void set_mail(String mail) {
		this.mail = mail;
	}
	
	public void set_data_di_nascita(LocalDateTime data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}
		
	public void set_username(String username) {
		this.username = username;
	}
	
	public void set_password(String password) {
		this.password = password;
	}
	
	public String get_password() {
		return this.password;
	}

	public String get_nome() {
		return nome;
	}

	public String get_cognome() {
		return cognome;
	}

	public String get_mail() {
		return mail;
	}

	public LocalDateTime get_data_di_nascita() {
		return data_di_nascita;
	}

	public int get_eta() {
		return (LocalDate.now().getYear() - this.data_di_nascita.getYear());
	}
	
	public String get_username() {
		return username;
	}

	@Override
	public String toString() {
		return "  ***** Utente Nome: " + this.nome + " - Cognome: " + this.cognome + " - Mail: " + this.mail + ", Data di Nascita: "
				+ this.data_di_nascita.getDayOfMonth()  +"/"+ this.data_di_nascita.getMonth().getValue() +"/"+
				this.data_di_nascita.getYear() +", Username= " + username + ", Password= $$$ "+ "*****";
	}

	/**
	 * Due utenti sono uguali quando hanno lo stesso username
	 */
	@Override
	public boolean equals(Object obj) {
		Utente u=(Utente) obj;
		if(this.username.equals(u.get_username()))
		{
			return true;
		}else
			return false;
	}
}
	
