import java.io.FileWriter;
import java.io.IOException;

public class Method4 extends Method{
    private double x = 0, last_x, delta = 1;
    public Method4(double a, double b, double e, boolean isConsole) {
        super(a, b, e, isConsole, 4);
    }

    @Override
    public void calculate() throws IOException {
        printerTable.printHeader(isConsole, method, "x_i-1", "x_i", "x_i+1", "f(x_i+1)", "|x_i+1 - x_i|");
        while(delta > e) {
            x = b;
            last_x = a;
            double pre_last_x = last_x;
            x = x - (x - last_x) / (Function.f(x) - Function.f(last_x)) * Function.f(x);

            printerTable.printLine(isConsole, method, iteration++, pre_last_x, last_x, x, Function.f(x), Math.abs(x - last_x));

            if (Function.f(b) * Function.f(x) < 0) {
                a = x;
            } else {
                b = x;
            }
            delta = Math.min(Math.abs(last_x - x), Math.abs(Function.f(x)));
        }
        printerGraph.draw(a, b);
        finalOutput(x);
    }
}
