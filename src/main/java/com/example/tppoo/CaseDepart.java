package com.example.tppoo;
public class CaseDepart extends Case {
    CaseDepart(int numero)
    {
        this.setCouleur(Couleur.JAUNE);
        this.setNumero(numero);
    }

    public void action(Joueur joueur){}
}
