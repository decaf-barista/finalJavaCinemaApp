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
public class MovieTableGateway {
    private static final String TABLE_NAME = "movie";
    private static final String COLUMN_ID = "movieID";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_MOVIE_YEAR = "movieYear";
    private static final String COLUMN_RUN_TIME = "runTime";
    private static final String COLUMN_CLASSIFICATION = "classification";
    private static final String COLUMN_DIRECTOR_F_NAME = "directorFName";
    private static final String COLUMN_DIRECTOR_L_NAME = "directorLName";
    private static final String COLUMN_GENRE = "genre";
    
    private Connection mConnection;
    
    public MovieTableGateway(Connection connection){
        mConnection = connection;
    }
    
    public int insertMovie(String t, int my, int rt,String c, String dfn, String dln, int g) throws SQLException {
        Movie m = null;
        
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int movieID = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " ("+
                COLUMN_TITLE + ", " +
                COLUMN_MOVIE_YEAR + ", " + 
                COLUMN_RUN_TIME + ", " + 
                COLUMN_CLASSIFICATION + ", " + 
                COLUMN_DIRECTOR_F_NAME + ", " + 
                COLUMN_DIRECTOR_L_NAME + ", " + 
                COLUMN_GENRE + 
                ") VALUES (?, ?, ?, ?, ?, ?, ?)"; //INSERT INTO screen (?, ?, ?, ?, ?, ?, ?, ?)
        
        stmt = mConnection.prepareStatement (query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, t);
        stmt.setInt(2, my);
        stmt.setInt(3, rt);
        stmt.setString(4, c);
        stmt.setString(5, dfn);
        stmt.setString(6, dln);
        if (g == -1) {
            stmt.setNull(7, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(7, g);
        }
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1){
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            movieID = keys.getInt(1);
        }
        return movieID;
    }
    
    public boolean deleteMovie(int id) throws SQLException{ //returns true or false if screen deleted
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
    
    public List<Movie> getMovies() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Movie> movies;
        
        int id, movieYear, runTime, genre;
        String title, classification, directorFName, directorLName;
        
        Movie m;
        
        query = "SELECT * FROM " + TABLE_NAME; //SELECT ALL FROM movie
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        movies = new ArrayList<Movie>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            title = rs.getString(COLUMN_TITLE);
            movieYear = rs.getInt(COLUMN_MOVIE_YEAR);
            runTime = rs.getInt(COLUMN_RUN_TIME);
            classification = rs.getString(COLUMN_CLASSIFICATION);
            directorFName = rs.getString(COLUMN_DIRECTOR_F_NAME);
            directorLName = rs.getString(COLUMN_DIRECTOR_L_NAME);
            genre = rs.getInt(COLUMN_GENRE);
            if (rs.wasNull()) {
                genre = -1;
            }
            
            m = new Movie(id, title, movieYear, runTime, classification, directorFName, directorLName, genre);
            movies.add(m);
        }
        return movies;
    }

    boolean updateMovie(Movie m) throws SQLException {
        String query; //SQL query to execute
        PreparedStatement stmt; 
        int numRowsAffected;
        int g;
        
        query = "UPDATE " + TABLE_NAME + " SET " + 
                COLUMN_TITLE + " = ?, " +
                COLUMN_MOVIE_YEAR + " = ?, " +
                COLUMN_RUN_TIME + " = ?, " +
                COLUMN_CLASSIFICATION + " = ?, " +
                COLUMN_DIRECTOR_F_NAME + " = ?, " +
                COLUMN_DIRECTOR_L_NAME + " = ?, " +
                COLUMN_GENRE + " = ? " +
                "WHERE " + COLUMN_ID + " = ?";//in each the questio mark is a placeholder which we declare later
               
        stmt = mConnection.prepareStatement(query);
        
        stmt.setString(1, m.getTitle());
        stmt.setInt(2, m.getMovieYear());
        stmt.setInt(3, m.getRunTime());
        stmt.setString(4, m.getClassification());
        stmt.setString(5, m.getDirectorFName());
        stmt.setString(6, m.getDirectorLName());
        stmt.setInt(7, m.getGenre());
        g = m.getGenre();
        if (g == -1) {
            stmt.setNull(7, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(7, g);
        }
        stmt.setInt(8,m.getMovieID());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
}
