
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mihai
 */
public class LNoSQL {
    private String name;
    private int no_nodes;
    private int max_capacity;
    private List<Node> nodes;
    private List<Entitate> entitati = new ArrayList();
    public LNoSQL(String name, Integer no_nodes, Integer max_capacity) {
        this.name = name;
        this.no_nodes = no_nodes;
        this.max_capacity = max_capacity;
        this.nodes = new ArrayList(no_nodes);
        for(int i = 0; i < no_nodes; i++){
            Node nod = new Node(max_capacity);
            this.nodes.add(nod);
        }
    }
    public void add_enitate(Entitate e){
        this.entitati.add(e);
    }
    public Entitate get_entitate(String s){
        for(Entitate i : this.entitati){
            if(i.getNume().equals(s))
                return i;
        }
        return null;
    }
    public void add_instance(Instanta a, int nr_noduri) throws CloneNotSupportedException{
        int rez;
        if (this.no_nodes < nr_noduri){
            System.err.println("Prea putine noduri");
            return;
        }
        int nr_noduri_adaugate = 0;
        int i = 0;
        while(i < this.no_nodes && nr_noduri_adaugate < nr_noduri){
            Instanta aClone =(Instanta) a.clone();
            rez = this.nodes.get(i).add_instance(aClone);
            i++;
            if(rez == 1){
                nr_noduri_adaugate++;
            }
        }
        if (nr_noduri_adaugate != nr_noduri){
            System.out.println("Nu au fost adaugate toate nodurile");
            if(i == this.no_nodes){
                System.out.println("Nu sunt suficiente noduri goale");
            }
        }
    }
    
    public int getMax_capacity() {
        return max_capacity;
    }

    public String getName() {
        return name;
    }

    public int getNo_nodes() {
        return no_nodes;
    }
    public void stergereInstanta(String e, String cheie, FileWriter f){
        int rez = 0;
        for(Node n : this.nodes){
            rez = n.eliminare(e, cheie);
        }
        if(rez == 0){
        PrintWriter printWriter = new PrintWriter(f);
        printWriter.println("NO INSTANCE TO DELETE");

        }
    }
    public void getInstanta(String e, String cheie, FileWriter f){
        String s = "";
        int i = 1;
        Instanta i1 = null;
        Instanta iFinal = null;
        for(Node n : this.nodes){
            i1 = n.cautare(e, cheie);
            if(i1 != null){
               System.out.println("Am gasit");
                s+="Nod"+i+" ";
                iFinal = n.cautare(e, cheie);
            }
            i++;
        }
        PrintWriter printWriter = new PrintWriter(f);
        if(s.equals("")){
            printWriter.println("NO INSTANCE FOUND");
        }
        else{
            printWriter.print(s);
            iFinal.afis(f);
        }
        
    }
    public void snapshot(FileWriter f){
        PrintWriter printWriter = new PrintWriter(f);
        int k = 1;
        int empty = 1;
        for (Node i : this.nodes){
            if(i.isEmpty()){
                k++;
                continue;
            }
            empty = 0;
            printWriter.println("Nod" + k + "");
            k++;
            i.print_instante(f);
        }
        if(empty == 1){
            printWriter.println("EMPTY DB");
        }
    }
    
}
