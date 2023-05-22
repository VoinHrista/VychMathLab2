import java.io.FileWriter;
import java.io.IOException;

public abstract class Method {
    PrinterGraph printerGraph = new PrinterGraph();
    PrinterTable printerTable = new PrinterTable();

    protected double a, b, e;
    protected int iteration = 0, method;
    protected boolean isConsole;

    public Method(double a, double b, double e, boolean isConsole, int method) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.isConsole = isConsole;
        this.method = method;
    }

    public abstract void calculate() throws IOException;
    public void resolve(String str) throws IOException{
        FileWriter writer = null;
        if(isConsole){
            System.out.println(str + "\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nТочность: " + e + "\n\n");
        } else {
            writer = new FileWriter("src/main/res/out" + method + ".txt", false);
            writer.write(str + "\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nТочность: " + e + "\n\n");
            writer.close();
        }

        calculate();
    }

    public void finalOutput(double x) throws IOException{
        if(!isConsole) {
            FileWriter writer = new FileWriter("src/main/res/out" + method + ".txt", true);
            writer.write("\nКорень уравнения:\n");
            writer.write("x* = " + x + "\n");
            writer.write("f(x*) = " + Function.f(x));
            writer.close();
        } else {
            System.out.println("\nКорень уравнения:");
            System.out.println("x* = " + x);
            System.out.print("f(x*) = " + Function.f(x));
        }
    }
}
