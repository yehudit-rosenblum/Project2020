package primitives;

/**
 * The Point3d class is in the primitive forms.
 * This is a three-point point that contains 3 coordinates on the graph axis.
 */
public  class Point3D {
    Coordinate x,y,z;
    public static final Point3D ZERO = new Point3D(0.0, 0.0, 0.0);


    /**
     * Constructor for creating a point from 3 cooridantes.
     * @param x1,x2,x3 coordinates.
     */
    public Point3D(Coordinate x1, Coordinate y1, Coordinate z1) {
        x= x1;
        y=y1;
        z=z1;
    }

    /**
     * Constructor for creating a Point3D from three double numbers.
     * Each number will go to a coordinate.
     * @param x1,x2,x3 double numbers.
     */
    public  Point3D(double x1, double y1, double z1){
        Coordinate x2=new Coordinate(x1);
        Coordinate y2=new Coordinate(y1);
        Coordinate z2=new Coordinate(z1);
        x=x2;
        y=y2;
        z=z2;
    }

    /**
     * A copy constructor for creating a Point3D from another point3D.
     * @param other point3D.
     */
    public Point3D(Point3D other) {
        x=other.getX();
        y=other.getY();
        z=other.getZ();
    }

    /**
     * getter for x.
     * @return x
     */
    public Coordinate getX() {
        return x;
    }

    /**
     * getter for y.
     * @return y
     */
    public Coordinate getY() {
        return y;
    }

    /**
     * getter for z.
     * @return z.
     */
    public Coordinate getZ() {
        return z;
    }


    /**
     * This Function subtracts Two points. myPoint mines the other point.
     * @param other Point
     * @return a new Vector with the new Point
     */
    public Vector subtract(Point3D other){
        Coordinate x2=new Coordinate(x.get()-other.x.get());
        Coordinate y2=new Coordinate(y.get()-other.y.get());
        Coordinate z2=new Coordinate(z.get()-other.z.get());
        Point3D p=new Point3D(x2, y2, z2);
        Vector v=new Vector(p);
        return v;
    }


    /**
     * Adds two points. gets a vector.
     * @param other vector
     * @return a new Point
     */
    public Point3D add(Vector other)
    {
        Coordinate x2=new Coordinate(other.head.x._coord+x._coord);
        Coordinate y2=new Coordinate(other.head.y._coord+y._coord);
        Coordinate z2=new Coordinate(other.head.z._coord+z._coord);
        Point3D p=new Point3D(x2, y2, z2);
        return p;

    }

    /**
     * starts the calculation of the distanse between two points.
     * @param other Point.
     * @return a double number.
     */
    public double  distanceSquared(Point3D other) {
        return ((other.x._coord-this.x._coord)*(other.x._coord-this.x._coord)
                +(other.y._coord-this.y._coord)*(other.y._coord-this.y._coord)
                +(other.z._coord-this.z._coord)*(other.z._coord-this.z._coord));
    }

    /** Ends the calculation of the distance between the two points.
     * by doing math.sqrt from the last function.
     * @param p point
     * @return a double number*/
    public double distance (Point3D p){
        return Math.sqrt(distanceSquared(p));
    }

    /**Class ClassName is the basic class representing a … of Euclidean geometry in Cartesian
     * 3-Dimensional coordinate system.
     * @author Student1 and Student2*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D) obj;
        return x.equals(oth.x) && y.equals(oth.y) && z.equals(oth.z);
    }



    /**
     * prints the three Coordinates in the Point3D
     * @return String
     */
    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}