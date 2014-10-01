public class Coordinate {
    private int x_;
    private int y_;

    public Coordinate() {
        this.x_ = 0;
        this.y_ = 0;
    }

    public Coordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return this.x_;
    }

    public int getY() {
        return this.y_;
    }

    public void setX(int x) {
        this.x_ = x;
    }

    public void setY(int y) {
        this.y_ = y;
    }

    public void setXY(int x, int y) {
        setX(x);
        setY(y);
    }

    public String toString() {
        String coords = "("+this.x_+", "+this.y_+")";
        System.out.println(coords);
        
        return coords;
    }
}
