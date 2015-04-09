package cinemaapp.java;

public class Genre {
    //attributes
    private int genreID;
    private String genreName;
    private String description;
    //test
    public Genre (int id, String gn, String d){//paramatized constructor
        this.genreID = id;
        this.genreName = gn;
        this.description = d;
    }
    
    public Genre (String gn, String d){
        this(-1, gn, d);//my genreID is auto increment, the user cannot edit genreID or give it a value
    }
    //my get methods
    public int getGenreID() {
        return genreID;
    }
    public String getGenreName() {
        return genreName;
    }
    public String getDescription() {
        return description;
    }
    //my set methods
    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
