package ClassiScuola;

import java.util.ArrayList;

public class Docente {

    private final String nome;
    private int oreServizio = 0;

    private ArrayList<Classe> classi = new ArrayList<>();
    private ArrayList<Materia> materie = new ArrayList<>();

    public Docente(String nome) {
        this.nome = nome;
    }

    public ArrayList<Materia> getCorsi() {
        return materie;
    }

    public String getNome(){
        return nome;
    }

    public void aggiungiInformazioni(Classe c, Materia m) {

        if(!classi.contains(c))
            classi.add(c);

        if(!materie.contains(m))
            materie.add(m);
    }

    public void calcolaOreServizio(){
        int oreTotali=0;
        for(Materia c:materie){
            oreTotali+=c.getOre();
        }
        oreServizio=oreTotali;
    }

    @Override
    public String toString() {
        calcolaOreServizio();
        String elencoClassi="";
        for (Materia c:materie){
            elencoClassi+="\n   - CLASSE: " + c.getClasse().getAnno() + c.getClasse().getSezione() + " " + c.getClasse().getCorso() + ", MATERIA: " + c.getMateria() + ", ORE: " + c.getOre();
        }
        return "INFORMAZIONI DOCENTE: " + nome
                + "\nORE DI LAVORO: " + oreServizio
                + "\nELENCO CLASSI ASSEGNATE: " + elencoClassi;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Docente d = (Docente) obj;

        // controllo parametro per parametro
        return this.nome.equals(d.getNome());
    }
}
