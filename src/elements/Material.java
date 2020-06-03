package elements;



public class Material {
    private double kd;
    private double ks;
    private double nShininess;

    /**Construcor that gets
     * @param kd1 defuse
     * @param ks1 specular
     * @param nShininess1*/
    public Material(double kd1, double ks1, int nShininess1){
        kd=kd1;
        ks=ks1;
        nShininess=nShininess1;
    }

    public double getKd() {
        return kd;
    }

    public double getKs() {
        return ks;
    }

    public double getnShininess() {
        return nShininess;
    }
}
