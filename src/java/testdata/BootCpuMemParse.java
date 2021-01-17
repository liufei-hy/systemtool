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
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;
import tools.DBUtil;

/**
 *
 * @author HaYang
 */
public class BootCpuMemParse {
    public BootCpuMemParse(){
        
    }
    
    private boolean calcAvg(ResultSet result, List<Integer> times, List<Integer> cpu0, List<Integer> cpu1, List<Integer> mem){
        List<Integer> cpu0Sum = new ArrayList<>();
        List<Integer> cpu1Sum = new ArrayList<>();
        List<Integer> memSum  = new ArrayList<>();
        int indexCount = 1;
        int lastTestIndex = 1;
        int sumIndex = 0;
        int dataLength = 0;

        try{
            while (result.next()){
                int testIndex = result.getInt("testindex");
                if (1 == testIndex){
                    times.add(result.getInt("time"));
                    cpu0Sum.add(result.getInt("cpu0"));
                    cpu1Sum.add(result.getInt("cpu1"));
                    memSum.add(result.getInt("mem"));
                }else{
                    if (lastTestIndex != testIndex){
                        if (lastTestIndex == 1){
                            dataLength = cpu0Sum.size();
                        }
                        lastTestIndex = testIndex;
                        indexCount ++;
                        sumIndex = 0;
                    }

                    if (sumIndex < dataLength){
                        cpu0Sum.set(sumIndex, cpu0Sum.get(sumIndex) + result.getInt("cpu0"));
                        cpu1Sum.set(sumIndex, cpu1Sum.get(sumIndex) + result.getInt("cpu1"));
                        memSum.set(sumIndex, memSum.get(sumIndex) + result.getInt("mem"));
                        sumIndex ++;
                    }
                }
            }

            //System.out.println("DataLength: " + dataLength + " indexCount: " + indexCount);
            for (int i = 0; i < dataLength; i ++){
                double avgCpu0 = cpu0Sum.get(i) / (indexCount * 1.0);
                double avgCpu1 = cpu1Sum.get(i) / (indexCount * 1.0);
                double avgMem  = memSum.get(i) / (indexCount * 1.0);

                cpu0.add((int)avgCpu0);
                cpu1.add((int)avgCpu1);
                mem.add((int)avgMem);
            }
        }catch(SQLException se){
            return false;
        }finally{
            
        }        
        return true;
    }
    
    public String getDataMsg(DataKeyInfo key){
        Statement  stmt = null;
        Connection conn = null;
        String dbResult = "OK";
        boolean needCallAvg;
        List<Integer> times = new ArrayList<>();
        List<Integer> cpu0  = new ArrayList<>();
        List<Integer> cpu1  = new ArrayList<>();
        List<Integer> mem   = new ArrayList<>();
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql;
            //System.out.println(key);
            if (key.getTestIndex().equals("*")){
                // need call avg value;
                sql = "select testindex,time,cpu0,cpu1,mem from" + key.getCommSqlConditionNoIndex();
                needCallAvg = true;
            }else{
                sql = "select time,cpu0,cpu1,mem from" + key.getCommSqlCondition() + " limit 120";
                needCallAvg = false;
            }
            
            ResultSet result = stmt.executeQuery(sql);
            
            if (needCallAvg){
                //System.out.println(sql);
                calcAvg(result, times, cpu0, cpu1, mem);
            }else{
                while (result.next()){
                    times.add(result.getInt("time"));
                    cpu0.add(result.getInt("cpu0"));
                    cpu1.add(result.getInt("cpu1"));
                    mem.add(result.getInt("mem"));
                }
            }
        }catch(SQLException se){
            dbResult = se.toString();
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
        
        return ("{\"times\":" + gs.toJson(times) + 
                ",\"cpu0s\":" + gs.toJson(cpu0) + 
                ",\"cpu1s\":" + gs.toJson(cpu1) + 
                ",\"mems\":" + gs.toJson(mem) +
                ",\"dbRes\":\"" + dbResult + "\"" +
                "}");
    }
    
    public String getCmpDataMsg(DataKeyInfo key, DataKeyInfo keyCmp){
        Statement  stmt = null;
        Connection conn = null;
        
        List<Integer> times = new ArrayList<>();
        List<Integer> cpu0  = new ArrayList<>();
        List<Integer> cpu1  = new ArrayList<>();
        List<Integer> mem   = new ArrayList<>();
        boolean needCallAvg = false;
        
        List<Integer> timesCmp = new ArrayList<>();
        List<Integer> cpu0Cmp  = new ArrayList<>();
        List<Integer> cpu1Cmp  = new ArrayList<>();
        List<Integer> memCmp   = new ArrayList<>();
        boolean needCallAvgCmp = false;
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql;
            
            if (key.getTestIndex().equals("*")){
                sql = "select testindex,time,cpu0,cpu1,mem from" + key.getCommSqlConditionNoIndex();
                needCallAvg = true;
            }else{
                sql = "select time,cpu0,cpu1,mem from" + key.getCommSqlCondition() + " limit 120";
                needCallAvg = false;
            }
            
            ResultSet result = stmt.executeQuery(sql);
            
            if (needCallAvg){
                //System.out.println(sql);
                calcAvg(result, times, cpu0, cpu1, mem);
            }else{
                while (result.next()){
                    times.add(result.getInt("time"));
                    cpu0.add(result.getInt("cpu0"));
                    cpu1.add(result.getInt("cpu1"));
                    mem.add(result.getInt("mem"));
                }
            }
            
            //Compare data get
            String sqlCmp;
            
            if (keyCmp.getTestIndex().equals("*")){
                sqlCmp = "select testindex,time,cpu0,cpu1,mem from" + keyCmp.getCommSqlConditionNoIndex();
                needCallAvgCmp = true;
            }else{
                sqlCmp = "select cpu0,cpu1,mem from" + keyCmp.getCommSqlCondition() + " limit 120";
                needCallAvgCmp = false;
            }
            
            result = stmt.executeQuery(sqlCmp);
            
            if (needCallAvgCmp){
                //System.out.println(sqlCmp);
                calcAvg(result, timesCmp, cpu0Cmp, cpu1Cmp, memCmp);
            }else{
                while (result.next()){
                    cpu0Cmp.add(result.getInt("cpu0"));
                    cpu1Cmp.add(result.getInt("cpu1"));
                    memCmp.add(result.getInt("mem"));
                }
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
        
        return ("{\"times\":" + gs.toJson(times) + 
                ",\"cpu0s\":" + gs.toJson(cpu0) + 
                ",\"cpu1s\":" + gs.toJson(cpu1) + 
                ",\"mems\":" + gs.toJson(mem) +
                ",\"cpu0sCmp\":" + gs.toJson(cpu0Cmp) +
                ",\"cpu1sCmp\":" + gs.toJson(cpu1Cmp) +
                ",\"memsCmp\":" + gs.toJson(memCmp) +
                "}");
    }
}
