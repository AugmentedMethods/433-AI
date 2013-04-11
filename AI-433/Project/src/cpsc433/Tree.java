package cpsc433;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 */
public class Tree {
    //this is the root node, it will be empty
    Node head = new Node();
    int counting = 0;
    Stack<Node> solution;
    Calculate genCalcObj = new Calculate();

    public Tree ()
    {
        solution = new Stack<Node>();
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
    
    public void traverse(Node current, int solutionSize)
    {
        Calculate calculate = new Calculate();
        int checkVal;
        if(current.getChildNodes().size()==0) {
            if(solution.size() == solutionSize)
                printStack();
            solution.pop();
            return;
        }
        else
        {
            for(int i = 0; i < current.getChildNodes().size(); i++)
            {

                checkVal = genCalcObj.update(current.getChildNodes().get(i));
                current.setVisited(i);
                solution.push(current.getChildNodes().get(i));
                if (current.getVisited() == -1 && checkVal == 1) {
                    traverse(current.getChildNodes().get(i), solutionSize);
                }
            }
            printStack();

        }
    }

    private void printStack()
    {
        for(Node n : solution)
            System.out.println(n.getPerson().getName());
    }
}
