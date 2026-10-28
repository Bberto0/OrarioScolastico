package AnalisiDati;
import ClassiScuola.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ElencoOrari {
    private final Dati d;
    private HashMap <Classe, ArrayList<Lezioni>> lezioniPerClasse=new HashMap<>();
    private HashMap <Classe, Lezioni[][]> griglieOrario=new HashMap<>();
    public ElencoOrari(Dati d){
        this.d=d;
        istanziaLezioni();
        generaOrario();
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
        for (Classe c: lezioniPerClasse.keySet()){
            ArrayList<Lezioni> lezioni = lezioniPerClasse.get(c);
            Lezioni[][] griglia=new Lezioni[5][6];
            int numLezione=0;
            for (int g=0;g<5;g++){
                for (int h=0;h<6;h++){
                    if(numLezione<lezioni.size()){
                        Lezioni lezioneSelezionata=lezioni.get(numLezione); // itero su tutte le lezioni, selezionandole una ad una
                        lezioneSelezionata.setGiorno(trovaGiorno(g)); // imposto un giorno
                        lezioneSelezionata.setFasciaOraria(trovaFasciaOraria(h)); // imposto una fascia oraria
                        griglia[g][h]=lezioneSelezionata; // inserisco la lezione nella griglia
                        numLezione++;
                    } else {
                        griglia[g][h]=null;
                    }

                }
            }
            griglieOrario.put(c, griglia); // inserisco la griglia e la classe nell'HashMap comprensivo di tutto
        }
    }
    private String trovaGiorno(int i){
        switch (i){
            case 0: return "Lunedì";
            case 1: return "Martedì";
            case 2: return "Mercoledì";
            case 3: return "Giovedì";
            case 4: return "Venerdì";
            default: return null;
        }
    }
    private String trovaFasciaOraria(int i){
        switch (i){
            case 0: return "08:00 - 09:00";
            case 1: return "09:00 - 10:00";
            case 2: return "10:00 - 11:00";
            case 3: return "11:00 - 12:00";
            case 4: return "12:00 - 13:00";
            case 5: return "13:00 - 14:00";
            default: return null;
        }
    }

    public HashMap<Classe, Lezioni[][]> getGriglieOrario() {
        return griglieOrario;
    }

    public void stampaGriglie(){
        for (Classe c : griglieOrario.keySet()){
            System.out.println("ORARIO CLASSE: " + c.getAnno() + c.getSezione() + " " + c.getCorso() + "\n");
            Lezioni[][] l = griglieOrario.get(c);

            // Intestazione con i giorni della settimana
            System.out.printf("%-10s | %-15s | %-15s | %-15s | %-15s | %-15s%n", "Ora", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì");
            System.out.println("------------------------------------------------------------------------------------------");

            for (int h=0;h<6;h++){
                System.out.printf("%-10s | ", (h + 1) + "° ORA");
                for (int g=0;g<5;g++){
                    String testoCella = "[ vuoto ]";
                    if (l[g][h] != null) {
                        testoCella = l[g][h].getMateria();
                    }
                    System.out.printf("%-15s | ", testoCella);
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------\n");
        }
    }
}
