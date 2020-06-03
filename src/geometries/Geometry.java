package geometries;
import primitives.*;

/**
 * This geometry abstract class contains the color of a geometric shape.
 * And also a function that returns the normal for any point on a shape.
 * All geometric classes inherit the color and implement this function.*/
public abstract class Geometry implements Intersectable {
    protected Color emmission;
    protected Material material;



    /** Constructor that gets the color of the shape and a material.
     * @param emission1 Color
     * @param material1*/
    public Geometry(Color emission1, Material material1) {
        emmission= new Color(emission1);
        material = material1;
    }


    /** Constructor that gets the color of the shape and puts a default material;
     * @param emmission1 Color*/
    public Geometry(Color emmission1) {
        this(emmission1, new Material(0d, 0d, 0));
    }


    /** Default Constructor that puts a default color black and a default material*/
    public Geometry() {
        this(Color.BLACK, new Material(0, 0, 0));
    }


    /**A emmeission getter
     * @return Emmission color*/
    public Color getEmmission(){
        return emmission;}


    /**A material getter
     * @return the material of the shape*/
     public Material getMaterial() {
         return material;
     }


    /**This function is abstract and all the different geometric shapes
     * will do this function.
     * @param p point in the shape.
     * @return normal Vector to the shape.*/
    public abstract Vector getNormal(Point3D p);
}
