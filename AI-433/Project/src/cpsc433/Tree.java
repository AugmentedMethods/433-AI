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
        int checkVal;

        if(current.getChildNodes().size() == 0)
        {
            //if(solution.size() == solutionSize)
                printStack();
            System.out.println("END Recursion");
            solution.pop();
            return;
        }

        for(Node n : current.getChildNodes() )
        {
            checkVal = genCalcObj.update(n);
            if(checkVal == 1) {
                solution.push(n);
                traverse(n, solutionSize);
            }
        }
}

    private void printStack()
    {
        //System.out.println(solution.size());
        for(Node n : solution)
            System.out.println(n.getPerson().getName());
    }
}
