// ------------------------- auteur, HAYS Océane 20240742, MANCAS Alessandra 20249098 -------------------------------

import java.util.ArrayList;


public class Main {
    public static <E> void main(String[] args) {


        String path = args[0];

        // lire le fichier & créer les listes de problèmes dans FileReader
        FileReader fr = new FileReader(path);
        fr.readFile();

        for (int i = 0; i < Command.grilles.size(); i++){

            // Réinitialiser le chemin, la grille et la liste de mots à chaque itération
            Command.listeCoords = new ArrayList<>();
            new Grille();
            Grille grille;
            grille = Command.grilles.get(i);

            String[] listeMots = Command.listesMots.get(i);
            Arbre arbre = new Arbre(listeMots);

            char[][] liste = Command.listLettre(grille);

            /*
                On parcourt les nœuds enfants de la racine de l'arbre, on effectue une instance de Lettre.
                 On récupère dans une liste les voisins de chacune de ses lettres. Finalement, on cherche
                 dans cette nouvelle liste l'existence d'une prochaine lettre associer au mot cherché.
             */
            for (TrieNode node : Command.sortEnfantRoot(arbre)) {

                for (Lettre lettre : Command.parcourirGrille(liste,node)){

                    Lettre[] voisins = Command.trouverVoisin(lettre.getIndexX(), lettre.getIndexY());
                    arbre.trouverProchain(voisins,lettre, node);

                }

            }

            System.out.println("\nProblème " + (i+1) + " : ");
            for(Object s : Command.buildOutput()){
                System.out.println(s);
            }
        }
    }

}