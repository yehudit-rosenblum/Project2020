package geometries;
import primitives.*;

import java.util.List;


/**
 * The tube class is in the geometric shapes.
 * It is a endless roll(galil).
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


    /**
     * * This function returns the normal to the tube according to the point.
     * @param P
     * @return
     */
    @Override
    public Vector getNormal(Point3D P) {
        /**
         * 0=the begning of the tube
         * v=the directoin of the tube
         */
        Point3D O=axisRay.getPo();
        Vector v=axisRay.getDir();

        /**
         * vec=dir of the point we got mines the o (begenning of the tube).
         */
        Vector vec=P.subtract(O);

        /**
         * dotProduct with v(dir of the tube) and vec (dir of the point we got mines the o)
         * The result tels us how much to length more the v(dir of tube)
         */
        double projection=vec.dotProduct(v);
        if (projection!=0)
        {
            /**
             * We take the o plus the lengh
             * And now we got the inside point that is continuation
             * of the p standing strait to the ray in the tube
             */
            O.add(v.scale(projection));
        }
        /**
         * The point we got mines that o is the continuation of the normal.
         */
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

    /**
     * Dount have to do this.
     * Its bonus.
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
