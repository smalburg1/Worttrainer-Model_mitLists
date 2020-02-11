package smalburg;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Hat ein WortEintrag-Array als Attribut, mit verschiednen Zugriffsmethoden auf dieses. Man kann Objekte dem 
 * Array sowohl hinzufügen als auch entfernen, einen Index auswählen und das ganze Array mithilfe dem Überschreiben von toString ausgegeben.
 * @author Sebastian Malburg
 * @version 2019_12.09.
 */
public class WortListe {
	private ArrayList<WortEintrag> we;
	public WortListe(String wort, String url) throws ParameterNotRightException {
		this.we = new ArrayList<>();
		addWord(wort, url);
	}
	
	public int getLength() {
		/*
		 * @return länge des we-Arrays
		 */
		return this.we.size();
	}
	
	public void addWord(String wort, String url) throws ParameterNotRightException{
		/*
		 * Fügt einen neuen Worteintrag mit den im Parameter übergeben Werten ans Ende der Liste hinzu
		 * @param die Werte für den neuen Worteintrag
		 */
		this.we.add(new WortEintrag(wort, url));
	}
	
	public WortEintrag getEintrag(int index) throws ParameterNotRightException {
		/*
		 * Gibt den WortEintrag des Gewünschten index zurück. Ist der Index nicht 
		 * zulässig, so wird eine exception geworfen
		 * @param der Index des WortEintrags-Arrays
		 * @return den WortEintrag des Index, wenn er vorhanden ist
		 */
		try{
			return this.we.get(index);
		}catch(IndexOutOfBoundsException e) {
			throw new ParameterNotRightException("\n Eintrag kann nicht gefunden werden");
		}
	}
	
	public boolean delEintrag(String wort) throws ParameterNotRightException{
		/*
		 * Löscht den WortEintrag des gewünschten wortes. Wenn es das Wort im Array nicht 
		 * gibt, so wird eine Exception geworfen
		 * @param das Wort-attribut eines WortEintrags im we-Array
		 * @return true, wenn erfolgreich, ansonsten false
		 */
		boolean vorhanden = false;
		int index = 0;//Der Index des Wortes wird ermittelt
		for(int i = 0; i < this.we.size(); i++) {
			if(this.we.get(i).getWort().equals(wort)) {
				vorhanden = true;
				index = i;
			}
		}
		if(vorhanden) {
			this.we.remove(index);
			return true;
		}else {
			throw new ParameterNotRightException("\n Wort nicht vorhanden, kann nicht gelöscht werden");
		}
	}
	
	public String toString() {
		/*
		 * Gibt den Inhalt des Arrays als String zurück, wobei jeder index mit einem Zeilenumbruch voneinander
		 * getrennt ist
		 * @return inhalt des Arrays
		 */
		String ausgabe = "";
		for(int i = 0; i < this.we.size(); i++) {
			ausgabe += this.we.get(i).getWort() + ", " + this.we.get(i).getURL() + " \n";
		}
		return ausgabe;
	}
	public double averageWordLength() {
		/*
		 * Berechnet die durchschnittliche Wortlaenge der Woerter in der Liste. 
		 * Dies wird mithilfe einer for-each Schleife realisiert. 
		 * Der durchschnitt ist die Summer der Laenge der einzelnen Worteintraege dividiert durch die anzahl der Elemente in 
		 * der Liste
		 * @return durchschnittliche Wortlaenge der in der Liste enthaltenen Elemente
		 */
		double size = 0;
		for(WortEintrag worteintrag : this.we) {
			size += worteintrag.getWort().length();
		}
		return size/this.we.size();
	}
	public void filter(int length) {
		/*
		 * loescht alle Elemente im Array, die kuerzer als die angegebene Laenge ist.
		 * @param die maximale Laenge
		 */
		Iterator<WortEintrag> iterator = this.we.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getWort().length() < length) iterator.remove();
		}
	}
}
