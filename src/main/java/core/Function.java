package core;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.util.ArrayList;



public class Function {

    private String expression;

    ArrayList<Double> getY_coords() {
        return y_coords;
    }


    ArrayList<Double> getX_coords() {
        return x_coords;
    }

    private ArrayList<Double> y_coords;
    private ArrayList<Double> x_coords;

    public Function(String expression) {
        this.expression = expression;
        y_coords = new ArrayList<>();
        x_coords = new ArrayList<>();
    }

    double FunctionReturnValue(double x) {
        Calculable calc;
        try {
            calc = new ExpressionBuilder(expression)
                    .withVariable("x", x)
                    .build();
            Double result = calc.calculate();
            x_coords.add(x);
            y_coords.add(result);
            System.out.println("Value of func in : "+x+" is  " + result);
            if (result.isInfinite() || result.isNaN()) return 0;

            return result;
        } catch (UnknownFunctionException | UnparsableExpressionException e) {
            e.printStackTrace();
            return 0;
        }
    }
}