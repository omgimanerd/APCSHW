public class demo {
    public static void main(String[] arg) {
        Coordinate xy = new Coordinate(10, 10);
        
        xy.toString();
        xy.setXY(11, 100);
        xy.toString();
        xy.setX(101);
        xy.toString();
    }
}
