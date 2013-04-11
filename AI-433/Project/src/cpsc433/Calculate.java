package cpsc433;

/**
 *
 */
public class Calculate {

    /**
     *
     * ---------------------------------------
     * Everything commented out fails
     * ---------------------------------------
     *
     * Main method to call to get the goodness value
     * return 1 for success
     * return 0 for bad node
     * return -1 for bad tree(total goodness to high
     * -2 for failure
     * @param node
     */
    public int update(Node node)
    {
        groupHeadLargeOffice(node);
        closeToSecretary(node);
        managerCloseGroupHead(node);

        if(node.getRoom().getPersonOne()!= null && node.getRoom().getPersonTwo() != null)  {
            smoker(node);
            headsLargeProjectsCloseSecretary(node);
            headsLargeProjectsCloseHeadGroup(node);
        }
        managerCloseSecretary(node);
        sameProjectNotShareRoom(node);
        wantOwnRoom(node);
        sharingShouldWorkTogether(node);
        dontShareSmallRoom(node);

        if(!calculation(node)|| node.getGoodnessValue() < -200)
            return 0;
        if(node.getParent().getTotalGoodnessValue() < -3000)     //should be else if
            return -1;
        else if(calculation(node)&& node.getGoodnessValue() > -200 &&node.getTotalGoodnessValue() > -3000 )
            return 1;
        return 1;
    }

    /**
     * Calculated the hard constraint
     * @param node
     * @return true if a valid node, false otherwise.
     */
    public Boolean calculation(Node node)
    {
        if(hardConstrainCheck(node))
            return true;
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

    private void groupHeadLargeOffice(Node node) // SC1
    {
        if(node.getRoom().isLarge())
            return;
        if(!node.getPerson().isHeadsGroup())
            return;
        //System.out.println("SC1!");
        node.setGoodnessValue(node.getGoodnessValue() -40);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue() - 40 + node.getGoodnessValue());
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
        //System.out.println("SC3!");
        node.setGoodnessValue(node.getGoodnessValue() -30);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-30 + node.getGoodnessValue());
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
        //System.out.println("SC6!");
        node.setGoodnessValue(node.getGoodnessValue() -20);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-20 + node.getGoodnessValue());
    }

    private void smoker(Node node) // SC11
    {
        //xnor
        boolean temp = (!((node.getRoom().getPersonOne().isSmoker()) ^  (node.getRoom().getPersonTwo().isSmoker())) );
        if(temp)
            return;
        //System.out.println("SC11!");
        node.setGoodnessValue(node.getGoodnessValue() -50);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue() - 50 + node.getGoodnessValue());
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
        //System.out.println("SC5!");
        node.setGoodnessValue(node.getGoodnessValue() -20);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-20 + node.getGoodnessValue());
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
        //System.out.println("SC9!");
        node.setGoodnessValue(node.getGoodnessValue() -10);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-10 + node.getGoodnessValue());
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
        //System.out.println("SC10!");
        node.setGoodnessValue(node.getGoodnessValue() -10);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-10 + node.getGoodnessValue());
    }

    private void sameProjectNotShareRoom(Node node) // SC12
    {
        if(node.getRoom().getPersonOne() == null)
            return;
        if(node.getRoom().getPersonTwo() == null)
            return;
        if(!(node.getRoom().getPersonOne().getProject() == node.getRoom().getPersonTwo().getProject()))
            return;
        //System.out.println("SC12!");
        node.setGoodnessValue(node.getGoodnessValue() -7);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-7 + node.getGoodnessValue());
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
        	//System.out.println("SC14!");
            node.setGoodnessValue(node.getGoodnessValue() -4);
            node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-4 + node.getGoodnessValue());
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
        //System.out.println("SC15!");
        node.setGoodnessValue(node.getGoodnessValue() -3);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-3 + node.getGoodnessValue());
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
        //System.out.println("SC16!");
        node.setGoodnessValue(node.getGoodnessValue() -25);
        node.setTotalGoodnessValue(node.getParent().getTotalGoodnessValue()-25 + node.getGoodnessValue());
    }
}
