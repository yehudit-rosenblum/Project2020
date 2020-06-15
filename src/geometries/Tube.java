package geometries;
import primitives.*;

import java.util.List;


/**The tube class is in the geometric shapes.
 * It is a endless roll(galil).
 * Tube class contains a ray. Which contains a 3D point that indicates the starting point,
 * and a vector directoin.
 * It also inhereites a radius.*/
public class Tube extends RadialGeometry {
    Ray axisRay;

    /**Constructer that gets a color, material , radius and the ray(starting point)
     * @param c color
     * @param m metarial
     * @param radius1
     * @param ax1 ray that the starting point*/
    public Tube(Color c, Material m, double radius1, Ray ax1){
        super(c, m, radius1);
        axisRay=ax1;
    }



    /**Constructer that gets a color , radius and the ray
     * and puts a default material
     * @param c
     * @param radius1
     * @param ax1*/
    public Tube(Color c ,double radius1, Ray ax1){
        this(c, new Material(0, 0, 0), radius1, ax1);
    }



    /**Constructer that gets the radius and the ray
     * and puts the default color black and a default material.
     * @param radius1
     * @param ax1 ray*/
    public Tube(double radius1, Ray ax1){
        this(Color.BLACK, new Material(0, 0, 0), radius1, ax1);
    }


    /**A getter for the ray
     * @return axisRay*/
    public Ray getAxisRay() {
        return axisRay;
    }


    /** This function returns the normal to the tube
     *  according to a point on it.
     * @param P
     * @return normal*/
    @Override
    public Vector getNormal(Point3D P) {
        /**0=the begning of the tube
         * v=the directoin of the tube*/
        Point3D O=axisRay.getPo();
        Vector v=axisRay.getDir();

        /** vec=dir of the point we got mines the o (begenning of the tube).*/
        Vector vec=P.subtract(O);

        /**dotProduct with v(dir of the tube) and vec (dir of the point we got mines the o)
         * The result tels us how much to length more the v(dir of tube)*/
        double projection=vec.dotProduct(v);
        if (projection!=0)
        {
            /** We take the o plus the lengh
             * And now we got the inside point that is continuation
             * of the p standing strait to the ray in the tube*/
            O.add(v.scale(projection));
        }
        /**The point we got mines that o is the continuation of the normal.*/
        Vector vector=P.subtract(O);
        return vector.normalize();
    }


    /**prints all the fields in the Tube
     * @return String*/
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**Dount have to do this.
     * Its bonus.*/
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
