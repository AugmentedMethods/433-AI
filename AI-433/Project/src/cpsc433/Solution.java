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
        buildTree(orTree.head,arrayCopyPerson(-1, personList), arrayCopyRoom(-1, roomList),0);
        orTree.traverse(orTree.head, personList.size());
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
        /**
         * This if statement controls the recursion, if the node fails the test then that branch is ended
         * THis will also update the partial list that the node will see, see the method defintions for more detail.
         */
        if(current.getPerson() != null && 0 < partialPersonList.size())
        {
            partialPersonList = arrayCopyPerson(nodeNum, partialPersonList);
            //create paring here
            current = createTuple(current,partialRoomList);
            checkVal=generalCalcObj.update(current) ;

            if(checkVal != 1)
                return;
            partialRoomList = arrayCopyRoom(1, partialRoomList);
        }
        /**
         * Second recursion control
         */
        if(partialPersonList.size() == 0 || partialRoomList.size() == 0)
            return;
        /**
         * Spawn all the possible child nodes (a child node of every person not yet
         * in the tree branch)
         */
        for(Person p : partialPersonList)
        {
            temp = createNode();
            temp.setPerson(p);
            orTree.add(current,temp);
        }

        /**
         * Cycle through every child and do the above recursion.
         */
        for(int i =0 ; i < orTree.getChildren(current).size();i++ )
        {

            orTree.getChildren(current).get(i).setParent(current);
            buildTree(orTree.getChildren(current).get(i), partialPersonList, partialRoomList, i);
        }
    }

    /**
     * This could probably be added else where, is a little old
     * @return
     */
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
            //check bellow for details
            check = skipConditions(rList.get(i));
            if(check == 1)
                tempList.add((rList.get(i)));
            if(check == -1)
                return emptyRoomList;
        }
        return tempList;

    }

    /**
     * This method controls where a room can still be used or not.
     * Please check that I have not missed any possible reason why a room would be full
     * or could not be used
     * Also check that they are all valid, if one is wrong here, it will destroy our results
     * @param r  - room list
     * @return - 0 for the room can't be used anymore, 1 for still usable, -1 for failed the hard constraint
     * The -1 has not yet be used (needs to be implemnted)
     */
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
        if(r.isSmall()&& (r.getPersonTwo() != null || r.getPersonOne() != null))
            return 0;
        if(r.getPersonOne() == null || r.getPersonTwo() == null)
            return 1;

        return 0;

    }

    /**
     * This is where are a lot of the changes will be needed!
     * @param current - Current node
     * @param partialRoomList
     * @return - Null if it fails, otherwise the node.
     */
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
