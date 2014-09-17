public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {
        x = 0;
        y = 0;
    }

    public Coordinate(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public void setXY(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public String toString() {
        String coords = "("+Integer.toString(x)+", "+Integer.toString(y)+")";
        System.out.println(coords);
        
        return coords;
    }
}
