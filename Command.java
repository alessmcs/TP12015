
import java.util.ArrayList;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;


public class Command {

    private static Arbre arbre;
    private static Grille grille;
    public static ArrayList<String[]> listesMots = new ArrayList<>();
    public static ArrayList<Grille> grilles = new ArrayList<>();

    private static char[][] listeGrille;

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


    public static Lettre[] trouverVoisin(int posI, int posJ)  {

            char[][] liste = listeGrille; // utiliser la liste locale de Command
            Lettre[] voisins = new Lettre[8]; // (make a list of lettre objects)

            int[][] directions = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};


            for (int pos = 0; pos < directions.length; pos++) {
                int i = posI + directions[pos][0];
                int j = posJ + directions[pos][1];


                if (i >= 0 && i < liste.length && j >= 0 && j < liste[i].length) { // changé >= 1 à >= 0 & char à Lettre
                    // voisins[pos] = liste[i][j];
                    voisins[pos] = new Lettre( liste[i][j], i, j);
                } else {
                    voisins[pos] = new Lettre (' ', -1,  -1 );
                   
                }
            }
            return voisins;
         
    }


    public static ArrayList<TrieNode> sortEnfantRoot(Arbre arbre){
        ArrayList<TrieNode> listEnfant = new ArrayList<>();
        for( TrieNode node : arbre.root.children.values()){
            listEnfant.add(node);
        }

        Collections.sort(listEnfant, (a, b) -> Character.compare(a.nodeCharacter, b.nodeCharacter));
        return listEnfant;
    }


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

    public static void addToList(String string){
        listeCoords.add(string);
    }

    public static List buildOutput(){
        Collections.sort(listeCoords);
        List<String> uniqueCoords = listeCoords.stream()
                .distinct()
                .collect(Collectors.toList());
        return uniqueCoords;
    }

}