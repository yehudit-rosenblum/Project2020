package geometries;
import primitives.Point3D;
import primitives.Vector;

/**
 * This geometry class is in interface.
 * It Contains a function that returns the normal for any point on a shape.
 * All geometric classes implement this function.
 */
public interface Geometry extends Intersectable {
public Vector getNormal(Point3D p);
}
