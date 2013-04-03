package cpsc433;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Sean
 * Date: 4/2/13
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomSort {

    ArrayList<Rooms> smallRooms = new ArrayList<Rooms>();
    ArrayList<Rooms> mediumRooms = new ArrayList<Rooms>();
    ArrayList<Rooms> largeRooms = new ArrayList<Rooms>();

    public void roomSort(ArrayList<Rooms> roomList)
    {
        for (Rooms r : roomList)
        {
            if(r.isSmall())
                smallRooms.add(r);
            else if(r.isMedium())
                mediumRooms.add(r);
            else if(r.isLarge())
                largeRooms.add(r);
        }
    }

    public ArrayList<Rooms> getSmallRooms() {
        return smallRooms;
    }

    public ArrayList<Rooms> getMediumRooms() {
        return mediumRooms;
    }

    public ArrayList<Rooms> getLargeRooms() {
        return largeRooms;
    }

}
