/* Classe:Comparer.java
 * Noms: Hani Berchan et Paul Nguimeya
 * Description: 
 * La classe Comparer nous permet de ranger nos mots et nos fréquences.
 */

import java.util.Comparator;

public class Comparer {

	// Comparaison des mots et rangement dans l'ordre alphabetique
	public static Comparator<Dictionnaire> sortMot = new Comparator<Dictionnaire>() {
		public int compare(Dictionnaire o1, Dictionnaire o2) {
			return o1.mot.compareTo(o2.mot);
		}
	};

	// Comparaison des fréquences et rangement dans l'ordre décroissant
	public static Comparator<Dictionnaire> sortFrequence = new Comparator<Dictionnaire>() {
		public int compare(Dictionnaire f1, Dictionnaire f2) {
			return f2.frequence - f1.frequence;
		}
	};

	// Comparaison des fréquences totales et rangement dans l'ordre décroissant
	public static Comparator<Recherche> sortFT = new Comparator<Recherche>() {
		public int compare(Recherche fd1, Recherche fd2) {
			return fd2.frequenceTotal - fd1.frequenceTotal;
		}
	};

}
