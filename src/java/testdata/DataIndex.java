/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

/**
 *
 * @author yhx
 */
public class DataIndex {
    private int index;
    
    public DataIndex(){
        index = 1;
    }
    
    public DataIndex(int indx){
        index = indx;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void setIndex(int indx){
        index = indx;
    }            
}
