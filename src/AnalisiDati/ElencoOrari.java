package AnalisiDati;
import ClassiScuola.*;

import java.util.ArrayList;
import java.util.HashMap;

// TO-DO:
// modificare il vincolo docente inserendo un arrayList di lezioni non istanziate e un ciclo che non termina
// fino a quando non rimane nessuna lezione

public class ElencoOrari {

    private final Dati d;
    private final HashMap<Classe, ArrayList<Lezione>> lezioniPerClasse = new HashMap<>();
    private final ArrayList<Lezione> lezioni = new ArrayList<>();
    private final HashMap <Classe, Lezione[][]> griglieOrario = new HashMap<>();

    // per controllare la disponibilita'
    private final HashMap<Classe, boolean[][]> occupazioneClassi = new HashMap<>();
    private final HashMap<Docente, boolean[][]> occupazioneDocenti = new HashMap<>();

    public ElencoOrari(Dati d) {

        this.d = d;
        istanziaLezioni();

        // ordina le lezioni in base al numero di ore del docente
        lezioni.sort((l1, l2) -> Integer.compare(
                l2.getDocente().calcolaOre(),
                l1.getDocente().calcolaOre()
        ));

        generaOrario(0);
    }

    public void istanziaLezioni() {

        // prendo l'elenco di materie per ogni classe
        ArrayList<Materia> materia = d.getElencoMaterie();

        // per ogni materia
        for(Materia m : materia) {

            // per ogni classe che ha la suddetta materia
            for(Classe c : m.getMappaClassiDocenti().keySet()) {

                Docente d = m.getDocente(c);

                griglieOrario.putIfAbsent(c, new Lezione[5][6]);
                occupazioneDocenti.putIfAbsent(d, new boolean[5][6]);
                occupazioneClassi.putIfAbsent(c, new boolean[5][6]);

                // per ogni ora della classe
                for(int j = 0; j < m.getOre(); j++) {

                    // istanzio un oggetto lezioni
                    Lezione l = new Lezione(d, c, m);

                    // controllo: se la classe non è già dell'HashMap, la inserisco
                    lezioniPerClasse.putIfAbsent(c, new ArrayList<>());

                    // inserisco la lezione dell' ArrayList della classe
                    lezioniPerClasse.get(c).add(l);

                    lezioni.add(l);
                }
            }
        }
    }

    // il metodo ritorna vero se è riuscito a posizionare tutte le lezioni
    public boolean generaOrario(int indiceLezione) {

        // caso base: tutte le lezioni sono posizionate
        if(indiceLezione == lezioni.size())
            return true;

        Lezione l = lezioni.get(indiceLezione);
        Classe c = l.getClasse();
        Docente d = l.getDocente();

        for(int g = 0; g < 5; g++) {

            for(int h = 0; h < 6; h++) {

                if(isDisponibile(c, d, g, h)) {

                    assegnaLezione(l, g, h);

                    if(generaOrario(indiceLezione + 1))
                        return true; // tutte le lezioni sono state assegnate

                    // se l'assegnazione ha portato ad un vicolo cieco torno indietro (BACKTRACKING)
                    rimuoviLezione(l, g, h);
                }
            }
        }

        // torno ancora piu' indietro se nessuna delle opzioni ha portato ad una soluzione con la lezione corrente
        return false;
    }

    public boolean isDisponibile(Classe classe, Docente docente, int giorno, int ora) {

        if(occupazioneClassi.get(classe)[giorno][ora])
            return false;

        return !occupazioneDocenti.get(docente)[giorno][ora];
    }

    public void assegnaLezione(Lezione lezione, int giorno, int ora) {

        Classe c = lezione.getClasse();
        Docente d = lezione.getDocente();

        // imposto giorno e ora della lezione
        lezione.setGiorno(trovaGiorno(giorno));
        lezione.setFasciaOraria(trovaFasciaOraria(ora));

        // aggiungo la lezione alla griglia orario della classe
        griglieOrario.get(c)[giorno][ora] = lezione;

        // gestisco la disponibilita'
        occupazioneClassi.get(c)[giorno][ora] = true;
        occupazioneDocenti.get(d)[giorno][ora] = true;
    }

    public void rimuoviLezione(Lezione lezione, int giorno, int ora) {

        Classe c = lezione.getClasse();
        Docente d = lezione.getDocente();

        lezione.setGiorno(null);
        lezione.setFasciaOraria(null);

        griglieOrario.get(c)[giorno][ora] = null;

        occupazioneClassi.get(c)[giorno][ora] = false;
        occupazioneDocenti.get(d)[giorno][ora] = false;
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

    public HashMap<Classe, Lezione[][]> getGriglieOrario() {
        return griglieOrario;
    }

    public void stampaGriglie(){
        for (Classe c : griglieOrario.keySet()){
            System.out.println("ORARIO CLASSE: " + c.getAnno() + c.getSezione() + " " + c.getCorso() + "\n"); // sostituire c.get... con toString() di classe
            Lezione[][] l = griglieOrario.get(c);

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

        java.util.Map<String, Lezione> occupazioniDocenti = new java.util.HashMap<>();

        for (Classe c : griglieOrario.keySet()) {
            Lezione[][] griglia = griglieOrario.get(c);

            for (int g = 0; g < 5; g++) {
                for (int h = 0; h < 6; h++) {
                    Lezione lezione = griglia[g][h];

                    if (lezione != null && lezione.getDocente() != null) {
                        String giorno = trovaGiorno(g);
                        String fascia = trovaFasciaOraria(h);
                        String docenteNome = lezione.getDocente().getNome();

                        // Creiamo una chiave univoca per il "momento" nel tempo
                        String chiaveTempoDocente = docenteNome + "_" + giorno + "_" + fascia;

                        if (occupazioniDocenti.containsKey(chiaveTempoDocente)) {
                            // CONFLITTO TROVATO!
                            Lezione lezionePrecedente = occupazioniDocenti.get(chiaveTempoDocente);

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