Noms: Hani Berchan et Paul Nguimeya
 
Description du Programme:

	Le programme sert en quelque sorte de barre de recherche comme sur tout navigateur. Sauf que
notre recherche est plus axée sur des mots contenus dans un document au format texte (.txt).
	
PAGE D'INDEXATION :

	1- Il commence par analyser le fichier en question (ATTENTION : insérer le ou les fichiers textes dans
un dossier au préalable svp. Sinon il ne pourra pas etre analysé comme prévu et ca affichera une erreur. Malheureusement
ceci fait parti d'un obstacle que nous avons eu lors de la programmation. le JFileChooser n'arrivait pas à lire plusieurs
fichiers à la fois à cause des instructions de notre programme).

	2- Après avoir trouvé le fichier, la Tokenisation entre en place. Chaque mot est donc pris individuellement, mis dans une 
liste qui plus tard calculera ses fréquence dans chaque document texte comme nommé dans notre programme. 
Les mots ajoutés dans la liste sont toujours convertis en minuscules (la première lettre).

	3 - Pour l'affichage des mots en inverse, les mots sont dans une liste, et le programme fais la recherche dans chaque document
pour verifier leur fréquence. Puis retourne le mot avec les documents et leur fréquence dans chacun d'entre eux.


PAGE DE RECHERCHE : 

	1- Cette page ne pourrait fonctionner si au prealable l'indexation n'a pas été faite. Tous simplement parceque notre recherche
se fait dans la liste de mot finale obtenue après indexation.

	2- en faisant donc la recherche d'un mot, comme précedemment on a le mot qui affiche ainsi que sa localisation dans les documents
et sa fréquence. Si on entre un mot inexistant, le programme affiche un message et ajoute le mot en question dans un nouveau document.txt 
(ajouterDocument.txt) dans le dossier ou est contenu les autres documents. Et oblige l'utilisateur à refaire une indexation des documents
car ce dernier doit mettre la liste de mot à jour pour les prochaines recherches. L'opération est la même si 2 mots inexistants sont entrés
dans la barre de recherche. Meme si parmis les 2 mots un est correct, il ne reconnaitra pas le second et l'opération sera la même.

	3- Pour les scores, vu que les mots doivent etre dans une même document avant d'afficher un score, la condition est donc respectée.
Car si un des 2 mots entrés n'est pas dans le même document que l'autre, on aura aucun score qui s'affiche ce qui causera le popUp d'un message
d'alerte.

INSTRUCTIONS:
	Ceci résume en gros notre programme. Maintenant place aux instructions à suivre pour le fonctionnement optimum de notre programme:

	1- Créer un dossier pour déposer les documents textes
	2- Insérer un ou plusieurs documents dans le dossiers
	3- executer le programme sur la page "InterfaceIndexation"
	4- Indexer avant de faire une recherche sinon aucun résultat ne sera obtenu (si l'opération est annulée vous aurez un message)
	5- Recherche du mot désiré. Si existant un resultat sera affiché. Sinon un nouveau document sera crée avec le mot et il faudra refaire
l'indexation.



  

