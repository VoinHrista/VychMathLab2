import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        IntervalChecker checker = new IntervalChecker();
        Scanner consoleScanner = new Scanner(System.in);

        double a, b, e;
        int number;
        boolean isConsole;

        System.out.println("""
                Формат входного файла:
                1. Коэффициент а
                2. Коэффициент b
                3. Точность Epsilon
                4. Метод решения: 1/2/4/5
                5. Вывод результата в файл/консоль: true/false
                """);

        System.out.println("Введите: 0 - для чтения файла; 1 - для ввода с консоли");
        int inputNum = consoleScanner.nextInt();

        while (!(inputNum == 0 || inputNum == 1)) {
            System.out.println("Ошибка ввода!");
            System.out.println("Введите: 0 - для чтения файла; 1 - для ввода с консоли");
            inputNum = consoleScanner.nextInt();
        }

        if(inputNum == 0){
            FileReader reader = new FileReader("src/main/res/input.txt");
            Scanner scanner = new Scanner(reader);

            a = Double.parseDouble(scanner.nextLine().trim().replaceAll(",", "\\."));
            b = Double.parseDouble(scanner.nextLine().trim().replaceAll(",", "\\."));
            e = Double.parseDouble(scanner.nextLine().trim().replaceAll(",", "\\."));

            int method = Integer.parseInt(scanner.nextLine().trim());
            isConsole = Boolean.parseBoolean(scanner.nextLine().trim());

            if(Math.abs(a - b) < 0.1){
                System.out.println("Интервал должен быть не менее 0.1");
            } else {
                if(checker.checkInterval(a, b)){
                    if(checker.checkIntervalCount(a, b) > 1){
                        System.out.println("На интервале должен быть только один корень");
                        System.out.println("Количество корней на интервале: " + checker.checkIntervalCount(a, b));
                    } else {
                        System.out.println("Выполнение...");
                        switch (method){
                            case 1 -> {
                                Method1 method1 = new Method1(a, b, e, isConsole);
                                method1.resolve("Метод половинного деления");
                            }
                            case 2 -> {
                                Method2 method2 = new Method2(a, b, e, isConsole);
                                method2.resolve("Метод хорд");
                            }
                            case 4 -> {
                                Method4 method4 = new Method4(a, b, e, isConsole);
                                method4.resolve("Метод секущих");
                            }
                            case 5 -> {
                                Method5 method5 = new Method5(a, b, e, isConsole);
                                method5.resolve("Метод простой итерации");
                            }

                        }
                        if(!isConsole){
                            System.out.println("Результаты записаны в файл out" + method + ".txt");
                        }
                    }

                } else {
                    System.out.println("В интервале нет корней");
                }
            }
        } else {

            System.out.println("Введите:\n0 - для выполнения методов решения нелинейных уравнений\n1 - для выполнения метода решения системы нелинейных уравнений");
            inputNum = consoleScanner.nextInt();

            while (!(inputNum == 0 || inputNum == 1)) {
                System.out.println("Ошибка ввода!");
                System.out.println("Введите: 0 - для чтения файла; 1 - для ввода с консоли");
                inputNum = consoleScanner.nextInt();
            }

            if (inputNum == 0) {
                while (true) {
                    int answer = 0;

                    while (true) {
                        System.out.println("Введите a: ");
                        try {
                            a = Double.parseDouble(consoleScanner.next().trim().replaceAll(",", "\\."));
                            break;
                        } catch (NumberFormatException ignored) {
                            System.out.println("Повторите ввод");
                        }
                    }

                    while (true) {
                        System.out.println("Введите b: ");
                        try {
                            b = Double.parseDouble(consoleScanner.next().trim().replaceAll(",", "\\."));
                            break;
                        } catch (NumberFormatException ignored) {
                            System.out.println("Повторите ввод");
                        }
                    }

                    while (true) {
                        System.out.println("Введите точность Epsilon:");
                        try {
                            e = Double.parseDouble(consoleScanner.next().trim().replaceAll(",", "\\."));
                            break;
                        } catch (NumberFormatException ignored) {
                            System.out.println("Повторите ввод");
                        }
                    }

                    if (Math.abs(a - b) < 0.1) {
                        System.out.println("Интервал должен быть не менее 0.1");
                    } else {
                        if (checker.checkInterval(a, b)) {
                            if (checker.checkIntervalCount(a, b) > 1) {
                                System.out.println("На интервале должен быть только один корень");
                                System.out.println("Количество корней на интервале: " + checker.checkIntervalCount(a, b));
                            } else {
                                System.out.println("""
                                        Выберите метод решения:
                                        1. Метод половинного деления
                                        2. Метод хорд
                                        4. Метод секущих
                                        5. Метод простой итерации""");
                                answer = consoleScanner.nextInt();
                                do {
                                    switch (answer) {
                                        case 1 -> {
                                            System.out.println("Введите: 0 - для вывода в файл; 1 - для вывода в консоль");
                                            int flag = consoleScanner.nextInt();
                                            isConsole = flag == 1;

                                            Method1 method1 = new Method1(a, b, e, isConsole);
                                            method1.resolve("Метод половинного деления");

                                            if (!isConsole) {
                                                System.out.println("Результаты записаны в файл out1.txt");
                                            }
                                        }

                                        case 2 -> {
                                            System.out.println("Введите: 0 - для вывода в файл; 1 - для вывода в консоль");
                                            int flag = consoleScanner.nextInt();
                                            isConsole = flag == 1;

                                            Method2 method2 = new Method2(a, b, e, isConsole);
                                            method2.resolve("Метод хорд");

                                            if (!isConsole) {
                                                System.out.println("Результаты записаны в файл out2.txt");
                                            }
                                        }

                                        case 4 -> {
                                            System.out.println("Введите: 0 - для вывода в файл; 1 - для вывода в консоль");
                                            int flag = consoleScanner.nextInt();
                                            isConsole = flag == 1;

                                            Method2 method2 = new Method2(a, b, e, isConsole);
                                            method2.resolve("Метод секущих");

                                            if (!isConsole) {
                                                System.out.println("Результаты записаны в файл out4.txt");
                                            }
                                        }

                                        case 5 -> {
                                            System.out.println("Введите: 0 - для вывода в файл; 1 - для вывода в консоль");
                                            int flag = consoleScanner.nextInt();
                                            isConsole = flag == 1;

                                            Method5 method5 = new Method5(a, b, e, isConsole);
                                            method5.resolve("Метод простой итерации");

                                            if (!isConsole) {
                                                System.out.println("Результаты записаны в файл out5.txt");
                                            }
                                        }
                                        case 3 -> System.out.println("Программа не поддерживает этот метод решения");
                                    }
                                    System.out.println("Если хотите запустить другой метод решения, введите его номер, иначе введите ноль: ");
                                    answer = consoleScanner.nextInt();
                                } while (answer == 1 || answer == 4 || answer == 5);

                            }
                        } else {
                            System.out.println("В выбранном интервале нет корней");
                            break;
                        }
                    }
                    if (answer == 0)
                        break;
                }
            } else{
                double x, y;

                System.out.println("""
                                   1. f1(x, y) = 0.1x^2 + x + 0.2y^2 - 0.3 = 0
                                      f2(x, y) = 0.2x^2 + y + 0.1x y - 0.7 = 0
                                      x = (0; 1)
                                      y = (0; 1)
                                   2. f1(x, y) = x^2 + y^2 - 4 = 0
                                      f2(x, y) = -3x^2 + y = 0
                                      x = (-1; 0)
                                      y = (1; 2)
                                   3. f1(x, y) = x^2 + 4x - 3y + 2 = 0
                                      f2(x, y) = y^2 + 4y + 2x = 0
                                      x = (-5; -4)
                                      y = (1; 2)
                                   """);

                while (true) {
                    System.out.println("Выберите систему нелинейных уравнений: ");
                    try {
                        number = Integer.parseInt(consoleScanner.next().trim());
                        break;
                    } catch (NumberFormatException ignored) {
                        System.out.println("Повторите ввод");
                    }
                }

                while (true) {
                    System.out.println("Введите x_0: ");
                    try {
                        x = Double.parseDouble(consoleScanner.next().trim().replaceAll(",", "\\."));
                        break;
                    } catch (NumberFormatException ignored) {
                        System.out.println("Повторите ввод");
                    }
                }

                while (true) {
                    System.out.println("Введите y_0: ");
                    try {
                        y = Double.parseDouble(consoleScanner.next().trim().replaceAll(",", "\\."));
                        break;
                    } catch (NumberFormatException ignored) {
                        System.out.println("Повторите ввод");
                    }
                }

                while (true) {
                    System.out.println("Введите точность Epsilon: ");
                    try {
                        e = Double.parseDouble(consoleScanner.next().trim().replaceAll(",", "\\."));
                        break;
                    } catch (NumberFormatException ignored) {
                        System.out.println("Повторите ввод");
                    }
                }

                Method7 method7 = new Method7(number, x, y, e);
                method7.resolve("Метод простой итерации");

            }
        }
    }
}
