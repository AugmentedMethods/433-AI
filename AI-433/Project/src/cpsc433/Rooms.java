package cpsc433;

import java.util.ArrayList;

/**
 * Author: Sean
 * This is the generic room class
 */
public class Rooms {
    private String roomNumber;
    private String size;
    private Person personOne;
    private Person personTwo;

    private boolean small;
    private boolean medium;
    private boolean large;

    ArrayList<Rooms> closeRooms = new ArrayList<Rooms>(); //will hold a list of rooms close to the current
    //one

    public Rooms copyRoom(Rooms r)
    {
        Rooms temp = new Rooms(r.getRoomNumber());
        temp.size = r.getSize();
        temp.personOne = r.getPersonOne();
        temp.personTwo= r.getPersonTwo();
        if(r.isSmall())
            temp.small = true;
        else if (r.isMedium())
            temp.medium = true;
        else if (r.isLarge())
            temp.large = true;

        temp.closeRooms = r.getClose();
        return temp;
    }

    public Rooms (String number) {
        this.roomNumber = number;
        size = "nuul";
        closeRooms = new ArrayList<Rooms>();
        small = false;
        medium = false;
        large = false;
        personOne = null;
        personTwo = null;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * This needs to be tested (could cause a bug)
     * @return
     */
    public boolean oneOccupant()
    {
        if((personOne == null) ^ (personTwo == null))
            return true;
        return false;
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
