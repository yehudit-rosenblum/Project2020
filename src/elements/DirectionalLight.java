package elements;

import primitives.Color;
import primitives.*;



/**The directional light is a far away light that has a direction.
 *  And to that direction it illuminates (meir) everything equally.
 *  There is no fading(deicha) with bigger os smaller distance from that light*/
public class DirectionalLight extends Light implements LightSource{
    private Vector dir;

    /**Constructor that gets the color(_intensity) and the direction vector.
     * @param colorIntensity
     * @param dir1 vector */
    public DirectionalLight(Color colorIntensity, Vector dir1) {
        this.intensity=colorIntensity;
        dir=dir1.normalized();
    }

    /**This function returns the _intensity which is equal to all points in the scene.
     * @param p point
     * @return the Color (_intensity)*/
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();//he does't have the getter of _intensity just his father light.
    }


    /**
     * This function returns the dir from the
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return dir;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
