package ClassiScuola;

public class Materia {

    private Classe classe;
    private Docente docente;
    private String materia;
    private int ore;

    public Materia(Classe classe, Docente docente, String materia, int ore) {

        this.classe=classe;
        this.docente=docente;
        this.materia=materia;
        this.ore=ore;
    }

    public String getMateria() {
        return materia;
    }

    public int getOre() {
        return ore;
    }

    public Docente getDocente() {
        return docente;
    }

    public Classe getClasse() {
        return classe;
    }
}
