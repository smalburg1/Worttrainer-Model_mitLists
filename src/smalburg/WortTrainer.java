package smalburg;
public class WortTrainer {
	/*
	 * Es gibt 1 WortListen-Attribut, für welches es diverse Methoden gibt:
	 * getRandomWord gibt einen zufälligen WortEintrag aus der WortListe zurück
	 * getAktWort gibt den 'zuletzt ausgewählten WortEintrag' zurück
	 * check und checkIgnoreCase überprüfen beide die Wörter des zuletzt ausgewählten
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
		 * Erstellt eine Zufallszahl und verwendet diese dann, um einen zufälligen 
		 * Worteintrag des we-Attributs zu returnen
		 * @return ein zufälliger WortEintrag, wenn das Array 0 lang ist return null
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
		 * @return ausgewählten index des we-attributes
		 */
		try {
			return this.we.getEintrag(this.getAusgewaelt());
		}catch(ParameterNotRightException e) {
			return null;
		}
	}
	public boolean check(String wort) {
		/*
		 * Prüft, ob zuletzt ausgewähltes Wort mit wort im Parameter übereinstimmt.
		 * @return true, wenn übereinstimmt, false , wenn nicht oder array 0 lang ist
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
		 * @return die Anzahl der bis jetzt abgefragten Einträge
		 */
		return this.anzAbgefragt;
	}
	public boolean checkIgnoreCase(String wort) {

		/*
		 * Prüft, ob zuletzt ausgewähltes Wort mit wort im Parameter übereinstimmt.(Aber beachtet keine 
		 * groß -und kleinschreibung)
		 * @return true, wenn übereinstimmt, false , wenn nicht oder array 0 lang ist
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
			throw new ParameterNotRightException("Der Wert für 'anzRichtig' ist ungültig");
		}
	}
	public void setAbgefragt(int wert) throws ParameterNotRightException{
		if(wert >= 0) {
			this.anzAbgefragt = wert;
		}else {
			throw new ParameterNotRightException("Wert für AnzAbgefrat ungültig");
		}
	}
	public int getRichtig() {
		return this.anzRichtig;
	}
	public void wähleAus(int index) throws ParameterNotRightException{
		/*
		 * wählt einen gewissen Index aus, also setzt es auf ausgewählt, wenn der 
		 * index größer als -1 und kleiner als die Länge des Arrays ist
		 * @param der Index, der ausgewählt werden soll
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
		 * @return ausgewählt-Attribut
		 */
		return this.ausgewaehlt;
	}
}