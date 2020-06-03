package geometries;
import primitives.*;
import static primitives.Util.*;

/**The cylinder class is in the geometric shapes,
 * and inherits from the tube class.
 * This is a final roll.
 * It has a ray that contains the starting point and a vector.
 * It has a radius that expresses the width of the circle
 * And also it contains a limited height for the roll.*/
public class Cylinder extends  Tube {
    double height;


    /**Constructer that gets
     * @param c color
     * @param m material
     * @param radius1
     * @param ax1 ray(starting point)
     * @param height1 limited*/
    public Cylinder(Color c, Material m, double radius1, Ray ax1, double height1) {
        super(c, m, radius1, ax1);
        height=height1;
    }

    /**Constructer that gets the color radius, ray and height
     * and puts a default material.
     * @param c color
     * @param radius1
     * @param ax1 ray(starting point)
     * @param height1*/
    public Cylinder(Color c, double radius1, Ray ax1, double height1) {
        this(c, new Material(0, 0, 0), radius1, ax1, height1);
    }


    /**Constructer that gets the radius, ray and height
     * and puts the default color black and a defalut material.
     * @param radius1
     * @param ax1 ray
     * @param height1*/
    public Cylinder(double radius1, Ray ax1, double height1) {
        this(Color.BLACK, new Material(0, 0, 0), radius1 ,ax1, height1);
    }



    public double getHeight() {
        return height;
    }

    /**This function return the normal to the cylinder
     * according to a point on it.
     * @param P point.
     * @return Vector thats the normal*/
        @Override
        public Vector getNormal(Point3D P) {
            Point3D o = axisRay.getPo();
            Vector v = axisRay.getDir();

            // projection of P-O on the ray:
            double t;
            try
            {
                t = alignZero(P.subtract(o).dotProduct(v));
            }
            catch (IllegalArgumentException e)
            { // P = O
                return v;
            }
            // if the point is at a base
            if (t == 0 || isZero(height - t)) // if it's close to 0, we'll get ZERO vector exception
                return v;

            o = o.add(v.scale(t));
            return P.subtract(o).normalize();
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
