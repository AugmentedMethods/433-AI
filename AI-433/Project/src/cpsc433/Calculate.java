package cpsc433;

/**
 *
 */
public class Calculate {

    /**
     * The basic entry function to preform a calculation
     * @param node
     * @return true if a valid node, false otherwise.
     */
    public Boolean calculation(Node node)
    {
         if(hardConstrainCheck(node))
            return false;
        return false;
    }

    private boolean hardConstrainCheck(Node node)
    {
        if(node.getPerson().isHeadsGroup()||node.getPerson().isHeadsProject() || node.getPerson().isManager())
        {
            if(node.getRoom().oneOccupant())
                return false;
        }
        return true;

    }

    private void groupHeadLargeOffice(Node node)
    {
        if(node.getRoom().isLarge())
            return;
        node.setGoodnessValue(node.getGoodnessValue() -40);
    }

    private void closeToSecretary(Node node)
    {
        for( Rooms r : node.getRoom().getClose())
        {
            if(r.getPersonOne()!= null)
            {
                if(r.getPersonOne().isSecretary())
                    return;
            }
            if(r.getPersonTwo()!=null)
            {
                if(r.getPersonTwo().isSecretary())
                    return;
            }
        }
        node.setGoodnessValue(node.getGoodnessValue() -30);
    }

    private void managerCloseGroupHead(Node node)
    {
        for( Rooms r : node.getRoom().getClose())
        {
            if(r.getPersonOne()!= null)
            {
                if(r.getPersonOne().isHeadsGroup())
                    return;
            }
            if(r.getPersonTwo()!=null)
            {
                if(r.getPersonTwo().isHeadsGroup())
                    return;
            }
        }
        node.setGoodnessValue(node.getGoodnessValue() -20);
    }

    private void smoker(Node node)
    {
        //xnor
        boolean temp = !((node.getRoom().getPersonOne().isSmoker()) ^  (node.getRoom().getPersonTwo().isSmoker()) );
        if(temp)
            return;
        node.setGoodnessValue(node.getGoodnessValue() -50);

    }
}

