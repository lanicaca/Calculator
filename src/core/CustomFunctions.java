package core;

import de.congrace.exp4j.CustomFunction;
import de.congrace.exp4j.InvalidCustomFunctionException;

public class CustomFunctions {

    CustomFunction logb = new CustomFunction("logb", 2) {
        @Override
        public double applyFunction(double... args) {
            return Math.log(args[0]) / Math.log(args[1]);
        }
    };

    public CustomFunctions() throws InvalidCustomFunctionException {
    }
}
