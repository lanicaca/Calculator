package core;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Function {

    private String expression;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    private int a;

    private int b;
    private ArrayList<Double> y_coords;
    private ArrayList<Double> x_coords;

    public Function(String expression){
        this.expression = expression;
        this.a = 0;
        this.b = 0;
        y_coords = new ArrayList<>();
        x_coords = new ArrayList<>();
    }

    public double FunctionReturnValue(double x) throws UnparsableExpressionException, UnknownFunctionException {
        Calculable calc = new ExpressionBuilder(expression)
                .withVariable("x", x)
                .build();
        Double result = calc.calculate();
        x_coords.add(x);
        y_coords.add(result);
        System.out.println("Value of func in : "+x+" is  " + result);
        if (result.isInfinite() || result.isNaN()) return 0;
        return result;
    }

    public void runPython()
    {
        String[] cmd = {
                "python",
                "plot.py",
                String.valueOf(this.a),
                String.valueOf(this.b),
                this.x_coords.toString(),
                this.y_coords.toString()
        };
        try {
            Runtime.getRuntime().exec(cmd);
            Arrays.stream(cmd).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
