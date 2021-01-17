/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

/**
 *
 * @author Administrator
 */
public class VariantName {
    private String name;
    
    public VariantName(){
        name = "AVN";
    }
    
    public VariantName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
