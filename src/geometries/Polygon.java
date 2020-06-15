package geometries;
import primitives.*;
import java.util.List;
import static primitives.Util.*;

/**Polygon class is a Plane(mishtach) which is closed with Point3D dots.
 * The amount of points that it will get wil make the particular shape.
 * It contains a List of Point3D and a plane.
 * @author Dan*/
public class Polygon extends Geometry {

    protected List<Point3D> _vertices;//vertices=points(kodkodim)
    protected Plane _plane;


    /**Constructor that gets a vertices(points) list.
     * The list must be ordered by edge path.
     * The polygon must be convex.
     * @param vertices list of vertices according to their order by edge path*/
    public Polygon(Color c, Material m, Point3D... vertices) {
        super(c, m);
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        _vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        _plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3) return; // no need for more tests for a Triangle

        Vector n = _plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }



    /**Constructor that gets a vertices(points) list and puts a default color black.
     * The polygon must be convex.
     * @param vertices  gets the different points.*/
    public Polygon(Color c ,Point3D... vertices) {
        this(c, new Material(0, 0, 0), vertices);
    }


    /**Constructor that gets a vertices(points) list and puts a default color black.
     * The polygon must be convex.
     * @param vertices  gets the different points.*/
    public Polygon(Point3D... vertices) {
        this(Color.BLACK, new Material(0, 0, 0), vertices);
    }



    /**This function returns the normal to the polygon.
     * The polygon contains a plane and that way return the normal to the plane.
     * @param point
     * @return the normal*/
    @Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal();
    }


    /**Dount have to do this. Its bonus.
     * @param ray
     * @return List.*/
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
