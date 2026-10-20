package com.example.demo.designpattern.behavioral.state;

/**
 * [상태 패턴] 상태에 따라 행동이 달라지는 걸 if문 대신 상태 객체로 표현하는 패턴
 * 예) 주문 상태: 접수 -> 배송중 -> 완료 (상태마다 "다음 단계"의 의미가 다르다)
 */
interface OrderState {
    void next(OrderContext ctx);
    String name();
}

class Received implements OrderState {
    public void next(OrderContext ctx) {
        System.out.println("접수 완료 -> 배송을 시작합니다.");
        ctx.setState(new Shipping());
    }
    public String name() { return "접수"; }
}

class Shipping implements OrderState {
    public void next(OrderContext ctx) {
        System.out.println("배송 중 -> 고객에게 도착했습니다.");
        ctx.setState(new Delivered());
    }
    public String name() { return "배송중"; }
}

class Delivered implements OrderState {
    public void next(OrderContext ctx) {
        System.out.println("이미 완료된 주문입니다.");
    }
    public String name() { return "완료"; }
}

/** 상태를 들고 있는 주문 */
class OrderContext {
    private OrderState state = new Received();

    void setState(OrderState state) { this.state = state; }
    void next() { state.next(this); }
    String currentState() { return state.name(); }
}

public class StateDemo {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        for (int i = 0; i < 3; i++) {
            System.out.println("현재 상태: " + order.currentState());
            order.next();
        }
        System.out.println("현재 상태: " + order.currentState());
        order.next(); // 완료 상태에서 또 호출
    }
}
