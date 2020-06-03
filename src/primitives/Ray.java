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

    public Ray(Point3D po1, Vector dir1) {
        dir=dir1;
        dir.normalize();
        po=po1;
    }

    public Ray(Ray other) {
        dir=other.dir;
        po=other.po;
    }

    public Vector getDir() {
        return dir;
    }




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
