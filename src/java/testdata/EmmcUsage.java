/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

/**
 *
 * @author HaYang
 */
public class EmmcUsage {
    private String name;
    private int    free;
    private int    use;
    
    public EmmcUsage(String n, int f, int u){
        name = n;
        free = f;
        use  = u;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public int getFree(){
        return free;
    }
    
    public void setFree(int f){
        free = f;
    }
    
    public int getUse(){
        return use;
    }
    
    public void setUse(int u){
        use = u;
    }    
}
