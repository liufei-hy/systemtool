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
public class DataVersion {
    private String version;
    
    public DataVersion(){
        version = "version1.0";
    }
    
    public DataVersion(String ver){
        version = ver;
    }
    
    public String getVersion(){
        return version;
    }
    
    public void setVersion(String ver){
        version = ver;
    }
}
