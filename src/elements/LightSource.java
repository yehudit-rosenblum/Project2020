package elements;
import primitives.*;


/** The lightSource is in interface.
 * It contains a function that returns the color of the light (that is based on the _intensity=merchak).
 * And also a function that builds the vector direction from the light to the object.*/
public interface LightSource {

    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);

}
