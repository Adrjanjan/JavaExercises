package Lab7;

public class BoundingBox {
    private Point p1, p2, p3, p4;

    BoundingBox(Double x1, Double y1, Double x2, Double y2, Double x3, Double y3, Double x4, Double y4) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
        this.p3 = new Point(x3, y3);
        this.p4 = new Point(x4, y4);
    }

//    public void addPoint(Double x, Double y) {
//
//    }
//
//    public boolean intersects(BoundingBox other) {
//
//
//    }
//
//    public boolean contains(BoundingBox other) {
//
//
//    }


    @Override
    public String toString() {
        return "(" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", p4=" + p4 +
                ')';
    }

//    Double area() {
//        return (p1.x*p2.y-p2.x*p1.y) + (p2.x*p3.y - p3.x*p2.y) + (p3.x*p4.y - p4.x*p3.y) + (p4.x*p1.y - p1.x*p4.y);
//    }

    class Point {
        Double x, y;

        Point(Double x, Double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }
}
