package geometries;
import primitives.*;
import java.util.List;



/**
 * This Intersectable class is in interface. The interface Geometry class inherits from this class.
 * It Contains a function that gets a ray and returns a list of points that the ray intersected with.
 * All geometric classes implements what is in the geometry and this function.
 */
public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);
}
