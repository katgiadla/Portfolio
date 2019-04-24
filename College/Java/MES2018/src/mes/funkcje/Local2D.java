package mes.funkcje;

public class Local2D {
    private double ksi, eta;
    private double x, y;

    public Local2D(double ksi, double eta) {
        this.ksi = ksi;
        this.eta = eta;
    }

    public double getKsi() {
        return ksi;
    }

    public double getEta() {
        return eta;
    }

    @Override
    public String toString() {
        return "Local2D{" +
                "ksi=" + ksi +
                ", eta=" + eta +
                '}';
    }

}
