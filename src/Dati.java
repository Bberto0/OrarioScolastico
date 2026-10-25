import java.util.ArrayList;

public class Dati {
    private ArrayList<String> dati;
    private ArrayList<Docente> elencoDocenti=new ArrayList<>();
    private ArrayList<Classe> elencoClassi=new ArrayList<>();
    private ArrayList<Corso> elencoCorsi=new ArrayList<>();

    public Dati(ArrayList<String> dati) {
        this.dati=dati;
    }

    public void costruisciClassi(){
        for(String s:dati){
            String[] div=s.split(";");

            String nomeDocente=div[0];
            int anno=Integer.parseInt(div[1]);
            String sezione=div[2];
            String corso=div[3];
            String materia=div[4];
            int ore=Integer.parseInt(div[5]);

            Classe c=new Classe(anno, sezione, corso);
            if(!elencoClassi.contains(c)){
                elencoClassi.add(c);
            }

            Docente d=new Docente(nomeDocente);
            if(!elencoDocenti.contains(d)){
                elencoDocenti.add(d);
            }

            Corso co=new Corso(c,d,materia, ore);
            elencoCorsi.add(co);

            Classe classeAttuale=elencoClassi.get(elencoClassi.indexOf(c));
            classeAttuale.aggiungiCorso(co);

            Docente docenteAttuale=elencoDocenti.get(elencoDocenti.indexOf(d));
            docenteAttuale.aggiungiCorso(co);
        }
        for(Docente dc:elencoDocenti){
            System.out.println(dc + "\n\n");
        }
    }

}
