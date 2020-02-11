
package smalburg;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.*;
public class WortTrainerLayout extends JPanel{
	private JTextField tf1;
	private JButton bZurueck;
	private JButton bAdd;
	private JButton bLoad;
	private JButton bSave;
	private Container cHead;
	private Container cFoot;
	private Container cFootLeft;
	private Container cFootRight;
	private Container cFootRightRight;
	private Container cFootMiddle;
	private JLabel JLueberschrift;
	private JLabel JLanzRight;
	private JLabel JLanzIns;
	private JLabel img;
	private WortTrainerController c1;
	
	public WortTrainerLayout(WortTrainerController c1) {
		this.setLayout(new BorderLayout());
		this.c1 = c1;
		
		//Kopfbereich wird erstellt
		this.cHead = new Container();
		this.cHead.setLayout(new GridLayout(2,1));
		this.tf1 = new JTextField("Wort Eingeben");
		this.tf1.addActionListener(c1);
        this.tf1.setActionCommand("enter");
		this.JLueberschrift = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen)?");
		this.cHead.add(this.JLueberschrift);
		this.cHead.add(this.tf1);
		this.add(this.cHead, BorderLayout.NORTH);
		
		//Center-Bereich mit Bild
		try {
			this.img = new JLabel();
			this.changeImage(this.c1.getTrainer().getWe().getEintrag(this.c1.getTrainer().getAusgewaelt()).getURL());
		} catch (ParameterNotRightException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(this.img);
		
		//Fußbereich wird erstellt
		this.cFoot = new Container();
		this.cFoot.setLayout(new GridLayout(1,4));
		
		//Linker Fußbereich
		this.cFootLeft = new Container();
		this.cFootLeft.setLayout(new GridLayout(2,1));
		this.cFootLeft.add(new JLabel("Richtige Wörter"));
		this.cFootLeft.add(new JLabel("Anzahl Wörter"));
		this.cFoot.add(this.cFootLeft);
		
		//Mittlerer Linker Fußbreich
		this.cFootMiddle = new Container();
		this.cFootMiddle.setLayout(new GridLayout(2,2));
		this.JLanzRight = new JLabel("" + this.c1.getTrainer().getRichtig());
		this.JLanzIns = new JLabel("" + this.c1.getTrainer().getAbgefragt());
		this.cFootMiddle.add(this.JLanzRight);
		this.cFootMiddle.add(this.JLanzIns);
		this.cFoot.add(cFootMiddle);
		
		// Mittlerer Rechter Fußbereich
		this.cFootRight = new Container();
		this.cFootRight.setLayout(new GridLayout(2,1,5,5));
		this.bAdd = new JButton("Wort hinzufügen");
		this.bZurueck = new JButton("Zurücksetzen");
		this.cFootRight.add(this.bZurueck);
		this.cFootRight.add(this.bAdd);
		this.cFoot.add(this.cFootRight);
		
		//Rechter Fußbereich
		this.cFootRightRight = new Container();
		this.cFootRightRight.setLayout(new GridLayout(2,1,5,5));
		this.bSave = new JButton("Speichern");
		this.bLoad = new JButton("Laden");
		this.cFootRightRight.add(this.bSave);
		this.cFootRightRight.add(this.bLoad);
		this.cFoot.add(this.cFootRightRight);
		
		this.add(this.cFoot, BorderLayout.SOUTH);
		
		this.bAdd.addActionListener(this.c1);
		this.bSave.addActionListener(this.c1);
		this.bLoad.addActionListener(this.c1);
		this.bZurueck.addActionListener(this.c1);
		setVisible(true);
	}
	public String getTextField() {
		/*
		 * @return den Inhalt des Textfeldes
		 */
		return this.tf1.getText();
	}
	public void changeImage(String url) {
		/*
		 * Ändert das Img-Attribut zum gewünschten Bild mit der gewünschten URL
		 * @param die URL, die das Bild hat
		 */
		try {
			ImageIcon icon = new ImageIcon(new URL(url));
			Image image = icon.getImage(); // umwandeln in ein Image-Objekt
			image = image.getScaledInstance(-1, 340,  Image.SCALE_SMOOTH); // skalieren auf gewünschte Größe
			this.img.setIcon(new ImageIcon(image)); // anzeigen in einem JLabel
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setLabels() {
		this.JLanzRight.setText("" + this.c1.getTrainer().getRichtig());
		this.JLanzIns.setText("" + this.c1.getTrainer().getAbgefragt());
	}
	public String[][] newWortEintrag() {
		String[][] str = new String[1][2];
		str[0][0] = JOptionPane.showInputDialog("URL eingeben");
		str[0][1] = JOptionPane.showInputDialog("Wort eingeben");
		return str;
	}
}
