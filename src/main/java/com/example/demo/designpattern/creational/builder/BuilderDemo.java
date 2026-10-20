package com.example.demo.designpattern.creational.builder;

/**
 * [빌더 패턴] 옵션이 많은 객체를 단계별로 읽기 쉽게 만드는 패턴
 * 예) 주문서: 필수값은 꼭, 선택값은 필요한 것만 지정
 */
class Order {
    private final String product;   // 필수
    private final int quantity;     // 필수
    private final String address;   // 선택
    private final boolean gift;     // 선택
    private final String memo;      // 선택

    private Order(Builder b) {
        this.product = b.product;
        this.quantity = b.quantity;
        this.address = b.address;
        this.gift = b.gift;
        this.memo = b.memo;
    }

    @Override
    public String toString() {
        return "Order{상품=" + product + ", 수량=" + quantity + ", 주소=" + address
                + ", 선물포장=" + gift + ", 메모=" + memo + "}";
    }

    static class Builder {
        private final String product;
        private final int quantity;
        private String address = "미입력";
        private boolean gift = false;
        private String memo = "";

        Builder(String product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        Builder address(String address) { this.address = address; return this; }
        Builder gift(boolean gift) { this.gift = gift; return this; }
        Builder memo(String memo) { this.memo = memo; return this; }

        Order build() {
            if (quantity <= 0) throw new IllegalStateException("수량은 1 이상이어야 합니다.");
            return new Order(this);
        }
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        Order simple = new Order.Builder("노트북", 1).build();

        Order full = new Order.Builder("키보드", 2)
                .address("경기도 성남시 분당구")
                .gift(true)
                .memo("문 앞에 놓아주세요")
                .build();

        System.out.println(simple);
        System.out.println(full);
    }
}
