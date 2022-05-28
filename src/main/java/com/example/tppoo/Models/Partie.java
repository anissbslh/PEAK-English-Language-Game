package com.example.tppoo.Models;
import com.example.tppoo.MainApplication;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.*;

public class Partie {
    private boolean enCours;
    private boolean suspendu;
    private boolean enPause;
    private int id;
    private ArrayList<Image> images;
    private ArrayList<Definition> definitions;
    private Plateau plateau;
    private Dé dé1;
    private Dé dé2;

    public Partie(Scene scene) {
        this.enCours = true;
        this.enPause = false;
        this.suspendu = false;
        this.plateau = new Plateau();
        this.plateau.genererPlateau(scene);
        this.dé1 = new Dé();
        this.dé2 = new Dé();
        chargerDonnees("");
    }

    public void rechargerPartie(int joueur_position,Scene scene)
    {
        Label player_name = (Label) scene.lookup("#player_name_label");
        player_name.setText(MainApplication.jeu.getJoueur_courant().getNom());

        Label score_label = (Label) scene.lookup("#score_label");
        score_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));

        Label position_label = (Label) scene.lookup("#position_label");
        position_label.setText(Integer.toString(MainApplication.jeu.getJoueur_courant().getPosition()));

        javafx.scene.image.Image image1 = new javafx.scene.image.Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/dice/dice" + Integer.toString(dé1.getValeur()) + ".png")));
        ImageView dice_image1 = (ImageView) scene.lookup("#dice_image1");
        dice_image1.setImage(image1);
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/dice/dice" + Integer.toString(dé2.getValeur()) + ".png")));
        ImageView dice_image2 = (ImageView) scene.lookup("#dice_image2");
        dice_image2.setImage(image2);

        this.plateau.chargerPlateauSurScene(joueur_position, scene);
    }

    public int traiterPosition(int joueur_position,boolean a_clique,int numero_clique,Scene scene) throws DestinationException
    {
        boolean auto = false;
        if(joueur_position>=100)
        {
            joueur_position = 99 - (joueur_position-99);
            auto = true;
        }

        var button = scene.lookup("#case"+Integer.toString(joueur_position));
        button.getStyleClass().clear();
        button.getStyleClass().add(this.plateau.getJoueurClassNameFromCouleur(this.plateau.getCases()[joueur_position].getCouleur()));


        return joueur_position;
    }

    public void setEnPause(boolean enPause) {
        this.enPause = enPause;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    //charger les images, mots, definitions du fichier ou ils sont stockés
    public void chargerDonnees(String nomFichier) {}

    //placer les cases sur le plateau
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public boolean isSuspendu() {
        return suspendu;
    }

    public boolean isEnPause() {
        return enPause;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Dé getDé1() {
        return dé1;
    }

    public Dé getDé2() {
        return dé2;
    }
}
