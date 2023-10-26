// ------------------------- auteur, HAYS Océane 20240742, MANCAS Alessandra 20249098 -------------------------------

/*
    La classe Lettre définit un constructeur d'Objet Lettre contenant les informations
    relatives à une Lettre, son caractère (valeur) et sa position x, y dans la grille ainsi
    que des méthodes d'accès.
 */
public class Lettre {
    char caractere;
    int indexX;
    int indexY;
    public Lettre(char caractere, int indexX, int indexY) {
        this.caractere = caractere;
        this.indexX = indexX;
        this.indexY = indexY;
    }


    public int getIndexX(){
        return indexX;
    }

    public int getIndexY(){
        return indexY;
    }
}