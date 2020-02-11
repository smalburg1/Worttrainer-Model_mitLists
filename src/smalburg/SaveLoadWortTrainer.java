package smalburg;
import java.io.*;
import javax.swing.*;
public class SaveLoadWortTrainer {
	/*
	 * Diese Klasse ist für das laden und abspeichern der WortListe des im
	 * Parameter übergebenen WortTrainer-Objekts zuständig.
	 * @version 2019_2.10.
	 * @author Sebastian Malburg
	 */
	private WortTrainer t;
	public SaveLoadWortTrainer(WortTrainer t1) throws ParameterNotRightException{
		/*
		 * Das WortTrainer-Arrtribut wird initialisiert
		 */
		if(t1 != null) {
			this.t = t1;
		}else {
			throw new ParameterNotRightException("WortTrainer-Objekt hat keinen Inhalt");
		}
	}
	public void save() throws IOException, ParameterNotRightException{
		/*
		 * Diese Methode ist für das Abspeichern unserer WortListe zuständig.
		 * Zuerst wird überprüft, ob das im Parameter angegebene File überhaupt 
		 * beschrieben werden kann. Wenn ja, dann wird es dort abgespeichert, wenn
		 * nicht, so wird eine Exception geworfen.
		 * @param der Pfad des files, das beschrieben werden soll
		 */
		JFileChooser chooser = new JFileChooser();
		int rueckgabe = chooser.showOpenDialog(null);
		
		File f = null;
		if(rueckgabe == JFileChooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
		}else {
			return;
		}
		if(f.canWrite()) {
			BufferedWriter outputstream = null;
			try {
				outputstream = new BufferedWriter(new FileWriter(f, false));
				outputstream.write(this.t.getWe().toString());
				outputstream.write(this.t.getAbgefragt() + ", " + this.t.getAusgewaelt() + ", " + this.t.getRichtig());
			}finally {
				if(outputstream != null) {
					outputstream.close();
				}
			}
		}else {
			throw new ParameterNotRightException("Angegebene Datei kann nicht beschrieben werden");
		}
	}
	public void load() throws IOException, ParameterNotRightException{
		/*
		 * Diese Methode lädt eine WortListe aus einer im Parameter angegebenen Datei
		 * Zuerst wird mithilfe eines File-Objekts überprüft, ob dieses File überhaupt
		 * gelesen werden kann. Wenn ja, wird das bereits vorhandene Array geleert und 
		 * daraufhin neue gefüllt. 
		 * @param der Pfad, den die gelesene Datei hat
		 */
		JFileChooser chooser = new JFileChooser();
		int rueckgabe = chooser.showOpenDialog(null);
		
		File f = null;
		if(rueckgabe == JFileChooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
		}else {
			return;
		}
		//Leere das Array
		int helpLänge = this.t.getWe().getLength();
		for(int i = this.t.getWe().getLength()-1; i >= 0; i--) {
			this.t.getWe().delEintrag(this.t.getWe().getEintrag(i).getWort());
		}
		
		if(f.canRead()) {//datei kann gelesen werden
			BufferedReader inputStream2 = null;
			try(BufferedReader inputStream = new BufferedReader(new FileReader(f))) {
				String text;
				int länge = 0;
				int anz = 0;
				inputStream2 = new BufferedReader(new FileReader(f));
				while(inputStream2.readLine() != null) länge++;
				
				while((text = inputStream.readLine()) != null) {
					anz++;
					if(anz == länge) {
						this.t.wähleAus(Integer.parseInt(text.substring(text.indexOf(',')+2, text.lastIndexOf(','))));
						this.t.setAbgefragt(Integer.parseInt(text.substring(0, text.indexOf(','))));
						this.t.setRichtig(Integer.parseInt(text.substring(text.lastIndexOf(',')+2, text.length())));
					}else {
						String url = text.substring(text.indexOf(',')+2, text.length()-1);
						String wort = text.substring(0, text.indexOf(','));
						this.t.getWe().addWord(wort, url);
					}
				}
			}catch(NumberFormatException e) {
				System.out.println("NumberFormatException");
			}finally{
				inputStream2.close();
			}
		}else {
			throw new ParameterNotRightException("angegebene Datei kann nicht gelesesen werden");
		}
	}
}
