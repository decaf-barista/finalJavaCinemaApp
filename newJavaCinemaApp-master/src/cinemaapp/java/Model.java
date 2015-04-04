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
    private ScreenTableGateway gateway;
    
    private Model(){
        
         try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new ScreenTableGateway(conn);
            
            this.screens = gateway.getScreens();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Screen> getScreens() {
        return new ArrayList<Screen>(this.screens);
    }
    public void addScreen(Screen s) {
        try {
            int id = this.gateway.insertScreen(s.getSeatNumbers(), s.getFireExits());
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
            removed = this.gateway.deleteScreen(s.getId());
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
            updated = this.gateway.updateScreen(s);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex );
        }
        return updated;
    }
}
