package primitives;

public class Material {
    private  double kD;
    private  double kS;
    private  int nShininess;

    public Material(double kD1, double kS1, int nShininess1) {
        kD = kD1;
        kS = kS1;
        nShininess = nShininess1; }

    public double getkD() {
        return kD; }

    public double getkS() {
        return kS;
    }

    public int getnShininess() {
        return nShininess;
    }


}
