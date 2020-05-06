package geometriesTest;
import geometries.*;
import primitives.Vector;
import primitives.Point3D;
import primitives.Ray;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;


/**
 * Test for geometries.Plane class.
 * @author Yehudit Rosenblum-first
 */

public class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p1=new Point3D(10,1,2);
        Point3D p2=new Point3D(5,2,3);
        Point3D p3=new Point3D(3,4,2);
        Plane plane=new Plane(p1,p2,p3);
        System.out.println(plane.getNormal(new Point3D(2,2,2)));
    }


    @Test
    public void findIntsersections() {

        Plane plane = new Plane(new Point3D(1, 1, 0), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        //TC01: ray intersects the plane
        assertEquals("ray intersects the plane", List.of(new Point3D(1, 0, 0)),
                plane.findIntersections(new Ray(new Point3D(0, 0, -1), new Vector(1, 0, 1))));

        //TC02: ray does'nt intersect the plane
        assertNull("ray does'nt intersect the plane", plane.findIntersections(new Ray(new Point3D(0, 0, -1), new Vector(1, 0, -1))));



        // =============== Boundary Values Tests ==================
        // **** Group: ray is parallel to the plane
        //TC11: the ray is included in the plane

        //TC12: the ray is not included in the plane
        assertNull("the ray is parallel and not included in the plane", plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,1,0))));

        //**** Group: ray is orthogonal to the plane
        //TC13: the ray is before the plane
        assertEquals("the ray is orthogonal and before the plane", List.of(new Point3D(1,1,0)),
                plane.findIntersections(new Ray(new Point3D(1,1,-1), new Vector(0,0,1))));

        //TC14: the ray is in the plane
        assertNull("the ray is orthogonal and in the plane",
                plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,0,1))));

        //TC15: the ray is after the plane
        assertEquals("the ray is orthogonal and after the plane", List.of(new Point3D(1,1,0)),
                plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,0,-1))));
        //TC16: the ray is neither orthogonal nor parallel to and begins at the plane
        assertNull("the ray is neither orthogonal nor parallel to and begins at the plane",
                plane.findIntersections(new Ray(new Point3D(2,2,0), new Vector(1,1,1))));

        //TC17: the ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane
        assertNull("the ray is neither orthogonal nor parallel to the plane and begins in " +
                "the same point which appears as reference point in the plane", plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(1,1,1))));
    }
}