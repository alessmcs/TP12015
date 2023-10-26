// ------------------------- auteur, HAYS Océane 20240742, MANCAS Alessandra 20249098 -------------------------------

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;


public class Command {


    // La liste des mots cherchés dans la grille
    public static ArrayList<String[]> listesMots = new ArrayList<>();

    // Tableau 2D de caractère  TODO
    public static ArrayList<Grille> grilles = new ArrayList<>();

    private static char[][] listeGrille;

    /*
        La méthode renvoie un tableau 2D de caractère. Elle prend en
        paramètre un objet grille, récupère le tableau de String et construit
        le tableau de char.
     */
    public static char[][]listLettre(Grille grille) {
        char[][] listChar = new char[grille.getLigne()][grille.getColonne()];
        for (int i = 0; i < grille.getLigne(); i++) {
            for (int j = 0; j < grille.getColonne(); j++) {

                listChar[i][j] = grille.getGrille()[i][j].charAt(0);
            }
        }
        listeGrille = listChar;
        return listChar;
    }

    /*
        La fonction trouverVoisin construit un tableau de Lettre.
        Il contient les voisins de la lettre correspondant aux positions
        donnés en paramètre.
     */
    public static Lettre[] trouverVoisin(int posI, int posJ)  {

            char[][] liste = listeGrille; // utiliser la liste locale de Command
            Lettre[] voisins = new Lettre[8]; // (make a list of lettre objects)

            int[][] directions = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};


            for (int pos = 0; pos < directions.length; pos++) {
                int i = posI + directions[pos][0];
                int j = posJ + directions[pos][1];


                if (i >= 0 && i < liste.length && j >= 0 && j < liste[i].length) {
                    voisins[pos] = new Lettre( liste[i][j], i, j);
                } else {
                    voisins[pos] = new Lettre (' ', -1,  -1 );
                }
            }
            return voisins;
    }

    /*
        La fonction sortEnfantRoot trie, dans une liste, les enfants
        de la racine de l'arbre, puis renvoie la liste triée.
     */
    public static ArrayList<TrieNode> sortEnfantRoot(Arbre arbre){
        ArrayList<TrieNode> listEnfant = new ArrayList<>(arbre.root.children.values());

        listEnfant.sort((a, b) -> Character.compare(a.nodeCharacter, b.nodeCharacter));
        return listEnfant;
    }


    /*
        La fonction parcourirGrille renvoie une liste de Lettre. Une liste contient
        toutes les occurrences de la première lettre d'un mot.
    */
    public static ArrayList<Lettre> parcourirGrille(char[][] grille, TrieNode node){

        ArrayList<Lettre> lettreInit =  new ArrayList<>();

        for(int i = 0; i < grille.length;i++) {


            for (int j = 0; j < grille[i].length; j++) {


                if (node.nodeCharacter == grille[i][j]) {
                    Lettre uneLettre = new Lettre(node.nodeCharacter, i, j);
                    lettreInit.add(uneLettre);
                }
            }
        }
        
        return lettreInit;
    }

    // Préparer la sortie finale du programme
    public static ArrayList<String> listeCoords = new ArrayList<>();

    // Les coordonnées String de chaque lettre sont ajoutés à une liste.
    public static void addToList(String string){
        listeCoords.add(string);
    }

    /*
     La méthode buildOutput() trie la sortie de chaque mot, en fonction des coordonnées
     de la première lettre. Elle renvoie la liste de la sortie pour chaque mot.
     */
    public static List buildOutput(){
        Collections.sort(listeCoords);
        return listeCoords.stream()
                .distinct()
                .collect(Collectors.toList());
    }

}