package geometriesTest;
import geometries.*;
import primitives.*;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Unit test for geometries.Plane class.
 * @author Yehudit Rosenblum-first
 */

public class PlaneTest {

    @Test
    public void getNormal() {
        Point3D p1=new Point3D(10,1,2);
        Point3D p2=new Point3D(5,2,3);
        Point3D p3=new Point3D(3,4,2);
        Plane plane=new Plane(p1,p2,p3);
        System.out.println(plane.getNormal(new Point3D(2,2,2)));
    }
}