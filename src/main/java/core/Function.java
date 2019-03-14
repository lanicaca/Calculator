package core;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import java.util.ArrayList;


public class Function {

    private String expression;
    private ArrayList<Double> y_coords;
    private ArrayList<Double> x_coords;

    public Function(String expression) {
        this.expression = expression;
        y_coords = new ArrayList<>();
        x_coords = new ArrayList<>();
    }

    ArrayList<Double> getY_coords() {
        return y_coords;
    }

    ArrayList<Double> getX_coords() {
        return x_coords;
    }

    double FunctionReturnValue(double x) throws UnparsableExpressionException, UnknownFunctionException {
        Calculable calc;
        calc = new ExpressionBuilder(expression)
                .withVariable("x", x)
                .build();
        Double result = calc.calculate();
        x_coords.add(x);
        y_coords.add(result);
        if (result.isInfinite() || result.isNaN()) return 0;
        return result;
    }
}