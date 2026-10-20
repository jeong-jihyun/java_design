package com.example.demo.designpattern.structural.decorator;

/**
 * [데코레이터 패턴] 기존 객체를 감싸서 기능을 덧붙이는 패턴 (상속 없이 기능 추가)
 * 예) 커피에 우유, 샷을 추가하면 가격과 설명이 누적
 */
interface Coffee {
    String description();
    int cost();
}

class Americano implements Coffee {
    public String description() { return "아메리카노"; }
    public int cost() { return 3000; }
}

/** 감싸는 쪽의 공통 부모 */
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee inner;

    CoffeeDecorator(Coffee inner) { this.inner = inner; }
}

class MilkDecorator extends CoffeeDecorator {
    MilkDecorator(Coffee inner) { super(inner); }
    public String description() { return inner.description() + " + 우유"; }
    public int cost() { return inner.cost() + 500; }
}

class ShotDecorator extends CoffeeDecorator {
    ShotDecorator(Coffee inner) { super(inner); }
    public String description() { return inner.description() + " + 샷추가"; }
    public int cost() { return inner.cost() + 700; }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        // 양파 껍질처럼 겹겹이 감싼다
        Coffee order = new ShotDecorator(new MilkDecorator(new Americano()));

        System.out.println(order.description() + " = " + order.cost() + "원");
    }
}
