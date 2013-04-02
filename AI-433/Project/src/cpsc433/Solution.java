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
    private Tree orTree;
    Node tempNode = new Node();

    public Solution (ArrayList<Person> personList, ArrayList<Rooms> roomList)
    {
        this.personList = personList;
        this.roomList = roomList;
        orTree = new Tree();
        runSolution();
    }


    public void runSolution()
    {
        PersonSort temp = new PersonSort();
        temp.sortPersonList(personList);

        groupHeads = temp.getGroupHeads();
        secretaryList = temp.getSecretaryList();
        managers = temp.getManagers();
        projectHeads = temp.getProjectHeads();
    }






}
