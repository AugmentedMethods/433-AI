package cpsc433;

import java.io.PrintStream;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Sean
 * Date: 3/14/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Environment extends PredicateReader implements SisyphusPredicates{

    Vector<String> vec = new Vector<String>();

    //umm not really sure what im doing here, little rusty on inheritance
    public Environment (String name){
        super(name);

    }



    public void a_person(String p)
    {
        if(!vec.contains("person("+p+")")){
            vec.add("person("+p+")");  	}
    }
    public boolean e_person(String p)
    {
        return false;
    }

    public void a_secretary(String p)
    {
        a_person(p);
        vec.add("secretary("+p+")");

    }
    public boolean e_secretary(String p)
    {
        return false;
    }


    public void a_researcher(String p)
    {
        a_person(p);
        vec.add("researcher("+p+")");
    }
    public boolean e_researcher(String p)
    {
        return false;
    }


    public void a_manager(String p)
    {
        a_person(p);
        vec.add("manager("+p+")");
    }
    public boolean e_manager(String p)
    {
        return false;
    }


    public void a_smoker(String p)
    {
        a_person(p);
        vec.add("smoker("+p+")");
    }
    public boolean e_smoker(String p)
    {
        return false;
    }

    public void a_hacker(String p)
    {
        a_person(p);
        vec.add("hacker("+p+")");
    }
    public boolean e_hacker(String p)
    {
        return false;
    }

    public void a_in_group(String p, String grp)
    {
        vec.add("group("+p+","+grp+")");
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

    public void a_works_with(String p, TreeSet<Pair<Predicate.ParamType,Object>> p2s)
    {
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
    }

}

