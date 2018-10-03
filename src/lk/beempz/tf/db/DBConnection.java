/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author badhr
 */
public class DBConnection {
    private Connection connection;
    private static DBConnection dbconn;

    private DBConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        File dbprop = new File("resources/dbproperties.properties");
        FileReader filer = new FileReader(dbprop);
        Properties prop = new Properties();
        prop.load(filer);
        String host = prop.getProperty("host");
        String port = prop.getProperty("port");
        String dbname = prop.getProperty("dbname");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("pass");
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname, user, pass);
    }
    
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        if(dbconn == null)
            dbconn = new DBConnection();
        return dbconn;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    
}
