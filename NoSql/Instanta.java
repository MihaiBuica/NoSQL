
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
public class Instanta implements Comparable<Instanta>, Cloneable{
    private List<Object> info = new ArrayList();
    private Long timpCreare;
    private Entitate e;
    private Object cheie_primara;
  
    public Instanta(Entitate e, String[] s){
        this.e = e;
        for(int i = 0; i < e.getNoAtrib(); i++){
            if(e.getTipAtribut(i).equals("Integer")){
                Integer x = Integer.parseInt(s[i]);
                this.info.add(x);
                if(i == 0){
                    this.cheie_primara = x;
                    e.setTipCheiePrimara("Integer");
                }
            }
            if(e.getTipAtribut(i).equals("Float")){
                Float x = Float.parseFloat(s[i]);
                this.info.add(x);
                if(i == 0){
                    this.cheie_primara = x;
                    e.setTipCheiePrimara("Float");
                }
            }
            if(e.getTipAtribut(i).equals("String")){
                String x = s[i];
                this.info.add(x);
                if(i == 0){
                    this.cheie_primara = x;
                    e.setTipCheiePrimara("String");
                }
            }
        }
       this.timpCreare = System.nanoTime();
    }
    private void time(){
        this.timpCreare = System.currentTimeMillis();
    }
    public String getNumeEnitate(){
        return e.getNume();
    }
    public String getCheiePrimara(){
        return this.cheie_primara.toString();
    }
    public Long getTimpCreare() {
        return timpCreare;
    }
    
    public void afis(FileWriter f){
        PrintWriter printWriter = new PrintWriter(f);
        printWriter.print(this.e.getNume()+" ");
        for(int i = 0; i < this.e.getNoAtrib(); i++ ){
            printWriter.print(this.e.getAtribut(i)+":");
            if(this.info.get(i) instanceof Float){
                Float x =(Float) this.info.get(i);
                if(x - Math.ceil(x) == 0){
                    int a = (int) Math.ceil(x);
                    printWriter.print(a);
                }
                else{
                    printWriter.print(this.info.get(i).toString());
                }
            } else{
                    printWriter.print(this.info.get(i).toString());
            }
            if(i != this.e.getNoAtrib() - 1){
                printWriter.print(" ");
            }
        }
       printWriter.println();
    }
    @Override
    public int compareTo(Instanta o) {
        if(this.timpCreare.compareTo(o.getTimpCreare()) > 0){
            return -1;
        } else if (this.timpCreare.compareTo(o.getTimpCreare()) < 0){
            return 1;
        }
        return 0;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        return (Instanta)super.clone();
    }
}
