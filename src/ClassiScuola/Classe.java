package ClassiScuola;

import java.util.ArrayList;

public class Classe {

    private final int anno;
    private final String sezione;
    private final String corso;

    private final ArrayList<Docente> docenti = new ArrayList<>();
    private final ArrayList<Materia> materie = new ArrayList<>();

    public Classe(int anno, String sezione, String corso) {

        this.anno=anno;
        this.sezione=sezione;
        this.corso = corso;
    }

    public void aggiungiInformazioni(Docente d, Materia m) {

        if(!docenti.contains(d))
            docenti.add(d);

        if(!materie.contains(m))
            materie.add(m);
    }

    public ArrayList<Materia> getCorsi() {
        return materie;
    }

    public ArrayList<Docente> getDocenti() { return docenti; }

    public int getAnno() {
        return anno;
    }

    public String getSezione() {
        return sezione;
    }

    public String getCorso() {
        return corso;
    }

    // override di equals per un controllo efficiente
    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Classe c = (Classe) obj;

        // controllo parametro per parametro
        return this.anno == c.anno &&
               this.sezione.equals(c.sezione) &&
               this.corso.equals(c.corso);
    }

    // override del metodo hashCode per far funzionare le HashMap
    @Override
    public int hashCode() {
        return java.util.Objects.hash(anno, sezione, corso);
    }
}
