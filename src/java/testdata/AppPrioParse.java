/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.DBUtil;

/**
 *
 * @author HaYang
 */
public class AppPrioParse {
    public AppPrioParse(){
        
    }
    
    public String getDataMsg(DataKeyInfo key, String isAllData){
        Statement  stmt;
        Connection conn = null;
        boolean isAll = false;
        
        if (isAllData != null){
            isAll = true;
        }
        
        Gson gs = new Gson();
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            int SelfId = 1;
            
            if (!isAll){
                List<ProcessInfo> procInfo = new ArrayList<>();
                
                String sql = "select pid,pname from" + key.getCommSqlCondition() + " group by pid order by pname";
                
                //System.out.println(sql);
                //System.out.println(isAllData);
                
                ResultSet result = stmt.executeQuery(sql);
                while (result.next()){
                    ProcessInfo tmp = new ProcessInfo(SelfId, 
                            result.getInt("pid"), 
                            result.getString("pname"));

                    procInfo.add(tmp);

                    SelfId ++;
                }
                return (gs.toJson(procInfo));
            }else{
                List<ProcessInfoAll> procInfo = new ArrayList<>();
                
                String sql = "select pid,pname,tid,tname,prio,priotype from" 
                        + key.getCommSqlCondition() 
                        + " order by prio desc";
                
                ResultSet result = stmt.executeQuery(sql);
                while (result.next()){
                    ProcessInfoAll tmp = new ProcessInfoAll(SelfId, 
                            result.getInt("pid"), 
                            result.getString("pname"),
                            result.getInt("tid"),
                            result.getString("tname"),
                            result.getInt("prio"),
                            result.getString("priotype"));

                    procInfo.add(tmp);

                    SelfId ++;
                }
                return gs.toJson(procInfo);
            }
        }catch(SQLException se){
            
        }finally{
            DBUtil.closeConnection(conn);
        }
        return "{}";
    }
    
    public String getSubDataMsg(DataKeyInfo key, String Pid){
        Statement  stmt;
        Connection conn = null;
        
        List<ThreadInfo> procInfo = new ArrayList<>();
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql = "select tid,tname,prio,priotype from" + key.getCommSqlCondition() + " and pid=" + Pid;
            
            System.out.println(sql);
            System.out.println(Pid);
            
            ResultSet result = stmt.executeQuery(sql);
            
            int SelfId = 1;
            
            while (result.next()){
                ThreadInfo tmp = new ThreadInfo(SelfId, 
                        result.getInt("tid"), 
                        result.getString("tname"),
                        result.getInt("prio"),
                        result.getString("priotype"));
                
                procInfo.add(tmp);
                
                SelfId ++;
            }
        }catch(SQLException se){
            
        }finally{
            DBUtil.closeConnection(conn);
        }
        
        Gson gs = new Gson();       
        return (gs.toJson(procInfo));
    }
}
