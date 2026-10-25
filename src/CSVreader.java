import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class CSVreader {

    // creo un delimitatore per dividere i valori

    // modificare il percorso se il file non si trova nella stessa cartella del progetto
    public final static Path path = Paths.get("ore_docente_classe.csv");

    public static void main(String[] args) {
        String line;

        // creating an array that contains the different values
        ArrayList<String> valori = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {

            // the first line only contains the header for the columns, we ignore it
            br.readLine();

            while((line = br.readLine()) != null){

                // storing the different values of the line whenever a COMMA_DELIMITER is encountered
                valori.add(line);
            }

            //printValues(valori);
            Dati d=new Dati(valori);
            d.costruisciClassi();

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

