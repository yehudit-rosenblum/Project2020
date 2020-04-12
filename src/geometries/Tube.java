package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The tube class is in the geometric shapes.
 * It is a form of endless roll(galil).
 * Tube class contains a ray. Which contains a 3D point that indicates the starting point,
 * and a vector directoin.
 * It also inhereites a radius.
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

        Point3D O=axisRay.getPo();
        Vector v=axisRay.getDir();
        Vector vec=P.subtract(O);
        double d=vec.dotProduct(vec);
        if (d!=0)
        {
            O.add(v.scale(d));
        }
        Vector vector=P.subtract(O);
        return vector.normalize();
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
