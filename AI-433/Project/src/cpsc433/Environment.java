package cpsc433;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Author: Sean
 * We really should make this as a hash table, would make a huge difference,
 * Just don't have time right now.
 */
public class Environment extends PredicateReader implements SisyphusPredicates{

    Vector<String> vec = new Vector<String>();
    ArrayList<Person> PersonList = new ArrayList<Person>();
    ArrayList<Rooms> RoomList = new ArrayList<Rooms>();

    public Environment (String name){
        super(name);

    }



    public void a_person(String p)
    {
        //if(!vec.contains("person("+p+")")){
        //    vec.add("person("+p+")");  	}
        Person newPerson = new Person();
        newPerson.setName(p);
        PersonList.add(newPerson);

    }
    public boolean e_person(String p)
    {
        return false;
    }

    public void a_secretary(String p)
    {
        //a_person(p);
        //vec.add("secretary("+p+")");
        Person temp = findPerson(p);

        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setPosition("secretary");
            newPerson.setSecretary(true);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setSecretary(true);
            temp.setPosition("secretary");
        }


    }
    public boolean e_secretary(String p)
    {
        return false;
    }


    public void a_researcher(String p)
    {
        //a_person(p);
        //vec.add("researcher("+p+")");
        Person temp = findPerson(p);
        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setPosition("researcher");
            PersonList.add(newPerson);
        }
        else
        {
            temp.setPosition("researcher");
        }

    }
    public boolean e_researcher(String p)
    {
        return false;
    }


    public void a_manager(String p)
    {
        Person temp = findPerson(p);
        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setPosition("manager");
            newPerson.setManager(true);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setManager(true);
        }
    }
    public boolean e_manager(String p)
    {
        return false;
    }


    public void a_smoker(String p)
    {
        //a_person(p);
        //vec.add("smoker("+p+")");
        Person temp = findPerson(p);
        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setSmoker(true);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setSmoker(true);
        }
    }
    public boolean e_smoker(String p)
    {
        return false;
    }

    public void a_hacker(String p)
    {
        //a_person(p);
        //vec.add("hacker("+p+")");
        Person temp = findPerson(p);
        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setHacker(true);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setHacker(true);
        }
    }
    public boolean e_hacker(String p)
    {
        return false;
    }

    public void a_in_group(String p, String grp)
    {
        Person temp = findPerson(p);

        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setGroup(grp);
            newPerson.setHeadsGroup(false);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setGroup(grp);
            temp.setHeadsGroup(false);
        }
    }
    public boolean e_in_group(String p, String grp)
    {
        return false;
    }

    public void a_group(String p, String grp)
    {
        Person temp = findPerson(p);

        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setGroup(grp);
            newPerson.setHeadsGroup(false);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setGroup(grp);
            temp.setHeadsGroup(false);
        }
    }
    public boolean e_group(String p, String grp)
    {
        return false;
    }


    public void a_in_project(String p, String prj)
    {
        Person temp = findPerson(p);

        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setProject(prj);
            newPerson.setHeadsProject(false);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setHeadsProject(false);
            temp.setProject(prj);
        }
    }

    public boolean e_in_project(String p, String prj)
    {
        return false;
    }
    public void a_project(String p, String prj)
    {
        //vec.add("project("+p+","+prj+")");
        Person temp = findPerson(p);

        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setProject(prj);
            newPerson.setHeadsProject(false);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setHeadsProject(false);
            temp.setProject(prj);
        }
    }
    public boolean e_project(String p, String prj)
    {
        return false;
    }

    public void a_heads_group(String p, String grp)
    {
        Person temp = findPerson(p);

        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setGroup(grp);
            newPerson.setHeadsGroup(true);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setGroup(grp);
            temp.setHeadsGroup(true);
        }
    }
    public boolean e_heads_group(String p, String grp)
    {
        return false;
    }

    public void a_heads_project(String p, String prj)
    {
        Person temp = findPerson(p);

        if(temp==null)
        {
            Person newPerson = new Person();
            newPerson.setName(p);
            newPerson.setProject(prj);
            newPerson.setHeadsProject(true);
            PersonList.add(newPerson);
        }
        else
        {
            temp.setProject(prj);
            temp.setHeadsProject(true);
        }
    }
    public boolean e_heads_project(String p, String prj)
    {
        return false;
    }


    /**
     * This will add everyone that a person works with to that persons
     * object
     * works_with(Sean,{andrew,adam})
     * p = Sean
     *  tempList.add(new Person(per.getValue().toString())) will make an object
     *  for both andrew and adam
     * @param p persons name
     * @param p2s tuple containing all the works with employees
     */
    public void a_works_with(String p, TreeSet<Pair<Predicate.ParamType,Object>> p2s)
    {
        Person temp = findPerson(p);
        Person temp2=null;
        //templist is used to object creation in a loop
        ArrayList <Person> tempList = new ArrayList<Person>();
        int counter =0; //keep track of to current object

        //checks to see if the origional person already has an entry in the list,
        //if they do then temp will have that entry, otherwise it will have null
        if(temp == null)
        {
            Person newPerson = new Person(p); //create a new person and add the works with
            //details
            //This will just iterated through the tuple
            for(Pair<Predicate.ParamType,Object> per : p2s)
            {
                temp2 = findPerson(per.getValue().toString());
                //Will check to see if the works with person already has an entry in the person list
                if(temp2 == null)
                {
                    //creates a new person for every element in the works with tuple
                    tempList.add(new Person(per.getValue().toString()));
                    //add the new works with person to the origionally created new person
                    newPerson.addToWorksWith(tempList.get(counter));
                    counter++;
                }
                else //If the person in the works with tuple was found, add them to the newPersons structure
                    newPerson.addToWorksWith(temp2);
            }
            PersonList.add(newPerson);
        }
        else //The person was found
        {
            for(Pair<Predicate.ParamType,Object> per : p2s)
            {
                temp2=findPerson(per.getValue().toString());
                if(temp2 == null)
                {
                    tempList.add(new Person(per.getValue().toString()));   //creates a new person
                    temp.addToWorksWith(tempList.get(counter));
                    counter++;
                }
                else
                    temp.addToWorksWith(temp2);
            }
        }
    }
    public boolean e_works_with(String p, TreeSet<Pair<Predicate.ParamType,Object>> p2s)
    {
        return false;
    }

    public void a_works_with(String p, String p2)
    {

    }
    public boolean e_works_with(String p, String p2)
    {
        return false;
    }

    public void a_assign_to(String p, String room) throws Exception
    {

    }
    public boolean e_assign_to(String p, String room)
    {
        return false;
    }

    // ROOMS
    public void a_room(String r)
    {
        Rooms newRoom = new Rooms(r);
        newRoom.setRoomNumber(r);
        RoomList.add(newRoom);
    }
    public boolean e_room(String r)
    {
        return false;
    }

    public void a_close(String room, String room2)
    {
        Rooms temp = findRoom(room);
        Rooms temp2 = findRoom(room2);
        if(temp==null)
        {
            Rooms newRoom = new Rooms(room);
            newRoom.addToClose(temp2);
            RoomList.add(newRoom);
        }
        else
        {
            temp.addToClose(temp2);
        }
    }
    public boolean e_close(String room, String room2)
    {
        return false;
    }

    public void a_close(String room, TreeSet<Pair<Predicate.ParamType,Object>> set)
    {
        //C&P from a_works_with
        Rooms temp = findRoom(room);
        Rooms temp2 = null;
        //templist is used to object creation in a loop
        ArrayList <Rooms> tempList = new ArrayList<Rooms>();
        int counter =0; //keep track of to current object

        //checks to see if the original room already has an entry in the list,
        //if they do then temp will have that entry, otherwise it will have null
        if(temp == null)
        {
            Rooms newRoom = new Rooms(room); //create a new room and add the close
            for(Pair<Predicate.ParamType,Object> rm : set)
            {
                temp2 = findRoom(rm.getValue().toString());
                //Will check to see if the works with room already has an entry in the room list
                if(temp2 == null)
                {
                    //creates a new room for every element in the close to tuple
                    tempList.add(new Rooms(rm.getValue().toString()));
                    //add the new close to room to the originally created new room
                    newRoom.addToClose(tempList.get(counter));
                    counter++;
                }
                else
                    newRoom.addToClose(temp2);
            }
            RoomList.add(newRoom);
        }
        else //The Room was found
        {
            for(Pair<Predicate.ParamType,Object> rm : set)
            {
                temp2=findRoom(rm.getValue().toString());
                if(temp2 == null)
                {
                    tempList.add(new Rooms(rm.getValue().toString()));   //creates a new room
                    temp.addToClose(tempList.get(counter));
                    counter++;
                }
                else
                    temp.addToClose(temp2);
            }
        }
    }

    public boolean e_close(String room, TreeSet<Pair<Predicate.ParamType,Object>> set)
    {
        return false;
    }

    public void a_large_room(String r)
    {
        Rooms temp = findRoom(r);

        if(temp==null)
        {
            Rooms newRoom = new Rooms(r);
            newRoom.setLarge();
            RoomList.add(newRoom);
        }
        else
        {
            temp.setLarge();
        }
    }
    public boolean e_large_room(String r)
    {
        return false;
    }

    public void a_medium_room(String r)
    {
        Rooms temp = findRoom(r);

        if(temp==null)
        {
            Rooms newRoom = new Rooms(r);
            newRoom.setMedium();
            RoomList.add(newRoom);
        }
        else
        {
            temp.setMedium();
        }

    }
    public boolean e_medium_room(String r)
    {
        return false;
    }

    public void a_small_room(String r)
    {
        Rooms temp = findRoom(r);

        if(temp==null)
        {
            Rooms newRoom = new Rooms(r);
            newRoom.setSmall();
            RoomList.add(newRoom);
        }
        else
        {
            temp.setSmall();
        }

    }
    public boolean e_small_room(String r)
    {
        return false;
    }
    // GROUPS
    public void a_group(String g)
    {
        vec.add("group("+g+")");
    }
    public boolean e_group(String g)
    {
        return false;
    }

    // PROJECTS
    public void a_project(String p)
    {
        if(!vec.contains("project("+p+")"))
            vec.add("project("+p+")");
    }

    public boolean e_project(String p)
    {
        return false;
    }

    public void a_large_project(String prj)
    {
        a_project(prj);
        vec.add("large-project("+prj+")");
    }
    public boolean e_large_project(String prj)
    {
        return false;
    }
    public void printvector(PrintStream file)
    {

        for (Person s: PersonList)
        {
            file.println("person("+s.getName()+")");
            file.println(s.getPosition()+"("+s.getName()+")");
            if(s.isSmoker()){file.println("smoker("+s.getName()+")");}
            if(s.isHacker()){file.println("hacker("+s.getName()+")");}

            if(s.getProject()!=null)
            {
                file.println("\nproject("+s.getProject()+")");
                file.println("project("+s.getName()+", "+s.getProject()+")");
            }

            if(s.isHeadsProject()){file.println("heads-project("+s.getName()+","+s.getProject()+")");}

            if(s.getGroup()!=null)
            {
                file.println("\ngroup("+s.getGroup()+")");
                file.println("group(" + s.getName() + ", " + s.getGroup()+")");
            }

            if(s.isHeadsGroup()){file.println("heads-group("+s.getName()+","+s.getGroup()+")");}

            if(!(s.getWorksWith().isEmpty())){
                file.print("works-with("+s.getName()+", {");
                for(Person t :s.getWorksWith()){
                    if(t != s.getWorksWith().get(s.getWorksWith().size()-1))
                        file.print(t.getName()+", ");
                    else
                        file.println(t.getName()+"})");
                }
            }
        }
        for (Rooms r: RoomList)
        {
            file.println("\nroom("+r.getRoomNumber()+")");
            file.println(r.getSize()+"-room"+"("+r.getRoomNumber()+")");
        }
        file.println("\n//room proximity");

        for (Rooms y: RoomList)
        {
            if(!y.getClose().isEmpty()){
                file.print("\nclose("+y.getRoomNumber()+", {");
                for(Rooms x : y.closeRooms){
                    if(x != y.closeRooms.get(y.closeRooms.size()-1))
                        file.print(x.getRoomNumber()+", ");
                    else
                        file.print(x.getRoomNumber()+"})");
                }

            }
        }
    }

    /**
     * Will check to see if that person already has a person entry
     * @param person persons name, and a blank person
     */
    private Person findPerson(String person)
    {
        for(Person p : PersonList)
        {
            if(person.equals(p.getName()))
                return p;
        }
        return null;
    }
    private Rooms findRoom(String room)
    {
        for(Rooms r : RoomList)
        {
            if(room.equals(r.getRoomNumber()))
                return r;
        }
        return null;
    }

    public ArrayList<Person> getPersonList() {
        return PersonList;
    }

    public ArrayList<Rooms> getRoomList() {
        return RoomList;
    }

}



