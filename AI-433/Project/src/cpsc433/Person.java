package cpsc433;

import java.util.ArrayList;

/**
 * Author: Sean
 * Date: 3/19/13
 * This will be the central person data structure that will be used
 * through out the evaluation
 */
public class Person {

    private String name;
    private boolean smoker;
    private Rooms currentRoom;
    private String position;
    private boolean isHacker;
    private boolean headsGroup;
    private boolean headsProject;
    private boolean isManager;
    private boolean isSecretary;

    private String project;
    private ArrayList<Person> worksWith;

    public Person()
    {
        worksWith = new ArrayList<Person>();
        name ="";
        smoker = false;
        currentRoom = null;
        position = "";
        isHacker = false;
        headsGroup = false;
        headsProject = false;
        isManager = false;
        isSecretary = false;
    }

    public Person (String name)
    {
        this.name = name;
        worksWith = new ArrayList<Person>();
        smoker = false;
        currentRoom = null;
        position = "";
        isHacker = false;
        headsGroup = false;
        headsProject = false;
    }

    public boolean isSecretary() {
        return isSecretary;
    }

    public void setSecretary(boolean secretary) {
        isSecretary = secretary;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isHeadsProject() {
        return headsProject;
    }

    public void setHeadsProject(boolean headsProject) {
        this.headsProject = headsProject;
    }


    public boolean isHeadsGroup() {
        return headsGroup;
    }

    public void setHeadsGroup(boolean headsGroup) {
        this.headsGroup = headsGroup;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public ArrayList<Person> getWorksWith() {
        return worksWith;
    }

    public void addToWorksWith(Person per) {
        worksWith.add(per);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private String group;

    public String getName() {
        return name;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public Rooms getCurrentRoom() {
        return currentRoom;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public void setCurrentRoom(Rooms currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public void setHacker(boolean isHacker) {
        this.isHacker = isHacker;
    }

    public boolean isHacker() {
        return isHacker;
    }


}

