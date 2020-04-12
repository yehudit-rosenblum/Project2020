package geometries;

import primitives.*;

/**
 *  The plane class is in the geometric shapes.
 *  It is a surface (mishtach). It contains a normal to the plane and points.
 *  From this data you can complete the plane.
 */

public class Plane implements Geometry {

    Point3D p;
    Vector normal;

    public Plane(Point3D a, Point3D b, Point3D c){
        p=a;
        Vector v1=new Vector(a.subtract(b));
        Vector v2=new Vector(b.subtract(c));
        normal=v1.crossProduct(v2);
    }
    public Plane(Point3D a, Vector c){
        p=a;
        normal=c;
    }


    public Point3D getP1() {
        return p;
    }

    public Vector getNormal(){
        return normal;
    }

    @Override
    public Vector getNormal(Point3D P) {
        return normal;
    }


    /**
     * prints the fields in the Plane.
     *
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

