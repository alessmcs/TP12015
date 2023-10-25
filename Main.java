import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String path = "TP1Input";

        Grille grille = Command.readFile(path);

        char[][] liste = Command.listLettre(grille);


        Arbre arbre = Command.arbre;

        for (TrieNode node : Command.sortEnfantRoot(arbre)) {

        Lettre[] listeVoisins = new Lettre[8];

        Lettre parent = new Lettre(liste[2][0], 2, 0); //TODO try for level

        //listeVoisins = Command.trouverVoisin(parent.indexX,parent.indexY);

        listeVoisins = Command.trouverVoisin(3,0);

        System.out.println(listeVoisins);
        Arbre arbre = Command.arbre;


        // trouver les enfants de la lettre du centre à partir de la racine
        arbre.trouverDebut(listeVoisins, parent, Command.arbre.root); // pour l'exemple, utiliser listeVoisins pour le (2,2)
        System.out.println(Command.buildOutput());



//        for (TrieNode n : listeEnfants){
//            // use the index attributes with the letters!!!
//
//            lettre[] nouvVoisins = Command.trouverVoisin()
//        }


        // une fois avoir construit la grille, parcourir l'arbre en cherchant les voisins
        // Command.parcourirArbre(listeVoisins, arbre);



            for (Lettre lettre : Command.parcourirGrille(liste,node)){

                Lettre[] voisins = Command.trouverVoisin(lettre.getIndexX(), lettre.getIndexY());
                //System.out.println(Arrays.toString(voisins));

                arbre.trouverDebut(voisins,lettre, node);

            }

        }

    }

}