package edu.jsu.mcis.cs408.calculator;

import java.util.HashMap;

public class CalculatorController extends AbstractController {

    public static final String KEY_PROPERTY = "Key";
    public static final String OUTPUT_PROPERTY = "Output";
    public static final String FUNCTION_PROPERTY = "Function";

    private final HashMap<String, Character> keyMap;
    private final HashMap<String, CalculatorFunction> functionMap;

    public CalculatorController() {
        super();
        keyMap = createKeyMap();
        functionMap = createFunctionMap();
    }

    public void changeKey(Character newText) {
        setModelProperty(KEY_PROPERTY, newText);
    }

    public void changeFunction(CalculatorFunction newFunction) {
        setModelProperty(FUNCTION_PROPERTY, newFunction);
    }

    public void changeScreen(String newText) {
        setModelProperty(OUTPUT_PROPERTY, newText);
    }

    public void processInput(String tag) {

        switch (tag) {

            case "buttonAdd": case "buttonClear": case "buttonDivide":
            case "buttonEqual": case "buttonMultiply": case "buttonPercent":
            case "buttonSign": case "buttonSqrt": case "buttonSubtract":

                changeFunction(functionMap.get(tag));
                break;

            default:

                changeKey( keyMap.get(tag) );
                break;

        }
    }

    private HashMap createKeyMap() {

        HashMap<String, Character> map = new HashMap<>();

        map.put("button0", '0');
        map.put("button1", '1');
        map.put("button2", '2');
        map.put("button3", '3');
        map.put("button4", '4');
        map.put("button5", '5');
        map.put("button6", '6');
        map.put("button7", '7');
        map.put("button8", '8');
        map.put("button9", '9');
        map.put("buttonDecimal", '.');

        return map;
    }

    private HashMap createFunctionMap() {
        HashMap<String, CalculatorFunction> map = new HashMap<>();

        map.put("buttonAdd", CalculatorFunction.ADD);
        map.put("buttonClear", CalculatorFunction.CLEAR);
        map.put("buttonDivide", CalculatorFunction.DIVIDE);
        map.put("buttonEqual", CalculatorFunction.EQUALS);
        map.put("buttonMultiply", CalculatorFunction.MULTIPLY);
        map.put("buttonPercent", CalculatorFunction.PERCENT);
        map.put("buttonSign", CalculatorFunction.SIGN);
        map.put("buttonClear", CalculatorFunction.CLEAR);
        map.put("buttonSqrt", CalculatorFunction.SQRT);
        map.put("buttonSubtract", CalculatorFunction.SUBTRACT);

        return map;
    }
}
