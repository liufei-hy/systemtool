/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author HaYang
 */
public class CmdInfo{
    private String projectName;
    private String number;
    private List<CmdItem> cmdItem;

    public CmdInfo(){
        cmdItem = new ArrayList<CmdItem>();
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String name){
        this.projectName = name;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public List<CmdItem> getCmdItem(){
        return cmdItem;
    }

    public void setCmdItem(List<CmdItem> items){
        for (CmdItem it : items){
            CmdItem item = new CmdItem(it.getType(), it.getKey(), it.getValue(), it.getId());
            cmdItem.add(item);
        }
    }
}
