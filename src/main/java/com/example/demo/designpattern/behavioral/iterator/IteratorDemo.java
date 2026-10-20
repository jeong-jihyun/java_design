package com.example.demo.designpattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * [이터레이터 패턴] 내부 구조를 몰라도 요소를 하나씩 순서대로 꺼내게 하는 패턴
 * 예) 책장(BookShelf)의 책을 for-each로 순회
 */
class BookShelf implements Iterable<String> {
    private final List<String> books = new ArrayList<>();

    void add(String book) { books.add(book); }

    // for-each 문이 이 메서드를 호출한다
    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() { return index < books.size(); }

            @Override
            public String next() { return books.get(index++); }
        };
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf();
        shelf.add("클린 코드");
        shelf.add("이펙티브 자바");
        shelf.add("GoF 디자인 패턴");

        for (String book : shelf) {
            System.out.println("책: " + book);
        }
    }
}
