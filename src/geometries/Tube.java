package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The tube class is in the geometric shapes.
 * It is a form of endless roll.
 * Tube class contains a 3D point that indicates the starting point, vector from the starting opint
 * and a radius.
 */


public class Tube extends RadialGeometry {
    Ray axisRay;

    public Tube(double radius1, Ray ax1){
        super(radius1);
        axisRay=ax1;
    }

    public Ray getAxisRay() {
        return axisRay;
    }



    @Override
    public Vector getNormal(Point3D P) {
        return null;
    }


    /**
     * prints all the fields in the Tube
     * @return String
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
