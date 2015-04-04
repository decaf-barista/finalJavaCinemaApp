package cinemaapp.java;

public class Screen {
    //attributes
    private int id;
    private int seatNumbers;
    private int fireExits;
    //test
    public Screen (int i, int sn, int f){//paramatized constructor
        this.id = i;
        this.seatNumbers = sn;
        this.fireExits = f;
    }
    
    public Screen (int sn, int f){
        this(-1, sn, f);//my id is auto increment, the user cannot edit id or give it a value
    }
    //my get methods
    public int getId() {
        return id;
    }
    public int getSeatNumbers() {
        return seatNumbers;
    }
    public int getFireExits() {
        return fireExits;
    }
    //my set methods
    public void setId(int id) {
        this.id = id;
    }
    public void setSeatNumbers(int seatNumbers) {
        this.seatNumbers = seatNumbers;
    }
    public void setFireExits(int fireExits) {
        this.fireExits = fireExits;
    }
}
