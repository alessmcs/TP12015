import java.io.*;

public class FileReader {

    private String fileName;

    //constructor
    public FileReader(String fileName){
        this.fileName = fileName;
    }

    /* La méthode readFile lit le fichier txt fourni et, en utilisant les valeurs lues par le BufferedReader,
       construit les grilles & les listes de mots qu'on utilisera dans Command pour trouver les mots
    * */
    public void readFile(){
        String fileName = this.fileName;
        try {

            int problemIndex = 0;
            java.io.FileReader fileReader = new java.io.FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);
            String line;

            while( (line = br.readLine()) != null) {

                Grille grille = new Grille();

                // 1re ligne indique les dimensions de la grille
                String[] dimension = line.split(" ");
                int ligne = Integer.parseInt(dimension[0]);
                int colonne = Integer.parseInt(dimension[1]);
                String[][] lignesGrille = new String[ligne][colonne];

                // identifier les lignes de la grille & faire un tableau 2d
                for (int i = 0; i < ligne; i++) {
                    line = br.readLine();
                    String[] uneLigne = line.split(" ");
                    lignesGrille[i] = uneLigne;
                }

                // Créer un nouvel objet grille contenant les dimensions et la grille
                grille.setColonne(colonne);
                grille.setLigne(ligne);
                grille.setGrille(lignesGrille);

                Command.grilles.add(problemIndex, grille);

                // enfin, la dernière ligne indique la liste de mots à chercher dans la grille
                line = br.readLine();
                Command.listesMots.add(line.split(" "));

                problemIndex ++;
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            PrintStream out = System.out;
            File file = new File(fileName);
            out.println("Absolute path:" + file.getAbsolutePath());
        }
    }
}