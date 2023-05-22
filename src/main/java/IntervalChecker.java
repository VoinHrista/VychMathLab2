
public class IntervalChecker {

    public boolean checkInterval(double a, double b){
        for(double i = a; i <= b - 0.1; i += 0.1){
            if(Function.f(i) * Function.f(i + 0.1) < 0)
                return true;
        }

        if(Function.f(a) * Function.f(b) < 0)
            return true;
        return false;
    }

    public int checkIntervalCount(double a, double b){
        int count = 0;
        for(double i = a; i <= b - 0.1; i += 0.1){
            if(Function.f(i) * Function.f(i + 0.1) < 0)
                count++;
        }
        return count;
    }

}
