/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

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
public class NormalCommandParse {
    
    public NormalCommandParse(){
        
    }
    
    public List<CmdInfo> GetNormalCommands(){
        Statement  stmt;
        Connection conn = null;

        List<CmdInfo> cmdInfo = new ArrayList<>();
        
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            String sql = "select project,cmd,value from normalcommand";
            
            ResultSet result = stmt.executeQuery(sql);
            
            CmdInfo cinfo = null;
            List<CmdItem> citem = null;
            int idIndex  = 1;
            int prjIndex = 0;
            String prefix = "div";
            String lastProjectName = "";
            
            while (result.next()){
                String project = result.getString("project");
                String key     = result.getString("cmd");
                String value   = result.getString("value");
                
                if (project.length() <= 0){
                    continue;
                }
                
                if (project.equals(lastProjectName)){
                    String tmpId = prefix + String.valueOf(idIndex);
                    idIndex ++;
                    CmdItem tmpItem = new CmdItem("Cmd", key, value, tmpId);
                    if (citem != null){
                        citem.add(tmpItem);
                    }
                }else{
                    lastProjectName = project;
                    
                    if (prjIndex > 0){
                        if (cinfo != null){
                            cinfo.setCmdItem(citem);
                            cinfo.setNumber(String.valueOf(prjIndex));
                            prjIndex ++;
                            cmdInfo.add(cinfo);
                        }
                    }else{
                        prjIndex = 1;
                    }
                    
                    cinfo = new CmdInfo();
                    citem = new ArrayList<>();
                    
                    cinfo.setProjectName(project);
                    
                    //Add one item to citem list
                    String tmpId = prefix + String.valueOf(idIndex);
                    idIndex ++;
                    CmdItem tmpItem = new CmdItem("Cmd", key, value, tmpId);
                    citem.add(tmpItem);
                }               
            }
            
            if (cinfo != null){
                if ((citem != null) && (citem.size() > 0)){
                    cinfo.setCmdItem(citem);
                    cinfo.setNumber(String.valueOf(prjIndex));
                    cmdInfo.add(cinfo);
                }
            }
        }catch(SQLException se){
            
        }finally{
            DBUtil.closeConnection(conn);
        }
        
        return cmdInfo;
    }
}
