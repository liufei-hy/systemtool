/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author HaYang
 */
public class CpuMemData {
    private int time;
    private int cpu0;
    private int cpu1;
    private int mem;
    
    public CpuMemData(int tm, int c0, int c1, int m){
        time = tm;
        cpu0 = c0;
        cpu1 = c1;
        mem  = m;
    }
    
    public CpuMemData(){
        super();
    }
    
    public int getTime(){
        return time;
    }
    
    public void setTime(int tm){
        time = tm;
    }
    
    public int getCpu0(){
        return cpu0;
    }
    
    public void setCpu0(int c0){
        cpu0 = c0;
    }
    
    public int getCpu1(){
        return cpu1;
    }
    
    public void setCpu1(int c1){
        cpu1 = c1;
    }
    
    public int getMem(){
        return mem;
    }
    
    public void setMem(int m){
        mem = m;
    }
}
