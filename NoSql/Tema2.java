import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clasa principala, de aici sunt apelate toate celelalte functii.
 * @author Mihai_Buica
 */
public class Tema2 {

    /**
     * Functie utilizate pentru a elimina primul element dintr-un vector 
     * de stringuri.
     * @param comanda vectorul de stringuri din care trebuie eliminat.
     */
    public static void elimina_comanda(String[] comanda){
        for(int i = 0; i < comanda.length - 1; i++){
            comanda[i] = comanda[i+1];
        }       
    }

    /**
     * In main se creeaza baza de date si se identifica , apelandu-se 
     * functiile specializate.
     * @param args args[0] reprezinta fisierul de intrare
     * @throws FileNotFoundException
     * @throws IOException
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws 
            FileNotFoundException, IOException, CloneNotSupportedException {
        File file = new File(args[0]);
        FileWriter file_out = new FileWriter(args[0] + "_out");       
        Scanner scan = new Scanner(file);
        String create_command = scan.nextLine();
        String[] words;
        String delims = " ";
        words = create_command.split(delims);
       //Prima comanda va fi intodeauna cea de creare a bazei de date.
        LNoSQL db = new LNoSQL(words[1], Integer.parseInt(words[2]), 
                Integer.parseInt(words[3]));
        
        while(scan.hasNext()){
            String cuv = scan.nextLine();
            words = cuv.split(delims);
            if(words[0].equals("CREATE")){
                elimina_comanda(words);
                Integer rf = Integer.parseInt(words[1]);
                Integer noa = Integer.parseInt(words[2]);
                Entitate e = new Entitate(words[0], rf, noa);
                for(int i = 0; i < noa * 2; i+= 2){
                    e.addAtribut(words[i+3]);
                    e.addTip(words[i+1+3]);
                }
                db.add_enitate(e);
            } else if(words[0].equals("INSERT")){
                elimina_comanda(words);
                Entitate e  = db.get_entitate(words[0]);
                Instanta i;
                elimina_comanda(words);
                i = new Instanta(e,words);
                db.add_instance(i, e.getRf());
            } else if(words[0].equals("SNAPSHOTDB")){
                db.snapshot(file_out);
            } else if(words[0].equals("DELETE")){
                db.stergereInstanta(words[1], words[2], file_out);
            } else if(words[0].equals("GET")){
                db.getInstanta(words[1], words[2], file_out);
            }
            
        }
        file_out.close();
        
}
}
