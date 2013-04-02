package cpsc433;

import java.util.ArrayList;

/**

 */
public class Node {
    private Person person;
    private Rooms room;    //Both a room and person are added to aid in the room proximity calculations
    //I imagine it should make it easier.
    private int totalGoodnessValue;    //the current goodness value of the the prior nodes
    private int goodnessValue;         //the current goodness value.

    public Node ()
    {
        person = null;
        room = null;
        goodnessValue = 0;
    }

    public int getTotalGoodnessValue() {
        return totalGoodnessValue;
    }

    public void setTotalGoodnessValue(int totalGoodnessValue) {
        this.totalGoodnessValue = totalGoodnessValue;
    }

    public int getGoodnessValue() {
        return goodnessValue;
    }

    public void setGoodnessValue(int goodnessValue) {
        this.goodnessValue = goodnessValue;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public void clearNode()
    {
        person = null;
        room = null;
        totalGoodnessValue = 0;
        goodnessValue = 0;
    }

}
