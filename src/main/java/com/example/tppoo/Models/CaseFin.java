package com.example.tppoo.Models;
public class CaseFin extends Case {
    CaseFin(int numero)
    {
        this.setNumero(numero);
        this.setCouleur(Couleur.NOIR);
    }

    public void action(Joueur joueur)
    {}
}