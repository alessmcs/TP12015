
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    public static Arbre arbre;
    private static ArrayList<String[]> listesMots = new ArrayList<>();
    private static ArrayList<Grille> grilles = new ArrayList<>();

    private static char[][] listeGrille;

    public static void setListeMots(String[] mots){
        listesMots.add(mots);
    }

    public static void setGrilles(Grille grille){
        grilles.add(grille);
    }


    // itérer à travers les listes pour traverser le fichier txt


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


    public static Lettre[] trouverVoisin(int posI, int posJ) {
        //char[]  voisins = new char[8];

        char[][] liste = listeGrille; // utiliser la liste locale de Command
        Lettre[] voisins = new Lettre[8]; // (make a list of lettre objects)

        int[][] directions = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

        for (int pos = 0; pos < directions.length; pos++) {
            int i = posI + directions[pos][0];
            if ( i > listeGrille.length - 1) i = -1;
            int j = posJ + directions[pos][1];
            if ( j > listeGrille[0].length - 1) j = -1;


            if (i >= 0 && i <= liste.length && j >= 0 && j <= liste[0].length) { // changé >= 1 à >= 0 & char à Lettre
                // voisins[pos] = liste[i][j];
                voisins[pos] = new Lettre( liste[i][j], i, j);
            } else {
                voisins[pos] = new Lettre (' ', ' ', ' ');
            }
        }
        return voisins;
    }

    public static Lettre[] lettreGrille(char[][] liste){
        ArrayList<Lettre> listeLettre = new ArrayList<>();

        for(int i = 0; i < liste.length;i++){
            for(int j = 0; j < liste[i].length; j++) {
                Lettre uneLettre = new Lettre(liste[i][j],i,j);
                listeLettre.add(uneLettre);
            }
        }
        Lettre[] resultat = listeLettre.toArray(new Lettre[0]);

        return resultat;
    }


    public static void parcourirArbre(char[] listeVoisins, Arbre arbre){


    }

    private static ArrayList<String> listeCoords = new ArrayList<>();

    public static void addToList(String string){
        listeCoords.add(string);
    }

    public static List buildOutput(){
        List<String> uniqueCoords = listeCoords.stream()
                .distinct()
                .collect(Collectors.toList());
        return uniqueCoords;
    }


}