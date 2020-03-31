package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * The geometry interface class is in the geometric shapes.
 * It Contains a function that returns the normal for any point on a shape.
 * All geometric classes inherit from it and perform this function according to their shape.
 */



public interface Geometry {
public Vector getNormal(Point3D p);
}
