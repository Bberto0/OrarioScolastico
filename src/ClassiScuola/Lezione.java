package ClassiScuola;

public class Lezione {

    private Docente docente;
    private Classe classe;
    private Materia materia;
    private String giorno;
    private String fasciaOraria;

    public Lezione(Docente docente, Classe classe, Materia materia) {
        this.docente = docente;
        this.classe = classe;
        this.materia = materia;
    }

    // -- SETTER --

    public void setGiorno(String giorno){
        this.giorno = giorno;
    }

    public void setFasciaOraria(String fasciaOraria){
        this.fasciaOraria = fasciaOraria;
    }

    // -- GETTER --

    public Materia getMateria(){ return materia; }

    public Docente getDocente() { return docente; }

    public Classe getClasse() { return classe; }
}
