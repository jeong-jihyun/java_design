package com.example.demo.designpattern.behavioral.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [인터프리터 패턴] 간단한 문법(언어)을 클래스로 표현해서 해석하는 패턴
 * 예) 후위 표기식 "3 4 + 2 -" 을 계산 -> (3 + 4) - 2 = 5
 */
interface Expression {
    int interpret();
}

class Number implements Expression {
    private final int value;
    Number(int value) { this.value = value; }
    public int interpret() { return value; }
}

class Plus implements Expression {
    private final Expression left, right;
    Plus(Expression left, Expression right) { this.left = left; this.right = right; }
    public int interpret() { return left.interpret() + right.interpret(); }
}

class Minus implements Expression {
    private final Expression left, right;
    Minus(Expression left, Expression right) { this.left = left; this.right = right; }
    public int interpret() { return left.interpret() - right.interpret(); }
}

class Times implements Expression {
    private final Expression left, right;
    Times(Expression left, Expression right) { this.left = left; this.right = right; }
    public int interpret() { return left.interpret() * right.interpret(); }
}

/** 문자열을 읽어서 표현식 트리를 만든다 */
class ExpressionParser {
    static Expression parse(String postfix) {
        Deque<Expression> stack = new ArrayDeque<>();
        for (String token : postfix.split(" ")) {
            switch (token) {
                case "+" -> { Expression r = stack.pop(); Expression l = stack.pop(); stack.push(new Plus(l, r)); }
                case "-" -> { Expression r = stack.pop(); Expression l = stack.pop(); stack.push(new Minus(l, r)); }
                case "*" -> { Expression r = stack.pop(); Expression l = stack.pop(); stack.push(new Times(l, r)); }
                default -> stack.push(new Number(Integer.parseInt(token)));
            }
        }
        return stack.pop();
    }
}

public class InterpreterDemo {
    public static void main(String[] args) {
        String expr = "3 4 + 2 -";
        System.out.println(expr + " = " + ExpressionParser.parse(expr).interpret());

        String expr2 = "2 3 4 * +";
        System.out.println(expr2 + " = " + ExpressionParser.parse(expr2).interpret());
    }
}
