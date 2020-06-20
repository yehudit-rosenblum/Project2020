package elements;
import geometries.*;
import primitives.*;


/**The AmbientLight is a constant (kavua) light that illuminates (meir) everything I see.
 * And affects everyone equally in all directions or distance.
 * The AmbientLight inherits from light. (Light has an intensity/color and the getter)
 * */
public class AmbientLight extends Light {

    /**Constructor that gets the color and also a ka=mekadem hanchata)
     * @param intensity1 color
     * @param ka (mekadem hanchata)*/
    public AmbientLight(Color intensity1, double ka) {
        this.intensity=intensity1.scale(ka); //Multiplies the color by a fixed parameter(mekadem hanchata=1)
    }
}
