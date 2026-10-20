package com.example.demo.designpattern.creational.factorymethod;

/**
 * [팩토리 메서드 패턴] 객체 생성을 하위 클래스에게 맡기는 패턴
 * 예) 알림 발송: 이메일/SMS 중 어떤 걸 만들지는 각 공장이 결정
 */
interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) { System.out.println("이메일 발송: " + message); }
}

class SmsNotification implements Notification {
    public void send(String message) { System.out.println("SMS 발송: " + message); }
}

/** 생성자(Creator): 어떤 객체를 만들지는 하위 클래스가 정한다 */
abstract class NotificationFactory {
    // 팩토리 메서드
    protected abstract Notification create();

    // 공통 로직: 만들어서 보내는 흐름은 같다
    public void notifyUser(String message) {
        Notification n = create();
        n.send(message);
    }
}

class EmailFactory extends NotificationFactory {
    protected Notification create() { return new EmailNotification(); }
}

class SmsFactory extends NotificationFactory {
    protected Notification create() { return new SmsNotification(); }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        NotificationFactory email = new EmailFactory();
        NotificationFactory sms = new SmsFactory();

        email.notifyUser("주문이 완료되었습니다.");
        sms.notifyUser("배송이 시작되었습니다.");
    }
}
