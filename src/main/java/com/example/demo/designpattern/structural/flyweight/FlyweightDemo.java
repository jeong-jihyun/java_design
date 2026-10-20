package com.example.demo.designpattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * [플라이웨이트 패턴] 같은 데이터는 공유해서 메모리를 아끼는 패턴
 * 예) 숲에 나무 1000그루: 나무 종류(이미지 등 무거운 데이터)는 공유, 위치만 따로 저장
 */
class TreeType {
    private final String name;
    private final String color;

    TreeType(String name, String color) {
        this.name = name;
        this.color = color;
        System.out.println("TreeType 새로 생성: " + name);
    }

    void draw(int x, int y) {
        System.out.println(color + " " + name + " 나무를 (" + x + "," + y + ")에 그림");
    }
}

/** 공유 객체를 관리하는 공장: 있으면 재사용, 없으면 생성 */
class TreeTypeFactory {
    private static final Map<String, TreeType> cache = new HashMap<>();

    static TreeType get(String name, String color) {
        return cache.computeIfAbsent(name + color, k -> new TreeType(name, color));
    }

    static int size() { return cache.size(); }
}

/** 나무 한 그루: 바뀌는 값(위치)만 가진다 */
class Tree {
    private final int x, y;
    private final TreeType type;

    Tree(int x, int y, TreeType type) { this.x = x; this.y = y; this.type = type; }

    void draw() { type.draw(x, y); }
}

public class FlyweightDemo {
    public static void main(String[] args) {
        Tree[] forest = new Tree[5];
        for (int i = 0; i < forest.length; i++) {
            // 종류는 2개뿐이라 TreeType은 2번만 만들어진다
            TreeType type = (i % 2 == 0)
                    ? TreeTypeFactory.get("소나무", "초록")
                    : TreeTypeFactory.get("단풍나무", "빨간");
            forest[i] = new Tree(i * 10, i * 5, type);
        }

        for (Tree t : forest) t.draw();
        System.out.println("나무 5그루, 공유된 TreeType 수 = " + TreeTypeFactory.size());
    }
}
