package cpsc433;

import java.util.ArrayList;

/**
 * Author: Sean
 * This is the generic room class
 */
public class Rooms {
    private String roomNumber;
    private String size;
    private boolean occupied;
    private boolean full;
    private boolean notFull;  //this is probably redundant
    ArrayList<Rooms> closeRooms = new ArrayList<Rooms>(); //will hold a list of rooms close to the current
    //one

    public Rooms (String number) {
        this.roomNumber = number;
        occupied = false;
        size = "nuul";
        full = false;
        notFull = false;
        closeRooms = new ArrayList<Rooms>();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isNotFull() {
        return notFull;
    }

    public void setNotFull(boolean notFull) {
        this.notFull = notFull;
    }

    public void addToClose(Rooms rm){
        closeRooms.add(rm);
    }

    public ArrayList<Rooms> getClose(){
        return closeRooms;
    }

    public void setSmall(){
        this.size = "small";
    }

    public void setMedium(){
        this.size = "medium";
    }

    public void setLarge(){
        this.size = "large";
    }

    public String getSize(){
        return this.size;
    }
}
