/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import tools.DBUtil;

/**
 *
 * @author yhx
 */
public class DataInfoGet {
    public DataInfoGet(){
        
    }
    
    public List<ProjectName>getProjectName(DataKeyInfo key){
        List<ProjectName> nameList = new ArrayList<>();
        Statement stmt = null;
        Connection conn = null;
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select distinct project from " + key.getTableName();
            
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()){
                ProjectName pName = new ProjectName(result.getString("project"));
                nameList.add(pName);
            }
        }catch(SQLException se){
        }finally{
            try{
                if (null != stmt){
                    stmt.close();
                }
            }catch(SQLException se){   
            }                
            DBUtil.closeConnection(conn);
        }
        return nameList;   
    }
    
    public List<VariantName>getVariantName(DataKeyInfo key){
        List<VariantName> nameList = new ArrayList<>();
        Statement stmt = null;
        Connection conn = null;
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select distinct variant from " 
                    + key.getTableName() 
                    + " where project='" 
                    + key.getProjectName() + "'";
            
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()){
                VariantName pName = new VariantName(result.getString("variant"));
                nameList.add(pName);
            }
        }catch(SQLException se){
        }finally{
            try{
                if (null != stmt){
                    stmt.close();
                }
            }catch(SQLException se){   
            }                
            DBUtil.closeConnection(conn);
        }
        return nameList;   
    }
    
    public List<DataVersion> getDataVersion(DataKeyInfo key){
        List<DataVersion>  dv = new ArrayList<>();
        Statement  stmt = null;
        Connection conn = null;
        
        try{
            conn = DBUtil.getConnection();    
            stmt = conn.createStatement();
            String sql = "select distinct version from " + key.getTableName()
                    + " where project='" + key.getProjectName()
                    + "' and variant='"  + key.getVariantName()
                    + "' order by version DESC";
            
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()){
                DataVersion tmpVer = new DataVersion(result.getString("version"));
                dv.add(tmpVer);
            }
        }catch(SQLException se){
        }finally{
            try{
                if (null != stmt){
                    stmt.close();
                }
            }catch(SQLException se){
            }
            DBUtil.closeConnection(conn);
        }
        return dv;
    }
    
    public List<DataIndex> getDataIndex(DataKeyInfo key){
        List<DataIndex>  dv = new ArrayList<>();
        Statement  stmt = null;
        Connection conn = null;
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql = "select distinct testindex from " + key.getTableName()
                    + "  where project='" + key.getProjectName()
                    + "' and variant='"  + key.getVariantName()
                    + "' and version='" + key.getVersion() + "'";
            
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()){
                DataIndex tmpVer = new DataIndex(result.getInt("testindex"));
                dv.add(tmpVer);
            }
        }catch(SQLException se){
        }finally{
            try{
                if (null != stmt){
                    stmt.close();
                }
            }catch(SQLException se){
            }
            DBUtil.closeConnection(conn);
        }
        return dv;
    }
}
