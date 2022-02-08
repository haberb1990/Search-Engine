/* Classe: Dictionnaire.java
 * Noms: Hani Berchan et Paul Nguimeya 
 * Description: 
 * Classe qui initialise les variables suivantes: le mot dans un document, la fr�quence
 * de ce mot dans le m�me document et le num�ro du document.
 * Elle contient aussi des m�thodes qui assurent l'affichage 
 * dans une structure donn�e.
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
	// 			 M�thode d'affichage et de recherche 		        //
	// *************************************************************//

	@Override
	public String toString() {
		return "[" + mot + "|" + frequence + "|" + document + "]";
	}

	// m�thode qui imprime le r�sultat de la structure 1
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

	// m�thode qui imprime le r�sultat de la structure 2
	public static String imprimerIndexInverse(ArrayList<Dictionnaire> liste) {
		String afficher = "";
		String tempo = ""; // mot temporaire
		/* Le mot temporaire permettra d'�crire les documents avec leur fr�quence respective 
		 * sur la m�me ligne. 
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

	// m�thode qui imprime un mot sp�cifique dans la structure 2
	public static String imprimerIndexInverseMot(ArrayList<Dictionnaire> liste, String r) {
		String afficher = "Mot recherch� : " + r + "\n";

		for (int i = 0; i < liste.size(); i++) {
			if (r.equals(liste.get(i).getMot())) {
				afficher += "Document " + liste.get(i).getDocument() + " : " + "La frequence est: "
						+ liste.get(i).getFrequence() + ".\n";

			}
		}
		return afficher;
	}

	// m�thode pour trouver un mot dans une liste
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
