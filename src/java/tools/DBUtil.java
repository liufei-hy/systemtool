/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.*;

/**
 *
 * @author yhx
 */
public class DBUtil {
    private static final String USER = "root";
    private static final String PASSWORD = "yhxH@4321";
    private static final String URL = "jdbc:mysql://localhost:3306/testdata";
    //private static final String URL = "jdbc:mysql://10.81.21.153:3306/testdata";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Connection database error.");
        }
        return conn;
    }
    
    public static void closeConnection(Connection conn){
        if (conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                System.out.println("Close database error.");
            }
        }
    }
}
