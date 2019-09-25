import java.util.ArrayList;
import java.util.List;
/**
 * Clasa ce implementeaza o entitate.
 * @author Mihai_Buica
 */
public class Entitate {
    private String numeEntitate;
    private int rf;
    protected int noAtrib;
    private List<String> atribut;
    protected List<String> tip_atribut;
    protected List<Instanta> tablou_instante;
    private Object cheiePrimara;
    private String tipCheiePrimara;
    public Entitate() {
    }
    
    /**
     * Constructor
     * @param nume nume entitate
     * @param rf rf
     * @param no_atrib numar de atribute
     */
    public Entitate(String nume, int rf, int no_atrib) {
        this.numeEntitate = nume;
        this.rf = rf;
        this.noAtrib = no_atrib;
        this.atribut = new ArrayList<>(no_atrib);
        this.tip_atribut = new ArrayList<>(no_atrib);
        this.tablou_instante = new ArrayList<Instanta>(no_atrib);
   }

    /**
     * Adaugare tip atribut
     * @param s tipul atributului sub forma de string
     */
    public void addTip(String s){
        this.tip_atribut.add(s);
    }

    /**
     * Metoda de adaugare atribut
     * @param s atribut
     */
    public void addAtribut(String s){
        this.atribut.add(s);
    }

    /**
     * @return numarul de atribute
     */
    public int getNoAtrib() {
        return noAtrib;
    }

    /**
     * @param tipCheiePrimara tipul cheii primare
     */
    public void setTipCheiePrimara(String tipCheiePrimara) {
        this.tipCheiePrimara = tipCheiePrimara;
    }

    public String getTipCheiePrimara() {
        return tipCheiePrimara;
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
}
