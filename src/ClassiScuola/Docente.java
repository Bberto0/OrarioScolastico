package ClassiScuola;

import java.util.ArrayList;

public class Docente {
    private String nome;
    private ArrayList<Materia> corsi=new ArrayList<>();
    private int oreServizio=0;

    public Docente(String nome) {
        this.nome=nome;
    }

    public void aggiungiCorso(Materia c){
        this.corsi.add(c);
    }

    public ArrayList<Materia> getCorsi() {
        return corsi;
    }

    public String getNome(){
        return nome;
    }

    public void calcolaOreServizio(){
        int oreTotali=0;
        for(Materia c:corsi){
            oreTotali+=c.getOre();
        }
        oreServizio=oreTotali;
    }

    @Override
    public String toString() {
        calcolaOreServizio();
        String elencoClassi="";
        for (Materia c:corsi){
            elencoClassi+="\n   - CLASSE: " + c.getClasse() + ", MATERIA: " + c.getMateria() + ", ORE: " + c.getOre();
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
