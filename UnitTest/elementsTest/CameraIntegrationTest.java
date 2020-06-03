package elementsTest;
import primitives.*;
import elements.*;
import geometries.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;


/**
 * Test for integration with camera and intersections with shapes.
 * @author Yehudit Rosenblum-first
 */
public class CameraIntegrationTest {

    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));


    // TC01: The view plane is before The spere. And the sphere is in the size of one fixel.
    @Test
    public void cameraIntersectionSpherTest1() {
        Sphere sph = new Sphere(1, new Point3D(0, 0, 3));
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Intersectable.GeoPoint> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 2, count);
        System.out.println("count: " + count);
    }

    // TC02: The view plane is outside before the sphere. And every square in the view hits the sphere.
    @Test
    public void cameraIntersectionSphereTest2() {
        Sphere sph = new Sphere(2.5, new Point3D(0, 0, 2.5));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 18, count);
        System.out.println("count: " + count);
    }

    // TC03: view plane is a little inside the spere
    @Test
    public void cameraIntersectionSpherTest3() {
        Sphere sph = new Sphere(2, new Point3D(0, 0, 2));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 10, count);
        System.out.println("count: " + count);
    }

    // TC04: view plane is a all inside the spere
    @Test
    public void cameraIntersectionSpherTest4() {
        Sphere sph = new Sphere(4, new Point3D(0, 0, 2));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 9, count);
        System.out.println("count: " + count);
    }

    // TC05: The Sphere is behind the camera.
    @Test
    public void cameraIntersectionSpherTest5() {
        Sphere sph = new Sphere(0.5, new Point3D(0, 0, -1));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 0, count);
        System.out.println("count: " + count);
    }

    // TC06: The plane is parallel to the view plane.
    @Test
    public void cameraIntersectionPlaneTest1() {
        Plane p= new Plane (new Point3D(0, 0, 3), new Point3D(0, 1, 3), new Point3D(1,0,3));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = p.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 9, count);
        System.out.println("count: " + count);
    }
    // TC06: The plane is a little tilted ("mote") from the view plane.
    @Test
    public void cameraIntersectionPlaneTest2() {
        Plane p= new Plane (new Point3D(0, 0, 2), new Point3D(0, -4, 1), new Point3D(1,4,3));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = p.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 9, count);
        System.out.println("count: " + count);
    }

    // TC07: The plane is more tilted ("mote") from the view plane.
    @Test
    public void cameraIntersectionPlaneTest3() {
        Plane p= new Plane (new Point3D(0, 0, 2), new Point3D(0, -1, 1), new Point3D(1,1,3));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = p.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 6, count);
        System.out.println("count: " + count);
    }
    // TC08: The triangle is in the size of one pixel in the view plane.
    @Test
    public void cameraIntersectionTriengleTest1() {
        Triangle t1= new Triangle (new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1,1,2));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = t1.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 1, count);
        System.out.println("count: " + count);
    }
    // TC09: The triangle is mooved from the view plane
    @Test
    public void cameraIntersectionTriangleTest2() {
        Triangle t2= new Triangle (new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1,1,2));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = t2.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 2, count);
        System.out.println("count: " + count);
    }
}