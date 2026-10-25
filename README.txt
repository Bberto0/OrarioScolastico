============================================
BREVE GUIDA SUL CODICE DAL VOSTRO CARO BERTO
============================================


1. Uso di Path:


la prima cosa da notare è l'uso della classe Path che si trova nella libreria java.nio.file.*
come suggerito dal nome serve ad istanziare il nome del percorso del nostro file .csv


2. Uso di BufferedReader:


la classe legge uno stream di caratteri, ho usato in particolare Files.newBufferedReader(Path path) che mantiene il codice 
pulito senza bisogno di dover instanziare un fileReader.


Per farvi capire la sintassi sarebbe così se non usassi Files:

BufferedReader br = new BufferedReader(newFileReader(percorso file));


dovremmo anche gestire le eccezioni che ne derivano!


3. Logica del while()


Leggo riga per riga il file .csv fino a che .readLine() ritorna un valore null, quindi abbiamo raggiunto la fine del file


uso poi il metodo split() che "spezza" la stringa in sottostringhe che contengono i nostri singoli valori


N.B: il bufferedReader genera l'eccezione IOException, inserirlo sempre in un blocco try-catch!!!



Spero di non avervi annoiato e che il file possa essere stato d'aiuto.


