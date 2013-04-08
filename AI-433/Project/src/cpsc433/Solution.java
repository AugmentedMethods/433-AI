package cpsc433;

import java.util.ArrayList;

/**
 *
 */
public class Solution {

    private ArrayList<Person> personList;

    private ArrayList<Person> secretaryList = new ArrayList<Person>();
    private ArrayList<Person> groupHeads = new ArrayList<Person>();
    private ArrayList<Person> managers = new ArrayList<Person>();
    private ArrayList<Person> projectHeads = new ArrayList<Person>();

    private ArrayList<Rooms> roomList;
    ArrayList<Rooms> smallRooms = new ArrayList<Rooms>();
    ArrayList<Rooms> mediumRooms = new ArrayList<Rooms>();
    ArrayList<Rooms> largeRooms = new ArrayList<Rooms>();

    private Tree orTree;
    private Node head;
    private Node tempNode = new Node();
    int personCounter;

    public Solution (ArrayList<Person> personList, ArrayList<Rooms> roomList)
    {
        this.personList = personList;
        this.roomList = roomList;
        orTree = new Tree();
        head = new Node();
        getSortedData();
    }

/*    public void setPersonCounter(int counter)
    {
        personCounter = counter;
    }
    
    public int getPersonCounter()
    {
        return personCounter;
    } */
    
    public void getSortedData()
    {
        PersonSort temp = new PersonSort();
        RoomSort sortRoom = new RoomSort();

        temp.sortPersonList(personList);
        sortRoom.roomSort(roomList);

        groupHeads = temp.getGroupHeads();
        secretaryList = temp.getSecretaryList();
        managers = temp.getManagers();
        projectHeads = temp.getProjectHeads();

        smallRooms = sortRoom.getSmallRooms();
        mediumRooms = sortRoom.getMediumRooms();
        largeRooms = sortRoom.getLargeRooms();
    }

    public void beginSearch()
    {
        buildTree(0,personList.size(), head, personList);
        orTree.traverse(head);
    }

    /**
     * Populate the tree
     * Logic might be a little fuzzy?
     * Untested.
     */
    private void buildTree(int counter, int maxSize, Node current, ArrayList<Person> people)
    {
        if(counter == maxSize)
            return;
        else
        {
            int temp = counter;
            while(temp != maxSize)
            {
                orTree.add(current, createTuple(temp));
                temp++;
            }
            for(Node node : current.getChildNodes())
                addChildren(node, people);
        }
    }

    /**
     * Select the people from the people list that could be added,
     * removes any that already appear in the solution
     * Untested.
     */
    private void addChildren(Node current, ArrayList<Person> people)
    {
        ArrayList<Person> tempPersonList = new ArrayList<Person>();
        tempPersonList = people;
        tempPersonList.remove(this);
        buildTree(0, tempPersonList.size(), current, tempPersonList);
    }

    private Node createTuple(int counter)
    {
        Node node = new Node();
        node.setPerson(personList.get(counter));
        for(Rooms r : roomList)
        {
            if(r.isNotFull())
            {
                node.setRoom(r);
                if(r.getPersonOne()==null)
                    r.setPersonOne(personList.get(counter));
                else if(r.getPersonTwo() == null)
                    r.setPersonTwo(personList.get(counter));
                break;
            }
        }
        return node;
    }

}
