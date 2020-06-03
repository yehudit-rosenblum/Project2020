package geometries;


import primitives.Color;
import primitives.Material;

/*** The RadialGeometry is is an abstract class that contains a radius.
 * All shapes that also contain radius inherit from it*/
public abstract class RadialGeometry extends Geometry {
    protected Double radius;


    /** Constructer
     * @param c gets Color
     * @param radius1 and radius*/
    public RadialGeometry(Color c, Material m, double radius1) {
        super(c, m);
        radius=radius1;
    }

    /**Constructer that gets the radius
     * and puts the default color black
     * @param radius1*/
    public RadialGeometry(double radius1) {
        this(Color.BLACK, new Material(0, 0, 0), radius1);

    }


    /** Copy Constructer
     * @param t gets a different constructer*/
    public RadialGeometry(RadialGeometry t) {
        radius = t.radius;
    }


    /**A getter for the radius
     * @return radius*/
    public Double getRadius() {
        return radius;
    }


    /**This Function prints the radius in the abstract RadialGeomtry.
     * @return String*/
    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }
}
