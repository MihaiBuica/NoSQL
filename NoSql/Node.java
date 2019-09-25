
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class Node {
    private List<Instanta> insts;
    private int max_capacity;
    private int actual_capacity;

    public Node(int max_capacity) {
        this.max_capacity = max_capacity;
        this.insts = new ArrayList<>(max_capacity);
        this.actual_capacity = 0;
    }
    
    public int add_instance(Instanta a){
      if(this.actual_capacity < this.max_capacity){  
        this.insts.add(a);
        Collections.sort(this.insts);
        this.actual_capacity++;
        return 1;
      }
      else{
         // System.out.println("nu mai este loc in nod");
          return 0;
      }
          
    }
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
    public boolean isEmpty(){
        if (this.actual_capacity == 0){
            return true;
        }
        return false;
    }
    public void print_instante(FileWriter f){
        for(Instanta i : this.insts){
            i.afis(f);
        }
    }
    
}
