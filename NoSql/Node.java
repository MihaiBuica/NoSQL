import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clasa ce implementeaza nodurile.
 * @author Mihai
 */
public class Node {
    private List<Instanta> insts;
    private int max_capacity;
    private int actual_ocup;

    /**
     * Constructor ce initializeaza capacitatea maxima, ocuparea actuala=0,
     * creeaza lista de Instante.
     * @param max_capacity capacitatea maxima.
     */
    public Node(int max_capacity) {
        this.max_capacity = max_capacity;
        this.insts = new ArrayList<>(max_capacity);
        this.actual_ocup = 0;
    }
    
    /**
     * Metoda ce adauga o instanta in lista de instante din nod.
     * @param a instanta ce trebuie adaugata
     * @return 1=succes, 0=nu este loc
     */
    public int add_instance(Instanta a){
      if(this.actual_ocup < this.max_capacity){  
        this.insts.add(a);
        Collections.sort(this.insts);
        this.actual_ocup++;
        return 1;
      }
      else{
          return 0;
      }
          
    }

    /**
     * Eliminarea unei instante din nod. Instanta este identificata prin numele
     * entitatii si prin cheia primara(sub forma de string).
     * @param e numele entitatii
     * @param cheie cheia primara
     * @return 1 = eliminat cu succes, 0 = altfel
     */
    public int eliminare(String e, String cheie){
        int ok = 0;
        for(int i = 0; i < this.insts.size(); i++){
            Instanta i1 = this.insts.get(i);
            if(i1.getNumeEnitate().equals(e) &&
                    i1.getCheiePrimara().equals(cheie)){
                this.insts.remove(i);
                ok = 1;
            }
        }
        return ok;
    }

    /**
     * Cautarea unei instante in nod prin intermediul numelui entitatii si
     * a chesii primare.
     * @param e numele entitatii
     * @param cheie cheia primara
     * @return entitatea gasita, null in caz contrat
     */
    public Instanta cautare(String e, String cheie){
        for(int i = 0; i < this.insts.size(); i++){
            Instanta i1 = this.insts.get(i);
            if(i1.getNumeEnitate().equals(e) &&
                    i1.getCheiePrimara().equals(cheie)){
                return i1;
            }
        }
        return null;
    }

    /**
     * Verificare daca un nod este gol.
     * @return true or false
     */
    public boolean isEmpty(){
        return this.actual_ocup == 0;
    }

    /**
     * Printeaza fiecare instanta din nod.
     * @param f = fisierul in care scrie.
     */
    public void print_instante(FileWriter f){
        for(Instanta i : this.insts){
            i.afis(f);
        }
    }
    
}
