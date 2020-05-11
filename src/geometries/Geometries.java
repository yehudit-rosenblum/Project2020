package geometries;
import primitives.*;
import java.util.ArrayList;
import java.util.List;


public class Geometries implements Intersectable {
    /**
     * Array List was chosen because its faster to have accesses to the values then in linked List
     */
    private List<Intersectable> geometries=new ArrayList<>();


    /**
     * Default Constructor for creating a empty list of geometries.
     */
    public Geometries(){

        geometries=new ArrayList<>();
    }


    /**
     * Constructor for creating a list of different geometries.
     ** @param geometries eny number of geometries to put in the list.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable...geo) {
        for (Intersectable g: geo)
            geometries.add(g);
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;
        for (Intersectable g : geometries) {
            List<Point3D> tempintersection = g.findIntersections(ray);
            if (tempintersection != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempintersection);
            }
        }
                    return intersections;
    }
}
