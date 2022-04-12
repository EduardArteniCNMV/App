package com.group.app;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnector {
    private static final String url = "jdbc:postgresql://localhost/postgres";
    private static final String user = "postgres";
    private static final String password = "123";
    public static Connection connection = null;

    public static void connect(){
        connection = null;
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(connection!=null){
            System.out.println("Connected");
        }else{
            System.out.println("Not connected");
        }
    }
}
