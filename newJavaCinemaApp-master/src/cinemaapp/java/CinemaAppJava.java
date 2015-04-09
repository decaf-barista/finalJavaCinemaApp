package cinemaapp.java;

import java.util.List;
import java.util.Scanner;

public class CinemaAppJava 
{

    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        
        Model model = Model.getInstance();
        
        Screen s;
        Movie m;
        Genre g;
        
        int opt;
        do{//my loop that runs till 5/exit is chosen
            System.out.println("1. Create new Screen");
            System.out.println("2. Delete existing Screen");
            System.out.println("3. View all Screens");
            System.out.println("4. Edit existing Screen");
            System.out.println();
            System.out.println("5. Create new Movie");
            System.out.println("6. Delete existing Movie");
            System.out.println("7. View all Movies");
            System.out.println("8. Edit existing Movie");
            System.out.println();
            System.out.println("9. Create new Genre");
            System.out.println("10. Delete existing Genre");
            System.out.println("11. View all Genres");
            System.out.println("12. Edit existing Genre");
            System.out.println("13. Exit");
            System.out.println();
            
            System.out.println("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);
            
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
                    deleteScreen(keyboard,model);
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
                case 5: {//create a new movie
                    System.out.println("Creating movie");
                    m = readMovie(keyboard);
                    model.addMovie(m);
                    
                    break;
                }
                case 6: {//delete a movie
                    System.out.println("Deleting movie");
                    deleteMovie(keyboard,model);
                    break;
                }
                case 7: {//present a list of all my movies 
                    System.out.println("Viewing movie");
                    viewMovies(model);
                    break;
                }
                case 8: {//edit a genre
                    System.out.println("Edit genre");
                    editMovie(keyboard, model);
                    break;
                }
                case 9: {//create a new genre
                    System.out.println("Creating genre");
                    g = readGenre(keyboard);
                    model.addGenre(g);
                    
                    break;
                }
                case 10: {//delete a genre
                    System.out.println("Deleting genre");
                    deleteGenre(keyboard,model);
                    break;
                }
                case 11: {//present a list of all my genres 
                    System.out.println("Viewing genre");
                    viewGenres(model);
                    break;
                }
                case 12: {//edit a genre
                    System.out.println("Edit genre");
                    editGenre(keyboard, model);
                    break;
                }
            }
        }
        while (opt !=13);//exit
        System.out.println("Goodbye");        
    }
    //Screens
    private static Screen readScreen(Scanner keyb) {
        int seatNumbers, fireExits;//not giving the option to change id 
        String line1, line2;
        
        line1= getString(keyb, "Enter number of seats:");
        seatNumbers= Integer.parseInt(line1);//variable now equals user input
        line2= getString(keyb, "Enter number of Fire Exits:");
        fireExits= Integer.parseInt(line2);//variable now equals user input
        
        Screen s =
                new Screen(seatNumbers, fireExits);//auto increments id
        
        return s;
    }
    private static void deleteScreen(Scanner keyboard, Model model) {
        System.out.print("Enter the ScreenID of the screen to delete:");
        int id = Integer.parseInt(keyboard.nextLine());//fills the id variable with the users input
                    
        Screen s; 
        s = model.findScreenByID(id);//calls my findScreenByID method
        if (s != null) {//returns s, if its null then no id matching was found
            if (model.removeScreen(s)) {
                System.out.println("Screen deleted");
            }
            else {
                System.out.println("Screen not deleted");
            }
        }
        else {//no matching id was found
            System.out.println("Screen not found");
        }
    }
    private static void viewScreens(Model model) {
        List<Screen> screens = model.getScreens();
        System.out.printf("%6s %9s %9s\n", "Number", "Num Seats", "Num Exits");//columns sizes help present the data in a neater way 
        for (Screen sc : screens){
            System.out.printf("%6d %9d %9d\n", sc.getId(), sc.getSeatNumbers(), sc.getFireExits());
        }
    }
    private static void editScreen(Scanner keyboard, Model model) {
        System.out.print("Enter the ScreenID of the screen to edit:");
        int id = Integer.parseInt(keyboard.nextLine());//fills the id variable with the users input
                    
        Screen s; 
        s = model.findScreenByID(id);//calls my findScreenByID method
        if (s != null) {//returns s, if its null then no id matching was found
            editScreenDetails(keyboard, s);
            if (model.updateScreen(s)) {
                System.out.println("Screen updated");
            }
            else {
                System.out.println("Screen not updated");
            }
        }
        else {//no matching id was found
            System.out.println("Screen not found");
        }
    }
    private static void editScreenDetails(Scanner keyboard, Screen s) {
       int seatNumbers, fireExits;
       String line1, line2;//my strings that will read  in a string and then will be "made into" an int
       
       line1 = getString(keyboard, "Enter seat number [" + s.getSeatNumbers() + "]:");
       line2 = getString(keyboard, "Enter number of fire exits [" + s.getFireExits() + "]:");
       
       if(line1.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           seatNumbers = Integer.parseInt(line1);//i declare my integer he rather than above so i don't mess up my code
           s.setSeatNumbers(seatNumbers);//sets the new name
       }
       if(line2.length() !=0){
           fireExits = Integer.parseInt(line2);//parses a string into an int
           s.setFireExits(fireExits);
       } 
    }
    
    //Movies
    private static Movie readMovie(Scanner keyb) {
        int movieYear, runTime, genre;//not giving the option to change id 
        String title, line1, line2, classification, directorFName, directorLName, line3;
        
        title= getString(keyb, "Enter the title:");
        line1= getString(keyb, "Enter the year of release:");
        movieYear= Integer.parseInt(line1);//variable now equals user input
        line2= getString(keyb, "Enter the running time:");
        runTime= Integer.parseInt(line2);//variable now equals user input
        classification= getString(keyb, "Enter the classification:");
        directorFName= getString(keyb, "Enter the director's first name:");
        directorLName= getString(keyb, "Enter the director's surname:");
        line3= getString(keyb, "Enter the genre:");
        genre= Integer.parseInt(line3);//variable now equals user input
        Movie m =
                new Movie(title, movieYear, runTime, classification, directorFName, directorLName, genre);//auto increments id
        
        return m;
    }
    private static void deleteMovie(Scanner keyboard, Model model) {
        System.out.print("Enter the MovieID of the movie to delete:");
        int movieID = Integer.parseInt(keyboard.nextLine());//fills the id variable with the users input
                    
        Movie m; 
        m = model.findMovieByID(movieID);//calls my findMovieByID method
        if (m != null) {//returns m, if its null then no id matching was found
            if (model.removeMovie(m)) {
                System.out.println("Movie deleted");
            }
            else {
                System.out.println("Movie not deleted");
            }
        }
        else {//no matching id was found
            System.out.println("Movie not found");
        }
    }
    private static void viewMovies(Model model) {
        List<Movie> movies = model.getMovies();
        System.out.printf("%8s %20s %10s %12s %14s %19s %18s %20s\n", 
                "Movie ID", 
                "Title", 
                "Movie Year", 
                "Running Time", 
                "Classification", 
                "Director First Name", 
                "Director Last Name", 
                "Genre");//columns sizes help present the data in a neater way 
        for (Movie m : movies){
            Genre g = model.findGenreByID(m.getGenre());
            System.out.printf("%8d %20s %10d %12d %14s %19s %18s %20s\n", 
                    m.getMovieID(), 
                    m.getTitle(), 
                    m.getMovieYear(), 
                    m.getRunTime(), 
                    m.getClassification(), 
                    m.getDirectorFName(), 
                    m.getDirectorLName(), 
                    (g !=null) ? g.getGenreName() : "");
        }
    }
    private static void editMovie(Scanner keyboard, Model model) {
        System.out.print("Enter the MovieID of the movie to edit:");
        int movieID = Integer.parseInt(keyboard.nextLine());//fills the id variable with the users input
                    
        Movie m; 
        m = model.findMovieByID(movieID);//calls my findMovieByID method
        if (m != null) {//returns m, if its null then no id matching was found
            editMovieDetails(keyboard, m);
            if (model.updateMovie(m)) {
                System.out.println("Movie updated");
            }
            else {
                System.out.println("Movie not updated");
            }
        }
        else {//no matching id was found
            System.out.println("Movie not found");
        }
    }
    private static void editMovieDetails(Scanner keyboard, Movie m) {
       int movieYear, runTime, genre;//not giving the option to change id 
        String title, line1, line2, classification, directorFName, directorLName, line3;
        
        title = getString(keyboard, "Enter the title [" + m.getTitle() + "]:");
        line1 = getString(keyboard, "Enter the year of release [" + m.getMovieYear() + "]:");
        line2 = getString(keyboard, "Enter the running time [" + m.getRunTime() + "]:");
        classification = getString(keyboard, "Enter the classification [" + m.getClassification() + "]:");
        directorFName = getString(keyboard, "Enter the director's first name [" + m.getDirectorFName() + "]:");
        directorLName = getString(keyboard, "Enter the director's surname [" + m.getDirectorLName() + "]:");
        line3 = getString(keyboard, "Enter the genre [" + m.getGenre() + "]:");
       
       if(title.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           m.setTitle(title);//sets the new name
       }
       if(line1.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           movieYear= Integer.parseInt(line1);//i declare my integer he rather than above so i don't mess up my code
           m.setMovieYear(movieYear);//sets the new name
       }
       if(line2.length() !=0){
           runTime= Integer.parseInt(line2);//parses a string into an int
           m.setRunTime(runTime);
       } 
        if(classification.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           m.setClassification(classification);//sets the new name
       }
       if(directorFName.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           m.setDirectorFName(directorFName);//sets the new name
       }
       if(directorLName.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           m.setDirectorLName(directorLName);//sets the new name
       }
       if(line3.length() !=0){
           genre= Integer.parseInt(line3);//parses a string into an int
           m.setGenre(genre);
       } 
    }
    
    //Genres
    private static Genre readGenre(Scanner keyb) {
        
        String genreName, description;
        
        genreName= getString(keyb, "Enter the name:");
        description= getString(keyb, "Enter the description:");
        Genre g =
                new Genre(genreName,description);//SQL table will auto increments id
        
        return g;
    }
    private static void deleteGenre(Scanner keyboard, Model model) {
        System.out.print("Enter the GenreID of the genre to delete:");
        int genreID = Integer.parseInt(keyboard.nextLine());//fills the id variable with the users input
                    
        Genre g; 
        g = model.findGenreByID(genreID);//calls my findGenreByID method
        if (g != null) {//returns m, if its null then no id matching was found
            if (model.removeGenre(g)) {
                System.out.println("Genre deleted");
            }
            else {
                System.out.println("Genre not deleted");
            }
        }
        else {//no matching id was found
            System.out.println("Genre not found");
        }
    }
    private static void viewGenres(Model model) {
        List<Genre> genres = model.getGenres();
        System.out.printf("%8s %20s %30s \n", "Genre ID", "Genre Name", "Description");//columns sizes help present the data in a neater way 
        for (Genre m : genres){
            System.out.printf("%8d %20s %30s \n", m.getGenreID(), m.getGenreName(), m.getDescription());
        }
    }
    private static void editGenre(Scanner keyboard, Model model) {
        System.out.print("Enter the GenreID of the genre to edit:");
        int genreID = Integer.parseInt(keyboard.nextLine());//fills the id variable with the users input
                    
        Genre g; 
        g = model.findGenreByID(genreID);//calls my findGenreByID method
        if (g != null) {//returns m, if its null then no id matching was found
            editGenreDetails(keyboard, g);
            if (model.updateGenre(g)) {
                System.out.println("Genre updated");
            }
            else {
                System.out.println("Genre not updated");
            }
        }
        else {//no matching id was found
            System.out.println("Genre not found");
        }
    }
    private static void editGenreDetails(Scanner keyboard, Genre g) {
        
        String genreName, description;
        
        genreName = getString(keyboard, "Enter the name [" + g.getGenreName() + "]:");
        description = getString(keyboard, "Enter the description [" + g.getDescription() + "]:");
       
       if(genreName.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           g.setGenreName(genreName);//sets the new name
       }
        if(description.length() !=0){// this checks if the string is equal to null if it isn't then it overrides the old value for that variable
           g.setDescription(description);//sets the new name
       }
    }
    private static String getString(Scanner keyboard, String prompt) {//my method that read the users keyboard input
        System.out.print(prompt);
        return keyboard.nextLine();
    }
}
    

