package elements;
import geometries.*;
import primitives.*;


/**The AmbientLight class is a constant light that illuminates (meir) everything I see.
 * And affects everyone equally in all directions.*/
public class AmbientLight extends Light {

    public AmbientLight(Color intensity1, double ka) {
        this.intensity=intensity1.scale(ka); //Multiplies the color by a fixed parameter(mekadem hanchata=1)
    }
}
