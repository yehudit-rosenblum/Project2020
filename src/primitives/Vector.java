package primitives;


/**The vector class is in the primitive forms.
 * It has a head point, that shoews the direction and lengh of thr Vector
 * The head is a point with 3 coordinates.
 * The vector starts from the beginning of the axes.*/
public class Vector {
    Point3D head;


    /**Constructor for creating a vector from three coordinate.
     * @param x1 coordinate for the x axis
     * @param y1 coordinate for the y axis
     * @param z1 coordinate for the z axis
     * @throws IllegalArgumentException if the three coordinates input value are zero.*/
    public Vector(Coordinate x1, Coordinate y1, Coordinate z1) {
        Point3D p = new Point3D(x1, y1, z1);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("no meaning to the vector");
        head=new Point3D(p);
    }


    /**Constructor for creating a vector from three double numbers.
     * @param x1 double number for the x axis.
     * @param y1 double number for the y axis.
     * @param z1 double number for the z axis.
     * @throws IllegalArgumentException if the three double numbers input value are zero.*/
    public Vector(double x1, double y1, double z1) {
        Point3D p = new Point3D(x1, y1, z1);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("no meaning to the vector");
        head=new Point3D(p);
    }


    /**
     * Constructor for creating a vector from a Point3D.
     * @param p point3D for the direction.
     * @throws IllegalArgumentException if the point is eqeul to ZERO point.
     */
    public Vector(Point3D p) {
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("no meaning to the vector");
        head = new Point3D(p);
    }


    /**
     * Constructor for creating a vector from another Vector.
     * And also Copy constructor from other Vector.
     * @param other vector to copy.*/
    public Vector(Vector other) {

        head =new Point3D(other.head);
    }


    /**head getter
     * @return the head*/
    public Point3D getHead() {
        return head;
    }

    /**Subtracts between the two points in the vectors.
     * And returns a new vector.
     * @param other vector
     * @return new vector.*/
    public Vector subtract (Vector other) {
        Vector t =new Vector(head.subtract(other.head));
        return t;
    }

    /**Adds the head point of my Vector with the head of the other Vector
     * @param other Vector to add.
     * @return a new vector with a new point head.*/
    public Vector add(Vector other) {
        Point3D p=new Point3D (head.add(other));
        Vector v = new Vector(p);
        return v;
    }


    /**Multiplise the head point of the Vector with a number.
     * @param num double.
     * @return a new vector with a new point head.*/
    public Vector scale(double num) {
        Coordinate x2=new Coordinate (head.x._coord * num);
        Coordinate y2=new Coordinate (head.y._coord *num);
        Coordinate z2= new Coordinate (head.z._coord * num);
        Vector t=new Vector(x2, y2, z2);
        return  t;
    }

    /**Multiplices between 2 vectors.
     * And then returns a scalar.
     * @param other Vector
     * @return a double number.*/
    public double dotProduct(Vector other) {
        double num= (head.x._coord*other.head.x._coord+head.y._coord*other.head.y._coord
                +head.z._coord*other.head.z._coord);
        return num;
    }

    /**This function multiplices between 2 vectors.
     * And then returns the standing vector of both.
     * @param t Vector
     * @return a new Vector which is standing to both Vectors.*/
    public Vector crossProduct(Vector t){
        Vector v=new Vector(head.y.get()*t.head.getZ().get()-
                t.head.getY().get()*head.z.get(),head.z.get()*t.head.getX().get()-
                t.head.getZ().get()*head.x.get(),head.x.get()*t.head.getY().get()-
                t.head.getX().get()*head.y.get());
        return v;
    }


    /**This function does the begining of the Calculataion of lenths vector.
     * @return the calculation but still with out the sqrt.*/
    public double lengthSquared () {
        return (double) (head.x.get()*head.x.get())+
                (head.y.get()*head.y.get())+ (head.z.get()*head.z.get());
    }

    /**This function does the end of the Calculataion of lenths vector.
     * @return the sqrt of  the last functions.*/
    public double length () {
        return Math.sqrt(lengthSquared());
    }


    /**This function nurmilzes the current vector and changes it.
     * @return the current vector after numilazing its coordinates.*/
    public Vector normalize() {
        double num=1/length();
        head = scale(num).head;
        return this;
    }



    /**Creates a new Vector that is normilazed.
     * It puts in the Head point the nurmilazed coordinets that came back from the last function.
     * @return a new Vector that is nurmilazed.*/
    public Vector normalized() {
        Vector t=new Vector(normalize());
        return t;
    }

    /**
     * This function cheks if two Vectors are equeled
     * @param obj Object.
     * @return a boolianed answer.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector oth = (Vector) obj;
        return head.equals(oth.head);
    }


    /**
     *  prints the head in the Vector
     * @return String
     */
    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head.toString()+'}';
    }
}
