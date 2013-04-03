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

    private Person personOne;
    private Person personTwo;

    private boolean small;
    private boolean medium;
    private boolean large;

    ArrayList<Rooms> closeRooms = new ArrayList<Rooms>(); //will hold a list of rooms close to the current
    //one

    public Rooms (String number) {
        this.roomNumber = number;
        occupied = false;
        size = "nuul";
        full = false;
        notFull = false;
        closeRooms = new ArrayList<Rooms>();
        small = false;
        medium = false;
        large = false;
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

    public Person getPersonOne() {
        return personOne;
    }

    public void setPersonOne(Person personOne) {
        this.personOne = personOne;
    }

    public Person getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(Person personTwo) {
        this.personTwo = personTwo;
    }

    public boolean isLarge() {
        return large;
    }

    public void setLarge(boolean large) {
        this.large = large;
    }

    public boolean isSmall() {
        return small;
    }

    public void setSmall(boolean small) {
        this.small = small;
    }

    public boolean isMedium() {
        return medium;
    }

    public void setMedium(boolean medium) {
        this.medium = medium;
    }
}
