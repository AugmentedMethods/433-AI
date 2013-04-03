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

    public Solution (ArrayList<Person> personList, ArrayList<Rooms> roomList)
    {
        this.personList = personList;
        this.roomList = roomList;
        orTree = new Tree();
        head = new Node();
        getSortedData();
    }


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
        buildTree(0,personList.size(), head);
        orTree.traverse(head);
    }

    /**
     * This should be a simple linked list for now
     * @param counter
     */
    private void buildTree(int counter, int maxSize, Node current)
    {
        if(counter == maxSize)
            return;
        else
        {
            orTree.add(current, createTuple(counter));
            for(Node node : current.getChildNodes())
                buildTree(counter+1,maxSize, node);
        }
    }

    /**
     * Select the people from the people list that could be added,
     * removes any that already appear in the solution
     * @param current
     */
    private void addChildren(Node current)
    {

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
