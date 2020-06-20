package elements;
import primitives.*;


/**This abstract Light class contains the color of a light (that is based on the _intensity=merchak) .
 * All lights will inherit this field.*/
   public abstract class Light {
    protected Color intensity;

    /**returns the color of the Light
     * @return _intensity*/
    public Color getIntensity() {
        return intensity;
    }
}
