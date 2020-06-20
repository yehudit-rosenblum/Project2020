package elements;



public class Material {
    private double kd; //defuse (hetpashtut haor-tlatmeymad)
    private double ks; //specular (haor shechozer)
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

    /**A getter for the number of the defuse (hetpashtut haor-tlat meymad)
     * @return kd double number*/
    public double getKd() {
        return kd;
    }

    /**A getter for the number of the specular
     * @return ks double number*/
    public double getKs() {
        return ks;
    }


    /**A getter for the number int of the Shininess
     * @return ks int number*/
    public double getnShininess() {
        return nShininess;
    }
}
