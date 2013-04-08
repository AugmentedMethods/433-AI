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
        Calculate calc = new Calculate();
                        
        if(current.getChildNodes().size()==0)
            return;
        else{
            traverse(current.getChildNodes().get(0));
            if(current.getPerson()!=null &&current.getRoom() != null){
                

                
                calc.groupHeadLargeOffice(current);
                calc.closeToSecretary(current);
                calc.managerCloseGroupHead(current);
                calc.smoker(current);
                calc.managerCloseSecretary(current);
                calc.headsLargeProjectsCloseSecretary(current);
                calc.headsLargeProjectsCloseHeadGroup(current);
                calc.sameProjectNotShareRoom(current);
                calc.wantOwnRoom(current);
                calc.sharingShouldWorkTogether(current);
                calc.dontShareSmallRoom(current);
                
                System.out.println(current.getPerson().getName());
                System.out.println(current.getRoom().getRoomNumber());
                System.out.println(current.getGoodnessValue());
            }
        }
    }
}
