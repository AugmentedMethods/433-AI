package cpsc433;

import java.util.ArrayList;

/**
 *
 */
public class Tree {

    private Node Parent;
    private ArrayList<Node> child;

    public Tree ()
    {
        Parent = null;
        child = new ArrayList<Node>();
    }

    public Node getParent() {
        return Parent;
    }

    public void setParent(Node parent) {
        Parent = parent;
    }

    public ArrayList<Node> getChildNodes() {
        return child;
    }

    public void setChild(Node child) {
        this.child.add(child);
    }

}
