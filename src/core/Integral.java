package core;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import javafx.fxml.FXML;

public class Integral {

    public static final double INCREMENT = 0.1;

    private double area;

    public double IntegralReturnValue(double a, double b, Function function) throws UnknownFunctionException, UnparsableExpressionException {
        int modifier = 1;
        if (a > b){
            double temp = a;
            a = b;
            b = temp;
            modifier = -1;
        }
        double i = a;
        while (i < b){
            i+=INCREMENT;
            double dFromA = i - a;
            area += (INCREMENT / 2) * (function.FunctionReturnValue(a + dFromA) +
                    function.FunctionReturnValue(a + dFromA - INCREMENT));
            System.out.println("current area is:  "+area);
            System.out.println("i = "+i+"   b=  "+b+"  a=   "+a+"  dFroma=  "+dFromA);


        }
        return (Math.round(area * 1000.0) / 1000.0) * modifier;
    }
}
