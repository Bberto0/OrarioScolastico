package AnalisiDati;

import ClassiScuola.*;
import java.util.ArrayList;

public class Dati {

    private final ArrayList<String[]> dati;
    private final ArrayList<Docente> elencoDocenti = new ArrayList<>();
    private final ArrayList<Classe> elencoClassi = new ArrayList<>();
    private final ArrayList<Materia> elencoMaterie = new ArrayList<>();

    public Dati(ArrayList<String[]> dati) {
        this.dati=dati;
    }

    public void costruisciClassi(){
        for(String[] campi : dati){

            String nomeDocente = campi[0];

            // informazioni classe
            int anno =  Integer.parseInt(campi[1]);
            String sezione = campi[2];
            String corso = campi[3];

            String materia = campi[4];
            int ore=Integer.parseInt(campi[5]);

            // -- gestione Classe --
            Classe c = new Classe(anno, sezione, corso);

            if(!elencoClassi.contains(c)) {
                // se non esiste, la classe viene aggiunta all'elenco
                elencoClassi.add(c);
            } else {
                // altrimenti passo il riferimento a c per aggiungere le informazioni
                c = elencoClassi.get(elencoClassi.indexOf(c));
            }

            // -- gestione Docente --
            Docente d = new Docente(nomeDocente);

            if(!elencoDocenti.contains(d)){

                elencoDocenti.add(d);
            } else {

                d = elencoDocenti.get(elencoDocenti.indexOf(d));
            }

            // -- gestione Materia --
            Materia m = new Materia(c, d, materia, ore);

            if(!elencoMaterie.contains(m)) {

                elencoMaterie.add(m);
            } else {

                m = elencoMaterie.get(elencoMaterie.indexOf(m));
            }

            // aggiungo le informazioni appena ricavate
            c.aggiungiInformazioni(d, m);
            d.aggiungiInformazioni(c, m);
            m.aggiungiInformazioni(c, d);

        }
        /*for(Classe cl:elencoClassi){
            System.out.println(cl + "\n\n");
        }*/
    }
    private <T> void aggiornaInformazioni(T elemento) {


    }

    // -- METODI GET --

    public ArrayList<Classe> getElencoClassi() {
        return elencoClassi;
    }

    public ArrayList<Docente> getElencoDocenti() {
        return elencoDocenti;
    }

    public ArrayList<Materia> getElencoCorsi() {
        return elencoCorsi;
    }
}
