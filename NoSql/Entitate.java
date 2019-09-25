
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mihai
 */
public class Entitate {
    private String numeEntitate;
    private int rf;
    protected int noAtrib;
    private ArrayList<String> atribut;
    protected ArrayList<String> tip_atribut;
    protected ArrayList<Instanta> tablou_instante;
    private Object cheiePrimara;
    private String tipCheiePrimara;
    public Entitate() {
    }
    
    public Entitate(String nume, int rf, int no_atrib) {
        this.numeEntitate = nume;
        this.rf = rf;
        this.noAtrib = no_atrib;
        this.atribut = new ArrayList<>(no_atrib);
        this.tip_atribut = new ArrayList<>(no_atrib);
        this.tablou_instante = new ArrayList<Instanta>(no_atrib);
   }
    public void addTip(String s){
         //System.out.println("Aici - tip");
        this.tip_atribut.add(s);
    }
    public void addAtribut(String s){
       // System.out.println("Aici - atribut");
        this.atribut.add(s);
    }
    public int getNoAtrib() {
        return noAtrib;
    }

    public void setTipCheiePrimara(String tipCheiePrimara) {
        this.tipCheiePrimara = tipCheiePrimara;
    }

    public String getTipCheiePrimara() {
        return tipCheiePrimara;
    }
    
    public void addInstance(String s){
        //Instanta i = new Instanta(s);
        //System.out.println(this.getNo_atrib());
        //this.add(i);  
        //i.afis();
    }
    public String getNumeEntitate() {
        return numeEntitate;
    }
    public void setCheie_primara(Object cheie_primara) {
        this.cheiePrimara = cheie_primara;
    }
    
    public String getAtribut(int i){
        return this.atribut.get(i);
    }
    public String getTipAtribut(int i){
        return this.tip_atribut.get(i);
    }
    void add(Instanta i){
        this.tablou_instante.add(i);
    }

    public String getNume() {
        return numeEntitate;
    }
    public int getRf(){
        return this.rf;
    }
    public void afis(){
        for(int i = 0; i < this.noAtrib; i++){
            System.out.println(this.atribut.get(i) + " | " + this.tip_atribut.get(i));
        }
    }
    
}
