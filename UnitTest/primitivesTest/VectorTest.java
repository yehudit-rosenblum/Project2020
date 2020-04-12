package primitivesTest;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Unit test for primitives.vector class.
 * @author Yehudit Rosenblum-first
 */
public class VectorTest {

    /**
     * Test method for subtract.
     */
    @Test
    public void testsubtract() {
        Vector v1=new Vector(2,2,2);
        Vector v2=new Vector(3,3,3);
        assertEquals("subtract result is wrong", v2.subtract(v1), new Vector(1, 1, 1));
    }

    /**
     * Test method for add.
     */
    @Test
    public void testadd() {
        Vector v1=new Vector(2,2,2);
        Vector v2=new Vector(3,3,3);
        assertEquals("add result is not the solution", v1.add(v2), new Vector(5,5,5));

    }


    /**
     * Test method for scale.
     */
    @Test
    public void testscale() {
        Vector v1=new Vector(5,5,5);
        assertEquals("Scale result is not the solution", v1.scale(5), new Vector(25,25,25));
    }

    /**
     *  Test method for dotproduct.
     */
    @Test
    public void testdotProduct() {
        Vector v1 =new Vector(2,2,2);
        Vector v2=new Vector(1,1,1);
        double num=6.0;
        assertEquals("DotProduct() result is wrong", v1.dotProduct(v2), num, 1e-5);
    }

    /**
     * Test method for crossProduct.
     */
    @Test
    public void testcrossProduct() {
        Vector v1 =new Vector(10,5,2);
        Vector v2=new Vector(1,1,1);
        assertEquals("crossProduct() result is wrong", v1.crossProduct(v2), new Vector(3,-8,5));
    }


    /**
     * Test method for lenthSquared.
     */
    @Test
    public void lengthSquared() {
        Vector v1 =new Vector(2,2,2);
        int num=12;
        assertEquals("lenthSquared() result is wrong", v1.lengthSquared(), num, 1e-5);
    }

    /**
     * Test method for length.
     */
    @Test
    public void length() {
        Vector v1 =new Vector(1,2,2);
        assertEquals("lenth result is wrong", v1.length(), 3,  1e-5);
    }


    /**
     * Test method for normalize.
     */
    @Test
    public void normalize() {
        Vector v1 =new Vector(1,0,0);
        assertEquals("normalize() result is wrong", v1.normalize(), new Vector(1, 0, 0));
    }

    /**
     * Test method for normalized.
     */
    @Test
    public void normalized() {
        Vector v1 =new Vector(1,0,0);
        assertEquals("normalized() result is wrong", v1.normalized(), new Vector(1, 0, 0));
    }
}