package smalburg;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JOptionPane;
public class WortTrainerController implements ActionListener{
	private WortTrainer t1;
	private WortTrainerFrame f1;
	private WortTrainerLayout l1;
	private SaveLoadWortTrainer save;
	public static void main(String[] args) {
		WortTrainerController c1 = new WortTrainerController();
	}
	public WortTrainerController() {
		/*
		 * Es werden ein Paar start-Einträge erstellt und alle Objekte werden erzeugt
		 */
		try {
			this.t1 = new WortTrainer("Blume", "https://order.eurobulb.nl/1823-large_default/iris-cycloglossa-1211.jpg");
			this.t1.getWe().addWord("Hund", "https://www.zooroyal.de/magazin/wp-content/uploads/2017/06/hund-im-sommer-760x560.jpg");
			this.t1.getWe().addWord("Katze", "https://geliebte-katze.de/sites/geliebte-katze.de/files/spielregeln-katze.jpg");
			this.l1 = new WortTrainerLayout(this);
			this.save = new SaveLoadWortTrainer(t1);
			this.f1 = new WortTrainerFrame(l1);
		}catch(ParameterNotRightException e) {
			System.out.println("Exception: " + e.getGrund());
		}
	}
	public void actionPerformed(ActionEvent e) {
		/*
		 * Hier findet das event-handlin statt. 
		 * Wenn auf enter gedrückt wird, so wird die Eingabe auf Richtigkeit überprüft 
		 * und ein random Bild wird ausgewählt
		 * Wird auf zurücksetzten geklickt, so werden alle werte auf ihre standard-Werte zurückgesetzt
		 * Wird auf Wort Hinzufügen geklickt, so kann man über JOptionPane einen neuen Wort-Eintrag erstellen
		 * Klickt man auf Speichern oder laden, so kann man dies entsprechend mithilfe eines JFileChoosers machen.
		 * @param das Interaktionselement, dass die Methode aufgerufen hat
		 */
		try {
			String ac = e.getActionCommand();
			if(ac.equals("enter")) {
				if(this.t1.checkIgnoreCase(this.l1.getTextField())) {
					this.t1.setRichtig(this.t1.getRichtig()+1);
				}
				int zz = -1;
				do {
					zz = (int)(Math.random()*(this.t1.getWe().getLength()));
				}while(zz == this.t1.getAusgewaelt());
				this.t1.wähleAus(zz);
				this.l1.changeImage(this.t1.getWe().getEintrag(this.t1.getAusgewaelt()).getURL());
				this.l1.setLabels();
			}
			if(ac.equals("Wort hinzufügen")) {
				try {
					String[][] e1 = this.l1.newWortEintrag();
					if(e1[0][0] != null && e1[0][1] != null) {
						this.t1.getWe().addWord(e1[0][1], e1[0][0]);
					}
				}catch(ParameterNotRightException e1) {
					System.out.println("Error: " + e1.getGrund());
				}
			}
			if(ac.equals("Zurücksetzen")) {
				int zz = -1;
				do {
					zz = (int)(Math.random()*(this.t1.getWe().getLength()));
				}while(zz == this.t1.getAusgewaelt());
				this.t1.wähleAus(zz);
				this.t1.setAbgefragt(0);
				this.t1.setRichtig(0);
				this.l1.setLabels();
				this.l1.changeImage(this.t1.getWe().getEintrag(this.t1.getAusgewaelt()).getURL());
			}
			if(ac.equals("Speichern")) {
				this.save.save();
			}
			if(ac.equals("Laden")) {
				this.save.load();
				this.l1.setLabels();
				this.l1.changeImage(this.t1.getWe().getEintrag(this.t1.getAusgewaelt()).getURL());
			}
		} catch (ParameterNotRightException e1) {
			System.out.println("Exception: " + e1.getGrund());
		}catch(IOException e1) {
			System.out.println(e1);
		}
	}
	public WortTrainer getTrainer() {
		return this.t1;
	}
}
