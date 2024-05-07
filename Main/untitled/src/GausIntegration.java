import java.util.function.Function;
public class GausIntegration{

    public static double gausRule(double a, double b, double h, Function<Double, Double> f){

        double[] t = {-0.57735027, 0.57735027};
        double[] A = {1, 1};

        double I = 0;

        for (double i = a; i< b; i+=h) {
            double i1 = i + h;
            double F = 0;
            for (int j = 0; j < t.length; j++) {
               double x = (i + i1)/2 + ((i1-i)/2)*t[j];
                F = F + A[j]*f.apply(x);
            }
            I = I + ((i1-i)/t.length)*F;
        }
        return I;
    }

    public static double calculateFactorial(double n) {
        return (n == 0) ? 1 : n * calculateFactorial(n - 1);
    }

    public static double estimateError(double a, double b, double h ,Function<Double, Double> f) {
        double n = (b - a) / h;
        return (Math.pow((b-a), 2*n+1)*(Math.pow(calculateFactorial(n), 4)))
                /((Math.pow(calculateFactorial(2*n),3))*(2*n+1));
    }
}