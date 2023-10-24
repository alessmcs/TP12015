
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
    private static String[] listeMots;

    private static char[][] listeGrille;

    public static Grille readFile(String fileName){

        Grille grille = new Grille();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();

            // 1re ligne indique les dimensions de la grille
            String[] dimension = line.split(" ");
            int colonne = Integer.parseInt(dimension[0]);
            int ligne = Integer.parseInt(dimension[1]);
            String[][] lignesGrille = new String[ligne][colonne];

            // identifier les lignes de la grille & faire un tableau 2d
            for (int i = 0 ; i < colonne; i++) {
                line = br.readLine();
                String[] uneLigne = line.split(" ");
                lignesGrille[i] = uneLigne;
            }

            // Créer un nouvel objet grille contenant les dimensions et la grille
            grille.setColonne(colonne);
            grille.setLigne(ligne);
            grille.setGrille(lignesGrille);

            // enfin, la dernière ligne indique la liste de mots à chercher dans la grille
            line = br.readLine();

            listeMots = line.split(" ");
            arbre = new Arbre(listeMots);


            br.close();


        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            PrintStream out = System.out;
            File file = new File(fileName);
            out.println("Absolute path:" + file.getAbsolutePath());
        }

        return grille;

    }


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