package com.example.demo.designpattern.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [메멘토 패턴] 객체의 상태를 저장해 두었다가 나중에 되돌리는 패턴 (게임 세이브/로드)
 * 내부 구조는 노출하지 않고 "스냅샷"만 따로 보관한다
 */
class GameCharacter {
    private int level;
    private int hp;

    GameCharacter(int level, int hp) { this.level = level; this.hp = hp; }

    void levelUp() { level++; hp += 50; }
    void damage(int d) { hp -= d; }

    // 현재 상태를 저장
    Memento save() { return new Memento(level, hp); }

    // 저장된 상태로 복원
    void restore(Memento m) { this.level = m.level; this.hp = m.hp; }

    @Override
    public String toString() { return "레벨 " + level + ", HP " + hp; }

    /** 스냅샷: 캐릭터 안에 숨겨둔 클래스라 외부에서 내용을 바꿀 수 없다 */
    static class Memento {
        private final int level;
        private final int hp;

        private Memento(int level, int hp) { this.level = level; this.hp = hp; }
    }
}

public class MementoDemo {
    public static void main(String[] args) {
        GameCharacter hero = new GameCharacter(1, 100);
        Deque<GameCharacter.Memento> saves = new ArrayDeque<>();

        saves.push(hero.save());
        System.out.println("저장: " + hero);

        hero.levelUp();
        hero.damage(120);
        System.out.println("전투 후: " + hero);

        hero.restore(saves.pop());
        System.out.println("로드: " + hero);
    }
}
