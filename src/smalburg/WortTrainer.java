package smalburg;
public class WortTrainer {
	/*
	 * Es gibt 1 WortListen-Attribut, f�r welches es diverse Methoden gibt:
	 * getRandomWord gibt einen zuf�lligen WortEintrag aus der WortListe zur�ck
	 * getAktWort gibt den 'zuletzt ausgew�hlten WortEintrag' zur�ck
	 * check und checkIgnoreCase �berpr�fen beide die W�rter des zuletzt ausgew�hlten
	 * @author Sebastian Malburg
	 * @version 2019_16.09.
	 */
	private WortListe we;
	private int anzAbgefragt;
	private int anzRichtig;
	private int ausgewaehlt;
	public WortTrainer(String wort, String url) throws ParameterNotRightException{
		this.we = new WortListe(wort, url);
		this.anzAbgefragt = 0;
		this.anzRichtig = 0;
		this.ausgewaehlt = 0;
	}
	public WortListe getWe() {
		return this.we;
	}
	public WortEintrag getRandomWord() {
		/*
		 * Erstellt eine Zufallszahl und verwendet diese dann, um einen zuf�lligen 
		 * Worteintrag des we-Attributs zu returnen
		 * @return ein zuf�lliger WortEintrag, wenn das Array 0 lang ist return null
		 */
		int zz = (int)(Math.random()*((this.we.getLength()-1)+1));
		try {
			return this.we.getEintrag(zz);
		}catch(ParameterNotRightException e) {
			return null;
		}
	}
	public WortEintrag getAktWord() throws ParameterNotRightException {
		/*
		 * @return ausgew�hlten index des we-attributes
		 */
		try {
			return this.we.getEintrag(this.getAusgewaelt());
		}catch(ParameterNotRightException e) {
			return null;
		}
	}
	public boolean check(String wort) {
		/*
		 * Pr�ft, ob zuletzt ausgew�hltes Wort mit wort im Parameter �bereinstimmt.
		 * @return true, wenn �bereinstimmt, false , wenn nicht oder array 0 lang ist
		 * @param das Wot, dass verglichen werden soll
		 */
		
		try {
			if(this.we.getEintrag(this.getAusgewaelt()).getWort().equals(wort)) {
				setRichtig(this.anzRichtig + 1);
				return true;
			}else {
				return false;
			}
		}catch(ParameterNotRightException e) {
			return false;
		}
	}
	public int getAbgefragt() {
		/*
		 * @return die Anzahl der bis jetzt abgefragten Eintr�ge
		 */
		return this.anzAbgefragt;
	}
	public boolean checkIgnoreCase(String wort) {

		/*
		 * Pr�ft, ob zuletzt ausgew�hltes Wort mit wort im Parameter �bereinstimmt.(Aber beachtet keine 
		 * gro� -und kleinschreibung)
		 * @return true, wenn �bereinstimmt, false , wenn nicht oder array 0 lang ist
		 * @param das Wot, dass verglichen werden soll
		 */
		wort = wort.toLowerCase();
		try {
			if(this.we.getEintrag(this.getAusgewaelt()).getWort().toLowerCase().equals(wort)) {
				return true;
			}else {
				return false;
			}
		}catch(ParameterNotRightException e) {
			return false;
		}
	}
	public void setRichtig(int wert) throws ParameterNotRightException {
		/*
		 * setzt anzRichtig auf den richtigen Wert
		 * @param der Wert, auf den anzRIchtig gesetzt werden soll
		 */
		if(wert >= 0) {
			this.anzRichtig = wert;
		}else {
			throw new ParameterNotRightException("Der Wert f�r 'anzRichtig' ist ung�ltig");
		}
	}
	public void setAbgefragt(int wert) throws ParameterNotRightException{
		if(wert >= 0) {
			this.anzAbgefragt = wert;
		}else {
			throw new ParameterNotRightException("Wert f�r AnzAbgefrat ung�ltig");
		}
	}
	public int getRichtig() {
		return this.anzRichtig;
	}
	public void w�hleAus(int index) throws ParameterNotRightException{
		/*
		 * w�hlt einen gewissen Index aus, also setzt es auf ausgew�hlt, wenn der 
		 * index gr��er als -1 und kleiner als die L�nge des Arrays ist
		 * @param der Index, der ausgew�hlt werden soll
		 */
		this.anzAbgefragt++;
		if(index >= 0 && index < this.we.getLength()) {
			this.ausgewaehlt = index;
		}else {
			System.out.println("cannot choose");
		}
	}
	public int getAusgewaelt() {
		/*
		 * @return ausgew�hlt-Attribut
		 */
		return this.ausgewaehlt;
	}
}