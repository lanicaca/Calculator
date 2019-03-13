package core;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Integral {

    private static final Logger log = Logger.getLogger(Integral.class);

    private static final double INCREMENT = 0.01;

    private double area;

    Function getFunction() {
        return function;
    }

    private Function function;
    private int a, b;

    public Integral(Function function, int a, int b){
        this.function = function;
        this.a = a;
        this.b = b;
    }

    public double IntegralReturnValue(Function function) throws UnknownFunctionException, UnparsableExpressionException {
        this.function = function;
        int modifier = 1;
        double a = (double) this.a;
        double b = (double) this.b;
        if (a > b){
            double temp = a;
            a = b;
            b = temp;
            modifier = -1;
        }
        double i = a + INCREMENT;
        log.info("before while");
        while (i < b){
            double dFromA = i - a;

            area += (INCREMENT / 2) * (this.function.FunctionReturnValue(round(a+dFromA,5))+
                    this.function.FunctionReturnValue(round(a + dFromA - INCREMENT,5)));
            i+=INCREMENT;
            //System.out.println("Final area: "+area);
            log.info("end of while");
        }
        log.info("Before return integral value area:   "+area);
        return Math.round(area*modifier);
    }
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
