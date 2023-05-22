import java.io.FileWriter;
import java.io.IOException;

public class Method5 extends Method{
    private double x = b, delta = 1, last_x;
    public Method5(double a, double b, double e, boolean isConsole) {
        super(a, b, e, isConsole, 5);
    }

    @Override
    public void calculate() throws IOException {
        printerTable.printHeader(isConsole, method, "x_i", "x_i+1", "phi(x_i+1)", "f(x_i+1)", "|x_i+1 - x_i|");
        Function function = new Function();
        int type = 0;
        while(delta > e) {

            if(type == 0){
                    double lamda = - 1 / Math.max(Math.abs(Function.f_(a)), Math.abs(Function.f_(b)));
                    function.setPhi(Function.a * lamda, Function.b * lamda, Function.c * lamda + 1, Function.d * lamda);
                    if(function.phi_(a) < 1 && function.phi_(a) >= 0 || function.phi_(b) < 1 && function.phi_(b) >= 0)
                        type = 2;
                    else {
                        type = 3;
                        if(isConsole)
                            System.out.println("Коэффициент сходимости превышает 1: q = " + Math.min(function.phi_(b), function.phi_(a)) + "\nНевозможно выполнить метод");
                        else{
                            FileWriter writer = new FileWriter("src/main/res/out" + method + ".txt", false);
                            writer.write("Метод просто итерации\nКоэффициент сходимости превышает 1: q = " + Math.min(function.phi_(b), function.phi_(a)) + "\nНевозможно выполнить метод");
                            writer.close();
                        }
                        break;
                    }

            }

            //System.out.println(Math.min(function.phi_(b), function.phi_(a)));

            last_x = x;
            x = function.phi(x);

            printerTable.printLine(isConsole, method, iteration++, last_x, x, function.f(x), Function.f(x), Math.abs(x - last_x));

            delta = Math.abs(last_x - x);
        }
        printerGraph.lambdaDraw(Math.round(a), Math.round(b), - 1 / Math.max(Math.abs(Function.f_(a)), Math.abs(Function.f_(b))));
        if(type != 3)
        finalOutput(x);
    }
}
