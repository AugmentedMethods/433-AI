package cpsc433;

import java.util.ArrayList;

/**
 * This is a standard Node class used in both tree's and linked list
 */
public class Node {

    //A node will contain both a person and a room that that person is in
    private Person person;
    private Rooms room;

    //A node will contain both its individual goodness value and the summation of the
    //nodes in its path
    private int totalGoodnessValue;
    private int goodnessValue;

    //Standard parent child, the child is a list because we are not creating a binary tree
    private Node Parent;
    private ArrayList<Node> child;


    private int visited;

    public Node ()
    {
        person = null;
        room = null;
        goodnessValue = 0;
        child = new ArrayList<Node>();
        visited = 0;
    }

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
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

    //Could be used to clear a node, might not be needed anymore
    public void clearNode()
    {
        person = null;
        room = null;
        totalGoodnessValue = 0;
        goodnessValue = 0;
    }

    public ArrayList<Node> getChildNodes() {
        return child;
    }

    public void setChild(Node child) {
        this.child.add(child);
    }

    public Node getParent() {
        return Parent;
    }

    public void setParent(Node parent) {
        Parent = parent;
    }
}
