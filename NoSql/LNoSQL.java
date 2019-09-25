import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 * Clasa ce reprezinta baza de date si LQL.
 * Atribute:
 * name: numele bazei de date
 * no_nodes: numarul de noduri din baza
 * max_capacity: capacitatea maxima a unui nod
 * nodes: Lista de noduri
 * entitati: Lista de entitati(contine toate entitatile)
 * @author Mihai_Buica
 */
public class LNoSQL {
    private String name;
    private int no_nodes;
    private int max_capacity;
    private List<Node> nodes;
    private List<Entitate> entitati = new ArrayList();
   
    /**
     *Construnctor ce initializeaza baza de date.
     * @param name numele bazei de date
     * @param no_nodes numarul de noduri
     * @param max_capacity capacitatea maxima
     */
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

    /**
      Functie ce adauga entitatea in lista de entitati.
     * @param e entitatea adaugata
     */
    public void add_enitate(Entitate e){
        this.entitati.add(e);
    }

    /**
     *Functie ce returneaza entitatea cu numele s, aflata in noduri
     * @param s numele entitatii cautate
     * @return entitatea cu numele s, null in caz ca nu este gasita
     */
    public Entitate get_entitate(String s){
        for(Entitate i : this.entitati){
            if(i.getNume().equals(s))
                return i;
        }
        return null;
    }

    /**
     * Functie ce adauga in noduri clone ale instantei primite.
     * @param a instanta ce trebuie adaugata
     * @param nr_noduri in cate noduri trebuie adaugata instanta a
     * @throws CloneNotSupportedException
     */
    public void add_instance(Instanta a, int nr_noduri) throws CloneNotSupportedException{
        int rez;
        if (this.no_nodes < nr_noduri){
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
            if(i == this.no_nodes){
            }
        }
    }
    
    /**
     * @return capacitatea maxima.
     */
    public int getMax_capacity() {
        return max_capacity;
    }

    /**
     * @return numele bazeid e date.
     */
    public String getName() {
        return name;
    }

    /**
     * @return numarul de noduri din baza de date.
     */
    public int getNo_nodes() {
        return no_nodes;
    }

    /**
     * Functia cauta instanta entitatii e ce are cheia primara "cheie" si 
     * o elimina din fiecare nod in care apare.
     * @param e entitatea
     * @param cheie cheia primara
     * @param f fisierul in care scrie
     */
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

    /**
     * Inplementarea functiei GET. 
     * @param e numele entitatii
     * @param cheie cheia primara
     * @param f fisier de scriere
     */
    public void getInstanta(String e, String cheie, FileWriter f){
        String s = "";
        int i = 1;
        Instanta i1 = null;
        Instanta iFinal = null;
        for(Node n : this.nodes){
            i1 = n.cautare(e, cheie);
            if(i1 != null){
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

    /**
     * Functie ce implementeaza comanda "SNAPSHOT".
     * @param f fisier de scriere.
     */
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
