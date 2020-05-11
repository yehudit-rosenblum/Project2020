package elements;
import geometries.*;
import primitives.*;


/**
 * This AmbientLight class is in the elements a Constant light that affects everyone equally.
 * Like the sunlight that illuminates (meir) everything I see.
 */
public class AmbientLight {
    private Color intensity;

    public AmbientLight(Color intensity1, double ka) {
        intensity =intensity1.scale(ka); //Multiplies the color by a fixed parameter(mekadem hanchata=1)
    }

    /**
     * returns the color of the AmbientLight
     * @return intensity
     */


    public Color GetIntensity(){
        return intensity;//
    }
}
