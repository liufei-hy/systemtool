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
 * @author yhx
 */
public class BootTimerParse {
    public BootTimerParse(){
        
    }
    
    public String parseAppInfos(String info){
        String tmp = "";
        
        String []res = info.split("\n");
        
        for (String re : res) {
            if (re.contains("Supplied Interface:")) {
                
                int posStart = re.indexOf("]");
                int posEnd = re.indexOf(";");
                if ((-1 != posStart) && (-1 != posEnd)) {
                    if (posStart + 1 < posEnd){
                        tmp += "Supplied:" + re.substring(posStart + 1, posEnd) + "<br/>";
                    }
                }
            } else if (re.contains("Required Interface:")) {
                
                int posStart = re.indexOf("]");
                int posEnd = re.indexOf(";");
                if ((-1 != posStart) && (-1 != posEnd)) {
                    if (posStart + 1 < posEnd){
                        tmp += "Required:" + re.substring(posStart + 1, posEnd) + "<br/>";
                    }
                }
            }
        }        
        return tmp;
    }
    
    public String getDataMsg(DataKeyInfo key){
        Statement  stmt = null;
        Connection conn = null;
        
        List<String>  appNames = new ArrayList<>();
        List<Integer> starts   = new ArrayList<>();
        List<Integer> posts    = new ArrayList<>();
        List<Integer> runs     = new ArrayList<>();
        List<Integer> stops    = new ArrayList<>();
        List<String>  infos    = new ArrayList<>();
        
        try{
            conn = DBUtil.getConnection();
            
            stmt = conn.createStatement();
            
            
            String sql = "select name,start,post,run,stop,info from" + key.getCommSqlCondition();
            
            //String sql = "select * from testdata.boottime WHERE version='Version1.0' and testindex=1";
            
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()){
                appNames.add(0, result.getString("name"));
                starts.add(0, result.getInt("start"));
                posts.add(0, result.getInt("post"));
                runs.add(0, result.getInt("run"));
                stops.add(0, result.getInt("stop"));
                String infoResult = parseAppInfos(result.getString("info"));
                infos.add(0, infoResult);
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
        
        return ("{\"appNames\":" + gs.toJson(appNames) + 
                ",\"appStarts\":" + gs.toJson(starts) + 
                ",\"appPosts\":" + gs.toJson(posts) + 
                ",\"appRuns\":" + gs.toJson(runs) +
                ",\"appStops\":" + gs.toJson(stops) +
                ",\"appInfos\":" + gs.toJson(infos) +
                "}");
    }
    
    public String getCmpDataMsg(DataKeyInfo key, DataKeyInfo cmpKey){
        Statement  stmt = null;
        Connection conn = null;
        
        List<String>  appNames = new ArrayList<>();
        
        List<Integer> starts      = new ArrayList<>();
        List<Integer> posts       = new ArrayList<>();
        List<Integer> runs        = new ArrayList<>();
        List<Integer> stops       = new ArrayList<>();
        List<String>  infos       = new ArrayList<>();
        
        List<Integer> startsCmp   = new ArrayList<>();
        List<Integer> postsCmp    = new ArrayList<>();
        List<Integer> runsCmp     = new ArrayList<>();
        List<Integer> stopsCmp    = new ArrayList<>();
        List<String>  infosCmp    = new ArrayList<>();
        
        List<AppInfo> appInfoFirst  = new ArrayList<>();
        List<AppInfo> appInfoSecond = new ArrayList<>();
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql = "select name,start,post,run,stop,info from" + key.getCommSqlCondition();
            String sqlCmp = "select name,start,post,run,stop,info from" + cmpKey.getCommSqlCondition();
            
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()){
                AppInfo tmpAppInfo = new AppInfo(result.getString("name"),
                                                 result.getInt("start"),
                                                 result.getInt("post"),
                                                 result.getInt("run"),
                                                 result.getInt("stop"),
                                                 result.getString("info"));
                appInfoFirst.add(0, tmpAppInfo);
            }
            
            //Compare data get
            result = stmt.executeQuery(sqlCmp);
            
            while (result.next()){
                AppInfo tmpAppInfo = new AppInfo(result.getString("name"),
                                                 result.getInt("start"),
                                                 result.getInt("post"),
                                                 result.getInt("run"),
                                                 result.getInt("stop"),
                                                 result.getString("info"));
                appInfoSecond.add(0, tmpAppInfo);
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
        
        for (int i = 0; i < appInfoFirst.size(); i ++){
            String tmpAppName = appInfoFirst.get(i).getName();
            
            appNames.add(tmpAppName);
            starts.add(appInfoFirst.get(i).getStart());
            posts.add(appInfoFirst.get(i).getPost());
            runs.add(appInfoFirst.get(i).getRun());
            stops.add(appInfoFirst.get(i).getStop());
            String tmpInfos = parseAppInfos(appInfoFirst.get(i).getInfo());
            infos.add(tmpInfos);
            
            boolean isFind = false;
            
            for (int j = 0; j < appInfoSecond.size(); j ++){
                String tmpAppNameCmp = appInfoSecond.get(j).getName();
                
                if (tmpAppName.equals(tmpAppNameCmp))
                {
                    startsCmp.add(appInfoSecond.get(j).getStart());
                    postsCmp.add(appInfoSecond.get(j).getPost());
                    runsCmp.add(appInfoSecond.get(j).getRun());
                    stopsCmp.add(appInfoSecond.get(j).getStop());
                    String tmpInfosCmp = parseAppInfos(appInfoSecond.get(j).getInfo());
                    infosCmp.add(tmpInfosCmp);
                    
                    isFind = true;
                    break;
                }
            }
            
            if (!isFind)
            {
                startsCmp.add(0);
                postsCmp.add(0);
                runsCmp.add(0);
                stopsCmp.add(0);
                infosCmp.add("");
            }
        }
        
        Gson gs = new Gson();       
        
        return ("{\"appNames\":" + gs.toJson(appNames) + 
                ",\"appStarts\":" + gs.toJson(starts) + 
                ",\"appPosts\":" + gs.toJson(posts) + 
                ",\"appRuns\":" + gs.toJson(runs) +
                ",\"appStops\":" + gs.toJson(stops) +
                ",\"appInfos\":" + gs.toJson(infos) +
                ",\"appStartsCmp\":" + gs.toJson(startsCmp) +
                ",\"appPostsCmp\":" + gs.toJson(postsCmp) +
                ",\"appRunsCmp\":" + gs.toJson(runsCmp) +
                ",\"appStopsCmp\":" + gs.toJson(stopsCmp) +
                ",\"appInfosCmp\":" + gs.toJson(infosCmp) +
                "}");
        
    }
}
