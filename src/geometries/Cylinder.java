package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


/**
 * The cylinder class is in the geometric shapes,
 * and inherits from the tube class.
 * This is a final roll.
 * It has a ray that contains the starting point and a vector.
 * It has a radius that expresses the width of the circle
 * And also it contains a limited height for the roll.
 */

public class Cylinder extends  Tube {

    double height;

    public Cylinder(double radius1, Ray ax1, double height1) {
        super(radius1, ax1);
        height = height1;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point3D P) {
        return null;
    }


    /**
     * This Function prints all the fields in the Cylinder.
     * @return String
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
