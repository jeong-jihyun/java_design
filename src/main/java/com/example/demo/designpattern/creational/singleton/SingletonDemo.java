package com.example.demo.designpattern.creational.singleton;

/**
 * [싱글톤 패턴] 객체를 딱 1개만 만들어서 모두가 같이 쓰게 하는 패턴
 * 예) 설정 관리자, 로거, 커넥션 풀
 */
class AppConfig {
    // volatile: 여러 스레드가 동시에 접근해도 안전하게 값을 보게 함
    private static volatile AppConfig instance;
    private String mode = "DEV";

    // 밖에서 new 못 하게 막는다
    private AppConfig() { }

    // Double-Checked Locking: 처음 한 번만 잠그고 만든다
    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
}

/** enum 싱글톤: 가장 간단하고 안전한 방법 (직렬화/리플렉션 공격에도 강함) */
enum Logger {
    INSTANCE;

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        AppConfig a = AppConfig.getInstance();
        AppConfig b = AppConfig.getInstance();

        a.setMode("PROD");
        System.out.println("a == b ? " + (a == b));          // true: 같은 객체
        System.out.println("b의 모드 = " + b.getMode());      // a가 바꾼 PROD가 보임

        Logger.INSTANCE.log("enum 싱글톤 동작 확인");
    }
}
