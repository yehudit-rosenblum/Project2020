package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

/**
 *  The plane class is in the geometric shapes.
 *  It is a surface. It contains a vector and a point.
 *  From this data you can complete the plane.
 */

public class Plane implements Geometry {

    Point3D p;
    Vector normal;

    public Plane(Point3D a, Point3D b, Point3D c){
        p=a;
        normal=null;
    }
    public Plane(Point3D p1, Vector v){
        p=p1;
        normal=v;
    }


    public Point3D getP() {
        return p;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D P) {
        return null;
    }


    /**
     * prints the fields in the Plane.
     * @return String
     */
    @Override
    public String toString() {
        return "Plane{" +
                "p=" + p +
                ", normal=" + normal +
                '}';
    }
}

