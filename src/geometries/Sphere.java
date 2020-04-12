package geometries;

import primitives.Point3D;
import primitives.Vector;


/**
 * The Sphere class is in the geometric shapes.
 * It's a round ball.
 * It contains a center point and a radius.
 */

public class Sphere extends RadialGeometry {
Point3D center;

    public Sphere(double radius1, Point3D c1) {
        super(radius1);
        center=c1;
    }

    public Point3D getCenter() {
        return center;
    }


    @Override
    public Vector getNormal(Point3D P) {
        Vector normal = P.subtract(center);
        return normal.normalize();
    }




    /**
     * prints all the fields in the Sphere
     * @return String
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
