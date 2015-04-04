//N00130270
package cinemaapp.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//ScreenTableGateway contains all of the sql code that communicates to the database
public class ScreenTableGateway {
    private static final String TABLE_NAME = "screen";
    private static final String COLUMN_ID = "screenID";
    private static final String COLUMN_SEAT_NUMBERS = "seatNumber";
    private static final String COLUMN_FIRE_EXITS = "fireExits";
    
    private Connection mConnection;
    
    public ScreenTableGateway(Connection connection){
        mConnection = connection;
    }
    
    public int insertScreen(int sn, int f) throws SQLException {
        Screen s = null;
        
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " ("+
                COLUMN_SEAT_NUMBERS + ", " +
                COLUMN_FIRE_EXITS + 
                ") VALUES (?, ?)"; //INSERT INTO screen (?, ?)
        
        stmt = mConnection.prepareStatement (query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, sn);
        stmt.setInt(2, f);
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1){
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
        return id;
    }
    
    public boolean deleteScreen(int id) throws SQLException{ //returns true or false if screen deleted
        String query; //SQL query to execute
        PreparedStatement stmt; 
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";//had a problem her because i had no space between WHERE and COLUMN_ID
        //DELETE FROM screen WHERE screenID = ?      
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1,id);
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    public List<Screen> getScreens() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Screen> screens;
        
        int id, seatNumbers, fireExits;
        
        Screen s;
        
        query = "SELECT * FROM " + TABLE_NAME; //SELECT ALL FROM screen
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        screens = new ArrayList<Screen>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            seatNumbers = rs.getInt(COLUMN_SEAT_NUMBERS);
            fireExits = rs.getInt(COLUMN_FIRE_EXITS);
            
            s = new Screen(id, seatNumbers, fireExits);
            screens.add(s);
        }
        return screens;
    }

    boolean updateScreen(Screen s) throws SQLException {
        String query; //SQL query to execute
        PreparedStatement stmt; 
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " + 
                COLUMN_SEAT_NUMBERS + " = ?, " +
                COLUMN_FIRE_EXITS + " = ? " +
                "WHERE " + COLUMN_ID + " = ?";//in each the questio mark is a placeholder which we declare later
               
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1,s.getSeatNumbers());
        stmt.setInt(2,s.getFireExits());
        stmt.setInt(3,s.getId());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
}
