package renderer;
import primitives.*;
import scene.*;
import geometries.*;
import primitives.Color;
import elements.Camera;
import java.util.List;
import primitives.Point3D;


/**
 * The Render class is in the Renderer.
 * It is responsible for taking the scene and actually creating the image.
 * It contains a class Scene(name, backround color, ambientlight, list of geometries, camera and distance from view plane)
 * And also a class imageWriter
 *
 */
public class Render {
    Scene scene;
    ImageWriter imageWriter;


    /**
     * Constructor for creating the Render
     * gets a scene.
     * @param scene1
     */
    public Render(Scene scene1) {
        scene = scene1;
    }


    /**
     * Constructor for creating the Render
     * gets a scene and the imageWriter.
     * @param scene1
     */
    public Render(ImageWriter imageWriter1, Scene scene1) {
        imageWriter = imageWriter1;
        scene = scene1;
    }


    public void writeToImage() {
        imageWriter.writeToImage();
    }
    /**
     * In the intersectionPoints-find the point with minimal distance from the ray begin point
     * (now it is just the camera location) and return i
     *
     * @return
     */
    private Point3D getClosesPoint(List<Point3D> intersectionPoints) {
        Point3D sourcePoint = scene.getCamera().getSpo();
        Point3D result = null;

        double minDist = Double.MAX_VALUE;
        double distance = 0;

        for (Point3D pt : intersectionPoints) {
            distance = sourcePoint.distance(pt);
            if (distance < minDist) {
                minDist = distance;
                result = pt;
            }
        }
        return result;
    }


    private Color calcColor(Point3D closestPoint) {
        Color result = scene.getAmbientLight().GetIntensity();
        return result;
    }


    public void renderImage() {
        Camera camera = scene.getCamera();
        Intersectable geometries = scene.getGeometries();
        java.awt.Color background = scene.getBackground().getColor();
        double distance = scene.getDistance();

        //Nx and Ny are the width and height of the image.
        int Nx = imageWriter.getNx(); //columns
        int Ny = imageWriter.getNy(); //rows
        double width = imageWriter.getWidth();
        double height = imageWriter.getHeight();
        //pixels grid
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    imageWriter.writePixel(column, row, background);
                } else {
                    Point3D closestPoint = getClosesPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }


    /**
     * Printing the grid with a fixed interval between lines
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color color) {
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }
}






