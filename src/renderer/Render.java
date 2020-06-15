package renderer;
import geometries.Geometries;
import primitives.*;
import primitives.Material;
import scene.Scene;
import elements.*;
import java.util.List;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;
import geometries.Intersectable;


/** The Render class is in the Renderer.
 * It is responsible for taking the scene and actually creating the image.
 * It contains a class Scene(name, backround color, ambient light, list of geometries, camera and distance from view plane)
 * And also a class imageWriter*/
public class Render {
    Scene scene;
    ImageWriter imageWriter;
    private static final double DELTA = 0.1;

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    //lower then this number the (shkefut/maraa)has no power on the pixel because its to many objects away or one of the wes atum or not shakuf.
    private static final double MIN_CALC_COLOR_K = 0.001;

    private double superSamplingRate;//how crouded will I send the rays from  each other.



    /**Constructor for creating the Render
     * gets a scene.
     * @param scene1*/
    public Render(Scene scene1) {
        scene = scene1;
    }


    /**Constructor for creating the Render
     * gets a scene and the imageWriter.
     * @param scene1*/
    public Render(ImageWriter imageWriter1, Scene scene1) {
        imageWriter = imageWriter1;
        scene = scene1;
    }


    /**
     * This function activates the  writeToimage function that produces a png file*/
    public void writeToImage() {
        imageWriter.writeToImage();
    }





    /**This function gets a list of geo points.
     * And returns the closest geo point from the camera.
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


    /**This function calculates the specular that also impacts on the
     * color of the point on the shape
     * (specular is the shinnies on the object).
     * @param ks
     * @param l  the vector from the light to the point on object
     * @param n
     * @param nl
     * @param v the vector from camera direction
     * @param nShininess
     * @param ip
     * @return Color*/
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
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


    /**This function calculates the defuse that impacts
     * on the color of the point on the object.
     * The defuse is the 3d (tlat meymad)
     * @param kd
     * @param nl
     * @param c
     * @return a color that he got already of the point and adds the diffuse.*/
    private Color calcDiffusive(double kd, double nl, Color c) {
        if (nl < 0)
            nl = -nl;//to make the angle(zavit) nl (erech moochlat)
        return c.scale(nl * kd);
    }


    /**the image writer calls her with the specific ray
     * from camera and she will cal the down calcColor with the max level and a k=1
     * the highest merror or transparent(shakoof).*/
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        Color color = calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
        color = color.add(scene.getAmbientLight().getIntensity());//at the end he will add the ambient light
        return color;//return finnaly the color of that point that that specific ray from the camera sended
    }



    /**This function calculates the color that will be colored on the view plane
     * @param
     * @return result*/
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        if (level == 0 || k < MIN_CALC_COLOR_K) {
            return Color.BLACK;
        }
        Color result = geoPoint.getGeometry().getEmmission();
        Point3D pointGeo = geoPoint.getPoint();

        /**v=is the vector from camera strait ahead.
         * n=the vector normal to the point on that shape/object*/
        Vector v = pointGeo.subtract(scene.getCamera().getSpo()).normalize();
        Vector n = geoPoint.getGeometry().getNormal(pointGeo);

        Material material = geoPoint.getGeometry().getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD();
        double ks = material.getkS();
        double kr = geoPoint.getGeometry().getMaterial().getkR();//each time gets the amount of mirror in the object.
        double kt = geoPoint.getGeometry().getMaterial().getkT();//each time gets the amount of transparent in the object.
        double kkr = k * kr;//if kr/kt was small so it will smaller the kkr/kkt before we send to the function again.
        double kkt = k * kt;

        List<LightSource> lightSources = scene.getLightSources();
        if (lightSources != null) {
            for (LightSource light : lightSources) {//for each light will check
                Vector l = light.getL(pointGeo);//L=the vector from light to point
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));
                if (nl * nv > 0) {
                    /**how much shakof are all the objects between the light in the point. it can reduce the impact ot the light
                     * if there is no objects it will return 1. ktr*1=the same. no change.
                     * even if one is (atum) so it will block the light.
                     * and then will add the color with a (mekadem)that can make smaller the
                     * impact of the light because there are objects that are not so shakoof and disturb the color*/
                    double t = transparency(light, l, n, geoPoint);
                    if (t * k > MIN_CALC_COLOR_K) {
                        Color ip = light.getIntensity(pointGeo).scale(t);
                        result = result.add(calcDiffusive(kd, nl, ip),calcSpecular(ks, l, n, nl, v, nShininess, ip));
                    }
                }
            }
        }

        if (level == 1) {//Stop condition .we went a enough far away from the point
            return Color.BLACK;
        }

        /**A nother Stop condition.
         * if we in ecounterend a object with very little mirror so it smallered the kkr.
         * will check if the (mekadem), is still higher then the min number*/
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(pointGeo, inRay, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                result = result.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        if (kkt > MIN_CALC_COLOR_K) {//if the shkefut of the number is still high
            Ray refractedRay = constructRefractedRay(pointGeo, inRay, n);//so will send a ray from the knew object.
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);//ho is the point you got
            if (refractedPoint != null) {//if you got a point lets send that point again to see if he also is impacted.
                result = result.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return result;//returns the color that will be added to the point.
    }





    /**This function tells the function writePixel (in class ImageWrite)
     * how to color each pixel on the view plane. it uses calColor for that. it does the logic of caculating each pixcel*/
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
        if (superSamplingRate==0)//if there is no super sampling will do what we did untile now.
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findGeoIntersections(ray);
                if (intersectionPoints == null) {
                    imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint,ray).getColor();
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


    /**This function gets the refraction ray(shakoof) r that checks which other object
     * impact her color because shes is transparent(shakoof).
     * Then she creates a knew ray that's a little bit upper from the point to
     * the direction of the normal. And returns the knew ray.
     * @param point
     * @param inRay ray r.
     * @param n normal
     * @return a knew ray*/
    private Ray constructRefractedRay(Point3D point, Ray inRay, Vector n) {
        return new Ray(point, inRay.getDir(), n);
    }


    /**This function gets the reflection(maraa) ray that checks which other objects impacts here color
     *  because she is a mirror
     * Then she creates a knew ray that's a little bit upper from the point to
     * the direction of the normal. And returns the knew ray.
     * @param point
     * @param inRay ray r.
     * @param n normal
     * @return a knew ray*/
    private Ray constructReflectedRay(Point3D point, Ray inRay, Vector n) {
        Vector v = inRay.getDir();
        /**If crossProduct with v from camera to n normal=0
         * so the camera eny way will not see the color because the ray goes to the side*/
        double vn = v.dotProduct(n);
        if (vn == 0) {
            return null;
        }
        /**will calculate the r*/
        Vector r = v.subtract(n.scale(2 * vn));  //𝒓=𝒗−𝟐∙𝒗∙𝒏∙𝒏
        return new Ray(point, r, n);
    }


    /**
     *  This function bring a double number of how much the objects between the point in light
     *  are transparency (atum or shakoof). To knew how much impact the light has on the point.
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return a double number that can reduce the impact of the light.
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // switch to the opposite side from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);//build the Ray raised from the point.
        Point3D pointGeo = geopoint.getPoint();//the point.

        /**if the ray from point did not find eny objects so it will bring back 1=no change. do not reduse the impact of the light*/
        List<GeoPoint> intersections=null;
        intersections=scene.getGeometries().findGeoIntersections(lightRay);
        if (intersections == null) {
            return 1d;//all lights impact and go throw. there is no change.
        }
        double lightDistance = light.getDistance(pointGeo);
        double ktr = 1d;
        for (GeoPoint gp : intersections) {//adds all the objects that are in the middle in terms of their transparency.
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0) {
                ktr *= gp.getGeometry().getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K) {//if that specific object is atum so it will return 0.
                    return 0.0;
                }
            }
        }
        return ktr;//how much shakoof are all the different objects between the light to the point.
    }



    /**Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     * @param ray intersecting the scene
     * @return the closest point*/
    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null) {
            return null;
        }
        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.getPo();

        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null)
            return null;

        for (GeoPoint geoPoint : intersections) {
            double distance = ray_p0.distance(geoPoint.getPoint());
            if (distance < closestDistance) {
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }

}






