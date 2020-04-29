package geometriesTest;
import org.junit.Test;
import geometries.*;
import primitives.*;

import javax.sound.midi.Soundbank;

import static org.junit.Assert.*;

/**
 * Unit tests for geometries.Tube class
 * @author yehudit Rosenblum
 */

public class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        Ray r = new Ray( new Point3D(0, 0, 0),new Vector(5, 5, 5));
        Tube t = new Tube(3.5, r);
        Point3D P = new Point3D(2.5, 2.5, 2.5);
        System.out.println(t.getNormal(P));
    }
}