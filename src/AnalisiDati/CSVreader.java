package AnalisiDati;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class CSVreader {

    // creo un delimitatore per dividere i valori
    public final static String COMMA_DELIMITER = ";";

    // modificare il percorso se il file non si trova nella stessa cartella del progetto
    public final static Path path = Paths.get("ore_docente_classe.csv");

    public static void main(String[] args) {
        String line;

        // creo un array di stringhe per istanziare i valori per ogni riga
        ArrayList<String[]> valori = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {

            // la prima riga contiene solo il titolo dei campi quindi viene ignorata
            br.readLine();

            while((line = br.readLine()) != null){

                // spezzo la riga ogni volta che incontro una virgola
                String[] campi = line.split(COMMA_DELIMITER);
                valori.add(campi);
            }

            Dati d=new Dati(valori);
            d.costruisciClassi();
            ElencoOrari e=new ElencoOrari(d);
            e.stampaGriglie();

        } catch(IOException e) {

            System.err.println(e.getMessage());
        }
    }

    public static void printValues(ArrayList<String> values){

        for(String value : values){
            System.out.println(value);
        }
    }
}

