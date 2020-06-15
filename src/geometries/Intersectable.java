package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;



/**
 * This Intersectable class is in interface. The interface Geometry class inherits from this class.
 * It Contains a function that gets a ray and returns a list of points that the ray intersected with.
 * All geometric classes implements what is in the geometry and this function.
 */
public interface Intersectable {

    public static class GeoPoint {
        protected Geometry geometry;
        protected Point3D point;


        public GeoPoint(Geometry g, Point3D p) {
            geometry = g;
            point = p;
        }

        public void setPoint(Point3D point) {
            this.point = point;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public Point3D getPoint() {
            return point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Point3D)) return false;
            GeoPoint oth = (GeoPoint) obj;
            return geometry.equals(oth.geometry) && point.equals(oth.geometry);
        }

    }

    List<GeoPoint> findGeoIntersections(Ray ray);

    default List<Point3D> findIntersections(Ray ray) {
        List<GeoPoint> l1 = findGeoIntersections(ray);
        if (l1 == null) return null;
        List<Point3D> l2 = new LinkedList<>();
        for (GeoPoint gp : l1)
            l2.add(gp.point);
        return l2;
    }

}
