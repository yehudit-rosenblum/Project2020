package geometriesTest;
import geometries.*;
import primitives.*;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Unit tests for geometries.Cylinder class
 * @author yehudit Rosenblum
 */
public class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        Cylinder c = new Cylinder(1, new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0)), 1);
        System.out.println(c.getNormal(new Point3D(1, 1, 1)));

    }
}
