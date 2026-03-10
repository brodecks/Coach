package com.example.coach.presenter;

import com.example.coach.contract.ICalculView;
import com.example.coach.model.Profil;

public class CalculPresenter {
    private ICalculView vue;
    private Profil profil;

    /**
     *
     * @param vue
     */
    public CalculPresenter(ICalculView vue) {
        this.vue = vue;
    }

    /**
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public void creerProfil(Integer poids, Integer taille, Integer age,Integer sexe){
        profil = new Profil(poids, taille, age, sexe);

        vue.afficherResultat(profil.getImage(), profil.getImg(), profil.getMessage(), profil.normal());
    }
}
