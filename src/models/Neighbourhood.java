package models;

public class Neighbourhood {
    private final String name;
    private final Coordinate leftUp;
    private final Coordinate leftDown;
    private final Coordinate rightDown;
    private final Coordinate rightUp;

    public Neighbourhood(String name, Coordinate leftUp, Coordinate leftDown, Coordinate rightDown, Coordinate rightUp) {
        this.name = name;
        this.leftUp = leftUp;
        this.leftDown = leftDown;
        this.rightDown = rightDown;
        this.rightUp = rightUp;
    }

    public String getName() {
        return name;
    }

    public Coordinate getLeftUp() {
        return leftUp;
    }

    public Coordinate getLeftDown() {
        return leftDown;
    }

    public Coordinate getRightDown() {
        return rightDown;
    }

    public Coordinate getRightUp() {
        return rightUp;
    }

    @Override
    public String toString() {
        return "Neighbourhood{" +
                "name='" + name + '\'' +
                ", leftUp=" + leftUp +
                ", leftDown=" + leftDown +
                ", rightDown=" + rightDown +
                ", rightUp=" + rightUp +
                '}';
    }
}
