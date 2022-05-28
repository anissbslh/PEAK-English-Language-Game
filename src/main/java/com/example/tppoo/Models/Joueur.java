package com.example.tppoo.Models;
public class Joueur {

    private int id;
    private String nom;
    private int meilleurScore;
    private int position = 0;
    private int score;
    private int case_clique = 0;
    private boolean a_clique = false;

    public Joueur(String nom)
    {
        this.nom = nom;
        this.position = 0;
        this.score = 0;
    }
    public void setPosition(int position)
    {
        this.position = position;
    }

    //Deplacer le joueur sur le plateau
    public void deplacer(int dp) {
        this.position += dp;
    }

    //generer le lancer des deux des
    public int[] lancerDes(Dé dé1,Dé dé2) {
        int désValeurs[] = new int[2];
        désValeurs[0] = dé1.GenererCoup();
        désValeurs[1] = dé2.GenererCoup();
        return désValeurs;
    }

    //retourner le numero de case cliquée
    public void cliquerCase(int numero) {
        case_clique = numero;
        a_clique = true;
    }

    //ajouter "points" au score courant
    public void ajouterPoints(int points) {
        this.score += points;
    }

    public String getNom()
    {
        return nom;
    }

    public int getId()
    {
        return id;
    }

    public int getMeilleurScore()
    {
        return meilleurScore;
    }

    public int getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }

    public int getCase_clique() {
        return case_clique;
    }

    public boolean getA_clique() {
        return a_clique;
    }
}
