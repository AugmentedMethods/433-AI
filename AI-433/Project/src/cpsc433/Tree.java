package cpsc433;

import java.util.ArrayList;

/**
 *
 */
public class Tree {
    //this is the root node, it will be empty
    Node head = new Node();

    public Tree ()
    {
    }

    /**
     * will add a child to the list of children
     * @param current
     * @param toAdd
     */
    public void add(Node current, Node toAdd)
    {
        current.setChild(toAdd);
    }

    /**
     * Simple traverse function, currently set up only for a linked list
     * @param current
     */
    public void traverse(Node current)
    {
        if(current.getChildNodes().size()==0)
            return;
        else{

            traverse(current.getChildNodes().get(0));
            if(current.getPerson()!=null)
                System.out.println(current.getPerson().getName());
        }
    }
}
