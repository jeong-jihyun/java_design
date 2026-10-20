package com.example.demo.designpattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * [컴포지트 패턴] 개별 객체와 묶음(트리)을 똑같이 다루는 패턴
 * 예) 파일과 폴더: 폴더 크기 = 안에 든 파일/폴더 크기의 합
 */
interface FileSystemItem {
    String getName();
    long getSize();
    void print(String indent);
}

class FileItem implements FileSystemItem {
    private final String name;
    private final long size;

    FileItem(String name, long size) { this.name = name; this.size = size; }

    public String getName() { return name; }
    public long getSize() { return size; }
    public void print(String indent) { System.out.println(indent + "- " + name + " (" + size + "B)"); }
}

class Folder implements FileSystemItem {
    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();

    Folder(String name) { this.name = name; }

    void add(FileSystemItem item) { children.add(item); }

    public String getName() { return name; }

    public long getSize() {
        long total = 0;
        for (FileSystemItem c : children) total += c.getSize(); // 재귀적으로 합산
        return total;
    }

    public void print(String indent) {
        System.out.println(indent + "+ " + name + " (" + getSize() + "B)");
        for (FileSystemItem c : children) c.print(indent + "    ");
    }
}

public class CompositeDemo {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder src = new Folder("src");

        src.add(new FileItem("Main.java", 1200));
        src.add(new FileItem("Util.java", 800));
        root.add(src);
        root.add(new FileItem("README.md", 500));

        root.print("");
    }
}
