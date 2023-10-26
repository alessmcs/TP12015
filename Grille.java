// ------------------------- auteur, HAYS Océane 20240742, MANCAS Alessandra 20249098 -------------------------------

/*
    La classe Grille définit un constructeur d'Objet Grille contenant les informations
    relatives à la grille, les dimensions lignes et colonnes et un tableau 2D de String,
    ainsi que des méthodes d'accès.
 */

public class Grille {

    private int colonne;
    private int ligne;
    private String[][] lignesGrille;


    public Grille(int colonne , int ligne, String[][] lignesGrille) {
        this.colonne = colonne;
        this.ligne = ligne;
        this.lignesGrille = lignesGrille;
    }

    public Grille(){

    }

    public String[][] getGrille() {
        return lignesGrille;
    }


    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setGrille(String[][] grille) {
        this.lignesGrille = grille;
    }




}