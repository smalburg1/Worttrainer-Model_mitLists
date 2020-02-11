package smalburg;
/*
 * Es gibt 2 Attribute: wort & url. Für jedes dieser Attribute gibt es getter -und setter Methoden
 * Im konstrukter werden diese beiden Attribute mithilfe ihrer  setter Methode initialisiert, wobei die URL 
 * in einer eigenen Mehtode auf ihre Richtigkeit geprüft wird
 * @author Sebastian Malburg
 * @version 2019_10.09.
 */
public class WortEintrag {
	private String wort;
	private String url;
	
	public WortEintrag(String wort, String url) throws ParameterNotRightException{
		/*
		 * Die Attribute beiden Parameter werden in die Attribute eingesetzt, wenn die Bedingungen in den
		 * setter-Methoden erfüllt sind (siehe setWort & setURL)
		 * @param die Werte, die in die Attribute eingesetzt werden soll
		 */
		this.setWort(wort);
		this.setURL(url);
	}
	public boolean checkURL(String url) {
		/*
		 * Bedingungen:
		 * Beginn mit http oder https://
		 * danach Buchstabe, dann Punkt 
		 * nach Punkt dürfen nur Buchstaben stehen
		 * .............
		 * @return true wenn alle Bedingungen von Attribut URL erfüllt sind
		 * @param die URL, die überprüft werden soll
		 */
		try {
			if(url.substring(0, 7).equals("http://") || url.substring(0,8).equals("https://")) {//beginnt  es mit http oder https://
				if(istBuchstabe(url.indexOf('/')+2, url)) {
					if(url.charAt(url.length()-1) == '.') {
						String hilfe = url.substring(0, url.lastIndexOf('.'));
						String hilfe1 = hilfe.substring(hilfe.lastIndexOf('.')+1, hilfe.length());
						if(checkWord(hilfe1)) {
							return true;
						}else {
							return false;
						}
					}else {
						String hilfe = url.substring(url.lastIndexOf('.')+1, url.length());
						if(checkWord(hilfe)) {
							return true;
						}else {
							return false;
									
						}
					}
				}
			}
		}catch(StringIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}
	public boolean istBuchstabe(int index, String str) {
		/*
		 * In dieser Methode wird vom gewünschten Index des Übergebenen Strings überprüft, ob es sich
		 * bei diesem Zeichen um einen Buchstaben handelt. Dies wird gemacht, indem der Hexcode des Chars
		 * ermittelt wird.
		 * @param der gewünschte index und der String, der untersucht werden soll
		 * @true, wenn buchstabe
		 */
		try {
			int hexCode = (int)str.charAt(index);
			if((hexCode >= 97 && hexCode <= 122) || hexCode == 223 || hexCode == 146 || hexCode == 128 || hexCode == 252 || (hexCode >= 65 && hexCode <= 90))return true;
			return false;
		}catch(StringIndexOutOfBoundsException e) {
			return false;
		}
	}
	public String getWort() {
		return this.wort;
	}
	public boolean checkWord(String wort) {
		/*
		 * Überprüfft, ob das im Parameter übergebene Wort nur aus Buchstaben besteht
		 * @return true, wenn nur Buchstaben im Wort, false wenn nicht
		 * @param wort, das überprüft werden soll
		 */
		boolean wordCorrect = true;
		if(wort.length() == 0) return false;
		for(int i = 0; i < wort.length(); i++) {
			if(istBuchstabe(i, wort) == false) {
				wordCorrect = false;
				break;
			}
		}
		return wordCorrect;
	}
	public void setWort(String wort) throws ParameterNotRightException{
		/*
		 * Zuerst wird überprüft, dass jeder Buchstabe des Wortes auch wirklich ein Buchstabe ist
		 * Wenn ja dann wird es geändert, wenn nicht wird eine Exception geworfen
		 * @param der Wert, den das Attribut bekommmen soll
		 */
		boolean wordCorrect = checkWord(wort);
		if(wordCorrect == true) {
			this.wort = wort;
		}else {
			throw new ParameterNotRightException("\nwort nicht richtig ('" + wort + "')");
		}
	}

	public String getURL() {
		/*
		 * @return url-Attribut
		 */
		return this.url;
	}
	public void setURL(String url) throws ParameterNotRightException {
		/*
		 * Der Parameter wird in das Attribut gesetzt, sofern es eine korrekte URL ist (siehe checkURL)
		 * @param die Zahl, die in das Attribut gesetzt wird
		 */
		
		if(checkURL(url)) {
			this.url = url;
		}else {
			throw new ParameterNotRightException("\nURL nicht rightig (" + url + ")");
		}
	}
}
