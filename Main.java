import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String path = "TP1Input";

        Grille grille = Command.readFile(path);

        char[][] liste = Command.listLettre(grille);


        Arbre arbre = Command.arbre;

        for (TrieNode node : Command.sortEnfantRoot(arbre)) {


            for (Lettre lettre : Command.parcourirGrille(liste,node)){

                Lettre[] voisins = Command.trouverVoisin(lettre.getIndexX(), lettre.getIndexY());
                //System.out.println(Arrays.toString(voisins));

                arbre.trouverDebut(voisins,lettre, node);

            }

        }

    }

}