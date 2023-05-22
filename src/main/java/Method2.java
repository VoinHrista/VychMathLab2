import java.io.FileWriter;
import java.io.IOException;

/**
 * Метод секущих
 */
public class Method2 extends Method {
    PrinterGraph printerGraph = new PrinterGraph();
    PrinterTable printerTable = new PrinterTable();

    private double x = 0, last_x, delta = 1;

    public Method2(double a, double b, double e, boolean isConsole) {
        super(a, b, e, isConsole, 2);
    }

    public void calculate() throws IOException {
        printerTable.printHeader(isConsole, method, "a", "b", "x", "f(a)", "f(b)", "f(x)", "|x_n+1 - x_n|");
        last_x = a;
        while(delta > e) {
            last_x = x;
            x = (a * Function.f(b) - b * Function.f(a)) / (Function.f(b) - (Function.f(a)));

            printerTable.printLine(isConsole, method, iteration++, a, b, x, Function.f(a), Function.f(b), Function.f(x), Math.abs(last_x - x));

            if (Function.f(b) * Function.f(x) < 0) {
                a = x;
            } else {
                b = x;
            }
            delta = Math.min(Math.abs(a - b), Math.min(Math.abs(last_x - x), Math.abs(Function.f(x))));
        }
        printerGraph.draw(a, b);
        finalOutput(x);
    }

}
