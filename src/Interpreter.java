interface Expression {
    int interpret();
}

class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

class OperationExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;
    private char operator;

    public OperationExpression(Expression leftExpression, Expression rightExpression, char operator) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    @Override
    public int interpret() {
        switch (operator) {
            case '+':
                return leftExpression.interpret() + rightExpression.interpret();
            case '-':
                return leftExpression.interpret() - rightExpression.interpret();
            default:
                throw new UnsupportedOperationException("Unsupported operation: " + operator);
        }
    }
}

class Interpreterr {
    public static Expression parseExpression(String input) {
        String[] tokens = input.split(" ");
        Expression result = new NumberExpression(Integer.parseInt(tokens[0]));

        for (int i = 1; i < tokens.length; i += 2) {
            char operator = tokens[i].charAt(0);
            Expression nextNumber = new NumberExpression(Integer.parseInt(tokens[i + 1]));
            result = new OperationExpression(result, nextNumber, operator);
        }

        return result;
    }
}

public class Interpreter {
    public static void main(String[] args) {
        String input = "9 + 2 - 3";
        Expression expression = Interpreterr.parseExpression(input);
        int result = expression.interpret();
        System.out.println("Result: " + result);
    }
}
