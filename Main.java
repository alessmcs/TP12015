import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static <E> void main(String[] args) {

        String path = "TP1Input2";

        // lire le fichier
        FileReader fr = new FileReader(path);
        fr.readFile();

        Grille grille = Command.grilles.get(0);
        Arbre arbre = new Arbre(Command.listesMots.get(0));

        char[][] liste = Command.listLettre(grille);


        for (TrieNode node : Command.sortEnfantRoot(arbre)) {

            for (Lettre lettre : Command.parcourirGrille(liste,node)){

                Lettre[] voisins = Command.trouverVoisin(lettre.getIndexX(), lettre.getIndexY());
                arbre.trouverDebut(voisins,lettre, node);

            }

        }

        for(Object s : Command.buildOutput()){
            System.out.println(s);
        }

    }

}