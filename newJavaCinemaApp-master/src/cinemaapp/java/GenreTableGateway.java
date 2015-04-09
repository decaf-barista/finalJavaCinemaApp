//N00130270
package cinemaapp.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//GenreTableGateway contains all of the sql code that communicates to the database
public class GenreTableGateway {
    private static final String TABLE_NAME = "genre";
    private static final String COLUMN_ID = "genreID";
    private static final String COLUMN_GENRE_NAME = "genreName";
    private static final String COLUMN_DESCRIPTION = "description";
    
    private Connection mConnection;
    
    public GenreTableGateway(Connection connection){
        mConnection = connection;
    }
    
    public int insertGenre(String gn, String d) throws SQLException {
        Genre g = null;
        
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int genreID = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " ("+
                COLUMN_GENRE_NAME + ", " +
                COLUMN_DESCRIPTION + 
                ") VALUES (?, ?)"; //INSERT INTO screen (?, ?, ?, ?, ?, ?, ?, ?)
        
        stmt = mConnection.prepareStatement (query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, gn);
        stmt.setString(2, d);
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1){
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            genreID = keys.getInt(1);
        }
        return genreID;
    }
    
    public boolean deleteGenre(int id) throws SQLException{ //returns true or false if screen deleted
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
    
    public List<Genre> getGenres() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Genre> genres;
        
        int genreID;
        String genreName, description;
        
        Genre g;
        
        query = "SELECT * FROM " + TABLE_NAME; //SELECT ALL FROM genre
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        genres = new ArrayList<Genre>();
        while (rs.next()) {
            genreID = rs.getInt(COLUMN_ID);
            genreName = rs.getString(COLUMN_GENRE_NAME);
            description = rs.getString(COLUMN_DESCRIPTION);
            
            g = new Genre(genreID, genreName, description);
            genres.add(g);
        }
        return genres;
    }

    boolean updateGenre(Genre g) throws SQLException {
        String query; //SQL query to execute
        PreparedStatement stmt; 
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " + 
                COLUMN_GENRE_NAME + " = ?, " +
                COLUMN_DESCRIPTION + " = ? " +
                "WHERE " + COLUMN_ID + " = ?";//in each the questio mark is a placeholder which we declare later
               
        stmt = mConnection.prepareStatement(query);
        
        stmt.setString(1, g.getGenreName());
        stmt.setString(2, g.getDescription());
        stmt.setInt(3,g.getGenreID());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
}
