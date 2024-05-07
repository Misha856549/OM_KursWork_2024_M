    import java.util.function.Function;
    public class TrapezoidalIntegration {
        public static double trapezoidalRule(double a, double b, double h, Function<Double, Double> f) {

            double I = f.apply(a) + f.apply(b);
            for(double x = a+h; x < b; x += h) {
                I = I + 2 * f.apply(x);
            }
            return I*h/2;
        }
    
        public static double estimateError(double a, double b, double h){
            double n = (b - a) / h;
            double m2 = 3.02; // max f''(x) on [a, b]    f(x)'' = -16x^2*cos(2x^2)-4sin(2x^2)    max = 3.02
            return (n*Math.pow(h, 3))/12 * m2;
        }
    }