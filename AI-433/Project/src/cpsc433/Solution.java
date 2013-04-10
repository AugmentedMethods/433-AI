package cpsc433;

import java.util.ArrayList;

/**
 *
 */
public class Solution {

    private ArrayList<Person> personList;

    private ArrayList<Person> secretaryList;
    private ArrayList<Person> groupHeads;
    private ArrayList<Person> managers;
    private ArrayList<Person> projectHeads;

    private ArrayList<Rooms> roomList;
    ArrayList<Rooms> smallRooms;
    ArrayList<Rooms> mediumRooms;
    ArrayList<Rooms> largeRooms;

    private Tree orTree;
    private Node head;
    private Node tempNode = new Node();

    private Calculate generalCalcObj;

    int personCounter;

    public Solution (ArrayList<Person> personList, ArrayList<Rooms> roomList)
    {
        this.personList = personList;
        this.roomList = roomList;
        orTree = new Tree();
        head = new Node();
        getSortedData();
        generalCalcObj = new Calculate();
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
        buildTree(head, arrayCopy(),0);
        orTree.traverse(head);
    }

    /**
    * This should be a simple linked list for now
    */
    private void buildTree(Node current, ArrayList<Person> partialList, int nodeNum)
    {
        Node temp;
        int checkVal;
        if(partialList.size()==0)
            return;
        else
        {
            temp = createTuple(partialList.get(0));
            current = temp;
            partialList.remove(nodeNum);
            for(Person p : partialList)
            {
                temp = createTuple(p);
                current.setChild(temp);
            }
            for(int i = 0; i < current.getChildNodes().size(); i++)
            {
                buildTree(current.getChildNodes().get(i) , partialList,i);
            }
        }
    }

    private ArrayList<Person> arrayCopy ()
    {
         ArrayList<Person> tempList = new ArrayList<Person>();
        for(Person p : personList)
            tempList.add(p);
        return tempList;
    }

    private Node createTuple(Person p)
    {
        Node node = new Node();
        node.setPerson(p);
        for(Rooms r : roomList)
        {
            if(r.isNotFull())
            {
                node.setRoom(r);
                if(r.getPersonOne()==null)
                    r.setPersonOne(p);
                else if(r.getPersonTwo() == null)
                    r.setPersonTwo(p);
                break;
            }
        }
        return node;
    }


}
