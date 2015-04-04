package cinemaapp.java;

/**
 *
 * @author N00130270
 */
public class Movie {
    private int movieID;
    private String title;
    private int movieYear;
    private int runTime;
    private String classification;
    private String directorFName;
    private String directorLName;
    private String genre;
    
    public Movie(int mid, String t, int my, int rt,String c, String dfn, String dln, String g){
        this.movieID = mid;
        this.title = t;
        this.movieYear = my;
        this.runTime = rt;
        this.classification = c;
        this.directorFName = dfn;
        this.directorLName = dln;
        this.genre = g;
    }
    public Movie(String t, int my, int rt,String c, String dfn, String dln, String g){
        this(-1, t, my, rt, c, dfn, dln, g);//auto incremented so the user cannot assign an id for movie and they cannot edit it
    }
    
    //get methods
    
    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public int getRunTime() {
        return runTime;
    }

    public String getClassification() {
        return classification;
    }

    public String getDirectorFName() {
        return directorFName;
    }

    public String getDirectorLName() {
        return directorLName;
    }

    public String getGenre() {
        return genre;
    }

    
    //set methods
    
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setDirectorFName(String directorFName) {
        this.directorFName = directorFName;
    }

    public void setDirectorLName(String directorLName) {
        this.directorLName = directorLName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}