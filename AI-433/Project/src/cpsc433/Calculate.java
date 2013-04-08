package cpsc433;

/**
 *
 */
public class Calculate {

    /**
     * The basic entry function to perform a calculation
     * @param node
     * @return true if a valid node, false otherwise.
     */
    public Boolean calculation(Node node)
    {
        if(hardConstrainCheck(node))
            return true;
        return false;
    }

    public void update(Node node)
    {
        if (calculation(node))
        {
            groupHeadLargeOffice(node);
            closeToSecretary(node);
            managerCloseGroupHead(node);
            smoker(node);
            managerCloseSecretary(node);
            headsLargeProjectsCloseSecretary(node);
            headsLargeProjectsCloseHeadGroup(node);
            sameProjectNotShareRoom(node);
            wantOwnRoom(node);
            sharingShouldWorkTogether(node);
            dontShareSmallRoom(node);
        }
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

    private void groupHeadLargeOffice(Node node) // SC1
    {
        if(node.getRoom().isLarge())
            return;
        node.setGoodnessValue(node.getGoodnessValue() -40);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue() - 40);
    }

    private void closeToSecretary(Node node) // SC3
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
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-30);
    }

    private void managerCloseGroupHead(Node node) //SC6
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
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-20);
    }

    private void smoker(Node node) // SC11
    {
        //xnor
        boolean temp = (!((node.getRoom().getPersonOne().isSmoker()) ^  (node.getRoom().getPersonTwo().isSmoker())) );
        if(temp)
            return;
        node.setGoodnessValue(node.getGoodnessValue() -50);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue() - 50);

    }

    private void managerCloseSecretary(Node node) // SC5
    {
        for( Rooms r : node.getRoom().getClose())
        {
            if(r.getPersonOne() != null)
            {
                if(r.getPersonOne().isSecretary())
                    return;
            }
            if(r.getPersonTwo() != null)
            {
                if(r.getPersonTwo().isSecretary())
                    return;
            }
        }
        node.setGoodnessValue(node.getGoodnessValue() -20);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-20);
    }

    private void headsLargeProjectsCloseSecretary(Node node) // SC9
    {
        if(node.getRoom().getPersonOne().isHeadsProject() || node.getRoom().getPersonTwo().isHeadsProject())
        {
            for(Rooms r : node.getRoom().getClose())
            {
                if(r.getPersonOne() != null)
                {
                    if(r.getPersonOne().isSecretary())
                    {
                        if(node.getRoom().getPersonOne().getProject() == r.getPersonOne().getProject() ||
                                node.getRoom().getPersonTwo().getProject() == r.getPersonOne().getProject())
                            return;
                    }
                }
                if(r.getPersonTwo() != null)
                {
                    if(r.getPersonTwo().isSecretary())
                    {
                        if(node.getRoom().getPersonOne().getProject() == r.getPersonOne().getProject() ||
                                node.getRoom().getPersonTwo().getProject() == r.getPersonTwo().getProject())
                            return;
                    }
                }
            }
        }
        node.setGoodnessValue(node.getGoodnessValue() -10);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-10);
    }

    private void headsLargeProjectsCloseHeadGroup(Node node) // SC10
    {
        if(node.getRoom().getPersonOne().isHeadsProject() || node.getRoom().getPersonTwo().isHeadsProject())
        {
            for(Rooms r : node.getRoom().getClose())
            {
                if(r.getPersonOne() != null)
                {
                    if(r.getPersonOne().isHeadsGroup())
                    {
                        if(node.getRoom().getPersonOne().getGroup() == r.getPersonOne().getGroup() ||
                                node.getRoom().getPersonTwo().getGroup() == r.getPersonOne().getGroup())
                            return;
                    }
                }
                if(r.getPersonTwo() != null)
                {
                    if(r.getPersonTwo().isHeadsGroup())
                    {
                        if(node.getRoom().getPersonOne().getGroup() == r.getPersonOne().getGroup() ||
                                node.getRoom().getPersonTwo().getGroup() == r.getPersonTwo().getGroup())
                            return;
                    }
                }
            }
        }
        node.setGoodnessValue(node.getGoodnessValue() -10);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-10);
    }

    private void sameProjectNotShareRoom(Node node) // SC12
    {
        if(node.getRoom().getPersonOne() == null)
            return;
        if(node.getRoom().getPersonTwo() == null)
            return;
        if(!(node.getRoom().getPersonOne().getProject() == node.getRoom().getPersonTwo().getProject()))
            return;
        node.setGoodnessValue(node.getGoodnessValue() -7);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-7);
    }

    private void SC13(Node node) //SC13 - Not sure what's going on here...
    {

    }

    private void wantOwnRoom(Node node) //SC14
    {
        if(node.getRoom().getPersonOne() == null)
            return;
        else if(node.getRoom().getPersonTwo() == null)
            return;
        else {
            node.setGoodnessValue(node.getGoodnessValue() -4);
            node.setTotalGoodnessValue(node.getTotalGoodnessValue()-4);
        }
    }

    private void sharingShouldWorkTogether(Node node) // SC15
    {
        if(node.getRoom().getPersonOne() == null)
            return;
        if(node.getRoom().getPersonTwo() == null)
            return;
        if(!(node.getRoom().getPersonOne().getWorksWith().contains(node.getRoom().getPersonTwo()))) // Not sure about logic
            return;
        node.setGoodnessValue(node.getGoodnessValue() -3);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-3);
    }

    private void dontShareSmallRoom(Node node) // SC16
    {
        if(node.getRoom().isSmall())
        {
            if(node.getRoom().getPersonOne() == null)
                return;
            if(node.getRoom().getPersonTwo() == null)
                return;
        }
        node.setGoodnessValue(node.getGoodnessValue() -25);
        node.setTotalGoodnessValue(node.getTotalGoodnessValue()-25);
    }
}
