/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tools.DBUtil;

/**
 *
 * @author HaYang
 */
public class EmmcUsageParse {
    public EmmcUsageParse(){
        
    }
    
    public String getDataMsg(DataKeyInfo key){
        Statement  stmt = null;
        Connection conn = null;
        
        List<EmmcUsage> emmcUse = new ArrayList<>();
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql = "select etfsfree,etfsuse,mmc0free,mmc0use,mmc1free,mmc1use,mmc2free,mmc2use from" 
                    + key.getCommSqlCondition();
            
            //System.out.println(sql);
            //System.out.println(key);
            
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()){
                EmmcUsage us = new EmmcUsage("etfs", result.getInt("etfsfree"), result.getInt("etfsuse"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc0", result.getInt("mmc0free"), result.getInt("mmc0use"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc1", result.getInt("mmc1free"), result.getInt("mmc1use"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc2", result.getInt("mmc2free"), result.getInt("mmc2use"));
                emmcUse.add(us);
                
                break;
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
        
        Gson gs = new Gson();       
        
        return (gs.toJson(emmcUse));
    }
    
    public String getCmpDataMsg(DataKeyInfo key, DataKeyInfo cmpKey){
        Statement  stmt = null;
        Connection conn = null;
        
        List<EmmcUsage> emmcUse = new ArrayList<>();
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql = "select etfsfree,etfsuse,mmc0free,mmc0use,mmc1free,mmc1use,mmc2free,mmc2use from"
                    + key.getCommSqlCondition();
            
            String sqlCmp = "select etfsfree,etfsuse,mmc0free,mmc0use,mmc1free,mmc1use,mmc2free,mmc2use from"
                    + cmpKey.getCommSqlCondition();
            
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()){
                EmmcUsage us = new EmmcUsage("etfs", result.getInt("etfsfree"), result.getInt("etfsuse"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc0", result.getInt("mmc0free"), result.getInt("mmc0use"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc1", result.getInt("mmc1free"), result.getInt("mmc1use"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc2", result.getInt("mmc2free"), result.getInt("mmc2use"));
                emmcUse.add(us);
                
                break;
            }
            
            //Compare data get
            result = stmt.executeQuery(sqlCmp);
            
            while (result.next()){
                EmmcUsage us = new EmmcUsage("etfs", result.getInt("etfsfree"), result.getInt("etfsuse"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc0", result.getInt("mmc0free"), result.getInt("mmc0use"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc1", result.getInt("mmc1free"), result.getInt("mmc1use"));
                emmcUse.add(us);
                
                us = new EmmcUsage("mmc2", result.getInt("mmc2free"), result.getInt("mmc2use"));
                emmcUse.add(us);
                
                break;
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
        
        Gson gs = new Gson();       
        
        return (gs.toJson(emmcUse));
        
    }
}
