package scene;
import geometries.*;
import primitives.*;
import elements.*;
import renderer.*;

/**
 * The camera class is in the elements.
 * It contains a name, back round color, ambient light, list of geometries that are in the scene,
 * camera class(point location and up down right) and distance from the view plane.
 */

public class Scene {
    String name;
    Color background;
    AmbientLight ambientLight;
    Geometries geometries;
    Camera camera;
    double distance;


    /**
     * Constructor for creating the scene.
     * gets a name and also creates the geometries list=null
     * @param name1
     */
    public Scene(String name1) {
        name = name1;
        geometries=new Geometries();
    }


    /**
     * This function gets a non limited amount of different geometries
     * and adds them all to the list of geometries in the scene.
     * It puts all the geometries and the right side and then puts each one in the i.
     * @param DifGeometries (different shapes)
     */
    public void addGeometries(Intersectable... DifGeometries) {
        for (Intersectable i : DifGeometries) {
            geometries.add(i);
        }
    }


    public void setBackground(Color background) {
        this.background = background;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public Camera getCamera() {
        return camera;
    }

    public double getDistance() {
        return distance;
    }
}
