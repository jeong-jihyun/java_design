package com.example.demo.designpattern.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * [중재자 패턴] 객체끼리 직접 대화하지 않고 중재자를 통해서만 소통하게 하는 패턴
 * 예) 채팅방: 참가자들은 서로를 모르고 채팅방에만 메시지를 보낸다
 */
interface ChatMediator {
    void send(String message, User from);
    void join(User user);
}

class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    public void join(User user) { users.add(user); }

    public void send(String message, User from) {
        for (User u : users) {
            if (u != from) u.receive(message, from.getName()); // 보낸 사람 제외 전달
        }
    }
}

class User {
    private final String name;
    private final ChatMediator mediator;

    User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.join(this);
    }

    String getName() { return name; }

    void say(String message) {
        System.out.println(name + " 보냄: " + message);
        mediator.send(message, this);
    }

    void receive(String message, String from) {
        System.out.println("  -> " + name + " 받음 (" + from + "): " + message);
    }
}

public class MediatorDemo {
    public static void main(String[] args) {
        ChatMediator room = new ChatRoom();
        User kim = new User("김철수", room);
        User lee = new User("이영희", room);
        new User("박민수", room);

        kim.say("안녕하세요!");
        lee.say("반갑습니다.");
    }
}
