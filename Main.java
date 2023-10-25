import java.util.ArrayList;
import java.util.Arrays;

// TODO:  lire le fichier txt pour identifier la grille
// TODO: si un mot est le préfixe d'un autre, pas besoin de refaire la recherche
// TODO: arbre pour la liste de mots pour eviter de refaire la recherche
public class Main {
    public static <E> void main(String[] args) {

        String path = "TP1Input";

        // lire le fichier
        FileReader fr = new FileReader(path);
        fr.readFile();


//        System.out.println(grille.getColonne());
//
//        char[][] liste = Command.listLettre(grille);
//
//        Lettre[] listeVoisins = new Lettre[8];
//
//        Lettre parent = new Lettre(liste[2][0], 2, 0); //TODO try for level
//
//        listeVoisins = Command.trouverVoisin(0,0);
//
//        System.out.println(listeVoisins);
//        Arbre arbre = Command.arbre;
//
//
//        // trouver les enfants de la lettre du centre à partir de la racine
//        arbre.trouverDebut(listeVoisins, parent, Command.arbre.root); // pour l'exemple, utiliser listeVoisins pour le (2,2)
//        System.out.println(Command.buildOutput());
//
//        char a = Command.lettreGrille(liste)[0].getCaractere();
//
//        System.out.println(a);
//
//        System.out.println(Arrays.deepToString(liste));



    }

}