package com.example.demo.designpattern.behavioral.templatemethod;

/**
 * [템플릿 메서드 패턴] 전체 흐름(뼈대)은 부모가 정하고, 세부 단계만 자식이 채우는 패턴
 * 예) 데이터 처리: 읽기 -> 변환 -> 저장 순서는 고정, 각 단계 내용은 종류마다 다름
 */
abstract class DataProcessor {
    // 템플릿 메서드: final 로 순서를 못 바꾸게 한다
    public final void process() {
        read();
        transform();
        save();
    }

    protected abstract void read();
    protected abstract void transform();

    // 기본 구현이 있는 단계 (필요하면 자식이 재정의)
    protected void save() { System.out.println("3) 기본 저장소에 저장"); }
}

class CsvProcessor extends DataProcessor {
    protected void read() { System.out.println("1) CSV 파일 읽기"); }
    protected void transform() { System.out.println("2) 쉼표 기준으로 분리"); }
}

class JsonProcessor extends DataProcessor {
    protected void read() { System.out.println("1) JSON 파일 읽기"); }
    protected void transform() { System.out.println("2) 객체로 변환"); }

    @Override
    protected void save() { System.out.println("3) JSON은 DB에 저장"); }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("== CSV ==");
        new CsvProcessor().process();

        System.out.println("== JSON ==");
        new JsonProcessor().process();
    }
}
