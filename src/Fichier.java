/* Classe: Fichier.java
 * Noms: Hani Berchan et Paul Nguimeya
 * Description: 
 * Classe dans laquelle on fait la leture et écriture des documents, le comptage des mots
 * la tokenisation et la liste de fréquence.
 * Reference: demo 10
 */

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fichier {

	public String text;
	public String[] textTokens;
	public ArrayList<String> listeToken;

//****************************************************************//
//            Méthode pour la lecture des fichiers                //
//****************************************************************//

	public void readFile(String pathFile) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(pathFile));
		String line = "";     // stockage de le ligne
		String textRead = ""; // stockage de tout le texte lu

		while ((line = reader.readLine()) != null) {
			textRead += line + " ";
		}
		reader.close();
		this.text = textRead;

	}

//****************************************************************//
//  		       Méthode pour créer des éléments                //
//****************************************************************//

	public ArrayList<String> createTokens() {

		text = text.replaceAll("[^A-z0-9]", " ");

		this.textTokens = this.text.split(" +");
		this.listeToken = new ArrayList<String>();

		for (String tokenDisponible : this.textTokens) {
			this.listeToken.add(tokenDisponible.toLowerCase());
		}
		return listeToken;
	}

//****************************************************************//
//  				Méthode pour compter les mots                 //
//****************************************************************//	

	public int countWord(ArrayList<String> listWord, String token) {
		int count = 0;

		for (int i = 0; i < listWord.size(); i++) {
			if (listWord.get(i).equals(token)) {
				count += 1;
			}

		}
		return count;
	}

//****************************************************************//
//	       Méthode pour faire la liste avec fréquence             //
//****************************************************************//

	public List<Dictionnaire> listFrequence(ArrayList<String> listWord) {
		List<Dictionnaire> listOfWords = new ArrayList<>();
		/* liste temporaire pour ne pas ajouter le même mot plusieurs fois
		 * dans la liste de fréquence.
		 */
		ArrayList<String> listeTemporaire = new ArrayList<>();

		for (int i = 0; i < listWord.size(); i++) {
			if (!listeTemporaire.contains(listWord.get(i))) {
				int s = countWord(listWord, listWord.get(i));
				listOfWords.add(new Dictionnaire(listWord.get(i), s, 0));
				listeTemporaire.add(listWord.get(i));

			}
		}

		return listOfWords;
	}

//****************************************************************//
//          Méthode pour l'écriture dans un fichier               //
//****************************************************************//
	public static void writeList(ArrayList<String> listeExistante, String motDispo, String pathfile)
			throws IOException {

		File fichierSortie = new File(pathfile);

		FileWriter writ = new FileWriter(fichierSortie);
		if (listeExistante.size() == 0) {
			writ.write(motDispo);
			writ.write("\n");
		} else {
			/* si il y a des mots dans le fichierSortie, reajouter les
			 * mots dans le fichier (on ne veut pas supprimer les mots)
			 * dans le fichierSortie si l'utilisateur fait plusieurs recherches
			*/
			for (int i = 0; i < listeExistante.size(); i++) {
				writ.write(listeExistante.get(i));
				writ.write("\n");
			}

		}

		writ.close();

	}

}
