package com.example.coach.model;

import java.io.Serializable;
import java.util.Date;

public class Profil implements Serializable {
    private static final int MIN_FEMME = 25;
    private static final int MAX_FEMME = 30;
    private static final int MIN_HOMME = 15;
    private static final int MAX_HOMME = 20;

    private static final String[] MESSAGE = {"trop faible", "normal", "trop élevé"};
    private static final String[] IMAGE = {"maigre", "normal", "graisse"};

    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;

    //proprietes ignores par gson grace au mot cle transient
    private transient double img;
    private transient int indice;
    private Date dateMesure;

    /**
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe, Date dateMesure) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.img = calculImg();
        this.indice = calculIndice();
        this.dateMesure = dateMesure;
    }

    /**
     *
     * @return
     */
    public double getImg(){
        return calculImg();
    }

    /**
     *
     * @return
     */
    public String getMessage(){
        return MESSAGE[indice];
    }

    /**
     *
     * @return
     */
    public String getImage(){
        return IMAGE[indice];
    }

    /**
     *
     * @return
     */
    public boolean normal(){
        return indice==1;
    }

    /**
     *
     * @return
     */
    private double calculImg(){
        double tailleMetres = this.taille/100.0;

        return (1.2*poids/(tailleMetres*tailleMetres))+(0.23*age)-(10.83*sexe)-5.4;
    }

    /**
     *
     * @return
     */
    private int calculIndice(){
        int min = (sexe==0) ? MIN_FEMME : MIN_HOMME;
        int max = (sexe==0) ? MAX_FEMME : MAX_HOMME;

        if (img > max){
            return 2;
        }
        if (img >= min){
            return 1;
        }
        return 0;
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }
    public Date getDateMesure() {
        return dateMesure;
    }
}
