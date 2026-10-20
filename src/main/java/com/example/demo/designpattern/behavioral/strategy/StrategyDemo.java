package com.example.demo.designpattern.behavioral.strategy;

/**
 * [전략 패턴] 알고리즘을 갈아 끼울 수 있게 객체로 분리하는 패턴
 * 예) 할인 정책: 정가 / 정률 할인 / 정액 할인을 실행 중에 교체
 */
interface DiscountStrategy {
    int apply(int price);
}

class NoDiscount implements DiscountStrategy {
    public int apply(int price) { return price; }
}

class PercentDiscount implements DiscountStrategy {
    private final int percent;
    PercentDiscount(int percent) { this.percent = percent; }
    public int apply(int price) { return price - price * percent / 100; }
}

class FixedDiscount implements DiscountStrategy {
    private final int amount;
    FixedDiscount(int amount) { this.amount = amount; }
    public int apply(int price) { return Math.max(0, price - amount); }
}

class Cart {
    private DiscountStrategy strategy = new NoDiscount();

    void setStrategy(DiscountStrategy strategy) { this.strategy = strategy; }

    int checkout(int price) { return strategy.apply(price); }
}

public class StrategyDemo {
    public static void main(String[] args) {
        Cart cart = new Cart();
        int price = 50_000;

        System.out.println("정가: " + cart.checkout(price));

        cart.setStrategy(new PercentDiscount(10));
        System.out.println("10% 할인: " + cart.checkout(price));

        cart.setStrategy(new FixedDiscount(7_000));
        System.out.println("7천원 할인: " + cart.checkout(price));

        // 람다로도 전략을 바로 만들 수 있다 (함수형 인터페이스)
        cart.setStrategy(p -> p / 2);
        System.out.println("반값 이벤트(람다): " + cart.checkout(price));
    }
}
