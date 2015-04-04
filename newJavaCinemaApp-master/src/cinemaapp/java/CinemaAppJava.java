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
        
        int opt;
        do{//my loop that runs till 5/exit is chosen
            System.out.println("1. Create new Screen");
            System.out.println("2. Delete existing Screen");
            System.out.println("3. View all Screens");
            System.out.println("4. Edit existing Screen");
            System.out.println("5. Exit");
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
            }
        }
        while (opt !=5);//exit
        System.out.println("Goodbye");        
    }
    
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
    private static String getString(Scanner keyboard, String prompt) {//my method that read the users keyboard input
        System.out.print(prompt);
        return keyboard.nextLine();
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
}
    

