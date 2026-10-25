public class Corso {
    private Classe classe;
    private Docente docente;
    private String materia;
    private int ore;
    public Corso(Classe classe, Docente docente, String materia, int ore){
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

    public String getDocente() {
        return docente.getNome();
    }
    public String getClasse() {
        return classe.getAnno()+classe.getSezione()+" "+classe.getCorso();
    }
}
