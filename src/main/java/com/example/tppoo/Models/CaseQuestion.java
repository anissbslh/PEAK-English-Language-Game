package com.example.tppoo.Models;

import javafx.scene.Scene;

import java.io.Serializable;

public abstract class CaseQuestion extends Case {
    protected abstract void CreerFenetre();
    protected abstract void GenererQuestion();
    protected abstract void succes(Joueur joueur);
    protected abstract void echec(Joueur joueur);
}
