package geometriesTest;
import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;


public class GeometriesTest {

    @Test
    public void findIntersections() {
        Geometries geometries = new Geometries();

        // =============== Boundary Values Tests ==================
        //TC01: empty geometries collections
        assertNull("empty geometries collections",
                geometries.findGeoIntersections(new Ray(new Point3D(0,1,0), new Vector(1,0,5))));

        geometries.add(new Plane(new Point3D(1,1,0), new Vector(0,0,1)));
        geometries.add(new Triangle(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(0,0,1)));
        geometries.add(new Sphere(1d, new Point3D(1, 0, 0)));

        //TC02: each geometry does'nt have intersection points
        assertNull("each geometry does'nt have intersections points",
                geometries.findGeoIntersections(new Ray(new Point3D(0,0,2), new Vector(0,-1,0))));

        //TC03: just one geometry has intersections point
        assertEquals("just one geometry has intersections point", 1,
                geometries.findGeoIntersections(new Ray(new Point3D(0,5,-1), new Vector(0,0,1))).size());


        // ============ Equivalence Partitions Tests ==============
        //TC11: part of the ge ometries has intersection points
        assertEquals("part of the geometries has intersections points", 2,
                geometries.findGeoIntersections(new Ray(new Point3D(1,0,-1), new Vector(0,0,1))).size());
    }

}