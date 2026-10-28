package ClassiScuola;

import java.util.ArrayList;

public class Classe {
    private int anno;
    private String sezione;
    private String corso;
    private ArrayList<Materia> corsi=new ArrayList<>();

    public Classe(int anno, String sezione, String corso) {
        this.anno=anno;
        this.sezione=sezione;
        this.corso=corso;
    }

    public void aggiungiCorso(Materia c) {
        this.corsi.add(c);
    }

    public ArrayList<Materia> getCorsi() {
        return corsi;
    }

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

    @Override
    public String toString(){
        String elencoDocenti="";
        for (Materia c:corsi){
            elencoDocenti+="\n  - DOCENTE: " + c.getDocente().getNome() + ", MATERIA: " + c.getMateria() + ", ORE: " + c.getOre();
        }
        return "INFORMAZIONI CLASSE " + this.anno + this.sezione + " " + this.corso + ":"
                + "\nELENCO DOCENTI: " + elencoDocenti;
    }
    // override del metodo hashCode per far funzionare le HashMap
    @Override
    public int hashCode() {
        return java.util.Objects.hash(anno, sezione, corso);
    }
}
