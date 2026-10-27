package AnalisiDati;
import ClassiScuola.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ElencoOrari {
    private final Dati d;
    private Lezioni[][] griglia=new Lezioni[5][6];
    private HashMap <Classe, ArrayList<Lezioni>> lezioniPerClasse=new HashMap<>();
    public ElencoOrari(Dati d){
        this.d=d;
    }
    public void istanziaLezioni(){
        // prendo l'elenco di materie per ogni classe
        ArrayList<Materia> materia=d.getElencoCorsi();
        for(Materia m:materia){
            // creo tante lezioni quante sono le ore
            for(int i=0;i<m.getOre();i++){
                // istanzio un oggetto lezioni
                Lezioni l=new Lezioni(m.getDocente(), m.getClasse(), m.getMateria());
                // controllo: se la classe non è già dell'HashMap, la inserisco
                lezioniPerClasse.putIfAbsent(m.getClasse(), new ArrayList<>());
                // inserisco la lezione dell' ArrayList della classe
                lezioniPerClasse.get(m.getClasse()).add(l);
            }
        }
    }
    public void generaOrario(){

    }
}
