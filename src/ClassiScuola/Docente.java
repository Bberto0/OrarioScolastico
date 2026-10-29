package ClassiScuola;

import java.util.ArrayList;

public abstract class Docente {

    private final String nome;
    private int oreServizio = 0;

    private final ArrayList<Classe> classi = new ArrayList<>();
    private final ArrayList<Materia> materie = new ArrayList<>();

    public Docente(String nome) {

        this.nome = nome;

        this.oreServizio = calcolaOreServizio();
    }

    public void aggiungiInformazioni(Classe c, Materia m) {

        if(!classi.contains(c))
            classi.add(c);

        if(!materie.contains(m))
            materie.add(m);
    }

    public int calcolaOreServizio() {

        int oreTotali = 0;

        for(Materia c:materie)
            oreTotali+=c.getOre();

        return oreTotali;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Docente d = (Docente) obj;

        // controllo parametro per parametro
        return this.nome.equals(d.getNome());
    }

    // -- GETTER --

    public ArrayList<Materia> getCorsi() {
        return materie;
    }

    public String getNome(){
        return nome;
    }
}
