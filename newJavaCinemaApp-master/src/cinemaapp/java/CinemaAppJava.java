package cinemaapp.java;

import java.util.List;
import java.util.Scanner;

public class CinemaAppJava {

    public static void main(String[] args) throws DataAcessException {
        Scanner keyboard = new Scanner(System.in);

        Model model;

        Screen s;
        Movie m;
        Genre g;

        int opt = 16;
        do {//my loop that runs till 5/exit is chosen
            try {
                model = Model.getInstance();
                System.out.println("1. Create new Screen");
                System.out.println("2. Delete existing Screen");
                System.out.println("3. View all Screens");
                System.out.println("4. Edit existing Screen");
                System.out.println("5. View single Screen");
                System.out.println();
                System.out.println("6. Create new Movie");
                System.out.println("7. Delete existing Movie");
                System.out.println("8. View all Movies");
                System.out.println("9. Edit existing Movie");
                System.out.println("10. View single Movie");
                System.out.println();
                System.out.println("11. Create new Genre");
                System.out.println("12. Delete existing Genre");
                System.out.println("13. View all Genres");
                System.out.println("14. Edit existing Genre");
                System.out.println("15. View single Genre");
                System.out.println("16. Exit");
                System.out.println();

                opt = getInt(keyboard, "Enter option: ", 16);

                System.out.println("You chose option " + opt);

                switch (opt) {
                    case 1: {//create a new screen
                        System.out.println("Creating screen");
                        s = readScreen(keyboard);
                        model.addScreen(s);

                        break;
                    }
                    case 2: {//delete a screen
                        System.out.println("Deleting screen");
                        deleteScreen(keyboard, model);
                        break;
                    }
                    case 3: {//present a list of all my screens 
                        System.out.println("Viewing screen");
                        viewScreens(model);
                        break;
                    }
                    case 4: {//edit a screen
                        System.out.println("Edit screen");
                        editScreen(keyboard, model);
                        break;
                    }
                    case 5:{
                        System.out.println("Viewing single screen");
                        viewScreen(keyboard, model);
                        break;
                    }
                    case 6: {//create a new movie
                        System.out.println("Creating movie");
                        m = readMovie(keyboard);
                        model.addMovie(m);

                        break;
                    }
                    case 7: {//delete a movie
                        System.out.println("Deleting movie");
                        deleteMovie(keyboard, model);
                        break;
                    }
                    case 8: {//present a list of all my movies 
                        System.out.println("Viewing movie");
                        viewMovies(model);
                        break;
                    }
                    case 9: {//edit a movie
                        System.out.println("Edit movie");
                        editMovie(keyboard, model);
                        break;
                    }
                    case 10:{
                        System.out.println("Viewing single movie");
                        viewMovie(keyboard, model);
                        break;
                    }
                    case 11: {//create a new genre
                        System.out.println("Creating genre");
                        g = readGenre(keyboard);
                        model.addGenre(g);
                        break;
                    }
                    case 12: {//delete a genre
                        System.out.println("Deleting genre");
                        deleteGenre(keyboard, model);
                        break;
                    }
                    case 13: {//present a list of all my genres 
                        System.out.println("Viewing genre");
                        viewGenres(model);
                        break;
                    }
                    case 14: {//edit a genre
                        System.out.println("Edit genre");
                        editGenre(keyboard, model);
                        break;
                    }
                    case 15: {
                        System.out.println("Viewing single genre");
                        viewGenre(keyboard, model);
                        break;
                    }
                }
            }
            catch(DataAcessException e){
                System.out.println("");
                System.out.println(e.getMessage());
                System.out.println("");
            }
        }
        
        while (opt != 16);//exit
        System.out.println("Goodbye");
    }

    //Screens
    private static Screen readScreen(Scanner keyb) {
        int seatNumbers, fireExits;//not giving the option to change id 

        seatNumbers = getInt(keyb, "Enter number of seats:", 0);
        fireExits = getInt(keyb, "Enter number of Fire Exits:", 0);

        Screen s
                = new Screen(seatNumbers, fireExits);//auto increments id

        return s;
    }

    private static void deleteScreen(Scanner keyboard, Model model) throws DataAcessException {
        int id = getInt(keyboard, "Enter the ScreenID of the screen to delete:", 0);

        Screen s;
        s = model.findScreenByID(id);//calls my findScreenByID method
        if (s != null) {//returns s, if its null then no id matching was found
            if (model.removeScreen(s)) {
                System.out.println("Screen deleted");
            } else {
                System.out.println("Screen not deleted");
            }
        } else {//no matching id was found
            System.out.println("Screen not found");
        }
    }

    private static void viewScreens(Model model) {
        List<Screen> screens = model.getScreens();
        System.out.printf("%6s %9s %9s\n", "Number", "Num Seats", "Num Exits");//columns sizes help present the data in a neater way 
        for (Screen sc : screens) {
            System.out.printf("%6d %9d %9d\n", sc.getId(), sc.getSeatNumbers(), sc.getFireExits());
        }
    }

    private static void editScreen(Scanner keyboard, Model model) throws DataAcessException {
        int id = getInt(keyboard, "Enter the ScreenID of the screen to edit:", 0);

        Screen s;
        s = model.findScreenByID(id);//calls my findScreenByID method
        if (s != null) {//returns s, if its null then no id matching was found
            editScreenDetails(keyboard, s);
            if (model.updateScreen(s)) {
                System.out.println("Screen updated");
            } else {
                System.out.println("Screen not updated");
            }
        } else {//no matching id was found
            System.out.println("Screen not found");
        }
    }

    private static void editScreenDetails(Scanner keyboard, Screen s) {
        int seatNumbers, fireExits;

        seatNumbers = getInt(keyboard, "Enter seat number [" + s.getSeatNumbers() + "]:", 0);
        fireExits = getInt(keyboard, "Enter number of fire exits [" + s.getFireExits() + "]:", 0);

        if (seatNumbers != s.getSeatNumbers()) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            s.setSeatNumbers(seatNumbers);//sets the new name
        }
        if (fireExits != s.getFireExits()) {
            s.setFireExits(fireExits);
        }
    }
    private static void viewScreen(Scanner keyboard, Model model) throws DataAcessException {
        int id = getInt(keyboard, "Enter the ScreenID of the screen to view:", 0);

        Screen s;
        s = model.findScreenByID(id);//calls my findScreenByID method
        if (s != null) {//returns s, if its null then no id matching was found
            System.out.println("Screen ID:       " + s.getId());
            System.out.println("Seat Numbers:    " + s.getSeatNumbers());
            System.out.println("Fire Exits:      " + s.getFireExits());
        } else {//no matching id was found
            System.out.println("Screen not found");
        }
    }

    //Movies
    private static Movie readMovie(Scanner keyb) {
        int movieYear, runTime, genre;//not giving the option to change id 
        String title, classification, directorFName, directorLName;

        title = getString(keyb, "Enter the title:");
        movieYear = getInt(keyb, "Enter the year of release:", -1);
        runTime = getInt(keyb, "Enter the running time:", -1);
        classification = getString(keyb, "Enter the classification:");
        directorFName = getString(keyb, "Enter the director's first name:");
        directorLName = getString(keyb, "Enter the director's surname:");
        genre = getInt(keyb, "Enter the genre(enter -1 for no genre):", -1);

        Movie m
                = new Movie(title, movieYear, runTime, classification, directorFName, directorLName, genre);//auto increments id

        return m;
    }

    private static void deleteMovie(Scanner keyboard, Model model) throws DataAcessException {
        int movieID = getInt(keyboard, "Enter the MovieID of the movie to delete:", -1);

        Movie m;
        m = model.findMovieByID(movieID);//calls my findMovieByID method
        if (m != null) {//returns m, if its null then no id matching was found
            if (model.removeMovie(m)) {
                System.out.println("Movie deleted");
            } else {
                System.out.println("Movie not deleted");
            }
        } else {//no matching id was found
            System.out.println("Movie not found");
        }
    }

    private static void viewMovies(Model model) {
        List<Movie> movies = model.getMovies();
        if(movies.isEmpty()){
            System.out.println("There are no movies in the database.");
        }
        else{
                displayMovies(movies, model);
            }
                
    }
    private static void displayMovies(List<Movie> movies, Model model){
    
        System.out.printf("%8s %20s %10s %12s %14s %30s %20s\n",
                    "Movie ID",
                    "Title",
                    "Movie Year",
                    "Running Time",
                    "Classification",
                    "Director",
                    "Genre");//columns sizes help present the data in a neater way 
            for (Movie m : movies) {
                Genre g = model.findGenreByID(m.getGenre());
                System.out.printf("%8d %20s %10d %12d %14s %30s %20s\n",
                        m.getMovieID(),
                        m.getTitle(),
                        m.getMovieYear(),
                        m.getRunTime(),
                        m.getClassification(),
                        m.getDirectorFName() + " " + m.getDirectorLName(),
                        (g != null) ? g.getGenreName() : "");
            }
    }
    private static void editMovie(Scanner keyboard, Model model) throws DataAcessException {
        int movieID = getInt(keyboard, "Enter the MovieID of the movie to edit:", -1);

        Movie m;
        m = model.findMovieByID(movieID);//calls my findMovieByID method
        if (m != null) {//returns m, if its null then no id matching was found
            editMovieDetails(keyboard, m);
            if (model.updateMovie(m)) {
                System.out.println("Movie updated");
            } else {
                System.out.println("Movie not updated");
            }
        } else {//no matching id was found
            System.out.println("Movie not found");
        }
    }

    private static void editMovieDetails(Scanner keyboard, Movie m) {
        int movieYear, runTime, genre;//not giving the option to change id 
        String title, classification, directorFName, directorLName;

        title = getString(keyboard, "Enter the title [" + m.getTitle() + "]:");
        movieYear = getInt(keyboard, "Enter the year of release [" + m.getMovieYear() + "]:", -1);
        runTime = getInt(keyboard, "Enter the running time [" + m.getRunTime() + "]:", -1);
        classification = getString(keyboard, "Enter the classification [" + m.getClassification() + "]:");
        directorFName = getString(keyboard, "Enter the director's first name [" + m.getDirectorFName() + "]:");
        directorLName = getString(keyboard, "Enter the director's surname [" + m.getDirectorLName() + "]:");
        genre = getInt(keyboard, "Enter the genre [" + m.getGenre() + "]:", -1);

        if (title.length() != 0) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            m.setTitle(title);//sets the new name
        }
        if (movieYear != m.getMovieYear()) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            m.setMovieYear(movieYear);//sets the new name
        }
        if (runTime != m.getRunTime()) {
            m.setRunTime(runTime);
        }
        if (classification.length() != 0) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            m.setClassification(classification);//sets the new name
        }
        if (directorFName.length() != 0) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            m.setDirectorFName(directorFName);//sets the new name
        }
        if (directorLName.length() != 0) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            m.setDirectorLName(directorLName);//sets the new name
        }
        if (genre != m.getGenre()) {
            m.setGenre(genre);
        }
    }
    private static void viewMovie(Scanner keyboard, Model model) throws DataAcessException {
        int movieID = getInt(keyboard, "Enter the MovieID of the movie to view:", -1);
        Movie m;
        m = model.findMovieByID(movieID);//calls my findMovieByID method
        
        Genre g = model.findGenreByID(m.getGenre());
        
        if (m != null) {//returns m, if its null then no id matching was found
            System.out.println("Movie ID:           " + m.getMovieID());
            System.out.println("Title:              " + m.getTitle());
            System.out.println("Movie Year:         " + m.getMovieYear());
            System.out.println("Run Time:           " + m.getRunTime());
            System.out.println("Classification:     " + m.getClassification());
            System.out.println("Director:           " + m.getDirectorFName() + " " + m.getDirectorLName());
            System.out.println("Genre:              " + ((g != null) ? g.getGenreName() : ""));
        } else {//no matching id was found
            System.out.println("Movie not found");
        }
    }

    //Genres
    private static Genre readGenre(Scanner keyb) {

        String genreName, description;

        genreName = getString(keyb, "Enter the name:");
        description = getString(keyb, "Enter the description:");
        Genre g
                = new Genre(genreName, description);//SQL table will auto increments id

        return g;
    }

    private static void deleteGenre(Scanner keyboard, Model model) throws DataAcessException {
        int genreID = getInt(keyboard, "Enter the GenreID of the genre to delete:", -1);

        Genre g;
        g = model.findGenreByID(genreID);//calls my findGenreByID method
        if (g != null) {//returns m, if its null then no id matching was found
            if (model.removeGenre(g)) {
                System.out.println("Genre deleted");
            } else {
                System.out.println("Genre not deleted");
            }
        } else {//no matching id was found
            System.out.println("Genre not found");
        }
    }

    private static void viewGenres(Model model) {
        List<Genre> genres = model.getGenres();
        System.out.printf("%8s %20s %30s \n", "Genre ID", "Genre Name", "Description");//columns sizes help present the data in a neater way 
        for (Genre m : genres) {
            System.out.printf("%8d %20s %30s \n", m.getGenreID(), m.getGenreName(), m.getDescription());
        }
    }

    private static void editGenre(Scanner keyboard, Model model) throws DataAcessException {
        int genreID = getInt(keyboard, "Enter the GenreID of the genre to edit:", -1);

        Genre g;
        g = model.findGenreByID(genreID);//calls my findGenreByID method
        if (g != null) {//returns m, if its null then no id matching was found
            editGenreDetails(keyboard, g);
            if (model.updateGenre(g)) {
                System.out.println("Genre updated");
            } else {
                System.out.println("Genre not updated");
            }
        } else {//no matching id was found
            System.out.println("Genre not found");
        }
    }

    private static void editGenreDetails(Scanner keyboard, Genre g) {

        String genreName, description;

        genreName = getString(keyboard, "Enter the name [" + g.getGenreName() + "]:");
        description = getString(keyboard, "Enter the description [" + g.getDescription() + "]:");

        if (genreName.length() != 0) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            g.setGenreName(genreName);//sets the new name
        }
        if (description.length() != 0) {// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
            g.setDescription(description);//sets the new name
        }
    }
    private static void viewGenre(Scanner keyboard, Model model) throws DataAcessException {
        int genreID = getInt(keyboard, "Enter the GenreID of the genre to view:", -1);

        Genre g;
        g = model.findGenreByID(genreID);//calls my findGenreByID method

        if (g != null) {//returns m, if its null then no id matching was found
            System.out.println("Genre ID:               " + g.getGenreID());
            System.out.println("Name:                   " + g.getGenreName());
            System.out.println("Description:            " + g.getDescription());
            
            List<Movie> movieList = model.getMoviesByGenre(g.getGenreID());
            
            if(movieList.isEmpty()){
                System.out.println("There are no movies with this genre.");
            }
            else{
                System.out.println("There movies have this genre:");
                displayMovies(movieList, model);
            }
        } else {//no matching id was found
            System.out.println("Genre not found");
        }
    }

    private static String getString(Scanner keyboard, String prompt) {//my method that read the users keyboard input
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private static int getInt(Scanner keyboard, String prompt, int defaultValue) {
        int opt = 0;
        boolean finished = false;
        do {
            try {
                System.out.print(prompt);
                String line = keyboard.nextLine();
                if (line.length() > 0) {
                    opt = Integer.parseInt(line);
                }
                finished = true;
            } catch (NumberFormatException e) {
                System.out.println("Exception: " + e.getMessage());
            }

        } while (!finished);

        return opt;
    }
}
