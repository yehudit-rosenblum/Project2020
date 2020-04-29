package geometriesTest;
import geometries.*;
import primitives.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Unit test for geometries.Triangle class.
 * @author Yehudit Rosenblum-first
 */

public class TriangleTest {
    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ========== Equivalence Partition Test ==========
        Point3D p1=new Point3D(0,0,0);
        Point3D p2=new Point3D(1,0,0);
        Point3D p3=new Point3D(0,0,1);
        Triangle t1 = new Triangle(p1, p2, p3);
        assertEquals("getNormal() result is wrong", t1.getNormal(Point3D.ZERO), new Vector(0, -1, 0));
    }


    @Test
    public void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Triangle t=new Triangle(new Point3D(3,0,0),new Point3D(1,0,0),new Point3D(-2,0,5));

        // TC01: The point is inside the triangle(1 point)
        Ray r1=new Ray(new Point3D(2,1,0), new Vector(0,-4,1));
        assertEquals("findIntersection() result is not in the trianle",t.findIntersections(r1), List.of(new Point3D(2,0,0.25)));

        // TC02: The point is outside against edge (0 point)
        Ray r2=new Ray(new Point3D(2,1,0),new Vector(2,-1,5));
        assertEquals("findIntersections() result is not outside against edge" ,t.findIntersections(r2),null);

        // TC03: The point is outside against vertex (0 point)
        Ray r3=new Ray(new Point3D(0,2,3), new Vector(4, -2,-3.5));
        assertEquals("findIntersectins() is not outside against vertex", t.findIntersections(r3),null );

        // ============ Boundary Values Tests ==============

        // TC11: The point is on edge (0 point)
        Ray r4=new Ray(new Point3D(2,1,0), new Vector(4,-4,0));
        assertEquals("findIntersection() result is not outside against edge", t.findIntersections(r4), null);

        // TC12: The point is on vertex (0 point)
        Ray r5=new Ray(new Point3D(2,1,0), new Vector(0,-2,0));
        assertEquals("findIntersection() result is not outside against vertex", t.findIntersections(r5), null);

        // TC13: The point is on edge continuashion (0 point)
        Ray r6=new Ray(new Point3D(2,1,0), new Vector(6,-3,0));
        assertEquals("findIntersection() result is not on edge continuasion", t.findIntersections(r6), null);

    }

}

