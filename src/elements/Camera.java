package elements;
import primitives.*;
import geometries.*;
import static primitives.Util.*;



/**
 * The camera class is in the elements.
 * It contains a point which is the location of the camera. And also three vectors uo, down, right.
 */
public class Camera {
    private Point3D spo;
    private Vector Vto;
    private Vector Vup;
    private Vector Vright;


    /**
     * Constructor for creating a camera.
     * It gets a point and a vector to and a vector up.
     * The vector right we wll calculate are selfves.
     * @param spo1
     * @param Vto1
     * @param Vup1
     */
    public Camera(Point3D spo1, Vector Vto1, Vector Vup1) {
        if (Vto1.length() == 0 || Vup1.length()==0)
            throw new IllegalArgumentException("vectors can not be (0,0,0)");
        if (Vto1.dotProduct(Vup1) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");
        spo = new Point3D(spo1);
        Vto = Vto1.normalized();
        Vup = Vup1.normalized();
        Vright = Vto.crossProduct(Vup);
        Vright.normalize();
    }

    public Point3D getSpo() {
        return spo;
    }
    public Vector getVto() {
        return Vto;
    }
    public Vector getVup() {
        return Vup;
    }
    public Vector getVright() {
        return Vright;
    }

    /**
     * This Function gets the details about the view plane,
     * plus i, j index to a pixel.
     * And then builds and returns the ray throw the middle of that pixel.
     * @param nX,            number of squares in the Row (shura)
     * @param nY,            number of squares in the column.(amuda)
     * @param j              index to row (shura)
     * @param i              index to column (amuda)
     * @param screenDistance distince from camera to view plane
     * @param screenWidth    width of the view plane
     * @param screenHeight   height of the view plane
     * @return Ray
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {


        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = spo.add(Vto.scale(screenDistance));// Pc=The middle point on view plane.

        double Ry = screenHeight / nY; //the height of one square.
        double Rx = screenWidth / nX;  //the width of one square.

        /**
         * How much to move up/down ,left/right to be in the exact square in the middle of it.
         * nY/2 =number of squares/2 =middle square. index minus the middle square
         */
        double yi = ((i - nY / 2d) * Ry + Ry / 2d); //d=double
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        /**
         * if yi and xj !=0 move the Pij */
        if (!isZero(yi)) {
            Pij = Pij.add(Vup.scale(-yi)); // yi can ba positive or negative.
        }
        if (!isZero(xj)) {
            Pij = Pij.add(Vright.scale(xj)); //xj can be positive or negative.
        }

        /**
         * Pij=is the point in the middle of the exact pixel.
         * The vector from the starting point in the camera throw to the pij.(point in the middle of the pixel)
         */
        Vector Vij = Pij.subtract(spo);

        return new Ray(spo, Vij);


    }
}
