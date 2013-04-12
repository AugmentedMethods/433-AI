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
        int checkVal;
        System.out.println("Here");
        System.out.println(current.getChildNodes().get(1).getChildNodes().size());
        System.out.println("!Here");
//
//        if(current.getChildNodes().size() == 0)
//        {
//            //if(solution.size() == solutionSize)
//                printStack();
//            System.out.println("END Recursion");
//            solution.pop();
//            return;
//        }
//
//        for(Node n : current.getChildNodes() )
//        {
//            //checkVal = genCalcObj.update(n);
//            //if(checkVal == 1) {
//            solution.push(n);
//            traverse(n, solutionSize);
//            //}
//        }
}

    private void printStack()
    {
        //System.out.println(solution.size());
        for(Node n : solution)
            System.out.println(n.getPerson().getName());
    }
}
