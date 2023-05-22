import java.io.FileWriter;
import java.io.IOException;

public class Method1 extends Method{
    private double x, delta = 1;
    public Method1(double a, double b, double e, boolean isConsole) {
        super(a, b, e, isConsole, 1);
    }

    @Override
    public void calculate() throws IOException {
        printerTable.printHeader(isConsole, method, "a", "b", "x", "f(a)", "f(b)", "f(x)", "|a - b|");

        while(delta > e) {
            x = (a + b) / 2;

            printerTable.printLine(isConsole, method, iteration++, a, b, x, Function.f(a), Function.f(b), Function.f(x), Math.abs(a - b));

            if (Function.f(b) * Function.f(x) < 0) {
                a = x;
            } else {
                b = x;
            }
            delta = Math.min(Math.abs(a - b), Math.abs(Function.f(x)));
        }

        printerGraph.draw(a, b);
        finalOutput(x);
    }
}
