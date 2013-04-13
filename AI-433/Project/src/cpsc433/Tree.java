package cpsc433;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 */
public class Tree {
    //this is the root node, it will be empty
    Node head;
    int counting = 0;
    Stack<Node> solution;
    Calculate genCalcObj = new Calculate();

    public Tree ()
    {
        head = new Node();
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

    public ArrayList<Node> getChildren(Node current)
    {
        return current.getChildNodes();
    }


    public void traverse(Node current, int solutionSize)
    {
        if(current.getChildNodes().size()==0)
        {
            if(solution.size() == solutionSize)
                printStack();

            System.out.println("END Recursion, Stack size: "+ solution.size() +"Needs to be" + solutionSize);
            solution.pop();
            return;
        }

        for(Node n : current.getChildNodes() )
        {
            solution.push(n);
            traverse(n, solutionSize);
        }
        if(solution.size()>0)
            solution.pop();
}

    private void printStack()
    {
        //System.out.println(solution.size());
        for(Node n : solution)
            System.out.println("Name: "+n.getPerson().getName() + ", Room: " + n.getRoom().getRoomNumber());
        System.out.println(solution.lastElement().getTotalGoodnessValue());
    }
}
