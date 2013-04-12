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
        //System.out.println(orTree.head);
        //printList();
        buildTree(orTree.head,arrayCopyPerson(), 0);
        System.out.println(orTree.head.getChildNodes());
        System.out.println(orTree.head.getChildNodes().get(0).getChildNodes());

        //orTree.traverse(orTree.head, personList.size());

    }

    /**
    *
    */
    private void buildTree(Node current,ArrayList<Person> partialPersonList, int nodeNum)
    {
        Node temp;

        if(nodeNum < 1){
            for(Person p : partialPersonList)
            {
                temp = createNode();
                temp.setPerson(p);
                current.setChild(temp);
            }
            buildTree(current.getChildNodes().get(0), partialPersonList, 1);
        }




//        Node temp;
//        int checkVal;
//        //if(currentNode != null && currentNode.getPerson()!= null)
//            //System.out.println(currentNode.getPerson().getName());
//        if(partialPersonList.size()==0)
//        {
//            System.out.println("End of a branch");
//            return;
//        }
//        else
//        {
//            temp = createTuple(partialPersonList.get(0), partialRoomList);
//            orTree.add(currentNode, temp);
//
//            partialPersonList.remove(nodeNum);
//            partialRoomList.remove(nodeNum);
//
//            for(Person p : partialPersonList)
//            {
//                temp = createTuple(p,partialRoomList);
//                currentNode.setChild(temp);
//            }
//            for(int i = 0; i < currentNode.getChildNodes().size(); i++)
//            {
//                currentNode.getChildNodes().get(i).setParent(currentNode);
//            	//checkVal = generalCalcObj.update(currentNode.getChildNodes().get(i));
//                //if(checkVal != 1) {
//                //    currentNode.getChildNodes().remove(i);
//                //    if(partialPersonList.size()>0)
//                //        partialPersonList.remove(i);
//                //    i--;   //To componsate for the removal
//                //}
//                //else if (checkVal == 1)
//                buildTree(currentNode.getChildNodes().get(i) , partialPersonList, arrayCopyRoom(), i);
//
//            }
//        }
    }

    private Node createNode()
    {
        Node newNode = new Node();
        return newNode;
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

    private void printList()
    {
        for(Person p : personList)
            System.out.println(p.getName());
    }
}
