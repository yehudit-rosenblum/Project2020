package geometries;

import primitives.Point3D;


/**
 * A triangle class is in the geometric shapes.
 * It is a flat triangular shape.
 * It contains three three-dimensional points.
 */

public class Triangle extends Polygon {

    public Triangle(Point3D a, Point3D b, Point3D c) {
        super(a,b,c);
    }
}
