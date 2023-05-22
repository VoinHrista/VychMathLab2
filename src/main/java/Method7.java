import java.io.FileWriter;
import java.io.IOException;

public class Method7{
    private PrinterGraph printerGraph = new PrinterGraph();
    private PrinterTable printerTable = new PrinterTable();

    private int iteration = 0, number;
    private double ax = 0, bx = 0, ay = 0, by = 0, x, y, last_x, last_y, e;

    public Method7(int number, double x, double y, double e) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.e = e;
        switch (number){
            case 1 -> {
                ax = 0;
                ay = 0;
                bx = 1;
                by = 1;
            }
            case 2 -> {
                ax = 0;
                ay = 1;
                bx = 1;
                by = 2;
            }
            case 3 -> {
                ax = -2;
                ay = -3;
                bx = -1;
                by = -2;
            }
        }
    }


    public void calculate() throws IOException {
        double q;
        q = Math.max(Function.max_phi1_x(number, ax, bx, ay, by) + Function.max_phi1_y(number, ax, bx, ay, by),
                Function.max_phi2_x(number, ax, bx, ay, by) + Function.max_phi2_y(number, ax, bx, ay, by));
        if(q < 1)
            System.out.println("q = " + q + "\nПроцесс сходящийся\n\n");
        else {
            System.out.println("q = " + q + "\nПроцесс не сходящийся, метод не может быть исполнен");
            return;
        }

        last_x = x - 1;
        last_y = y - 1;

        printerTable.printHeader(true, 0, "x", "y", "|x(k) - x(k-1)|", "|y(k) - y(k-1)|");

        while (Math.abs(x - last_x) > e || Math.abs(y - last_y) > e) {
            printerTable.printLine(true, 0, iteration++, x, y, Math.abs(x - last_x), Math.abs(y - last_y));

            last_x = x;
            last_y = y;

            double tmp_x = x;
            x = Function.phi1(number, x, y);
            y = Function.phi2(number, tmp_x, y);

        }
        finalOutput();

        printerGraph.sysDraw(number, ax, bx, ay, by);

    }

    public void resolve(String str) throws IOException{
        System.out.println(str + "\n" +
                "Решение уравнения на интервале\nx ( " + ax + "; " + bx + " )\ny ( " + ay + "; " + by + " )\nТочность: " + e + "\n\n");

        calculate();
    }

    public void finalOutput() throws IOException{
        System.out.println("\nКорень уравнения:");
        System.out.println("x* = " + x);
        System.out.println("y* = " + y);
        System.out.println("f1(x*, y*) = " + Function.f1(number, x, y));
        System.out.print("f2(x*, y*) = " + Function.f2(number, x, y));
    }
}
