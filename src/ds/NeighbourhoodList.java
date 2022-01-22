package ds;

import controllers.CommandControllers;
import models.Neighbourhood;

public class NeighbourhoodList {
    private Neighbourhood[] neighbourhoods;
    private int arrayLength;
    private int index;

    public NeighbourhoodList() {
        arrayLength = 10;
        neighbourhoods = new Neighbourhood[arrayLength];
        index = 0;
    }

    public void add(Neighbourhood neighbourhood) {
        if (arrayLength == index) increaseArrayLength();

        neighbourhoods[index++] = neighbourhood;
    }

    public void increaseArrayLength() {
        Neighbourhood[] newNeighbourhoods = new Neighbourhood[2 * arrayLength];
        System.arraycopy(neighbourhoods, 0, newNeighbourhoods, 0, arrayLength);
        arrayLength = 2 * arrayLength;
        neighbourhoods = newNeighbourhoods;
    }

    @Override
    public String toString() {
        String printing = "";
        for (Neighbourhood n :
                neighbourhoods) {
            printing += n.toString() + ", ";
        }
        return printing;
    }
}
