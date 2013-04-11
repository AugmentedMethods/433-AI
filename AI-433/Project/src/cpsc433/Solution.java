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

    private ArrayList<Person> emptyList = new ArrayList<Person>();

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
        buildTree(orTree.head, arrayCopyPerson(), arrayCopyRoom(), 0);
        //System.out.println(orTree.head.getChildNodes());
        orTree.traverse(orTree.head, personList.size());
    }

    /**
    *
    */
    private void buildTree(Node current, ArrayList<Person> partialPersonList, ArrayList<Rooms> partialRoomList, int nodeNum)
    {
        Node temp;
        int checkVal;
        if(partialPersonList.size()==0)
            return;
        else
        {
            temp = createTuple(partialPersonList.get(0), partialRoomList);
            orTree.add(current, temp);

            partialPersonList.remove(nodeNum);
            partialRoomList.remove(nodeNum);

            for(Person p : partialPersonList)
            {
                temp = createTuple(p,partialRoomList);
                current.setChild(temp);
            }
            for(int i = 0; i < current.getChildNodes().size(); i++)
            {
                current.getChildNodes().get(i).setParent(current);
            	checkVal = generalCalcObj.update(current.getChildNodes().get(i));

                if(checkVal != 1) {
                    current.getChildNodes().remove(i);
                    if(partialPersonList.size()>0)
                        partialPersonList.remove(i);
                    i--;
                }
                else
                {
                    buildTree(current.getChildNodes().get(i) , partialPersonList, arrayCopyRoom(), i);
                }
            }
        }
    }

    private ArrayList<Person> arrayCopyPerson ()
    {
         ArrayList<Person> tempList = new ArrayList<Person>();
        for(Person p : personList)
            tempList.add(p);
        return tempList;
    }
    
    private ArrayList<Rooms> arrayCopyRoom()
    {
    	ArrayList<Rooms> tempList = new ArrayList<Rooms>();
    	for(Rooms r : roomList){
    		r.setPersonOne(null);
    		r.setPersonTwo(null);
    		r.setNotFull(false);
    		tempList.add(r);
    	}
    	return tempList;
    }

    private Node createTuple(Person p, ArrayList<Rooms> partialPersonList)
    {
        Node node = new Node();
        node.setPerson(p);
        for(Rooms r : partialPersonList)
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
