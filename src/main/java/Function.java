import java.beans.Expression;
import java.util.function.UnaryOperator;

public class Function {
    final static double a = -2.7, b = -1.48, c = 19.23, d = 6.35;
    double phi_a, phi_b, phi_c, phi_d;

    public void setPhi(double a, double b, double c, double d){
        phi_a = a;
        phi_b = b;
        phi_c = c;
        phi_d = d;
    }

    public static double f(double x) { //f(x)
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }

    public double phi(double x){
        return phi_a * Math.pow(x, 3) + phi_b * Math.pow(x, 2) + phi_c * x + phi_d;
    }
    public double phi_(double x){
        return phi_a * 3 * Math.pow(x, 2) + phi_b * 2 * x + phi_c;
    }

    public static double f_(double x){ //f'(x)
        return a * 3 * Math.pow(x, 2) * b * 2 * x + c;
    }

    public static double f__(double x){ //f''(x)
        return a * 6 * x + b * 2;
    }


    public static double f1(int number, double x, double y){
        switch (number) {
            case 1 -> {
                return 0.1 * Math.pow(x, 2) + x + 0.2 * Math.pow(y, 2) - 0.3; //f1 = 0.1 x^2 + x + 0.2 y^2 - 0.3
            }
            case 2 -> {
                return Math.pow(x, 2) + Math.pow(y, 2) - 4; //f1 = x^2 + y^2 - 4
            }
            case 3 -> {
                return Math.sin(x - 0.6) - y - 1.6; //f1 = sin(x - 0.6) - y - 1.6
            }
            default -> {
                return 0;
            }
        }
    }

    public static double f2(int number, double x, double y){
        switch (number) {
            case 1 -> {
                return 0.2 * Math.pow(x, 2) + y + 0.1 * x * y - 0.7; //f2 = 0.2 x^2 + x + 0.1 xy - 0.7
            }
            case 2 -> {
                return -3 * Math.pow(x, 2) + y; //f2 = -3 x^2 + y
            }
            case 3 -> {
                return 3 * x - Math.cos(y) - y;  //f2 = 3x - cos(y) - y
            }
            default -> {
                return 0;
            }
        }
    }

    public static double phi1(int number, double x, double y){
        switch (number) {
            case 1 -> {
                return 0.3 - 0.1 * Math.pow(x, 2) - 0.2 * Math.pow(y, 2); //x = 0.3 - 0.1 x^2 - 0.2 y^2
            }
            case 2 -> {
                return Math.pow(x, 2) + Math.pow(y, 2) - 4 + x; //x = x^2 + x + y^2 - 4
            }
            case 3 -> {
                return Math.sin(x - 0.6) - y - 1.6; //x = sin(x - 0.6) - y - 1.6
            }
            default -> {
                return 0;
            }
        }
    }

    public static double phi2(int number, double x, double y){
        switch (number) {
            case 1 -> {
                return 0.7 - 0.2 * Math.pow(x, 2) - 0.1 * x * y; //y = 0.7 - 0.2 x^2 - 0.1 xy
            }
            case 2 -> {
                return -3 * Math.pow(x, 2) + 2 * y; //y = -3 x^2 + 2 y
            }
            case 3 -> {
                return 3 * x + y - Math.cos(y); //f2 = 3x - cos(y)
            }
            default -> {
                return 0;
            }
        }
    }

    public static double phi1_x(int number, double x, double y){ //d(phi_1)/d(x)
        switch (number) {
            case 1 -> {
                return -0.2 * x;
            }
            case 2 -> {
                return 2 * x + 1;
            }
            case 3 -> {
                return Math.cos(x - 0.6);
            }
            default -> {
                return 0;
            }
        }
    }

    public static double phi2_x(int number, double x, double y){ //d(phi_2)/d(x)
        switch (number) {
            case 1 -> {
                return -0.4 * x - 0.1 * y;
            }
            case 2 -> {
                return -6 * x;
            }
            case 3 -> {
                return 3;
            }
            default -> {
                return 0;
            }
        }
    }

    public static double phi1_y(int number, double x, double y){ //d(phi_1)/d(y)
        switch (number) {
            case 1 -> {
                return -0.4 * y;
            }
            case 2 -> {
                return 2 * y;
            }
            case 3 -> {
                return -1;
            }
            default -> {
                return 0;
            }
        }
    }

    public static double phi2_y(int number, double x, double y){ //d(phi_2)/d(y)
        switch (number) {
            case 1 -> {
                return -0.1 * x;
            }
            case 2 -> {
                return 2;
            }
            case 3 -> {
                return Math.sin(y) + 1;
            }
            default -> {
                return 0;
            }
        }
    }

    public static double max_phi1_x(int number, double x1, double x2, double y1, double y2){
        return Math.max(Math.max(Math.abs(phi1_x(number, x1, y1)), Math.abs(phi1_x(number, x1, y2))), Math.max(Math.abs(phi1_x(number, x2, y1)), Math.abs(phi1_x(number, x2, y2))));
    }

    public static double max_phi2_x(int number, double x1, double x2, double y1, double y2){
        return Math.max(Math.max(Math.abs(phi2_x(number, x1, y1)), Math.abs(phi2_x(number, x1, y2))), Math.max(Math.abs(phi2_x(number, x2, y1)), Math.abs(phi2_x(number, x2, y2))));
    }

    public static double max_phi1_y(int number, double x1, double x2, double y1, double y2){
        return Math.max(Math.max(Math.abs(phi1_y(number, x1, y1)), Math.abs(phi1_y(number, x1, y2))), Math.max(Math.abs(phi1_y(number, x2, y1)), Math.abs(phi1_y(number, x2, y2))));
    }

    public static double max_phi2_y(int number, double x1, double x2, double y1, double y2){
        return Math.max(Math.max(Math.abs(phi2_y(number, x1, y1)), Math.abs(phi2_y(number, x1, y2))), Math.max(Math.abs(phi2_y(number, x2, y1)), Math.abs(phi2_y(number, x2, y2))));
    }

}
