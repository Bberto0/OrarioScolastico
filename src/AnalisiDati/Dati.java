package AnalisiDati;

import ClassiScuola.*;
import java.util.ArrayList;

public class Dati {
    private ArrayList<String[]> dati;
    private ArrayList<Docente> elencoDocenti=new ArrayList<>();
    private ArrayList<Classe> elencoClassi=new ArrayList<>();
    private ArrayList<Materia> elencoCorsi=new ArrayList<>();

    public Dati(ArrayList<String[]> dati) {
        this.dati=dati;
    }

    public void costruisciClassi(){
        for(String[] campi : dati){

            String nomeDocente = campi[0];

            int anno =  Integer.parseInt(campi[1]);
            String sezione = campi[2];
            String corso = campi[3];

            String materia = campi[4];
            int ore=Integer.parseInt(campi[5]);

            Classe c = new Classe(anno, sezione, corso);

            alreadyExists(elencoClassi, c);

            Docente d = new Docente(nomeDocente);

            alreadyExists(elencoDocenti, d);

            Materia ma=new Materia(c,d,materia, ore);
            elencoCorsi.add(ma);

            Classe classeAttuale=elencoClassi.get(elencoClassi.indexOf(c));
            classeAttuale.aggiungiCorso(ma);

            Docente docenteAttuale=elencoDocenti.get(elencoDocenti.indexOf(d));
            docenteAttuale.aggiungiCorso(ma);
        }
        for(Docente dc:elencoDocenti){
            System.out.println(dc + "\n\n");
        }
    }

    // implemento un metodo unico per controllare se l'istanza è gia inserita negli elenchi
    // uso un generico elemento T
    public <T> boolean alreadyExists(ArrayList<T> elenco, T elemento) {

        if(elenco.contains(elemento)) aggiornaInformazioni(elemento);

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
