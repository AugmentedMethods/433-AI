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
    Stack<Node> finalSolution;
    int solutionGoodness = - 5000;

    public Tree ()
    {
        head = new Node();
        solution = new Stack<Node>();
        finalSolution = new Stack<Node>();
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

    /**
     * This will traveres each branch, the stack will indicate a branch,
     * @param current
     * @param solutionSize
     */
    public void traverse(Node current, int solutionSize)
    {
        if(current.getChildNodes().size()==0)
        {
            if(solution.size() == solutionSize) {
               // printStack();
                if(solutionGoodness < solution.get(solution.size()-2).getTotalGoodnessValue()){
                    for(Node n : solution)
                        finalSolution.push(n);
                    solutionGoodness = solution.lastElement().getTotalGoodnessValue();
                }
            }
            //System.out.println("END Recursion, Stack size: "+ solution.size() +" Needs to be" + solutionSize);
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

    public void printStack()
    {
        //System.out.println(solution.size());
        for(Node n : finalSolution){

            if(n.getRoom()!= null)
                System.out.println("Name: "+ n.getPerson().getName() + " Room: "+ n.getRoom().getRoomNumber());
        }
        System.out.println(finalSolution.get(finalSolution.size()-2).getTotalGoodnessValue());
    }
}
