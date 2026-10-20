package com.example.demo.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * [옵저버 패턴] 한 객체가 바뀌면 구독 중인 객체들에게 자동으로 알려주는 패턴
 * 예) 유튜브 채널 구독: 새 영상이 올라오면 구독자 모두에게 알림
 */
interface Subscriber {
    void update(String videoTitle);
}

class Channel {
    private final String name;
    private final List<Subscriber> subscribers = new ArrayList<>();

    Channel(String name) { this.name = name; }

    void subscribe(Subscriber s) { subscribers.add(s); }
    void unsubscribe(Subscriber s) { subscribers.remove(s); }

    void upload(String title) {
        System.out.println("[" + name + "] 새 영상 업로드: " + title);
        for (Subscriber s : subscribers) s.update(title); // 모두에게 알림
    }
}

class Viewer implements Subscriber {
    private final String name;

    Viewer(String name) { this.name = name; }

    public void update(String videoTitle) {
        System.out.println("  " + name + "님에게 알림: " + videoTitle);
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        Channel channel = new Channel("자바 강의");
        Subscriber a = new Viewer("지현");
        Subscriber b = new Viewer("민수");

        channel.subscribe(a);
        channel.subscribe(b);
        channel.upload("디자인 패턴 입문");

        channel.unsubscribe(b); // 민수는 구독 취소
        channel.upload("옵저버 패턴 실습");
    }
}
