package apkuznetsov.solver;

import java.util.Stack;

public class Solver {

    public static String getExpandedForm(int number) {

        return toString(calcExpandedForm(number));
    }

    private static Stack<Integer> calcExpandedForm(int number) {
        if (number <= 0) {
            throw new ZeroOrNegativeNumberException();
        }

        Stack<Integer> numberParts = new Stack<>();

        for (int truncatedNumber = number, digit, order = 1; truncatedNumber > 0; truncatedNumber /= 10, order *= 10) {
            digit = truncatedNumber % 10;
            if (digit > 0) {
                numberParts.push(digit * order);
            }
        }

        return numberParts;
    }

    private static String toString(Stack<Integer> expandedFormNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(expandedFormNumber.pop());
        for (int i = 0; i <= expandedFormNumber.size(); i++) {
            sb.append(" + ");
            sb.append(expandedFormNumber.pop());
        }

        return sb.toString();
    }
}
