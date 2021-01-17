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
public class CmdItem{
    private String type;
    private String key;
    private String value;
    private String id;

    public CmdItem(){
    }

    public CmdItem(String type, String key, String value, String id){
        this.type  = type;
        this.key   = key;
        this.value = value;
        this.id    = id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
