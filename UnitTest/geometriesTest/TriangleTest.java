package geometriesTest;
import geometries.*;
import primitives.*;
import org.junit.Test;
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
}

