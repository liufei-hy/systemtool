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
public class ProcessInfoAll {
    private int id;
    private int pid;
    private String pname;
    private int tid;
    private String tname;
    private int prio;
    private String priotype;
    
    public ProcessInfoAll(int id, int pid, String pname, int tid, String tname, int prio, String priotype){
        this.id       = id;
        this.pid      = pid;
        this.pname    = pname;
        this.tid      = tid;
        this.tname    = tname;
        this.prio     = prio;
        this.priotype = priotype;
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
    
    public int getTid(){
        return tid;
    }
    
    public void setTid(int tid){
        this.tid = tid;
    }
    
    public String getTname(){
        return tname;
    }
    
    public void setTname(String tname){
        this.tname = tname;
    }
    
    public int getPrio(){
        return prio;
    }
    
    public void setPrio(int prio){
        this.prio = prio;
    }
    
    public String getPriotype(){
        return priotype;
    }
    
    public void setPriotype(String priotype){
        this.priotype = priotype;
    }
}
