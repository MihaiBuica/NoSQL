/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Mihai_Buica
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void elimina_comanda(String[] comanda){
        for(int i = 0; i < comanda.length - 1; i++){
            comanda[i] = comanda[i+1];
        }       
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException {
        // TODO code application logic here
        File file = new File(args[0]);
        FileWriter file_out = new FileWriter(args[0] + "_out");       
        Scanner scan = new Scanner(file);
        String create_command = scan.nextLine();
        String[] words;
        String delims = " ";
        words = create_command.split(delims);
       
        LNoSQL db = new LNoSQL(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
        
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
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        file_out.close();
        
//        
//        String cuv="Student 2 3 NrMatricol Integer Nume String Media Float";
//        String[] words;
//        String delims = "\\W+";
//        words = cuv.split(delims);
//       
//        Integer rf = Integer.parseInt(words[1]);
//        Integer noa = Integer.parseInt(words[2]);
//        Entitate e1 = new Entitate(words[0], rf, noa);
//        for(int i = 0; i < noa * 2 ; i+=2){
//            e1.add_atribut(words[i+3]);
//            e1.add_tip(words[i+1+3]);
//        }
//        
//        //e1.afis();
//        Instanta i1;
//        Instanta i2;
//        Instanta i5;
//        Instanta i4;        
//        
//        String cuv4 = "Profesor 2 3 Id Integer Nume String NumarStudenti Integer";
//        words = cuv4.split(delims);
//        rf = Integer.parseInt(words[1]);
//        noa = Integer.parseInt(words[2]);
//        Entitate e2 = new Entitate(words[0], rf, noa);
//        //System.out.println(e2.getNo_atrib());
//        for(int i = 0; i < noa * 2 ; i+=2){
//            e2.add_atribut(words[i+3]);
//            e2.add_tip(words[i+1+3]);
//        }
//       // e2.afis();
//        String cuv5 = "123 Popescu 10.0";
//        i1 = new Instanta(e1, cuv5);
//        //i1.afis();
//        String cuv6 = "125 Ionel 9.8";
//        i2 = new Instanta(e1, cuv6);
//        //i2.afis();
//        String cuv2 = "213 Ionesco 100";
//        i4 = new Instanta(e2, cuv2);
//        //i1.afis();
//        String cuv3 = "214 Brailescu 94";
//        i5 = new Instanta(e2, cuv3);
//        //i2.afis();
//        
//        LNoSQL bd1 = new LNoSQL("bd1: ", 3, 5);
//        bd1.add_instance(i1, rf);
//        bd1.add_instance(i2, rf);
//
//        
//        bd1.add_instance(i4,rf);
//        bd1.add_instance(i5, rf);
//        
//        
//        bd1.print_db();
//        
//        String cuv9 = "Secretar 3 3 Id Integer Nume String Salariu Float";
//        words = cuv9.split(delims);
//        rf = Integer.parseInt(words[1]);
//        noa = Integer.parseInt(words[2]);
//        Entitate e3 = new Entitate(words[0], rf, noa);
//        //System.out.println(e2.getNo_atrib());
//        for(int i = 0; i < noa * 2 ; i+=2){
//            e3.add_atribut(words[i+3]);
//            e3.add_tip(words[i+1+3]);
//        }
//       // e2.afis();
//        String cuv10 = "91 Nastase 3450";
//        i1 = new Instanta(e3, cuv10);
//        bd1.add_instance(i1, rf);
//        
//        bd1.print_db();
    }
//Acum trebuie sa iau comenzile pe rand si sa le implementez :)
}
