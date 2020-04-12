package geometriesTest;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;


/**
 * Unit test for geometries.Sphere class.
 * @author Yehudit Rosenblum-first
 */
public class SphereTest {

    @Test
    public void getNormal() {
        Sphere s=new Sphere(3, new Point3D(0,0,0));
        System.out.println(s.getNormal(new Point3D(2,2,2)));
    }
}