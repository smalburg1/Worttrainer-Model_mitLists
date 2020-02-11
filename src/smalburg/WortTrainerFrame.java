package smalburg;
/*
 * Diese Klasse ist für das Layout meiner GUI zuständig. Das Frame hat das layout boderlayout.
 * Im North-Bereich wird ein Container mit einem Gridlayout angezeigt
 * Im Center-Breich wird ein Bild mit einer URL angezeigt
 * Im South-Bereich wird angezeigt, was dein Score ist, sowie die Buttons zur Überprüfung der Eingabe des Benutzers
 * @author Sebastian Malburg
 * @verison 2019_22.09.
 */
 

import javax.swing.*;
public class WortTrainerFrame extends JFrame{
	public WortTrainerFrame(WortTrainerLayout l1) throws ParameterNotRightException{
		this.setTitle("Worttrainer");
		this.setSize(800,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		if(l1 != null) {
			this.add(l1);
		}else {
			throw new ParameterNotRightException("Das Layout muss initialisiert sein");
		}
		this.setVisible(true);
	}
}
