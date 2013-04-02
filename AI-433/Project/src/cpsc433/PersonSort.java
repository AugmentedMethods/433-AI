package cpsc433;

import java.util.ArrayList;

/**
 * This class will sort the person list into managers, group heads, project heads
 * and secretarys.
 */
public class PersonSort {

    private ArrayList<Person> secretaryList = new ArrayList<Person>();
    private ArrayList<Person> groupHeads = new ArrayList<Person>();
    private ArrayList<Person> managers = new ArrayList<Person>();
    private ArrayList<Person> projectHeads = new ArrayList<Person>();

    public void sortPersonList (ArrayList<Person> personList)
    {
        for(Person p : personList)
        {
            if(p.isHeadsGroup())
                groupHeads.add(p);
            else if (p.isHeadsProject())
                projectHeads.add(p);
            else if (p.isManager())
                managers.add(p);
            else if (p.isSecretary())
                secretaryList.add(p);
        }
    }

    public ArrayList<Person> getProjectHeads() {
        return projectHeads;
    }

    public ArrayList<Person> getSecretaryList() {
        return secretaryList;
    }

    public ArrayList<Person> getGroupHeads() {
        return groupHeads;
    }

    public ArrayList<Person> getManagers() {
        return managers;
    }



}
