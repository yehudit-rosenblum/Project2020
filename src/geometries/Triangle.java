package geometries;
import primitives.*;
import primitives.Material;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;


/**This Triangle class is in the geometric shapes.
 * It inheritance from Polygon.
 * It contains a infinite plane and a List _vertices (Which will have three points).*/
public class Triangle extends Polygon {



    /**Constructor for creating a triangle from three points, color and material.
     * @param p1 point
     * @param p2 point
     * @param p3 point*/
    public Triangle(Color c, Material m, Point3D p1, Point3D p2, Point3D p3) {
        super(c, m, p1 ,p2,p3);
    }




    /**Constructor for creating a triangle from three points, color,
     * and puts a default material.
     * @param p1 point
     * @param p2 point
     * @param p3 point*/
    public Triangle(Color c, Point3D p1, Point3D p2, Point3D p3) {
        this(c, new Material(0, 0, 0), p1,p2,p3);
    }


    /**Constructor for creating a triangle from three points.
     * And puts a default color black and a default material.
     * @param p1 point
     * @param p2 point
     * @param p3 point*/
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, new Material(0, 0, 0), p1,p2,p3);
    }


    /**This function gets a ray and checks if the ray touches this particular triangle.
     * @param ray
     * @return a list which contains or a null or a point if the ray touched the triangle.*/
    @Override
    public  List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findGeoIntersections(ray);
        if (intersections == null) return null;
        Point3D p0 = ray.getPo();
        Vector v = ray.getDir();
        /**vector v1=from the first to the starting of the ray of the camera
         * vector v2=from the second point of the camera to the starting point of the ray from the camera.
         */
        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);//second pointminus the starting of the ray of from the camera
        Vector v3 = _vertices.get(2).subtract(p0);
       /**first we do crossProduct with the two vectors from point of triangle to ray we get the normal.
        * then we do dotProduct with the dir of ray and the normal to the two vectors .*/
        double s1 = v.dotProduct(v1.crossProduct(v2).normalized());//to make sure that every
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3).normalized());
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1).normalized());
        if (isZero(s3)) return null;
        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            List<GeoPoint> result = new LinkedList<>();
            GeoPoint geo =intersections.get(0); //because there is max only one intersection point
                result.add(new GeoPoint(this, geo.getPoint()));
            return result;
        }
        return null;
    }
}



