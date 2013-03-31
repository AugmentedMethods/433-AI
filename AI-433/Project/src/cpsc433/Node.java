package cpsc433;

import java.util.ArrayList;

/**

 */
public class Node {
    private Node Parent;
    private ArrayList<Node> child;
    private Person person;
    private Rooms room;
    private int totalGoodnessValue;
    private int goodnessValue;

    public Node ()
    {
        Parent = null;
        child = new ArrayList<Node>();
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

    public Node getParent() {
        return Parent;
    }

    public void setParent(Node parent) {
        Parent = parent;
    }

    public ArrayList<Node> getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child.add(child);
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

}
