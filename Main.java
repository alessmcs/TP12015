import java.util.ArrayList;
import java.util.Arrays;

// TODO:  lire le fichier txt pour identifier la grille
// TODO: si un mot est le préfixe d'un autre, pas besoin de refaire la recherche
// TODO: arbre pour la liste de mots pour eviter de refaire la recherche
public class Main {
    public static void main(String[] args) {

        String path = "TP1Input";

        Grille grille = Command.readFile(path);
        System.out.println(grille.getColonne());

        char[][] liste = Command.listLettre(grille);

        Lettre[] listeVoisins = new Lettre[8];

        listeVoisins = Command.trouverVoisin(1,1);

        System.out.println(listeVoisins);
        Arbre arbre = Command.arbre;

        ArrayList<TrieNode> listeEnfants = arbre.trouverDebut(listeVoisins, liste[1][1], Command.arbre.root); // pour l'exemple, utiliser listeVoisins pour le (2,2)

//        for (TrieNode n : listeEnfants){
//            // use the index attributes with the letters!!!
//
//            lettre[] nouvVoisins = Command.trouverVoisin()
//        }


        // une fois avoir construit la grille, parcourir l'arbre en cherchant les voisins
        // Command.parcourirArbre(listeVoisins, arbre);


        char a = Command.lettreGrille(liste)[0].getCaractere();

        System.out.println(a);

        System.out.println(Arrays.deepToString(liste));



    }

}