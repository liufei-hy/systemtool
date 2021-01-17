/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 *
 * @author HaYang
 */
public class DataInfo {
    private String m_name;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/testdata";
    static final String USER = "root";
    static final String PASS = "yhxH@4321"; 
    
    public DataInfo(){
        m_name = "Demo test names";
    }
    
    public String getName(int num){
        Connection conn = null;
        Statement  stmt = null;
        
        List<String>  appNames = new ArrayList<String>();
        List<Integer> starts   = new ArrayList<Integer>();
        List<Integer> posts    = new ArrayList<Integer>();
        List<Integer> runs     = new ArrayList<Integer>();
        List<Integer> stops    = new ArrayList<Integer>();
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            stmt = conn.createStatement();
            
            //String tmpSql = "id >=" + num + " and id <=" + (num + 50);
            
            String sql = "select name,start,post,run,stop from boottime2";
            
            ResultSet res = stmt.executeQuery(sql);
            
            while (res.next()){
                appNames.add(0, res.getString("name"));
                starts.add(0, res.getInt("start"));
                posts.add(0, res.getInt("post"));
                runs.add(0, res.getInt("run"));
                stops.add(0, res.getInt("stop"));                
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(ClassNotFoundException ss){
            ss.printStackTrace();
        }finally{
        
            try{
                if (stmt != null){
                    stmt.close();
                }
            }
            catch(SQLException se2){
                se2.printStackTrace();
            }
            
            try
            {
                if (conn != null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        
        Gson gs = new Gson();       
        
        return ("{\"appNames\":" + gs.toJson(appNames) + 
                ",\"appStarts\":" + gs.toJson(starts) + 
                ",\"appPosts\":" + gs.toJson(posts) + 
                ",\"appRuns\":" + gs.toJson(runs) +
                ",\"appStops\":" + gs.toJson(stops) +
                "}");
    }
    
    public void setName(String n){
        m_name = n;
    }
    
    public String getJsonTest(){        
        CpuMemData data = new CpuMemData(1, 23, 44, 22);
        
        Gson gs = new Gson();
        
        return gs.toJson(data);
    }
}
