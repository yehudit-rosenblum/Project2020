package elements;

import primitives.*;

/**
 * This Spotlight inherits from spotLight.
 * He also has a decay(deicha) with distance and inherits the three (mekadmey hanchata).
 * But its like a projector(panas) so it lights to a specific direction (and not all over).
 * That's way he has a field of vector direction.*/
public class SpotLight extends PointLight {
    private Vector dir;

    /**Constructor that gets:
     * @param c color _intensity
     * @param position1
     *  @param dir1
     * @param kC1 (matmedet)
     * @param kL1 (linary)
     * @param kQ1 (rebuey)
     */
    public SpotLight(Color c, Point3D position1, Vector dir1, double kC1, double kL1, double kQ1) {
        super(c, position1, kC1, kL1, kQ1);
        this.dir =dir1.normalize();
     }




    /**This function returns the _intensity according to a specific point on scene.
     * @param p point
     * @return the _intensity (which is a color based on hes distance and angle.*/
    @Override
    public Color getIntensity(Point3D p) {
        /**calculates the angle (zavit) between the _dir from light and the L(from light to object).*/
        double cos = dir.dotProduct(getL(p));
        if (cos <= 0) //if the point is behind the light (90 deg or more)
            return Color.BLACK;
        /**His Intensity Color multiplied with the angle(zavit) between the L and _dir.
         * If the factor came out zero so it will (leapes) the number*/
        return super.getIntensity(p).scale(cos);
    }

}
