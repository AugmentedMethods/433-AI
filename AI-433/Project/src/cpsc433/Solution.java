package cpsc433;

import javax.swing.tree.RowMapper;
import java.io.PrintStream;
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
    double startTime;

    public Solution (double startTime ,ArrayList<Person> personList, ArrayList<Rooms> roomList)
    {
        this.personList = personList;
        this.roomList = roomList;
        emptyRoomList = new ArrayList<Rooms>();
        this.startTime = startTime;
        orTree = new Tree();
        generalCalcObj = new Calculate(500000, 500);
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
//        for(Rooms r:roomList)
//            System.out.println(r.getRoomNumber());
        buildTree(orTree.head,arrayCopyPerson(-1, personList), arrayCopyRoom(roomList),0);
        orTree.traverse(orTree.head, personList.size());


        System.out.println("Nothing...");
    }

    /**
     * This is a completed Tree works by recursion.
     *
     * @param current - The current node in the tree
     * @param partialPersonList - A partial list of people that the current node can see,
     *                          it includes everyone that has not yet been placed in the
     *                          branch.
     * @param partialRoomList  - A partial list of rooms that the current node can see,
     *                         it includes every room that can still contain another person
     * @param nodeNum - The current node number in the list of child nodes.
     */
    private void buildTree(Node current,ArrayList<Person> partialPersonList, ArrayList<Rooms> partialRoomList, int nodeNum)
    {
        Node temp =null;
        int checkVal;

        if(((System.nanoTime()-startTime)/1000000000) > 25)
            return;

        if(current.getPerson() != null && 0 < partialPersonList.size())
        {
            partialPersonList = arrayCopyPerson(nodeNum, partialPersonList);

            current = pickRoom(current, partialRoomList);
            checkVal = generalCalcObj.update(current);
            if(checkVal != 1)
                return;
            partialRoomList = setRoomData(current,partialRoomList);
            partialRoomList =roomRemove(current, partialRoomList);


        }
        if(partialPersonList.size() == 0)
        {
            return;
        }
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

    /**
     * THis will update the partial person list, a new list needs to be returned because of how java
     * passes arguments.
     * @param skip - THe next person to be removed from the list
     * @param pList - The person list from the prior node.
     * @return - The new partial person list
     */
    private ArrayList<Person> arrayCopyPerson (int skip, ArrayList<Person> pList)
    {

        ArrayList<Person> tempList = new ArrayList<Person>();
        //This is for the first copy
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
     * This will created the partial room list
     * Need to account for the -1 in the skip condition, it indicated a dead branch
     * @param skip - Only used to indicated the first copy
     * @param rList - The old room list
     * @return - The new room list
     */
    private ArrayList<Rooms> arrayCopyRoom (ArrayList<Rooms> rList)
    {
        ArrayList<Rooms> tempList = new ArrayList<Rooms>();

        for(Rooms r : roomList)
            tempList.add(r.copyRoom(r));
        return tempList;
    }

    private ArrayList<Rooms> roomRemove (Node current, ArrayList<Rooms> rList)
    {

        ArrayList<Rooms> tempList = new ArrayList<Rooms>();
        boolean check;
        for(Rooms r : rList)  {
            //System.out.println(r.getPersonOne());
            check = r.getRoomNumber().equals(current.getRoom().getRoomNumber());
            if(!check)
                tempList.add(r.copyRoom(r));
            else if(skipConditions(r,current)==1)
                tempList.add(r.copyRoom(r));

        }

        return tempList;
    }

    /**
     * This is where are a lot of the changes will be needed!
     * @param current - Current node
     * @param partialRoomList
     * @return - Null if it fails, otherwise the node.
     */
    private Node pickRoom(Node current,ArrayList<Rooms> partialRoomList)
    {
        Rooms temp;
        for(Rooms r : partialRoomList){

            if(r.getPersonOne() == null) {
                temp = r.copyRoom(r);
                temp.setPersonOne(current.getPerson());
                current.setRoom(temp);
                return current;
            }
            else if(r.getPersonTwo() == null) {
                temp = r.copyRoom(r);
                temp.setPersonTwo(current.getPerson());
                current.setRoom(temp);
                return current;
            }
        }
        return current;
    }

    private ArrayList<Rooms> setRoomData(Node current, ArrayList<Rooms> rList)
    {
        ArrayList<Rooms> temp = new ArrayList<Rooms>();
        for(int i = 0 ; i < rList.size() ; i++)
        {
            temp.add(rList.get(i).copyRoom(rList.get(i)));
            if(rList.get(i).getRoomNumber().equals(current.getRoom().getRoomNumber()) && temp.get(i).getPersonOne() == null )
            {
                temp.get(i).setPersonOne(current.getPerson());
            }
            else if(rList.get(i).getRoomNumber().equals(current.getRoom().getRoomNumber()) && temp.get(i).getPersonTwo() == null )
            {
                temp.get(i).setPersonTwo(current.getPerson());
            }
        }
        return temp;
    }

    /**
     * This method controls whether a room can still be used or not.
     * Please check that I have not missed any possible reason why a room would be full
     * or could not be used
     * Also check that they are all valid, if one is wrong here, it will destroy our results
     * @param r  - room list
     * @return - 0 for the room can't be used anymore, 1 for still usable, -1 for failed the hard constraint
     * The -1 has not yet be used (needs to be implemnted)
     */
    private int skipConditions(Rooms r, Node current)
    {
        boolean sameRoom = r.getRoomNumber().equals(current.getRoom().getRoomNumber());

        //System.out.println(r.getPersonOne() + " + " + r.getPersonTwo());

        if(r.isSmall())
            return 0;
        if(current.getPerson().needsSeperateRoom())
            return 0;
        if(r.getPersonOne() == null || r.getPersonTwo()== null)
            return 1;

        return 0;
    }
    private void printList(ArrayList<Person> pList)
    {
        for(Person p : pList){
            System.out.println(p.getName());
        }
    }

    public void print(PrintStream outFile)
    {
        orTree.printStack(outFile);
    }
}
