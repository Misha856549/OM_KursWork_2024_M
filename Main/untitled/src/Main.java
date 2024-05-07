import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    static Function<Double, Double> f = x -> {  // Задана функція
        double d = 2.0;
        double c = 2.0;
        return (c * x) / 2 + Math.cos(d * x * x);
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the left limit of integration (a):");
        double a;
        while (true) {
            try {
                a = Double.parseDouble(scanner.nextLine()); // Ліва межа інтегрування
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }

        double b;
        while(true) {
            System.out.println("Enter the right limit of integration (b):");
            try {
                b = Double.parseDouble(scanner.nextLine()); // Права межа інтегрування
                if (b > a) {
                    break;
                } else {
                    System.out.println("The right limit of integration must be greater than the left limit of integration.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }

        int steps;
        while (true) {
            System.out.println("Enter the number of steps:");
            try {
                steps = Integer.parseInt(scanner.nextLine()); // Кількість кроків інтегрування
                if (steps > 0) {
                    break;
                } else {
                    System.out.println("The number of steps must be greater than 0.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }

        scanner.close();

        double[] h = new double[steps]; // Масив для збереження кроків інтегрування

        for (int i = 0; i < steps; i++) {
            System.out.println("Enter step " + (i + 1) + ":");
            try {
                h[i] = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                i--; // зменшуємо значення і, щоб повторити цикл для введення правильного значення
            }
        }

        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println("------------------------------------------------------------\n");
        for (double i : h) {
            System.out.println("Step " + (i + 1) + ":");
            System.out.println("Trapezoid method h = " + i + " I = " + df.format(TrapezoidalIntegration.trapezoidalRule(a, b, i, f)));
            System.out.println("Error Trapezoid  h = " + i + " E = " + df.format(TrapezoidalIntegration.estimateError(a, b, i)) + "\n");
            System.out.println("Gaus method      h = " + i + " I = " + df.format(GausIntegration.gausRule(a, b, i, f)));
            System.out.println("Error Gaus       h = " + i + " E = " + df.format(GausIntegration.estimateError(a, b, i, f)) + "\n");
            System.out.println("Chebyshev method h = " + i + " I = " + df.format(ChebishovIntegration.chebishovRule(a, b, i, f)));
            System.out.println("Error Chebyshev  h = " + i + " E = " + df.format(ChebishovIntegration.estimateError(a, b, i)) + "\n");
            System.out.println("------------------------------------------------------------\n");
        }
    }
}