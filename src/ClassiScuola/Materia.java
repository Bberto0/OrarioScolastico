package ClassiScuola;

import java.util.*;

public class Materia {

    private String nome;
    private int ore;

    private final HashMap<Classe, Docente> mappaClassiDocenti = new HashMap<>();

    public Materia(Classe classe, Docente docente, String nome, int ore) {

        this.nome = nome;
        this.ore=ore;
    }

    public String getNome() {
        return nome;
    }

    public int getOre() {
        return ore;
    }

    public void aggiungiInformazioni(Classe c, Docente d) {

        // controlla automaticamente che non ci siano duplicati
        mappaClassiDocenti.put(c, d);
    }

    // -- METODI GETTER --


    public HashMap<Classe, Docente> getMappaClassiDocenti() { return mappaClassiDocenti; }

    // trova un docente data la classe
    public Docente getDocente(Classe c) {

        return mappaClassiDocenti.get(c);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Materia m = (Materia) obj;

        return this.mappaClassiDocenti.equals(m.mappaClassiDocenti) &&
               this.nome.equals(m.nome) &&
               this.ore == m.ore;
    }
}
