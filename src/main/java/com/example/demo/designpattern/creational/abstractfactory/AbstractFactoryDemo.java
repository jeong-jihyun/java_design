package com.example.demo.designpattern.creational.abstractfactory;

/**
 * [추상 팩토리 패턴] 서로 어울리는 객체 묶음(제품군)을 한 번에 만드는 패턴
 * 예) 라이트/다크 테마: 버튼과 체크박스를 같은 테마로 맞춰서 생성
 */
interface Button { void render(); }
interface Checkbox { void render(); }

class LightButton implements Button {
    public void render() { System.out.println("밝은 버튼"); }
}
class LightCheckbox implements Checkbox {
    public void render() { System.out.println("밝은 체크박스"); }
}
class DarkButton implements Button {
    public void render() { System.out.println("어두운 버튼"); }
}
class DarkCheckbox implements Checkbox {
    public void render() { System.out.println("어두운 체크박스"); }
}

/** 제품군을 만드는 공장 */
interface ThemeFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class LightThemeFactory implements ThemeFactory {
    public Button createButton() { return new LightButton(); }
    public Checkbox createCheckbox() { return new LightCheckbox(); }
}

class DarkThemeFactory implements ThemeFactory {
    public Button createButton() { return new DarkButton(); }
    public Checkbox createCheckbox() { return new DarkCheckbox(); }
}

/** 클라이언트는 어떤 테마인지 몰라도 된다 */
class Screen {
    private final Button button;
    private final Checkbox checkbox;

    Screen(ThemeFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    void draw() {
        button.render();
        checkbox.render();
    }
}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        System.out.println("== 라이트 테마 ==");
        new Screen(new LightThemeFactory()).draw();

        System.out.println("== 다크 테마 ==");
        new Screen(new DarkThemeFactory()).draw();
    }
}
