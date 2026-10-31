package AnalisiDati;
import ClassiScuola.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ElencoOrari {

    private final Dati d;
    private HashMap <Classe, ArrayList<Lezioni>> lezioniPerClasse = new HashMap<>();
    private HashMap <Classe, Lezioni[][]> griglieOrario = new HashMap<>();

    public ElencoOrari(Dati d) {

        this.d = d;
        istanziaLezioni();
        generaOrario();
    }

    public void istanziaLezioni() {

        // prendo l'elenco di materie per ogni classe
        ArrayList<Materia> materia = d.getElencoMaterie();

        // per ogni materia
        for(Materia m : materia) {

            // per ogni classe che ha la suddetta materia
            for(Classe c : m.getMappaClassiDocenti().keySet()) {

                // per ogni ora della classe
                for(int j = 0; j < m.getOre(); j++) {

                    // istanzio un oggetto lezioni
                    Lezioni l = new Lezioni(m.getDocente(c), c, m);

                    // controllo: se la classe non è già dell'HashMap, la inserisco
                    lezioniPerClasse.putIfAbsent(c, new ArrayList<>());

                    // inserisco la lezione dell' ArrayList della classe
                    lezioniPerClasse.get(c).add(l);
                }
            }
        }
    }
    public void generaOrario(){
        for (Classe c: lezioniPerClasse.keySet()){

            ArrayList<Lezioni> lezioni = lezioniPerClasse.get(c);

            Collections.shuffle(lezioni);

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
    private String trovaGiorno(int i) {
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
                        testoCella = l[g][h].getMateria().getNome();
                    }
                    System.out.printf("%-15s | ", testoCella);
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------\n");
        }
    }
    public void rilevaSovrapposizioni() {
        System.out.println("\n=== CONTROLLO SOVRAPPOSIZIONI DOCENTI ===");
        boolean conflittoTrovato = false;

        // Mappa temporanea per tracciare dove si trova ciascun docente: Chiave = "Giorno - FasciaOraria", Valore = Lezione
        // Oppure confrontiamo direttamente tutte le griglie tra loro.

        // Approccio diretto: raccogliamo tutte le lezioni posizionate in tutte le griglie
        // e controlliamo se lo stesso docente compare nello stesso giorno e ora in classi differenti.

        java.util.Map<String, Lezioni> occupazioniDocenti = new java.util.HashMap<>();

        for (Classe c : griglieOrario.keySet()) {
            Lezioni[][] griglia = griglieOrario.get(c);

            for (int g = 0; g < 5; g++) {
                for (int h = 0; h < 6; h++) {
                    Lezioni lezione = griglia[g][h];

                    if (lezione != null && lezione.getDocente() != null) {
                        String giorno = trovaGiorno(g);
                        String fascia = trovaFasciaOraria(h);
                        String docenteNome = lezione.getDocente().getNome();

                        // Creiamo una chiave univoca per il "momento" nel tempo
                        String chiaveTempoDocente = docenteNome + "_" + giorno + "_" + fascia;

                        if (occupazioniDocenti.containsKey(chiaveTempoDocente)) {
                            // CONFLITTO TROVATO!
                            Lezioni lezionePrecedente = occupazioniDocenti.get(chiaveTempoDocente);

                            System.out.println("⚠️ CONFLITTO: Il docente **" + docenteNome +
                                    "** è assegnato contemporaneamente in due classi!");
                            System.out.println("   - Giorno/Ora: " + giorno + " (" + fascia + ")");
                            System.out.println("   - Classe 1: " + lezionePrecedente.getClasse().getAnno() +
                                    lezionePrecedente.getClasse().getSezione() + " " + lezionePrecedente.getClasse().getCorso() +
                                    " (" + lezionePrecedente.getMateria() + ")");
                            System.out.println("   - Classe 2: " + c.getAnno() + c.getSezione() + " " + c.getCorso() +
                                    " (" + lezione.getMateria() + ")\n");

                            conflittoTrovato = true;
                        } else {
                            occupazioniDocenti.put(chiaveTempoDocente, lezione);
                        }
                    }
                }
            }
        }

        if (!conflittoTrovato) {
            System.out.println("Nessuna sovrapposizione rilevata! Tutti i docenti sono liberi al momento giusto.");
        }
        System.out.println("==========================================\n");
    }
}