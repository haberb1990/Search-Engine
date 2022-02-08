/* Classe: InterfaceRecherche.java

 * Noms: Hani Berchan et Paul Nguimeya
 * 
 * Description: Cette interface permet de faire la recherche d'un mot
 * après avoir realiser l'indexation d'un document.
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Color;

public class InterfaceRecherche extends JFrame {

	private JPanel contentPane;
	private JTextField searchField;
	private JOptionPane confirmer;

	// liste mots en commun avec documents.
	protected static ArrayList<Dictionnaire> documentsRecherche = new ArrayList<Dictionnaire>();  
	// liste mots avec fréquence totale
	protected static ArrayList<Recherche> recherche = new ArrayList<Recherche>(); 
	 // liste nouveau mots non existant dans les documents (à ajouter dans un nouveau fichier .txt)
	protected static ArrayList<String> listeExistante = new ArrayList<String>(); 
																				  

	/**
	 * Creation de la fenetre
	 */
	public InterfaceRecherche() {
		JFrame popUp = new JFrame();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 674);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnQuitterRech = new JButton("Quitter");
		btnQuitterRech.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnQuitterRech.setBounds(816, 606, 85, 21);
		contentPane.add(btnQuitterRech);

		searchField = new JTextField();
		searchField.setToolTipText("Entre ta recherche ici...");
		searchField.setColumns(10);
		searchField.setBounds(134, 176, 175, 25);
		contentPane.add(searchField);

		JButton rechercher = new JButton("Rechercher");

		rechercher.setFont(new Font("Tahoma", Font.BOLD, 10));
		rechercher.setBounds(20, 178, 103, 21);
		contentPane.add(rechercher);

		Label label = new Label("PAGE DE RECHERCHE :");
		label.setFont(new Font("Arial Black", Font.BOLD, 15));
		label.setBounds(10, 10, 631, 38);
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 209, 881, 101);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JButton retour = new JButton("Retour");
		retour.setFont(new Font("Tahoma", Font.BOLD, 13));
		retour.setBounds(713, 607, 85, 21);
		contentPane.add(retour);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("A travers cette page, on fait la recherche d'un mot contenu dans un document."
				+ " Le mot trouvé sera donc affiché, ainsi que le document dans lequel il se trouve avec son nombre d'apparition.");
		textPane.setFont(new Font("Arial", Font.BOLD, 15));
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(10, 54, 839, 56);
		contentPane.add(textPane);

		// *************************************************************//
		// 					ActionListener Rechercher 					//
		// *************************************************************//

		rechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				String r = searchField.getText().toLowerCase();

				ArrayList<String> rechercheMot = new ArrayList<String>();
				ArrayList<String> rechercheMotTrue = new ArrayList<String>();

				// boucle pour rechercher les caractères spéciaux et faire un split
				for (String word : r.split("[^A-z0-9]")) {
					rechercheMot.add(word);
				}

				// reinitialisation de la liste recheche et documentRecherche pour éviter d'avoir
				// un conflit lors d'une prochaine recherche
				if (!documentsRecherche.isEmpty()) {
					documentsRecherche.clear();
					recherche.clear();

				}

				// Cas 1: la recherche est lancée sans mot dans le champ de texte
				if (searchField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(popUp,"     Entrez un mot");
					rechercheMot.clear();
				}

				// Cas 2: utilisateur entre 1 seul mot dans le champ de texte
				if (rechercheMot.size() == 1) {
					if (Dictionnaire.trouverMot(InterfaceIndexation.documents, r.replaceAll("\\s*", "")) == true) {
						textArea.append(Dictionnaire.imprimerIndexInverseMot(InterfaceIndexation.documents,
								r.replaceAll("\\s*", "")) + "\n");
					} else {
						try {
							// ajouter le nouveau mot dans une liste si il n'existe pas dans les documents 
							listeExistante.add(rechercheMot.get(0));
							
							Fichier.writeList(listeExistante, rechercheMot.get(0),
									InterfaceIndexation.getDirectory() + "/ajouterNouveau.txt");
							
							JOptionPane.showMessageDialog(popUp,
									"Un ou plusieurs mots non disponible(s) dans les documents textes."
											+ " Veuillez indexer pour ajouter le(s) mot(s)");
							dispose();
							new InterfaceIndexation().setVisible(true);

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

				// Cas 3: utilisateur recherche plusieurs mots
				if (rechercheMot.size() > 1) {
					
					// boucle dans le cas ou il faut ajouter des nouveaux mots
					for (int i = 0; i < rechercheMot.size(); i++) {
						if (Dictionnaire.trouverMot(InterfaceIndexation.documents, rechercheMot.get(i)) == false) {
							// ajouter le nouveau mot dans une liste si il n'existe pas dans les documents 
							listeExistante.add(rechercheMot.get(i));
							try {
								Fichier.writeList(listeExistante, rechercheMot.get(i),
										InterfaceIndexation.getDirectory() + "/ajouterNouveau.txt");
							} catch (IOException e1) {

								e1.printStackTrace();
							}
						} else {
							// si on trouve le mot, on va ajouter le mot dans rechercheMotTrue
							rechercheMotTrue.add(rechercheMot.get(i));
						}
					}

					// afficher un message si un mot n'existe pas parmis la liste de document indexé
					// les deux listes sont égales dans le cas ou tous les mots dans le champ de recherche
					// sont dans les documents.
					if (rechercheMot.size() != rechercheMotTrue.size()) {
						JOptionPane.showMessageDialog(popUp,
								"Un ou plusieurs mots non disponible dans les documents textes."
										+ " Veuillez indexer pour ajouter le ou les mot(s)");
						dispose();
						new InterfaceIndexation().setVisible(true);
					}

					// condition pour listeExistante non vide : Ajouter le nouveau document à la liste des fichiers
					if (!listeExistante.isEmpty()) {
						InterfaceIndexation.listfiles.add("ajouterNouveau.txt");

					}

					// condition dans le cas ou un des mots recherché existe dans le document
					if (rechercheMotTrue.size() > 0) {
						
						// lorsque le mot rechercheMotTrue est égale au mot dans documents, on l'ajoute dans 
						// documentRecherche
						for (int i = 0; i < rechercheMotTrue.size(); i++) {
							for (int j = 0; j < InterfaceIndexation.documents.size(); j++) {
								if (rechercheMotTrue.get(i).equals(InterfaceIndexation.documents.get(j).getMot())) {
									documentsRecherche.add(InterfaceIndexation.documents.get(j));
								}
							}
						}

						// calcul de la fréquence totale
						int count = 0;
						int frequenceTotal = 0;
						int numDoc = 0;
						for (int i = 0; i < InterfaceIndexation.listfiles.size(); i++) {
							count = 0;
							frequenceTotal = 0;
							for (int j = 0; j < documentsRecherche.size(); j++) {
								if (i + 1 == documentsRecherche.get(j).getDocument()) {
									count += 1;
									frequenceTotal += documentsRecherche.get(j).getFrequence();
									numDoc = j;
								}

							}
							if (count != rechercheMotTrue.size()) {
								frequenceTotal = 0;
							} else {
								recherche.add(new Recherche(rechercheMotTrue,
										documentsRecherche.get(numDoc).getDocument(), frequenceTotal));
							}

						}

						// Condition si un ou plusieurs des mots recherchés ne sont pas dans un meme document
						if (recherche.isEmpty()) {
							JOptionPane.showMessageDialog(popUp, "Les mots ne sont pas dans un même document texte. \n"
									+ "              Le score est impossible à afficher.");
						}

						recherche.sort(Comparer.sortFT);
						textArea.append(Recherche.imprimerRechercheMot(recherche) + "\n");
					}
				}
			}
		});

		// *************************************************************//
		// 						ActionListener Retour 					//
		// *************************************************************//

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							dispose();
							new InterfaceIndexation().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		// *************************************************************//
		// 					ActionListener Quitter 						//
		// *************************************************************//

		btnQuitterRech.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							int resultat = confirmer.showConfirmDialog(null, "Voulez vous Vraiment quitter ?",
									"Quitter", JOptionPane.OK_CANCEL_OPTION);
							if (resultat == 0) { // reponse oui
								System.exit(0);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
	}
}
