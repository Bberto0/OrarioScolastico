package ClassiScuola;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Materia {

    private String nome;
    private int ore;

    private ArrayList<Classe> classi = new ArrayList<>();
    private ArrayList<Docente> docenti = new ArrayList<>();

    public Materia(Classe classe, Docente docente, String nome, int ore) {

        this.nome = nome;
        this.ore=ore;
    }

    public String getMateria() {
        return nome;
    }

    public int getOre() {
        return ore;
    }

    public void aggiungiInformazioni(Classe c, Docente d) {

        if(!classi.contains(c))
            classi.add(c);

        if(!docenti.contains(d))
            docenti.add(d);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Materia m = (Materia) obj;

        return this.classi.equals(m.classi) &&
               this.docenti.equals(m.docenti) &&
               this.nome.equals(m.nome) &&
               this.ore == m.ore;
    }
}
