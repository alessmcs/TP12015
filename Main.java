import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static <E> void main(String[] args) {

        String path = "TP1Input";


        // lire le fichier
        FileReader fr = new FileReader(path);
        fr.readFile();

        Grille grille = Command.readFile(path);




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