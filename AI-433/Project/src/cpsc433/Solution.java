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
    Node tempNode = new Node();

    public Solution (ArrayList<Person> personList, ArrayList<Rooms> roomList)
    {
        this.personList = personList;
        this.roomList = roomList;
        orTree = new Tree();
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






}
