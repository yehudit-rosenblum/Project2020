package primitives;

/**
 * A dot class is in the primitive forms.
 * This is a three-point point that contains 3 coordinates on the graph axis.
 */
public  class Point3D {
    Coordinate x,y,z;
    public static final Point3D ZERO = new Point3D(0.0, 0.0, 0.0);


    public Point3D(Coordinate x1, Coordinate y1, Coordinate z1) {
        x= x1;
        y=y1;
        z=z1;
    }
     public  Point3D(double x1, double y1, double z1){
        Coordinate x2=new Coordinate(x1);
        Coordinate y2=new Coordinate(y1);
        Coordinate z2=new Coordinate(z1);
        x=x2;
        y=y2;
        z=z2;
     }


    public Point3D(Point3D other) {
    x=other.getX();
    y=other.getY();
    z=other.getZ();
    }

    public Coordinate getX() {
        return x;
    }
    public Coordinate getY() {
        return y;
    }
    public Coordinate getZ() {
        return z;
    }



    public  Vector subtract(Point3D other){
        Coordinate x2=new Coordinate(x.get()-other.x.get());
        Coordinate y2=new Coordinate(y.get()-other.y.get());
        Coordinate z2=new Coordinate(z.get()-other.z.get());
        Point3D p=new Point3D(x2, y2, z2);
        Vector v=new Vector(p);
        return v;
    }


       public Point3D add(Vector other)
       {
           Coordinate x2=new Coordinate(other.head.x._coord+x._coord);
           Coordinate y2=new Coordinate(other.head.y._coord+y._coord);
           Coordinate z2=new Coordinate(other.head.z._coord+z._coord);
           Point3D p=new Point3D(x2, y2, z2);
           return p;

       }

       public double  distanceSquared(Point3D other) {
           return (double)((other.x._coord-this.x._coord)*(other.x._coord-this.x._coord)
                   +(other.y._coord-this.y._coord)*(other.x._coord-this.x._coord)
                  +(other.z._coord-this.z._coord)*(other.z._coord-this.z._coord));
       }

    public double distance (Point3D p){
    return Math.sqrt(distanceSquared(p));
    }

    /**
     * Class ClassName is the basic class representing a … of Euclidean geometry in Cartesian
     * 3-Dimensional coordinate system.
     * @author Student1 and Student2
     */
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