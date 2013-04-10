package cpsc433;

import java.util.ArrayList;

/**
 *
 */
public class Tree {
    //this is the root node, it will be empty
    Node head = new Node();
    int counting = 0;
    
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
    /*public void traverse(Node current)
    {
        if(current.getChildNodes().size()==0)
        {
            return;
        }
            
        else{;
        	int sizeGet = current.getChildNodes().size();
            traverse(current.getChildNodes().get(0));
            if(current.getPerson()!=null){// &&current.getRoom() != null){
                System.out.println(current.getPerson().getName());
                //System.out.println(current.getRoom().getRoomNumber());
                System.out.println(current.getGoodnessValue()+"\n");
            }
        }
    }*/
    
    public void traverse(Node current)
    {
    	Calculate calculate = new Calculate();
        int checkVal;
        if(current.getChildNodes().size()==0)
            return;
        else
        {
        	
        	for(int i = 0; i < current.getChildNodes().size(); i++)
        	{
        		System.out.println(current.getChildNodes().get(i).getPerson().getName());
        		System.out.println(current.getChildNodes().get(i).getRoom().getRoomNumber());
//        		System.out.println(calculate.update(current.getChildNodes().get(i)));
//        		System.out.println("n " + current.getChildNodes().get(i).getGoodnessValue() + " t " + current.getChildNodes().get(i).getTotalGoodnessValue());
        	}
            for(int i = 0; i < current.getChildNodes().size(); i++)
            {
                traverse(current.getChildNodes().get(i));
            }
        }
    }
}
