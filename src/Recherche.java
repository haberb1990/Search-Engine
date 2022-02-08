/* Classe: Recherche.java 
 * Noms: Hani Berchan et Paul Nguimeya
 * Description: 
 * Classe qui initialise les variables suivantes: la liste de mot à rechercher, 
 * le document et la fréquence totale. Cette classe contient aussi une 
 * méthode qui imprime les mots à rechercher.
 */

import java.util.ArrayList;

public class Recherche {

	protected int document;
	protected int frequenceTotal;
	protected ArrayList<String> liste;

	public Recherche(ArrayList<String> liste, int document, int frequenceTotal) {
		this.document = document;
		this.frequenceTotal = frequenceTotal;
		this.liste = liste;
	}

	// *************************************************************//
	// 						Getters et Setters 						//
	// *************************************************************//

	public int getDocument() {
		return document;
	}

	public void setDocument(int document) {
		this.document = document;
	}

	public int getFrequenceTotal() {
		return frequenceTotal;
	}

	public void setFrequenceTotal(int frequenceTotal) {
		this.frequenceTotal = frequenceTotal;
	}

	public ArrayList<String> getListe() {
		return liste;
	}

	public void setListe(ArrayList<String> liste) {
		this.liste = liste;
	}

	@Override
	public String toString() {
		return "[" + liste + "|" + document + "|" + frequenceTotal + "]";
	}

	// *************************************************************//
	// 						Méthode recherche					    //
	// *************************************************************//

	public static String imprimerRechercheMot(ArrayList<Recherche> liste) {
		String afficher = "";

		for (int i = 0; i < liste.size(); i++) {
			afficher += "Document " + liste.get(i).getDocument();
			afficher += " --> " + "Le score est égale à : " + liste.get(i).getFrequenceTotal() + "\n";

		}
		return afficher;
	}
}
