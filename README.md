# SAE31_2023 : Evaluation de menu

Le menu de notre application se base sur l'onglet `Paramètres` du navigateur `Opera GX`.


Listes des réferences correspondant aux protocoles de test :

- `a7dbf3d6-872a-11ee-bd67-525400125400` : Trouver l'action qui permet d'activer les raccourcis souris.
- `a7dc0800-872a-11ee-bd67-525400125400` : Modifier les IPFS/IPNS.
- `a7dc1386-872a-11ee-bd67-525400125400` : Définir comme fond d'écran le fond d'écran personnalisé "Batman".
- `a7dc1f59-872a-11ee-bd67-525400125400` : Effacer l'historique de navigation.
- `a7dc2a91-872a-11ee-bd67-525400125400` : Lancer Discord.exe qui vient d'être téléchargé.
- `a7dc372a-872a-11ee-bd67-525400125400` : Activer la fonctionnalité qui rend transparent le lecteur vidéo lors d'une lecture en Picture-in-Picture..
- `a7dc434a-872a-11ee-bd67-525400125400` : Activer ou désactiver les sons du navigateur.
- `a7dc4e77-872a-11ee-bd67-525400125400` : Définir la musique de fond pour : "Confluence - Dominik Kalajdzic".
- `a7dc5b07-872a-11ee-bd67-525400125400` : Ajouter ChatGPT à la barre latérale.
- `a9f5af1c-8632-11ee-bd67-525400125400` : Ajouter un fond d'écran personnalisé.


Commandes :

- `make run-tests` : compile le projet et lance le programme de tests.
- `make run-stats` : compile le projet et lance le programme de statistiques.
- `make build` : compile le projet.
- `make clean` : supprime le dossier build qui contient les classes compilées.

Pour créer une archive JAR des deux programmes :

- `make jar-tests` : crée une archive .jar du programme de tests.
- `make jar-stats` : crée une archive .jar du programme de statistiques.


Exemple de lecture du premier camembert du programme de statistiques :

- `Réussite - 1` : Cela représente la bonne action, qui sera toujours à 1.
- `Échec - 10` : 10 mauvaises actions ont été confondues avec la bonne.

Exemple de lecture du deuxième camembert du programme de statistiques :

- `7 tests de 8` : 8 sous-menus ont été déployés dans 7 tests différents.
- `3 tests de 15` : 15 sous-menus ont été déployés dans 3 tests différents.



Répartition des différents dossiers :

- [Bibliothèque](./lib) : ce qui est utilisé pour se connecter à une base de données MariaDB 
- [Base de données](./sql) : ce qui sert à récupérer et générer les données correspondant aux protocoles.
- [Fichiers sources](./src/fr/iutfbleau/sae31_2023) : tous les fichiers de code de notre projet, contenus dans un package (src/fr/iutfbleau/sae31_2023).

---

© Baptiste BLANCHON, Ayoub AMMARA & Samet DURAN  
- [Sujet disponible ici](http://www.iut-fbleau.fr/sitebp/sae3/31_2023/R9O9Y6NMKZMEE0M1.php).
