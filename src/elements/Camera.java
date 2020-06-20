package elements;
import primitives.*;
import geometries.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;



/**The camera class contains a point which is the location of the camera.
 * And also three vectors to(forward), up, right.*/
public class Camera {
    private Point3D spo;
    private Vector Vto;
    private Vector Vup;
    private Vector Vright;


    /**Constructor for creating a camera.
     * It gets a location camera point and a vector to(forward) and a vector up.
     * The vector right we will calculate are selfs, By doing cross Product.
     * @param spo1
     * @param Vto1
     * @param Vup1
     */
    public Camera(Point3D spo1, Vector Vto1, Vector Vup1) {
        //if the vectors are zeros
        if (Vto1.length() == 0 || Vup1.length()==0)
            throw new IllegalArgumentException("vectors can not be (0,0,0)");
        //if the vector forward and up are not orthogonal
        if (Vto1.dotProduct(Vup1) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");
        spo = new Point3D(spo1);
        Vto = Vto1.normalized();
        Vup = Vup1.normalized();
        Vright = Vto.crossProduct(Vup);
        Vright.normalize();
    }

    /**Spo getter
     * @return spo starting point of camera*/
    public Point3D getSpo() {
        return spo;
    }

    /**Vto getter
     * @return Vto vector to forward*/
    public Vector getVto() {
        return Vto;
    }

    /**Vup getter
     * @return Vup vector to up*/
    public Vector getVup() {
        return Vup;
    }


    /**Vright getter
     * @return Vright vector to right*/
    public Vector getVright() {
        return Vright;
    }

    /**This Function gets the details about the view plane, i, j indexes to a pixel.
     * And then builds and returns the ray throw the middle of that pixel.
     * @param nX,            number of squares in the Row (shura)
     * @param nY,            number of squares in the column.(amuda)
     * @param j              index to row (shura)
     * @param i              index to column (amuda)
     * @param screenDistance distance from camera to view plane
     * @param screenW    width of the view plane
     * @param screenH   height of the view plane
     * @return Ray*/
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenW, double screenH) {
        //if the distance between the view plane and camera is 0
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0"); }

        Point3D Pc = spo.add(Vto.scale(screenDistance));// Pc=The middle pixel on view plane.

        //the width of the whole view plane/number of squares on row(shura).
        double Rx = screenW / nX;
        //the height of the whole view plane/number of squares on//Rx=the width of one square. column(amuda).
        double Ry = screenH / nY;//the height of one square.


        /**xj=the exact middle of that pixel.
         * How much to move up/down ,left/right to be in the middle of that square.
         *(i index- the middle square) =the distance from the middle.
         * move that multiply the real height*/
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);
        double yi = ((i - nY / 2d) * Ry + Ry / 2d); //d=double

        //the middle pixel
        Point3D Pij = Pc;

        /**
         * if yi and xj !=0 move the Pij */
        if (!isZero(yi)) {
            Pij = Pij.add(Vup.scale(-yi)); // yi can ba positive or negative.
        }
        if (!isZero(xj)) {
            Pij = Pij.add(Vright.scale(xj)); //xj can be positive or negative.
        }

        /**know the Pij=is the point in the middle of the exact pixel.
         * know we will build the vector from the starting point in the camera throw to the pij.(point in the middle of the pixel)*/
        Vector Vij = Pij.subtract(spo);

        return new Ray(spo, Vij);//ray=the starting point of the ray and the vector direction


    }


    /**This Function gets the details about the view plane, i, j indexes to a pixel.
     * And then builds and returns  a list of rays throw the middle and all over that pixel.
     * @param nx,            number of squares in the Row (shura)
     * @param ny,            number of squares in the column.(amuda)
     * @param j              index to row (shura)
     * @param i              index to column (amuda)
     * @param distance distance from camera to view plane
     * @param width   width of the view plane
     * @param height   height of the view plane
     * @return Ray*/
    public List<Ray> constructNRaysThroughPixel(int nx, int ny, int j, int i, double distance, int width, int height, double superSamplingRate, int amountRays) {

        //the width of the whole view plane/number of squares on row(shura).
        double Rx = width/nx;//the width of one square.
        double Ry = height/ny;//the height of one square.

        Point3D Pc = spo.add(Vto.scale(distance));// Pc=The middle pixel on view plane.


        /**xj=the exact middle of that pixel.
         * How much to move up/down ,left/right to be in the middle of that square.
         *(j index of colmn- the middle square) =the distance from the middle.
         * move that multiply the real width plus another half*/
        double xj = ((j - nx / 2d) * Rx + Rx / 2d);
        double yi = ((i- ny / 2d) * Ry + Ry / 2d); //d=double

        //the middle pixel
        Point3D Pij = Pc;

        /**
         * if yi and xj !=0 move the Pij */
        if (!isZero(yi)) {
            Pij = Pij.add(Vup.scale(-yi)); //know the Pij=is the point in the middle of the exact pixel on column.
        }
        if (!isZero(xj)) {
            Pij = Pij.add(Vright.scale(xj));//know the Pij=is the point in the middle of the exact pixel on row.
        }

        //-----SuperSampling-----
        List<Ray> rays = new LinkedList<>();

        double gapX = Rx / 2d;//half the width of pne pixel.
        double gapY = Ry / 2d;//half the height of one square.

        //Math.floor=("erech tachton").
        int delta = (int) Math.floor(Math.sqrt(amountRays));//delta is (shoresh) of the amount of rays we are sending)

        //force odd(e zugi) value for delta;
        if (delta % 2 == 0) delta += 1;


        for (int r = -delta; r <= delta; r++) {//r=rows from up to down.
            for (int col = -delta; col <= delta; col++) {//col=column from left to right.
                if (r != 0 && col != 0) {//skip the center point
                    //always the same Z.
                    Point3D tmp = new Point3D(Pij.getX().get() + r * gapX, Pij.getY().get() + col * gapY, Pij.getZ().get());
                    rays.add(new Ray(spo, tmp.subtract(spo).normalize()));//create the ray from camera to the temp point on pixel.
                }
            }
        }
        return rays; //the list of the different rays throw one pixel.
    }
}
