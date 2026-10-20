package com.example.demo.designpattern.structural.proxy;

/**
 * [프록시 패턴] 진짜 객체 앞에 대리인을 세워서 접근을 통제하는 패턴
 * 예) 무거운 이미지는 처음 볼 때만 로딩(지연 로딩), 이후엔 재사용
 */
interface Image {
    void display();
}

/** 진짜 객체: 생성 시 로딩이 오래 걸린다고 가정 */
class RealImage implements Image {
    private final String file;

    RealImage(String file) {
        this.file = file;
        System.out.println("(무거운 작업) 디스크에서 이미지 로딩: " + file);
    }

    public void display() { System.out.println("이미지 표시: " + file); }
}

/** 대리인: 실제로 필요할 때까지 진짜 객체 생성을 미룬다 */
class ImageProxy implements Image {
    private final String file;
    private RealImage real; // 처음엔 null

    ImageProxy(String file) { this.file = file; }

    public void display() {
        if (real == null) {
            real = new RealImage(file);
        }
        real.display();
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        Image image = new ImageProxy("photo.png"); // 아직 로딩 안 함
        System.out.println("프록시 생성 완료");

        image.display(); // 첫 호출: 로딩 발생
        image.display(); // 두 번째: 로딩 없이 바로 표시
    }
}
