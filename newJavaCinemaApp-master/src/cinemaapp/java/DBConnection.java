package cinemaapp.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//DBConnection is te class in which the connection to the phpMyAdmin database is implemented
public class DBConnection {

    private static Connection sConnection;

    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        
        host = "daneel";
        db = "n00130270";
        user = "N00130270";
        password = "N00130270";
        
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;//the url is made by concatinating all of the strings together 
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
        }

        return sConnection;
    }
}
