package com.fewlaps.android.donaelpas;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    public static List<CampaignBean> getDatabase() {
        List<CampaignBean> campaigns = new ArrayList<CampaignBean>();

        campaigns.add(new CampaignBean(1l, "Pobresa i exclusió social", "Prop del 25% dels nens del nostre pais viuen en la pobresa: Busquem solucions"));
        campaigns.add(new CampaignBean(2l, "Integració Laboral", "Oportunitats per a tots, per a que les persones amb dificultats puguin accedir a una feina"));
        campaigns.add(new CampaignBean(3l, "Atenció integral a malalts avançats", "La Obra Social \"la Caixa\", per la atenció integral"));
        campaigns.add(new CampaignBean(4l, "Salut", "Treballem per a unes condicions sanitàries millors per a tots"));
        campaigns.add(new CampaignBean(5l, "Persones grans", "Promovem l'envelliment actiu de la nostra gent gran, afavorim la seva participació social i prevenim la dependència"));
        campaigns.add(new CampaignBean(6l, "Cooperació Internacional", "Coneix les iniciatives solidàries a les que donem suport"));
        campaigns.add(new CampaignBean(7l, "Interculturalitat y Cohesió Social", "La cotidianitat de les relacions interculturals, una realitat, un repte"));
        campaigns.add(new CampaignBean(8l, "Beques", "Compromesos amb el talent"));
        campaigns.add(new CampaignBean(9l, "Educació", "Un complement a l'educació formal de les escoles, únic i innovador"));
        campaigns.add(new CampaignBean(10l, "Convocatòries", "Donem suport a milers de projectes solidaris"));
        campaigns.add(new CampaignBean(11l, "Voluntariat", "Donem suport al voluntariat amb valor de solidaritat"));
        campaigns.add(new CampaignBean(12l, "Cultura", "Activitats culturals a la teva disposició"));
        campaigns.add(new CampaignBean(13l, "Ciència i investigació", "Contribuïm a la generaió de coneixement científic i la seva difusió per a tots els públics"));
        campaigns.add(new CampaignBean(14l, "Medi Ambient", "Fomentem el respecte per l'entorn i la conservació dels espais naturals"));
        campaigns.add(new CampaignBean(15l, "Vivenda social", "Diversos programes d'accès a la vivenda assequible i solidari"));

        return campaigns;
    }
}
