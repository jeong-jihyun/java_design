package com.example.demo.designpattern.structural.adapter;

/**
 * [어댑터 패턴] 맞지 않는 인터페이스를 변환해서 연결해 주는 패턴 (콘센트 변환 플러그)
 * 예) 기존 XML 방식 결제 모듈을 새 JSON 방식 인터페이스에 끼워 맞추기
 */
interface JsonPayment {
    void payJson(String json);
}

/** 이미 있는 옛날 모듈 (수정할 수 없다고 가정) */
class LegacyXmlPayment {
    void payXml(String xml) { System.out.println("레거시 XML 결제 처리: " + xml); }
}

/** 어댑터: JSON 요청을 받아서 XML로 바꿔 레거시에 전달 */
class PaymentAdapter implements JsonPayment {
    private final LegacyXmlPayment legacy;

    PaymentAdapter(LegacyXmlPayment legacy) { this.legacy = legacy; }

    @Override
    public void payJson(String json) {
        String xml = "<payment>" + json + "</payment>"; // 단순 변환 예시
        legacy.payXml(xml);
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        JsonPayment payment = new PaymentAdapter(new LegacyXmlPayment());
        payment.payJson("{\"amount\":10000}");
    }
}
