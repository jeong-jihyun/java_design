package com.example.demo.designpattern.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * [프로토타입 패턴] 새로 만들지 않고 기존 객체를 복사해서 만드는 패턴
 * 예) 문서 템플릿 복사
 */
interface Prototype<T> {
    T copy();
}

class Document implements Prototype<Document> {
    private String title;
    private final List<String> sections = new ArrayList<>();

    Document(String title) { this.title = title; }

    void setTitle(String title) { this.title = title; }
    void addSection(String s) { sections.add(s); }

    // 깊은 복사: 리스트도 새로 만들어야 원본이 안 바뀐다
    @Override
    public Document copy() {
        Document clone = new Document(this.title);
        clone.sections.addAll(this.sections);
        return clone;
    }

    @Override
    public String toString() { return title + " " + sections; }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        Document template = new Document("보고서 템플릿");
        template.addSection("개요");
        template.addSection("본문");

        Document report = template.copy();
        report.setTitle("10월 업무 보고서");
        report.addSection("결론");

        System.out.println("원본: " + template);   // 결론이 없음 -> 깊은 복사 성공
        System.out.println("복사본: " + report);
    }
}
