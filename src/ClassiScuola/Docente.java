package ClassiScuola;

import java.util.ArrayList;

public class Docente {

    // implementare isLaboratorio (controllo con numOre e stessa lezione)
    private final String nome;

    private final ArrayList<Classe> classi = new ArrayList<>();
    private final ArrayList<Materia> materie = new ArrayList<>();
    private final ArrayList<Lezione> lezioni = new ArrayList<>();

    private boolean[][] disponibilita = new boolean[5][6];
    private final boolean laboratorio = false;

    public Docente(String nome) {

        this.nome = nome;

        inizializzaDisponibilita();

    }

    public void aggiungiInformazioni(Classe c, Materia m) {

        if(!classi.contains(c))
            classi.add(c);

        if(!materie.contains(m))
            materie.add(m);
    }

    public int calcolaOre() {

        int oreTotali = 0;

        for(Materia c : materie)
            oreTotali += c.getOre();

        return oreTotali;
    }

    private void inizializzaDisponibilita() {
        for(int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                disponibilita[i][j] = true;
            }
        }
    }

    public boolean isDisponibile(int i, int j) { return disponibilita[i][j]; }

    // -- VINCOLI AVANZATI --

    public void aggiungiGiornoLibero(int giornoLibero) {

        for(int i=0;i<5;i++) { disponibilita[i][giornoLibero] = false; }
    }
    public void vincoloPrimaOra() {

        for(int i=0; i<5; i++) { disponibilita[i][0] = false; }
    }

    public void vincoloUltimaOra() {

        for(int i=0; i<5; i++) { disponibilita[i][5] = false; }
    }

    // -- GETTER --

    public ArrayList<Materia> getCorsi() { return materie; }

    public ArrayList<Classe> getClassi() { return classi; }

    public ArrayList<Lezione> getLezioni() { return lezioni; }

    public String getNome(){ return nome; }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Docente d = (Docente) obj;

        // controllo parametro per parametro
        return this.nome.equals(d.getNome());
    }
}
