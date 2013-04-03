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
        int counter = personList.size();
        buildTree(counter, head);
        orTree.traverse(head);
    }

    /**
     * This should be a simple linked list for now
     * @param counter
     */
    private void buildTree(int counter, Node current)
    {
        if(counter ==0)
            return;
        else
        {
            orTree.add(current, createTuple(counter));
            buildTree(counter-1, current.getChildNodes().get(0));
        }
    }

    private Node createTuple(int counter)
    {
        Node node = new Node();
        node.setPerson(personList.get(counter-1));
        return node;
    }
}
