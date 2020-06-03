package geometries;
import primitives.*;
import java.util.List;
import static primitives.Util.*;

/**The Plane class is in the geometric shapes.
 * It is a surface (mishtach) which is infinite.
 * It contains a normal to the plane and a point in the plane.*/
public class Plane extends Geometry {
    Point3D p;
    Vector normal;


    /**Constructor that gets a color material and three points
     * @param c color
     * @param m material
     * @param p1
     * @param p2
     * @param p3
     * From thews points we calculate the 2 vectors on the plane.
     * And then we do crossProduct and we knew the normal to the plane.*/
    public Plane(Color c, Material m, Point3D p1, Point3D p2, Point3D p3) {
        super(c, m);
        p = p1;
        Vector v1 = new Vector(p1.subtract(p2));
        Vector v2 = new Vector(p2.subtract(p3));
        normal = v1.crossProduct(v2);
        normal.normalize();
    }

    /**Constructor that gets a color and three points
     * and puts a default material.
     * @param c color
     * @param p1
     * @param p2
     * @param p3
     * From thews points we calculate the 2 vectors on the plane.
     * And then we do crossProduct and we knew the normal to the plane.*/
    public Plane(Color c, Point3D p1, Point3D p2, Point3D p3) {
        this(c, new Material(0, 0, 0), p1, p2, p3);
    }


    /** Constructor for creating a plane from three points.
     * And puts a default color black and a default material.
     * @param a for the p point
     * @param b
     * @param c
     * From thews points we calculate the 2 vectors on the plane.
     * And then we do crossProduct and we knew the normal to the plane.*/
    public Plane(Point3D a, Point3D b, Point3D c) {
        this(Color.BLACK, new Material(0, 0, 0), a,b,c);
    }

    /** Constructor for creating a Plane from a point and a normal to the plane.
     * and gets a color and a material.
     * @param a for the p point.
     * @param n for the normal.*/
    public Plane(Color c, Material m, Point3D a, Vector n) {
        super(c, m);
        p = a;
        normal = n.normalized();
    }

    /** Constructor for creating a Plane from a point and a normal to the plane.
     * and also gets the color. And puts a default material.
     * @param a for the p point.
     * @param n for the normal.*/
    public Plane(Color c, Point3D a, Vector n) {
        this(c, new Material(0, 0, 0), a, n);
    }

    /** Constructor for creating a Plane from a point and a normal to the plane.
     * And puts a default color black and a default color material.
     * @param a for the p point.
     * @param n for the normal.*/
    public Plane(Point3D a, Vector n) {
        this(Color.BLACK, new Material(0, 0, 0), a, n);
    }



    /** p getter
     * @return the point that is in the plane.*/
    public Point3D getP1() {
        return p;
    }


    /**normal getter
     * @return the normal to the plane.*/
    public Vector getNormal() {
        return normal;
    }


    /**The function that returns the normal to the shape according to the point.
     * In this case there is no meaning to which point will get.
     * @param P point
     * @return the normal to the plane.*/
    @Override
    public Vector getNormal(Point3D P) {
        return normal;
    }


    /**This function gets a ray.
     * And then checks if the ray touches this particular plane.
     * @param ray
     * @return a list which contains or a null or a point if the ray touched the plane.*/
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
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
        if (t<=0){
            return null;}

        GeoPoint geo = new GeoPoint(this, ray.getTargetPoint(t));
        return List.of(geo);

    }

    /**prints the fields in the Plane.
     * @return String*/
    @Override
    public String toString () {
        return "Plane{" +
                "p=" + p +
                ", normal=" + normal +
                '}';
    }
}

