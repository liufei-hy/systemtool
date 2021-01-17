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
public class AppInfo {
    private String name;
    private int start;
    private int post;
    private int run;
    private int stop;
    private String info;
    
    public AppInfo(String appName, int appStart, int appPost, int appRun, int appStop, String appInfo){
        name  = appName;
        start = appStart;
        post  = appPost;
        run   = appRun;
        stop  = appStop;
        info  = appInfo;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String appName){
        name = appName;
    }
    
    public int getStart(){
        return start;
    }
    
    public void setStart(int appStart){
        start = appStart;
    }
    
    public int getPost(){
        return post;
    }
    
    public void setPost(int appPost){
        post = appPost;
    }
    
    public int getRun(){
        return run;
    }
    
    public void setRun(int appRun){
        run = appRun;
    }
    
    public int getStop(){
        return stop;
    }
    
    public void setStop(int appStop){
        stop = appStop;
    }
    
    public String getInfo(){
        return info;
    }
    
    public void setInfo(String appInfo){
        info = appInfo;
    }
}
