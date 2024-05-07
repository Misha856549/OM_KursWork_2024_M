import java.util.function.Function;

public class ChebishovIntegration {
    public static final int n = 6;
    public static final double[] t = {-0.866247, -0.422519, -0.266635, 0.266635, 0.422519, 0.866247};
    public static double chebishovRule(double a, double b, double h, Function<Double, Double> f) {
        double I = 0;
        for (double i = a; i < b; i += h) {
            double i1 = i + h;
            double F = 0;
            for (int j = 0; j < n; j++) {
                double x = (i + i1) / 2 + ((i1 - i) / 2) * t[j];
                F = F + f.apply(x);
            }
            I = I + ((i1 - i) / n) * F;
        }
        return I;
    }

    public static double estimateError(double a, double b, double h){
        double n = (b - a) / h;
        double m2 = 3.02; // max f''(x) on [a, b]    f(x)'' = -16x^2*cos(2x^2)-4sin(2x^2)    max = 3.02
        return ((n*Math.pow(h, 2))*m2)/(8*n*n);
    }
}