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


    //umm not really sure what im doing here, little rusty on inheritance
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
            PersonList.add(newPerson);
        }
        else
        {
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
            PersonList.add(newPerson);
        }
        else
        {
            temp.setPosition("manager");
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
            newPerson.setPosition("manager");
            PersonList.add(newPerson);
        }
        else
        {
            temp.setPosition("manager");
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
            newPerson.setPosition("hacker");
            PersonList.add(newPerson);
        }
        else
        {
            temp.setPosition("hacker");
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
            PersonList.add(newPerson);
        }
        else
        {
            temp.setGroup(grp);
        }
    }
    public boolean e_in_group(String p, String grp)
    {
        return false;
    }

    public void a_group(String p, String grp)
    {
        vec.add("group("+p+","+grp+")");
    }
    public boolean e_group(String p, String grp)
    {
        return false;
    }


    public void a_in_project(String p, String prj)
    {
        vec.add("project("+p+","+prj+")");
    }

    public boolean e_in_project(String p, String prj)
    {
        return false;
    }
    public void a_project(String p, String prj)
    {
        vec.add("project("+p+","+prj+")");
    }
    public boolean e_project(String p, String prj)
    {
        return false;
    }

    public void a_heads_group(String p, String grp)
    {
        vec.add("heads-group("+p+","+grp+")");
    }
    public boolean e_heads_group(String p, String grp)
    {
        return false;
    }

    public void a_heads_project(String p, String prj)
    {
        vec.add("heads-project("+p+","+prj+")");
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
        if(!vec.contains("room("+r+")"))
            vec.add("room("+r+")");
    }
    public boolean e_room(String r)
    {
        return false;
    }

    public void a_close(String room, String room2)
    {
        vec.add("close("+room+","+room2+")");
    }
    public boolean e_close(String room, String room2)
    {
        return false;
    }

    public void a_close(String room, TreeSet<Pair<Predicate.ParamType,Object>> set)
    {

    }

    public boolean e_close(String room, TreeSet<Pair<Predicate.ParamType,Object>> set)
    {
        return false;
    }

    public void a_large_room(String r)
    {
        a_room(r);
        vec.add("large-room("+r+")");
    }
    public boolean e_large_room(String r)
    {
        return false;
    }

    public void a_medium_room(String r)
    {
        a_room(r);
        vec.add("medium-room("+r+")");
    }
    public boolean e_medium_room(String r)
    {
        return false;
    }

    public void a_small_room(String r)
    {
        a_room(r);
        vec.add("small-room("+r+")");
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
        for (String s: vec)
            file.println(s);


        for (Person s: PersonList)
        {
            System.out.println(s.getName());
            //System.out.println(s.getPosition());
        }
//        for (Person s: PersonList)
//        {
//            if(s.getWorksWith().size()>0)
//            {
//                for(Person ww : s.getWorksWith())
//                {
//                    System.out.println(ww.getName());
//                }
//            }
//        }
    }

    public void print()
    {
        for (Person s: PersonList)
        {
            System.out.println(s.getName());
            //System.out.println(s.getPosition());
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
            if(stringCmp(person, p.getName()))
            {
               return p;
            }
        }
       return null;
    }

    private boolean stringCmp(String s1, String s2 )
    {
        if(s1.length() != s2.length())
            return false;
        for(int i =0; i < s1.length(); i++)
        {
            if(s1.charAt(i)!=s2.charAt(i))
                return false;
        }
        return true;
    }
}



