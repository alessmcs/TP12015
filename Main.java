import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static <E> void main(String[] args) {

        String path = "TP1Input2";

        // lire le fichier & créer les listes de problèmes dans FileReader
        FileReader fr = new FileReader(path);
        fr.readFile();

        for (int i = 0; i < Command.grilles.size(); i++){

            // Réinitialiser le chemin, la grille et la liste de mots à chaque itération
            Command.listeCoords = new ArrayList<>();
            Grille grille = new Grille();
            grille = Command.grilles.get(i);

            String[] listeMots = Command.listesMots.get(i);
            Arbre arbre = new Arbre(listeMots);

            char[][] liste = Command.listLettre(grille);


            for (TrieNode node : Command.sortEnfantRoot(arbre)) {

                for (Lettre lettre : Command.parcourirGrille(liste,node)){

                    Lettre[] voisins = Command.trouverVoisin(lettre.getIndexX(), lettre.getIndexY());
                    arbre.trouverProchain(voisins,lettre, node);

                }

            }

            System.out.println("\n Problème " + (i+1) + " : ");
            for(Object s : Command.buildOutput()){
                System.out.println(s);
            }
        }
    }

}