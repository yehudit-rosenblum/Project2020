package geometries;
import primitives.*;
import java.util.List;
import static primitives.Util.*;

/**
 * The Plane class is in the geometric shapes.
 * It is a surface (mishtach) which is infinite.
 * It contains a normal to the plane and a point in the plane.
 */
public class Plane implements Geometry {
    Point3D p;
    Vector normal;


    /**
     * Constructor for creating a plane from three points.
     * @param a for the p point
     * @param b
     * @param c
     * From thows points we calculate the 2 vectors on the plane.
     * And then we do crossProduct and we knew the normal to the plane.
     */
    public Plane(Point3D a, Point3D b, Point3D c) {
        p = a;
        Vector v1 = new Vector(a.subtract(b));
        Vector v2 = new Vector(b.subtract(c));
        normal = v1.crossProduct(v2);
        normal.normalize();
    }

    /**
     * Constructor for creating a Plane from a point and a normal to the plane.
     * @param a for the p point.
     * @param n for the normal.
     */
    public Plane(Point3D a, Vector n) {
        p = a;
        normal = n.normalized();
    }


    /**
     * p getter
     * @return the point that is in the plane.
     */
    public Point3D getP1() {
        return p;
    }


    /**
     * normal getter
     * @return the normal to the plane.
     */
    public Vector getNormal() {
        return normal;
    }


    /**
     * The function that returns the normal to the shape according to the point.
     * In this case there is no meaning to which point will get.
     * @param P point
     * @return the normal to the plane.
     */
    @Override
    public Vector getNormal(Point3D P) {
        return normal;
    }


    /**
     * This function gets a ray.
     * And then checks if the ray touches this particular plane.
     * @param ray
     * @return a list which contains or a null or a point if the ray touched the plane.
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector temp;
        try
        {
            /**
             * temp=Vector expresses the dir from the beginning of the ray to the point in the plane.
             * We do, point in the plane mines point beginning of the ray(po).
             * If the ray starts inside the plane then its not considered a intersection point.
             * And The function Subtract will return a new Vector that is Zero.
             * (because its the same point) And will throw a illegal message
             */
            temp = p.subtract(ray.getPo());
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }

        /**
         * d= dotProduct with the normal to the plane and the and of the ray(dir).
         * if d=0 so its Parallel.
         * */
        double d=normal.dotProduct(ray.getDir());
        if (d==0)
            return null;

        /**
         * t=dotProduct with the normal and the temp,
         *(Vector that expresses the dir form the beginning of the ray to the point in the plane)
         * divided with dotProduct with the normal and the and of the ray(dir).
         */
        double t = alignZero(normal.dotProduct(temp) / d);

        /**
         * The starting Point of the ray plus the t is the intersection point.
         * List.of=puts the number in a List.
         */
        return t <= 0 ? null : List.of(ray.getTargetPoint(t));

    }

        /**
         * prints the fields in the Plane.
         *
         * @return String
         */
        @Override
        public String toString () {
            return "Plane{" +
                    "p=" + p +
                    ", normal=" + normal +
                    '}';
        }
    }

