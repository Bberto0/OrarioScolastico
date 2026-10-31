package ClassiScuola;

public class Lezioni {

    private Docente docente;
    private Classe classe;
    private Materia materia;
    private String giorno;
    private String fasciaOraria;

    public Lezioni(Docente docente, Classe classe, Materia materia){
        this.docente = docente;
        this.classe = classe;
        this.materia = materia;
    }

    public void setGiorno(String giorno){
        this.giorno = giorno;
    }

    public void setFasciaOraria(String fasciaOraria){
        this.fasciaOraria = fasciaOraria;
    }

    public Materia getMateria(){
        return materia;
    }

    public Docente getDocente() {
        return docente;
    }

    public Classe getClasse() {
        return classe;
    }
}
