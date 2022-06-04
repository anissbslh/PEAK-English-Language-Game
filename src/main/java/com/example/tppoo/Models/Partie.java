package com.example.tppoo.Models;
import com.example.tppoo.MainApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

public class Partie implements Serializable {
    private boolean enCours;
    private boolean suspendu;
    private boolean enPause;
    private int id;
    private ArrayList<Image> images;
    private ArrayList<Definition> definitions;
    private Plateau plateau;
    private Dé dé1;
    private Dé dé2;

    private int score;




    public Partie(Scene scene) {
        this.enCours = true;
        this.enPause = false;
        this.suspendu = false;
        this.plateau = new Plateau();
        this.dé1 = new Dé();
        this.dé2 = new Dé();
        this.definitions = new ArrayList<Definition>();
        this.images = new ArrayList<Image>();
        chargerDonnees("GameData.txt");
        this.plateau.genererPlateau(scene,this.definitions,this.images);
        //this.plateau.genererPlateauPerso(scene,this.definitions,this.images);
    }

    public void rechargerPartie(int joueur_position,int prochaine_position,Scene scene)
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

        if(joueur_position != prochaine_position)
        {
            HBox hint_container = (HBox) scene.lookup("#hint_container");
            hint_container.setVisible(true);

            Label hint_text = (Label) scene.lookup("#hint_text");
            hint_text.setText("Click on the case N°"+Integer.toString(prochaine_position));

            Button roll_button = (Button) scene.lookup("#roll_button");
            roll_button.setDisable(true);
        }


        this.plateau.chargerPlateauSurScene(joueur_position, scene);
    }

    public int traiterPosition(int joueur_position,Scene scene) throws DestinationException
    {
        boolean auto = false;
        if(joueur_position>=100)
        {
            joueur_position = 99 - (joueur_position-99);
            auto = true;
        }

        var button = (Button)scene.lookup("#case"+Integer.toString(joueur_position));
        Platform.runLater(
                () -> {
                    button.setGraphic(this.getPlateau().generateJoueurImage());
                }
        );


        if(joueur_position == 99) {
            System.out.println("Vous avez fini la partie");
            System.out.println("Score : "+Integer.toString(MainApplication.jeu.getJoueur_courant().getScore()));
        }
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
    public void chargerDonnees(String nomFichier) {
        try {
            File myObj = new File(nomFichier);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] line = data.substring(2,data.length()).split(" ",2);
                if(data.charAt(0) == 'I')
                {
                    this.images.add(new Image(line[1],line[0]));
                }
                else
                {
                    this.definitions.add(new Definition(line[1],line[0]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred While reading Players File.");
            e.printStackTrace();
        }
    }

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
