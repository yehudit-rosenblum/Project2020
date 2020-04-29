package geometries;
import primitives.*;
import java.util.List;
import static primitives.Util.*;


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

    /**
     * center getter
     * @return the center.
     */
    public Point3D getCenter() {
        return center;
    }


    @Override
    public Vector getNormal(Point3D P) {
        Vector normal = P.subtract(center);
        return normal.normalize();
    }


    /**
     * This function gets a ray and then checks if the ray touches this particular sphere.
     * @param ray
     * @return a list which contains or a null or a point or two points if the ray touched.
     */

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPo();
        Vector v = ray.getDir();
        Vector u;
        try {
            /**
             * u=vector dir from the beginning of the ray to the center.
             * By douing center minus po.
             * If the ray started in the center of the sphere,
             * the function Subtract will return a new Vector that is Zero,
             * and will throw a illegal message
             * The intersection Point will be the center plus the length of the radius.
             */
            u = center.subtract(p0);
        }
        catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(radius));
        }

        /**
        * dot product with vector u(beginning of ray to the center) and v dir of the ray.
        */
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(radius * radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
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
