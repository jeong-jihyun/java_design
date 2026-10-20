package com.example.demo.designpattern.structural.bridge;

/**
 * [브리지 패턴] "무엇(추상)"과 "어떻게(구현)"를 분리해서 따로 확장하는 패턴
 * 예) 리모컨(TV/라디오 상관없이) x 기기(TV, 라디오) 조합을 클래스 폭발 없이 구성
 */
interface Device {
    void powerOn();
    void powerOff();
    void setVolume(int volume);
}

class Tv implements Device {
    public void powerOn() { System.out.println("TV 켜짐"); }
    public void powerOff() { System.out.println("TV 꺼짐"); }
    public void setVolume(int v) { System.out.println("TV 볼륨 " + v); }
}

class Radio implements Device {
    public void powerOn() { System.out.println("라디오 켜짐"); }
    public void powerOff() { System.out.println("라디오 꺼짐"); }
    public void setVolume(int v) { System.out.println("라디오 볼륨 " + v); }
}

/** 추상부: 구현(Device)을 "가지고" 있다 -> 이것이 다리(bridge) */
class RemoteControl {
    protected final Device device;

    RemoteControl(Device device) { this.device = device; }

    void toggleOn() { device.powerOn(); }
    void toggleOff() { device.powerOff(); }
}

/** 추상부만 따로 확장 가능 */
class AdvancedRemote extends RemoteControl {
    AdvancedRemote(Device device) { super(device); }

    void mute() { device.setVolume(0); }
}

public class BridgeDemo {
    public static void main(String[] args) {
        AdvancedRemote tvRemote = new AdvancedRemote(new Tv());
        tvRemote.toggleOn();
        tvRemote.mute();

        AdvancedRemote radioRemote = new AdvancedRemote(new Radio());
        radioRemote.toggleOn();
        radioRemote.mute();
    }
}
