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
    ArrayList<Rooms> emptyRoomList;
    private Tree orTree;



    private Calculate generalCalcObj;

    public Solution (ArrayList<Person> personList, ArrayList<Rooms> roomList)
    {
        this.personList = personList;
        this.roomList = roomList;
        emptyRoomList = new ArrayList<Rooms>();
        orTree = new Tree();
        //getSortedData();
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
//        for(Person p : personList)
//                System.out.println(p.getName());
        buildTree(orTree.head,arrayCopyPerson(-1, personList), arrayCopyRoom(-1, roomList),0);

        //orTree.traverse(orTree.head, personList.size());


    }

    /**
    *
    */
    private void buildTree(Node current,ArrayList<Person> partialPersonList, ArrayList<Rooms> partialRoomList, int nodeNum)
    {
        Node temp =null;
        int checkVal;
        if(current.getPerson() != null && 0 < partialPersonList.size())
        {
            partialPersonList = arrayCopyPerson(nodeNum, partialPersonList);
            //create paring here
            current = createTuple(current,partialRoomList);
            checkVal=generalCalcObj.update(current) ;
            //if(checkVal != 1)
            //    return;
            partialRoomList = arrayCopyRoom(1, partialRoomList);
        }

        if(partialPersonList.size() == 0 || partialRoomList.size() == 0)
            return;
        for(Person p : partialPersonList)
        {
            temp = createNode();
            temp.setPerson(p);
            orTree.add(current,temp);
        }

        for(int i =0 ; i < orTree.getChildren(current).size();i++ )
        {

            orTree.getChildren(current).get(i).setParent(current);
            buildTree(orTree.getChildren(current).get(i), partialPersonList, partialRoomList, i);
        }
    }

    private Node createNode()
    {
        Node newNode = new Node();
        return newNode;
    }

    private ArrayList<Person> arrayCopyPerson (int skip, ArrayList<Person> pList)
    {

        ArrayList<Person> tempList = new ArrayList<Person>();
        if(skip == -1){
            for(Person p : personList)
                tempList.add(p);
            return tempList;
        }
        for(int i = 0; i < pList.size(); i++)
        {
            if(i != skip)
                tempList.add(pList.get(i));
        }
        return tempList;
    }

    /**
     * Need to account for the -1 in the skip condition, it indicated a dead branch
     * @param skip
     * @param rList
     * @return
     */
    private ArrayList<Rooms> arrayCopyRoom (int skip,ArrayList<Rooms> rList)
    {   ArrayList<Rooms> tempList = new ArrayList<Rooms>();
        int check;
        if(skip == -1)
        {
            for(Rooms r : roomList)
                tempList.add(r);
            return tempList;
        }
        for(int i = 0; i < rList.size(); i++)
        {
            check = skipConditions(rList.get(i));
            if(check == 1)
                tempList.add((rList.get(i)));
            if(check == -1)
                return emptyRoomList;
        }
        return tempList;

    }

    private int skipConditions(Rooms r)
    {
        if(r.getPersonOne() != null && r.getPersonTwo() != null && (r.getPersonOne().needsSeperateRoom() || r.getPersonTwo().needsSeperateRoom()))
            return -1;
         if(r.getPersonOne() != null && r.getPersonOne().needsSeperateRoom())
             return 0;
         if(r.getPersonTwo() != null && r.getPersonTwo().needsSeperateRoom())
             return 0;
        if(r.getPersonOne() != null && r.getPersonTwo() != null)
            return 0;
        if(r.getPersonOne() == null || r.getPersonTwo() == null)
            return 1;

        return 0;

    }

    private Node createTuple(Node current,ArrayList<Rooms> partialRoomList)
    {
        //System.out.println(partialRoomList.size());
        for(Rooms r : partialRoomList)   {

            if (r.getPersonOne() == null)
            {
                current.setRoom(r);
                r.setPersonOne(current.getPerson());
                return current;
            }
            else if(r.getPersonTwo() == null)
            {
                current.setRoom(r);
                r.setPersonTwo(current.getPerson());
                return current;
            }

        }

        return null;//failure
    }

    private void printList(ArrayList<Person> pList)
    {
        for(Person p : pList){
            System.out.println(p.getName());
        }
    }
}
