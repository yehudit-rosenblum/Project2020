package renderer;
import primitives.*;
import primitives.Material;
import geometries.*;
import scene.*;
import elements.*;
import java.util.List;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;


/** The Render class is in the Renderer.
 * It is responsible for taking the scene and actually creating the image.
 * It contains a class Scene(name, backround color, ambient light, list of geometries, camera and distance from view plane)
 * And also a class imageWriter*/
public class Render {
    Scene scene;
    ImageWriter imageWriter;


    /** Constructor for creating the Render
     * gets a scene.
     * @param scene1*/
    public Render(Scene scene1) {
        scene = scene1;
    }


    /** Constructor for creating the Render
     * gets a scene and the imageWriter.
     * @param scene1 */
    public Render(ImageWriter imageWriter1, Scene scene1) {
        imageWriter = imageWriter1;
        scene = scene1;
    }

    public void writeToImage() {
        imageWriter.writeToImage();
    }


    /**This function gets a list of geo points.
     *  And returns the closest geo point from the camera.
     * @return pt geo point.*/
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        Point3D p0 = scene.getCamera().getSpo();//the point location of the camera.
        double minDist = Double.MAX_VALUE;
        double currentDistance = 0;
        GeoPoint pt = null;
        for (GeoPoint geoPoint : intersectionPoints) {
            currentDistance = p0.distance(geoPoint.getPoint());
            if (currentDistance < minDist) {
                minDist = currentDistance;
                pt = geoPoint;
            }
        }
        return pt;
    }


    /**This function calculates the specular that also impacts on the color of the pint on the shape
     * (specular is the shinnies on the object).
     * @param ks
     * @param l the vector from the light to the point on object
     * @param n
     * @param nl
     * @param v the vector from camera direction
     * @param nShininess
     * @param ip
     * @return Color*/
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip){
        double p = nShininess;

        /**R is the vector that is symmetrically returned from L as a result of the speculator*/
        Vector R = l.add(n.scale(-2 * nl)).normalized();

        /**If v vector dir of the camera is util to R
         *or if the angle is opposite away from the camera so the R does'nt impact.*/
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return Color.BLACK;
        }
        return ip.scale(ks * Math.pow(minusVR, p));
    }


    /**This function calculates the defuse that impacta on the color of the point on the object.
     * The defuse is the 3d (tlat meymad)
     * @param kd
     * @param nl
     * @param c
     * @return*/
    private Color calcDiffusive(double kd, double nl, Color c) {
        if (nl < 0)
            nl = -nl;//to make the angle(zavit) nl (erech moochlat)
        return c.scale(nl * kd);
    }


    private boolean sign(double val) {
        return (val > 0d);
    }


    /**This function calculates the color that will be colored on the view plane
     * @param gp geopoint
     * @return result*/
    private Color calcColor(GeoPoint gp) {
        Color result = scene.getAmbientLight().getIntensity();//result=ambientlight
        result=result.add(gp.getGeometry().getEmmission());//resukt+=emmition
        List<LightSource> lights = scene.getLightSources();

        //vector from point to the camera
        Vector v = (gp.getPoint().subtract(scene.getCamera().getSpo())).normalize();
        //normal to that point.
        Vector n = gp.getGeometry().getNormal(gp.getPoint());

        Material material = gp.getGeometry().getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD();
        double ks = material.getkS();
        if (scene.getLightSources() != null) {
            for (LightSource lightSource : lights) {
                //l=vector from light to point
                Vector l = lightSource.getL(gp.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));
                /**First will check if nl and nv are!=0 and equal.
                 * c=color after impacting the light on that specific point.
                 * Then we calculate the specular and defuse and then we add the color all together*/
                if (sign(nl) == sign(nv)) {
                    Color c = lightSource.getIntensity(gp.getPoint());
                    result=result.add(calcDiffusive(kd, nl, c), calcSpecular(ks, l, n, nl, v, nShininess,c));
                }
            }
        }
        return result;
    }


    /** This function in the render class tells the class ImageWrite to function writePixel
     *  how to color each fixel on the view plane. it uses calColor for that*/
    public void renderImage() {
        Camera camera = scene.getCamera();//fot the function thats in the camera.constr.. to build the rays.
        Intersectable geometries = scene.getGeometries();//list of geomertries for the functon in geometries.findinter..
        java.awt.Color background = scene.getBackground().getColor();
        double distance = scene.getDistance();

        //height and width of the view plane
        int width = (int) imageWriter.getWidth();
        int height = (int) imageWriter.getHeight();
        int Nx = imageWriter.getNx(); // number of squares in the Row (shura). we need it for the for
        int Ny = imageWriter.getNy(); //number of squares in the column.(amuda). we need it for the for

        /**for each pixel we will send ray, and with the function findIntersection
         * we will get the Geo points(point 3d, color).
         * if we got nothing so we will color with the back round color
         * if we got the GeoPoints We will send to the function getClosestPoint and color*/
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    imageWriter.writePixel(column, row, background);
                }
                else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }


    /*** Printing the grid with a fixed interval between lines
     * @param interval The interval between the lines.*/
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






