package Lab7;

public class BoundingBox {
    Point p1, p2, p3, p4;

    public BoundingBox(Double x1, Double y1, Double x2, Double y2, Double x3, Double y3, Double x4, Double y4,) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
        this.p3 = new Point(x3, y3);
        this.p4 = new Point(x4, y4);
    }

    public void addPoint(Double x, Double y) {

    }

    public boolean intersects(BoundingBox other) {


    }

    public boolean contains(BoundingBox other) {


    }

    class Point {
        Double x, y;

        public Point(Double x, Double y) {
            this.x = x;
            this.y = y;
        }
    }


}
