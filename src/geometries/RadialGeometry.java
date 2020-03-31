package geometries;

public abstract class RadialGeometry implements Geometry{
    protected Double radius;

    /**
     * The RadialGeometry is is an abstract class that contains a radius.
     * All shapes that also contain radius inherit from it
     */

public RadialGeometry(double radius1) {
         radius=radius1;
}

public RadialGeometry(RadialGeometry t){
        radius=t.radius;}


    public Double getRadius() {
        return radius;
    }


    /**
     * This Function prints the radius in the abstract RadialGeomtry.
     * @return String
     */
    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }
}
