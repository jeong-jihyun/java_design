package com.example.demo.designpattern.behavioral.visitor;

import java.util.List;

/**
 * [비지터 패턴] 객체 구조는 그대로 두고, 새 기능(연산)을 밖에서 추가하는 패턴
 * 예) 도형(원, 사각형)은 건드리지 않고 "넓이 계산", "그리기" 기능을 따로 추가
 */
interface Shape {
    void accept(ShapeVisitor visitor);
}

class Circle implements Shape {
    final double radius;
    Circle(double radius) { this.radius = radius; }
    public void accept(ShapeVisitor v) { v.visit(this); } // 더블 디스패치
}

class Rectangle implements Shape {
    final double width, height;
    Rectangle(double width, double height) { this.width = width; this.height = height; }
    public void accept(ShapeVisitor v) { v.visit(this); }
}

interface ShapeVisitor {
    void visit(Circle c);
    void visit(Rectangle r);
}

/** 기능 1: 넓이 계산 */
class AreaVisitor implements ShapeVisitor {
    public void visit(Circle c) {
        System.out.printf("원 넓이 = %.2f%n", Math.PI * c.radius * c.radius);
    }
    public void visit(Rectangle r) {
        System.out.printf("사각형 넓이 = %.2f%n", r.width * r.height);
    }
}

/** 기능 2: 설명 출력 (도형 클래스는 수정하지 않고 추가됨) */
class DescribeVisitor implements ShapeVisitor {
    public void visit(Circle c) { System.out.println("반지름 " + c.radius + "인 원"); }
    public void visit(Rectangle r) { System.out.println(r.width + " x " + r.height + " 사각형"); }
}

public class VisitorDemo {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Circle(3), new Rectangle(4, 5));

        ShapeVisitor area = new AreaVisitor();
        ShapeVisitor describe = new DescribeVisitor();

        for (Shape s : shapes) s.accept(describe);
        for (Shape s : shapes) s.accept(area);
    }
}
