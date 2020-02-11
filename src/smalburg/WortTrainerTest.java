package smalburg;

import java.io.IOException;

public class WortTrainerTest {
	public static void main(String[] args) {
		try {
			WortTrainer t1 = new WortTrainer("funchseins", "https://fuchis.com.");
			t1.getWe().addWord("Fuchs", "https://fuchsi.com");
			t1.getWe().addWord("Schwein", "https://schweini.com");
			t1.getWe().addWord("Rüde", "https://rüdi.com");
			t1.getWe().addWord("Bulle", "http://bulli.com");
			t1.getWe().addWord("Katze", "https://katzi.com");
			t1.getWe().addWord("hannes", "https://Hannes.at");
			t1.getWe().delEintrag("Rüde");
			System.out.println("Durchschnittliche laenge: " + t1.getWe().averageWordLength());
			t1.getWe().filter(10);
			System.out.println("Einträge: \n" + t1.getWe());
		}catch(ParameterNotRightException e) {
			System.out.println("\nException. Grund:" + e.getGrund());
		}
	}
}
