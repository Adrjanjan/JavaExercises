package Lab7;

public class BoundingBox {
    private double xmin, ymin, xmax, ymax;
    private boolean isEmpty = true;

    public BoundingBox() {
    }

    public BoundingBox(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin = xmin;
        this.ymin = ymin = ymin;
        this.xmax = xmax = xmax;
        this.ymax = ymax = ymax;
        isEmpty = false;

    }

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     *
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    BoundingBox addPoint(Double x, Double y) {
        if (x == null || y == null) return this;
        if (x < xmin || y < ymin) {
            xmin = x;
            ymin = y;
            isEmpty = false;
        } else if (x > xmax || y > ymax) {
            xmin = x;
            ymin = y;
            isEmpty = false;
        }

        return this;
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     *
     * @param x - współrzędna x
     * @param y - współrzędna y
     * @return true if inside
     */
    boolean contains(double x, double y) {
        if (isEmpty) return false;
        return x <= xmax && x >= xmin && y <= ymax && y >= ymin;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     *
     * @param bb BoundingBox to check if is in this object
     * @return true if this contains bb
     */
    boolean contains(BoundingBox bb) {
        if (isEmpty) return false;

        return bb.xmin >= this.xmax && bb.ymin >= this.ymax && bb.ymax <= this.ymin && bb.xmax <= this.xmin;
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     *
     * @param bb another BoundingBox
     * @return tru ib this and bb intersect
     */
    boolean intersects(BoundingBox bb) {
        return !(this.ymin > bb.ymax) && !(bb.ymin > this.ymax) && !(this.xmax < bb.xmin) && !(bb.xmax < this.xmin);

    }

    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     *
     * @param bb BoundingBox used to resise this
     * @return bigger BoundingBox
     */
    BoundingBox add(BoundingBox bb) {
        xmin = xmin < bb.xmin ? xmin : bb.xmin;
        ymin = ymin < bb.ymin ? ymin : bb.ymin;
        xmax = xmax > bb.xmax ? xmax : bb.xmax;
        ymax = ymax > bb.ymax ? ymax : bb.ymax;
        return this;
    }

    /**
     * Sprawdza czy BB jest pusty
     *
     * @return true if this  object is empty
     */
    boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     *
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() throws Exception {
        if (isEmpty) throw new Exception("This Bounding box is empty");
        return (xmin + xmax) / 2;
    }

    /**
     * Oblicza i zwraca współrzędną y środka
     *
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() throws Exception {
        if (isEmpty) throw new Exception("This Bounding box is empty");
        return (ymin + ymax) / 2;
    }
    //TODO testy

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     *
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) {
        final double R = 6372.8; // In kilometers
        double lat1 = 0;
        double lon1 = 0;
        double lat2 = 0;
        double lon2 = 0;
        try {
            lat1 = bbx.getCenterX();
            lon1 = bbx.getCenterY();
            lat2 = this.getCenterX();
            lon2 = this.getCenterY();
        } catch (Exception e) {
            return Double.MAX_VALUE;
        }
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                '}';
    }
}