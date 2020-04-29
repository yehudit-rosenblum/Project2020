package geometries;
import primitives.*;
import java.util.List;
import static primitives.Util.*;

/**
 * This Triangle class is in the geometric shapes.
 * It inheritance from Polygon.
 * It contains a infinite plane and a List _vertices (Which will have three points).
 */
public class Triangle extends Polygon {


    /**
     * Constructor for creating a triangle from three points.
     * @param a point
     * @param b point
     * @param c point
     */
    public Triangle(Point3D a, Point3D b, Point3D c) {
        super(a,b,c);
    }



    /**
     * This function gets a ray and checks if the ray touches this particular triangle.
     * @param ray
     * @return a list which contains or a null or a point if the ray touched the triangle.
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        /**
         * First will check if the ray touches the infinite plane.
         */
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;


        Point3D p0 = ray.getPo();
        Vector v = ray.getDir();

        /**
         * v1= First point in the Triangle mines the starting point of the ray
         * v2= Second point in the Triangle mines the starting point of the ray
         * v3= Third point in the Triangle mines the starting point of the ray
         */
        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

       /**
         * s1/2/3=dot product with the dir of ray and the normal to the two vectors.
         */
        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;

    }
}