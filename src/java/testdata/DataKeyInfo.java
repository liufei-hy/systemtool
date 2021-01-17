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
public class DataKeyInfo {
    private String tableName;
    private String projectName;
    private String variantName;
    private String version;
    private String testIndex;
    
    public DataKeyInfo(){
        
    }
    
    public DataKeyInfo(String tName){
        tableName   = tName;
    }
    
    public DataKeyInfo(String tName, String pName){
        tableName   = tName;
        projectName = pName;
    }
    
    public DataKeyInfo(String tName, String pName, String vName){
        tableName   = tName;
        projectName = pName;
        variantName = vName;
    }
    
    public DataKeyInfo(String tName, String pName, String vName, String ver){
        tableName   = tName;
        projectName = pName;
        variantName = vName;
        version     = ver;
    }
    
    public DataKeyInfo(String tName, String pName, String vName, String ver, String tIndex){
        this.tableName   = tName;
        this.projectName = pName;
        this.variantName = vName;
        this.version     = ver;
        this.testIndex   = tIndex;
    }
    
    public String getCommSqlCondition(){
        return  " " + tableName + " where"
                + "  project='" + projectName
                + "' and variant='" + variantName
                + "' and version='" + version 
                + "' and testindex=" + testIndex;
    }
    
    public String getCommSqlConditionNoIndex(){
        return  " " + tableName + " where"
                + "  project='" + projectName
                + "' and variant='" + variantName
                + "' and version='" + version + "'";
    }
    
    public String getTableName(){
        return tableName;
    }
    
    public void setTableName(String tName){
        tableName = tName;
    }
    
    public String getProjectName(){
        return projectName;
    }
    
    public void setProjectName(String pName){
        projectName = pName;
    }
    
    public String getVariantName(){
        return variantName;
    }
    
    public void setVariantName(String vName){
        variantName = vName;
    }
    
    public String getVersion(){
        return version;
    }
    
    public void setVersion(String ver){
        version = ver;
    }
    
    public String getTestIndex(){
        return testIndex;
    }
    
    public void setTestIndex(String index){
        testIndex = index;
    }
    
    @Override
    public String toString(){
        return "tableName: " + tableName + " projectName: " + projectName
                + " variantName: " + variantName + " version: " + version 
                + " testIndex: " + testIndex;
                
    }
}
