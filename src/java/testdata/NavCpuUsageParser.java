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
public class NavCpuUsageParser {
    public NavCpuUsageParser(){
        
    }
    
    public String getDataMsg(DataKeyInfo key){
        Statement  stmt;
        Connection conn = null;
        String dbResult = ""
                + "OK";
        List<String> times = new ArrayList<>();
        List<Integer> cpu0  = new ArrayList<>();
        List<Integer> cpu1  = new ArrayList<>();
        List<Integer> cpuStats = new ArrayList<>();
        
        int cpu0Min = 0;
        int cpu0Max = 0;
        int cpu0Avg = 0;
        int cpu0Sum = 0;
        
        int cpu1Min = 0;
        int cpu1Max = 0;
        int cpu1Avg = 0;
        int cpu1Sum = 0;
        
        int dataCount = 0;
        
        try{
            conn = DBUtil.getConnection();
            
            stmt = conn.createStatement();
            
            String sql = "select time,cpu0,cpu1 from" + key.getCommSqlCondition();
            
            
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()){
                times.add(result.getString("time"));
                int tmpCpu0 = result.getInt("cpu0");
                int tmpCpu1 = result.getInt("cpu1");
                
                cpu0.add(tmpCpu0);
                cpu1.add(tmpCpu1);
                
                if (tmpCpu0 > cpu0Max)
                {
                    cpu0Max = tmpCpu0;
                }
                
                if (tmpCpu0 < cpu0Min)
                {
                    cpu0Min = tmpCpu0;
                }
                
                cpu0Sum += tmpCpu0;
                
                if (tmpCpu1 > cpu1Max)
                {
                    cpu1Max = tmpCpu1;
                }
                
                if (tmpCpu1 < cpu1Min)
                {
                    cpu1Min = tmpCpu1;
                }
                
                cpu1Sum += tmpCpu1;
                
                dataCount ++;
            }
            
        }catch(SQLException se){
            dbResult = se.toString();
        }finally{
            DBUtil.closeConnection(conn);
        }
        
        if (dataCount == 0)
        {
            dataCount = 1;
        }
        cpu0Avg = cpu0Sum / dataCount;
        cpu1Avg = cpu1Sum / dataCount;
        
        cpuStats.add(cpu0Min);
        cpuStats.add(cpu0Max);
        cpuStats.add(cpu0Avg);
        cpuStats.add(cpu1Min);
        cpuStats.add(cpu1Max);
        cpuStats.add(cpu1Avg);        
        
        Gson gs = new Gson();
        
        return ("{\"times\":" + gs.toJson(times) + 
                ",\"cpu0s\":" + gs.toJson(cpu0) + 
                ",\"cpu1s\":" + gs.toJson(cpu1) +
                ",\"cpuStats\":" + gs.toJson(cpuStats) +
                ",\"dbRes\":\"" + dbResult + "\"" +
                "}");
    }
    
    public String getCmpDataMsg(DataKeyInfo key, DataKeyInfo keyCmp){
        Statement  stmt;
        Connection conn = null;
        
        List<String>  timeCmp  = new ArrayList<>();
        List<Integer> cpu0Cmp  = new ArrayList<>();
        List<Integer> cpu1Cmp  = new ArrayList<>();
        List<Integer> cpuStats = new ArrayList<>();
        
        int cpu0Min = 0;
        int cpu0Max = 0;
        int cpu0Avg = 0;
        int cpu0Sum = 0;
        
        int cpu1Min = 0;
        int cpu1Max = 0;
        int cpu1Avg = 0;
        int cpu1Sum = 0;
        
        int dataCount = 0;
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sqlCmp = "select time,cpu0,cpu1 from" + keyCmp.getCommSqlCondition();
            
            //Compare data get
            ResultSet result = stmt.executeQuery(sqlCmp);
            
            while (result.next()){
                timeCmp.add(result.getString("time"));
                
                int tmpCpu0 = result.getInt("cpu0");
                int tmpCpu1 = result.getInt("cpu1");
                cpu0Cmp.add(tmpCpu0);
                cpu1Cmp.add(tmpCpu1);
                
                if (tmpCpu0 > cpu0Max)
                {
                    cpu0Max = tmpCpu0;
                }
                
                if (tmpCpu0 < cpu0Min)
                {
                    cpu0Min = tmpCpu0;
                }
                
                cpu0Sum += tmpCpu0;
                
                if (tmpCpu1 > cpu1Max)
                {
                    cpu1Max = tmpCpu1;
                }
                
                if (tmpCpu1 < cpu1Min)
                {
                    cpu1Min = tmpCpu1;
                }
                
                cpu1Sum += tmpCpu1;
                
                dataCount ++;
            }
            
        }catch(SQLException se){
            
        }finally{
            DBUtil.closeConnection(conn);
        }
        
        if (dataCount == 0)
        {
            dataCount = 1;
        }
        cpu0Avg = cpu0Sum / dataCount;
        cpu1Avg = cpu1Sum / dataCount;
        
        cpuStats.add(cpu0Min);
        cpuStats.add(cpu0Max);
        cpuStats.add(cpu0Avg);
        cpuStats.add(cpu1Min);
        cpuStats.add(cpu1Max);
        cpuStats.add(cpu1Avg);
        
        Gson gs = new Gson();       
        
        return ("{\"times\":" + gs.toJson(timeCmp) + 
                ",\"cpu0sCmp\":" + gs.toJson(cpu0Cmp) +
                ",\"cpu1sCmp\":" + gs.toJson(cpu1Cmp) +
                ",\"cpuStats\":" + gs.toJson(cpuStats) +
                "}");
    }
}
