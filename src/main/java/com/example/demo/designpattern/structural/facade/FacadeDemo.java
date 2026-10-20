package com.example.demo.designpattern.structural.facade;

/**
 * [퍼사드 패턴] 복잡한 여러 시스템을 하나의 간단한 창구로 감싸는 패턴
 * 예) 주문 한 번 호출로 재고확인 -> 결제 -> 배송 요청을 모두 처리
 */
class InventoryService {
    boolean check(String product) {
        System.out.println("재고 확인: " + product);
        return true;
    }
}

class PaymentService {
    void pay(int amount) { System.out.println("결제 처리: " + amount + "원"); }
}

class ShippingService {
    void ship(String address) { System.out.println("배송 요청: " + address); }
}

/** 퍼사드: 사용자는 이것만 알면 된다 */
class OrderFacade {
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final ShippingService shipping = new ShippingService();

    void order(String product, int amount, String address) {
        if (!inventory.check(product)) {
            System.out.println("재고가 없습니다.");
            return;
        }
        payment.pay(amount);
        shipping.ship(address);
        System.out.println("주문 완료!");
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        new OrderFacade().order("마우스", 25000, "성남시 분당구");
    }
}
