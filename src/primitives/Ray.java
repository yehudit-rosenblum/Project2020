package primitives;
import static primitives.Util.*;

/**The Ray class is in the primitive forms.
 * It has a direction vector that contains a 3D point of direction
 * from the beginning of the narrow.
 * Plus the class contains another 3D point that shows the starting point of a vector,
 * that can start from any point.*/
public class Ray {
    Vector dir;
    Point3D po;
    private static final double DELTA = 0.1;


    /**Constructor that gets a starting point and Vector.
     * @param po1
     * @param dir1 Vector*/
    public Ray(Point3D po1, Vector dir1) {
        dir=dir1;
        dir.normalize();
        po=po1;
    }

    /**Constructor that gets a starting point, vector direction and a vector normal to the point.
     * And then raises the ray a little bit.
     * @param point1
     * @param direction1
     * @param normal*/
    public Ray(Point3D point1, Vector direction1, Vector normal) {
        //head+ normal.scale(±DELTA)
        dir = new Vector(direction1).normalized();
        double nv = normal.dotProduct(direction1);//calculates the angle between the normal and dir.
        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        po = point1.add(normalDelta);
    }

    /**copy constructor*/
    public Ray(Ray other) {
        dir=other.dir;
        po=other.po;
    }

    /**getter for Vector direction.
     * @return dir*/
    public Vector getDir() {
        return dir;
    }



    /**getter for starting point of the ray.
     * @return point*/
    public Point3D getPo() {
        return po;
    }


    /**This function makes a new point.
     * She adds the starting point of the ray with the lenth.
     * @param lenth
     * @return a new Point which is the starting Point of the ray plus the lenth.*/
    public Point3D getTargetPoint(double lenth){
        if (isZero(lenth))
            return po;
        Vector v=dir.scale(lenth);
        return new Point3D(po.add(v));
    }


    /**
     * Class ClassName is the basic class representing a … of Euclidean geometry in Cartesian
     * 3-Dimensional coordinate system.
     * @author Student1 and Student2
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray) obj;
        return dir.equals(oth.dir)&& po.equals(oth.po);
    }


    /**
     * prints all the fields in the Ray
     * @return String
     */
    @Override
    public String toString() {
        return "Ray{" +
                "dir=" + dir.toString() +
                ", po=" + po.toString() +
                '}';
    }
}
