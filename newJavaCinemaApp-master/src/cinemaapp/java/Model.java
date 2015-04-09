package cinemaapp.java;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//how i understand the model class is that when i create, update and delete inside this java application the model class contains everything that changes the java application
public class Model {
    
    private static Model instance = null;
    
    public static synchronized Model getInstance() {
        if (instance ==null) {
            instance = new Model();
        }
        return instance;
    }
    private List<Screen> screens;
    private ScreenTableGateway screenGateway;
    private List<Movie> movies;
    private MovieTableGateway movieGateway;
    private List<Genre> genres;
    private GenreTableGateway genreGateway;
    
    private Model(){
        
         try {
            Connection conn = DBConnection.getInstance();
            this.screenGateway = new ScreenTableGateway(conn);
            this.movieGateway = new MovieTableGateway(conn);
            this.genreGateway = new GenreTableGateway(conn);
            
            this.screens = screenGateway.getScreens();
            this.movies = movieGateway.getMovies();
            this.genres = genreGateway.getGenres();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Screens/
    public List<Screen> getScreens() {
        return new ArrayList<Screen>(this.screens);
    }
    public void addScreen(Screen s) {
        try {
            int id = this.screenGateway.insertScreen(s.getSeatNumbers(), s.getFireExits());
            s.setId(id);
            
            this.screens.add(s);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean removeScreen(Screen s){
        boolean removed = false;
        
        try{
            removed = this.screenGateway.deleteScreen(s.getId());
            if(removed){
                removed = this.screens.remove(s);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex );
        }
        return removed;
    }
    public Screen findScreenByID(int id) {
        Screen s = null;
        int i = 0;
        boolean found = false;
        while (i < this.screens.size() && !found) {
            s = this.screens.get(i);
            if (s.getId() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            s = null;
        }
        return s;
    }
    boolean updateScreen(Screen s) {
        boolean updated = false;
        
        try{
            updated = this.screenGateway.updateScreen(s);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex );
        }
        return updated;
    }
    
    //Movies
    public List<Movie> getMovies() {
        return new ArrayList<Movie>(this.movies);
    }
    public void addMovie(Movie m) {
        try {
            int movieID = this.movieGateway.insertMovie(m.getTitle(), m.getMovieYear(), m.getRunTime(), m.getClassification(), m.getDirectorFName(), m.getDirectorLName(), m.getGenre());
            m.setMovieID(movieID);
            
            this.movies.add(m);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean removeMovie(Movie m){
        boolean removed = false;
        
        try{
            removed = this.movieGateway.deleteMovie(m.getMovieID());
            if(removed){
                removed = this.movies.remove(m);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex );
        }
        return removed;
    }
    public Movie findMovieByID(int movieID) {
        Movie m = null;
        int i = 0;
        boolean found = false;
        while (i < this.movies.size() && !found) {
            m = this.movies.get(i);
            if (m.getMovieID() == movieID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            m = null;
        }
        return m;
    }
    boolean updateMovie(Movie m) {
        boolean updated = false;
        
        try{
            updated = this.movieGateway.updateMovie(m);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex );
        }
        return updated;
    }
    
    //Genres
    public List<Genre> getGenres() {
        return new ArrayList<Genre>(this.genres);
    }
    public void addGenre(Genre g) {
        try {
            int genreID = this.genreGateway.insertGenre(g.getGenreName(), g.getDescription());
            g.setGenreID(genreID);
            
            this.genres.add(g);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean removeGenre(Genre g){
        boolean removed = false;
        
        try{
            removed = this.genreGateway.deleteGenre(g.getGenreID());
            if(removed){
                removed = this.genres.remove(g);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex );
        }
        return removed;
    }
    public Genre findGenreByID(int genreID) {
        Genre g = null;
        int i = 0;
        boolean found = false;
        while (i < this.genres.size() && !found) {
            g = this.genres.get(i);
            if (g.getGenreID() == genreID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            g = null;
        }
        return g;
    }
    boolean updateGenre(Genre g) {
        boolean updated = false;
        
        try{
            updated = this.genreGateway.updateGenre(g);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex );
        }
        return updated;
    }
}
