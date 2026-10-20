package com.example.demo.designpattern.behavioral.chainofresponsibility;

/**
 * [책임 연쇄 패턴] 요청을 처리할 수 있는 사람을 만날 때까지 줄줄이 넘기는 패턴
 * 예) 결재 라인: 팀장(10만원) -> 부장(100만원) -> 사장(그 이상)
 */
abstract class Approver {
    private Approver next;

    Approver setNext(Approver next) {
        this.next = next;
        return next; // 체이닝을 위해 다음 객체 반환
    }

    void approve(int amount) {
        if (canHandle(amount)) {
            handle(amount);
        } else if (next != null) {
            next.approve(amount); // 내가 못 하면 다음 사람에게
        } else {
            System.out.println(amount + "원은 처리할 사람이 없습니다.");
        }
    }

    protected abstract boolean canHandle(int amount);
    protected abstract void handle(int amount);
}

class TeamLeader extends Approver {
    protected boolean canHandle(int amount) { return amount <= 100_000; }
    protected void handle(int amount) { System.out.println("팀장 승인: " + amount); }
}

class Director extends Approver {
    protected boolean canHandle(int amount) { return amount <= 1_000_000; }
    protected void handle(int amount) { System.out.println("부장 승인: " + amount); }
}

class Ceo extends Approver {
    protected boolean canHandle(int amount) { return amount <= 10_000_000; }
    protected void handle(int amount) { System.out.println("사장 승인: " + amount); }
}

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        Approver chain = new TeamLeader();
        chain.setNext(new Director()).setNext(new Ceo());

        chain.approve(50_000);
        chain.approve(500_000);
        chain.approve(5_000_000);
        chain.approve(50_000_000);
    }
}
