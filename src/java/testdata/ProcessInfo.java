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
public class ProcessInfo {
    private int id;
    private int pid;
    private String pname;
    
    public ProcessInfo(int id, int pid, String pname){
        this.id    = id;
        this.pid   = pid;
        this.pname = pname;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getPid(){
        return pid;
    }
    
    public void setPid(int pid){
        this.pid = pid;
    }
    
    public String getPname(){
        return pname;
    }
    
    public void setPname(String pname){
        this.pname = pname;
    }
}
