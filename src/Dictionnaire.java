/* Classe: Dictionnaire.java
 * Noms: Hani Berchan et Paul Nguimeya 
 * Description: 
 * Classe qui initialise les variables suivantes: le mot dans un document, la fréquence
 * de ce mot dans le même document et le numéro du document.
 * Elle contient aussi des méthodes qui assurent l'affichage 
 * dans une structure donnée.
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class Dictionnaire {

	protected String mot;
	protected int frequence;
	int document;

	public Dictionnaire(String mot, int frequence, int document) {
		this.mot = mot;
		this.frequence = frequence;
		this.document = document;
	}

	// *************************************************************//
	// 						Getters et Setters 						//
	// *************************************************************//

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public int getDocument() {
		return document;
	}

	public void setDocument(int document) {
		this.document = document;
	}

	// *************************************************************//
	// 			 Méthode d'affichage et de recherche 		        //
	// *************************************************************//

	@Override
	public String toString() {
		return "[" + mot + "|" + frequence + "|" + document + "]";
	}

	// méthode qui imprime le résultat de la structure 1
	public static String imprimerIndexation(List<Dictionnaire> d, int x) {
		String afficher = "";
		afficher += "Document " + x + "--> ";
		for (int i = 0; i < d.size(); i++) {
			afficher += "[" + d.get(i).getMot() + " | " + d.get(i).getFrequence() + "]";
			if ((i != d.size() - 1)) {
				afficher += " --> ";
			}
		}

		return afficher;
	}

	// méthode qui imprime le résultat de la structure 2
	public static String imprimerIndexInverse(ArrayList<Dictionnaire> liste) {
		String afficher = "";
		String tempo = ""; // mot temporaire
		/* Le mot temporaire permettra d'écrire les documents avec leur fréquence respective 
		 * sur la même ligne. 
		 */
		for (int i = 0; i < liste.size(); i++) {
			if (tempo.equals(liste.get(i).getMot())) {
				afficher += " --> " + "[" + "d" + liste.get(i).getDocument() + " | " + liste.get(i).getFrequence()
						+ "]";
			} else {
				afficher += "\nMot" + (i + 1) + " : " + liste.get(i).getMot() + " --> ";
				afficher += "[" + "d" + liste.get(i).getDocument() + " | " + liste.get(i).getFrequence() + "]";

			}
			tempo = "";
			tempo += liste.get(i).getMot();
		}
		return afficher;
	}

	// méthode qui imprime un mot spécifique dans la structure 2
	public static String imprimerIndexInverseMot(ArrayList<Dictionnaire> liste, String r) {
		String afficher = "Mot recherché : " + r + "\n";

		for (int i = 0; i < liste.size(); i++) {
			if (r.equals(liste.get(i).getMot())) {
				afficher += "Document " + liste.get(i).getDocument() + " : " + "La frequence est: "
						+ liste.get(i).getFrequence() + ".\n";

			}
		}
		return afficher;
	}

	// méthode pour trouver un mot dans une liste
	public static boolean trouverMot(ArrayList<Dictionnaire> liste, String motChercher) {
		boolean trouver = false;

		for (int i = 0; i < liste.size(); i++) {
			if (motChercher.equals(liste.get(i).getMot())) {
				trouver = true;
				break;
			}
		}

		return trouver;
	}

}
