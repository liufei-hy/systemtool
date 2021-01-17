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
public final class DataIndexHeader {
    private String projectName;
    private String variantName;
    private String version;
    private String dataIndex;
    
    private String projectNameCmp;
    private String variantNameCmp;
    private String versionCmp;
    private String dataIndexCmp;
    
    private boolean isVaild;
    
    public DataIndexHeader(){
        isVaild = false;
    }
    
    public DataIndexHeader(String pName, String vName, String ver, String index){
        projectName    = pName;
        variantName    = vName;
        version        = ver;
        dataIndex      = index;
        
        isVaild = checkVaildOne();
    }
    
    public DataIndexHeader(String pName, String vName, String ver, String index,
                           String pNameCmp, String vNameCmp, String verCmp, String indexCmp){
        projectName    = pName;
        variantName    = vName;
        version        = ver;
        dataIndex      = index;
        
        projectNameCmp = pNameCmp;
        variantNameCmp = vNameCmp;
        versionCmp     = verCmp;
        dataIndexCmp   = indexCmp;
        
        isVaild = checkVaildTwo();
    }
    
    public boolean checkVaildOne(){
        return ((null != projectName)
                && (null != variantName)
                && (null != version)
                && (null != dataIndex));
    }
    
    public boolean checkVaildTwo(){
        if ((null != versionCmp)
                && (null != dataIndexCmp) 
                && (null != projectNameCmp)
                && (null != variantNameCmp)){
            return checkVaildOne();
        }else{
            return false;
        }       
    }
    
    public boolean isVaildParam(){
        return isVaild;
    }
    
    public boolean saveToDataKeyInfo(DataKeyInfo info){
        if (isVaild){
            info.setProjectName(projectName);
            info.setVariantName(variantName);
            info.setVersion(version);
            info.setTestIndex(dataIndex);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean saveToDataKeyInfoCmp(DataKeyInfo info, DataKeyInfo infoCmp){
        if (isVaild){
            info.setProjectName(projectName);
            info.setVariantName(variantName);
            info.setVersion(version);
            info.setTestIndex(dataIndex);
            
            infoCmp.setProjectName(projectNameCmp);
            infoCmp.setVariantName(variantNameCmp);
            infoCmp.setVersion(versionCmp);
            infoCmp.setTestIndex(dataIndexCmp);
            return true;
        }else{
            return false;
        }
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
    
    public String getProjectNameCmp(){
        return projectNameCmp;
    }
    
    public void setProjectNameCmp(String pName){
        projectNameCmp = pName;
    }
    
    public String getVariantNameCmp(){
        return variantNameCmp;
    }
    
    public void setVariantNameCmp(String vName){
        variantNameCmp = vName;
    }
    
    public String getVersionCmp(){
        return versionCmp;
    }
    
    public void setVersionCmp(String ver){
        versionCmp = ver;
    }
    
    @Override
    public String toString(){
        return  "projectName: " + projectName +
                " veriantName: " + variantName +
                " version: " + version +
                " dataIndex: " + dataIndex +
                " projectNameCmp: " + projectNameCmp +
                " veriantNameCmp: " + variantNameCmp +
                " versionCmp: " + versionCmp +
                " dataIndexCmp: " + dataIndexCmp +
                " isVaild :" + isVaild;
    }
}
