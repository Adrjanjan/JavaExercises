package Lab7;

public class BoundingBox {
    double xmin, ymin, xmax, ymax;
    boolean isEmpty = true;

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
    BoundingBox addPoint(double x, double y) {
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
    //TODO haversine + testy

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     *
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) {
        throw new RuntimeException("Not implemented");
    }

}