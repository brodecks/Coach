package com.example.coach.presenter;


import com.example.coach.api.CoachApi;
import com.example.coach.api.HelperApi;
import com.example.coach.api.ICallbackApi;

import com.example.coach.contract.IHistoView;
import com.example.coach.model.Profil;

import java.util.Collections;
import java.util.List;


public class HistoPresenter {
    private IHistoView vue;

    public HistoPresenter(IHistoView vue) {
        this.vue = vue;
    }

    public void chargerProfils(){
        HelperApi.call(HelperApi.getApi().getProfils(), new ICallbackApi<List<Profil>>() {
            @Override
            public void onSuccess(List<Profil> result) {
                List<Profil> profils = result;
                if(profils != null && !profils.isEmpty()){
                    Collections.sort(profils, (p1, p2) -> p2.getDateMesure().compareTo(p1.getDateMesure()));
                    vue.afficherListe(profils);
                }else{
                    vue.afficherMessage("échec récupération des profils");
                }
            }

            @Override
            public void onError() {
                vue.afficherMessage("échec récupération des profils");
            }
        });
    }

    public void supprProfil(Profil profil, ICallbackApi<Void> callback){
        String profilJson = CoachApi.getGson().toJson(profil);
        HelperApi.call(HelperApi.getApi().supprProfil(profilJson), new ICallbackApi<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                if (result == 1) {
                    vue.afficherMessage("profil supprime");
                    callback.onSuccess(null);
                }else{
                    vue.afficherMessage("échec suppression profil");
                }
            }

            @Override
            public void onError() {
                vue.afficherMessage("échec suppression profil");
            }
        });
    }
    public void transfertProfil(Profil profil){
        vue.transfertProfil(profil);
    }
    public void purgerProfils(){
        HelperApi.call(HelperApi.getApi().purgerProfils(), new ICallbackApi<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                vue.afficherMessage(result + " enregistrements supprimés");
                chargerProfils();
            }
            @Override
            public void onError() {
                vue.afficherMessage("Échec de la purge");
            }
        });
    }
}
