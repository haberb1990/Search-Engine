/* Classe: InterfaceIndexation.java
 * Noms: Hani Berchan et Paul Nguimeya
 * 
 * Description: Consideré comme notre Main, il permet l'exécution du programme
 * et c'est notre page principale.
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.filechooser.FileSystemView;

import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import java.awt.Color;

public class InterfaceIndexation extends JFrame {

	private JPanel contentPane;
	private JOptionPane confirmer;

	protected static ArrayList<Dictionnaire> documents = new ArrayList<Dictionnaire>();

	static ArrayList<String> listfiles = new ArrayList<>();
	protected static String directory;

	public static String getDirectory() {
		return directory;
	}

	public static void setDirectory(String directory) {
		InterfaceIndexation.directory = directory;
	}

	/**
	 * creation de notre page Principale
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceIndexation frame = new InterfaceIndexation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public InterfaceIndexation() {

		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 627);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label labelMain = new Label("Bienvenue :");
		labelMain.setBounds(10, 0, 814, 57);
		labelMain.setFont(new Font("Arial", Font.BOLD, 31));
		contentPane.add(labelMain);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnQuitter.setBounds(848, 559, 85, 21);
		contentPane.add(btnQuitter);

		JButton btnIndexer = new JButton("Indexer");
		btnIndexer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIndexer.setBounds(30, 246, 125, 32);
		contentPane.add(btnIndexer);

		Label label_2 = new Label("Indexer Doc(s) :");
		label_2.setBounds(29, 209, 115, 21);
		contentPane.add(label_2);

		JButton btnRecherche = new JButton("Rechercher");

		btnRecherche.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRecherche.setBounds(30, 296, 125, 32);
		contentPane.add(btnRecherche);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(199, 246, 734, 251);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Arial", Font.BOLD, 15));
		textPane.setEditable(false);
		textPane.setText("La page ci pr\u00E9sente servira comme un moteur de recherche. Au pr\u00E9alable, "
				+ "elle analysera des fichiers textes contenus dans notre PC et determinera le nombre de mot "
				+ "et les occurences de ceux-ci dans chaque dossier. Pour que cela fonctionne, suivre les "
				+ "\u00E9tapes suivantes au pr\u00E9alable :"+"\n"
				+ "1- Créer un dossier pour déposer les documents textes\n" + 
				"2- Insérer un ou plusieurs documents dans le dossiers\n" + 
				"3- Clickez sur Indexer pour executer le programme.");
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(10, 78, 839, 125);
		contentPane.add(textPane);

		// **************************************************************//
		// 					ActionListener Indexer 						 //
		// **************************************************************//

		// Affiche les resultats de l'indexation dans le textArea
		btnIndexer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {

							String com = e.getActionCommand();
							textArea.setText("");
							if (com.equals("Indexer")) {

								// créer un object de la classe JFileChooser
								JFileChooser jfile = new JFileChooser(
										FileSystemView.getFileSystemView().getHomeDirectory());

								// defini la selection au chemin uniquement
								jfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

								// appel de la fonction showsSaveDialog
								int r = jfile.showSaveDialog(null);

								// affichage du save Dialog et Choix d'une option
								if (r == JFileChooser.APPROVE_OPTION) {

									if (!documents.isEmpty()) {
										documents.clear();
										listfiles.clear();
									}

									Fichier tokenizer = new Fichier();

									// sélection du chemin et affichage dans le textArea
									File directoryPath = new File(jfile.getSelectedFile().getAbsolutePath());
									textArea.append("Chemin du dossier: \n");
									textArea.append(" " + directoryPath + "\n");
									textArea.append("\n");
									setDirectory("" + directoryPath);
									
									
									// lire les fichiers
									for (File file : directoryPath.listFiles()) {

										listfiles.add(file.getName());

										tokenizer.readFile(directoryPath + "/" + file.getName());

									}

									List<Dictionnaire> d = new ArrayList<>();
									textArea.append("Indexation: \n");
									// tokenisation des fichiers et ajouter les mots dans la liste documents.
									for (int i = 0; i < listfiles.size(); i++) {

										tokenizer.readFile(directoryPath + "/" + listfiles.get(i));

										d = tokenizer.listFrequence(tokenizer.createTokens());
										for (int j = 0; j < d.size(); j++) {
											documents.add(new Dictionnaire(d.get(j).getMot(), d.get(j).getFrequence(),
													(i + 1)));
										}
										// afficher la structure 1
										
										textArea.append(Dictionnaire.imprimerIndexation(d, i + 1) + "\n");
										
									}
									textArea.append("\n");
									// trier fréquence en premier et mot en deuxième
									documents.sort(Comparer.sortFrequence);
									documents.sort(Comparer.sortMot);
									// afficher la structure 2
									textArea.append("Indexation inverse: \n");
									textArea.append(Dictionnaire.imprimerIndexInverse(documents) + "\n");

								}
								
								// si l'utilisateur annule l'opération de recherche de dossier
								else
									textArea.setText("Opération annulée");

							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});

			}
		});

		// *************************************************************//
		// 					ActionListener Recherche					//
		// *************************************************************//

		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							dispose();
							new InterfaceRecherche().setVisible(true);
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

		btnQuitter.addActionListener(new ActionListener() {
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