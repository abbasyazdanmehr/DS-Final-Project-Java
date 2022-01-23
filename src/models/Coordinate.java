package models;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int distancePower2(Coordinate coordinate1, Coordinate coordinate2) {
        return ((coordinate1.x - coordinate2.x) * (coordinate1.x - coordinate2.x))
                + ((coordinate1.y - coordinate2.y) * (coordinate1.y - coordinate2.y));
    }

    public static int perpendicularDistanceByX(Coordinate target, Coordinate coordinate) {
        return abs(coordinate.x - target.x);
    }

    public static int perpendicularDistanceByY(Coordinate target, Coordinate coordinate) {
        return abs(coordinate.y - target.y);
    }

    public static int abs(int n) {
        if (n > 0) return n;
        else return -n;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
