package elements;
import primitives.*;
import static primitives.Util.alignZero;


/**The pointLight is like a lamp that lights all around him to all direction.
 * There is decay(deicha) with distance.
 * It contains a point position were the lamp is located. and also three mekadmey hanchata.*/
public class PointLight extends Light implements LightSource {
    protected Point3D position;
    protected double kC; // Constant attenuation  (matmeted)
    protected double kL; // Linear attenuation  (leniarit)
    protected double kQ; // Quadratic attenuation (rebuet)


    /**Constructor that gets the color that the light gives,
     *  the location of the light and three mekadmey hanchata.
     *  @param intensity1 color
     *  @param position1 point3D
     * @param kC1 constant (matmedet)
     * @param kL1 (leniary)
     * @param kQ1 (rebuey)*  */
    public PointLight(Color intensity1, Point3D position1, double kC1, double kL1, double kQ1) {
        intensity = intensity1;//from his father light
        position = new Point3D(position1);
        this.kC = kC1;
        this.kL = kL1;
        this.kQ = kQ1;
    }


    /**Constructor that only gets the color that the light gives and his location.
     * And puts a default number to the Attenuation coefficients(mekadmey hanchata)
     * @param intensity
     * @param position*/
    public PointLight(Color intensity, Point3D position) {
        this(intensity, position, 1d, 0d, 0d);
    }



    /**This function returns the intensity according to a specific point on scene.
     * @param p point
     * @return the intensity (which is a color based on he distance.*/
    @Override
    public Color getIntensity(Point3D p) {
        /**calculates the distance between the location point of the light and the point on the scene.*/
        double dsquared = p.distanceSquared(position);//
        double d = p.distance(position);
        /**This is the(nooscha)his color divided by the Attenuation coefficients(mekadmey hanchata)
         * multiplied by the distance.*/
        return (intensity.reduce(kC + kL * d + kQ * dsquared));
    }


    /**ThIs function gets a point on scene and returns the vector from the light to that point.
     * @param p point
     * @return a vector*/
    @Override
    public Vector getL(Point3D p) {
        /**If the point in the object is the same point of the light position return null*/
        if (p.equals(position)) {
            return null;
        }
        /**p minus position. which is p point to the light position*/
        return p.subtract(position).normalize();


    }

    @Override
    public double getDistance(Point3D point) {
        return alignZero(position.distance(point));
    }

}
