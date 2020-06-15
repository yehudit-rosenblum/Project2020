package primitives;

public class Material {
    private  double kD;
    private  double kS;
    private  int nShininess;
    private double kT; //transparansy ("Shakoof")
    private double kR; //("maraatiut")




    public Material(double kD1, double kS1, int nShininess1, double kT1, double kR1) {
        kD = kD1;
        kS = kS1;
        nShininess = nShininess1;
        kT=kT1;
        kR=kR1;
    }


    public Material(double kD1, double kS1, int nShininess1) {

        this(kD1, kS1, nShininess1, 0, 0);
    }




    public double getkD() {
        return kD; }

    public double getkS() {
        return kS;
    }

    public int getnShininess() {
        return nShininess;
    }

    public double getkT() {
        return kT;
    }


    public double getkR() {
        return kR;
    }
}
