# GoF 23가지 디자인 패턴 예제 (Java)

Spring 없이 순수 Java만으로 동작하는 예제입니다. 각 패턴 폴더의 `XxxDemo.java` 안에 `main`이 있어서 바로 실행할 수 있습니다.

## 한 줄 요약

### 생성 패턴 (Creational) - 객체를 "어떻게 만들까"

| 패턴 | 한 줄 설명 | 예제 |
|---|---|---|
| Singleton | 객체를 딱 1개만 만든다 | 설정 관리자, 로거 |
| Factory Method | 생성을 하위 클래스에게 맡긴다 | 이메일/SMS 알림 |
| Abstract Factory | 어울리는 객체 묶음을 한 번에 만든다 | 라이트/다크 테마 |
| Builder | 옵션 많은 객체를 단계별로 만든다 | 주문서 |
| Prototype | 기존 객체를 복사해서 만든다 | 문서 템플릿 |

### 구조 패턴 (Structural) - 객체를 "어떻게 엮을까"

| 패턴 | 한 줄 설명 | 예제 |
|---|---|---|
| Adapter | 맞지 않는 인터페이스를 변환한다 | XML 결제 -> JSON |
| Bridge | 추상과 구현을 분리해 따로 확장한다 | 리모컨 x 기기 |
| Composite | 개별과 묶음을 똑같이 다룬다 | 파일/폴더 |
| Decorator | 감싸서 기능을 덧붙인다 | 커피 토핑 |
| Facade | 복잡한 시스템에 간단한 창구를 둔다 | 주문 처리 |
| Flyweight | 같은 데이터는 공유해 메모리를 아낀다 | 숲의 나무 |
| Proxy | 대리인을 세워 접근을 통제한다 | 이미지 지연 로딩 |

### 행위 패턴 (Behavioral) - 객체끼리 "어떻게 일할까"

| 패턴 | 한 줄 설명 | 예제 |
|---|---|---|
| Chain of Responsibility | 처리할 사람을 만날 때까지 넘긴다 | 결재 라인 |
| Command | 요청을 객체로 만들어 저장/취소한다 | 편집기 Undo |
| Interpreter | 간단한 문법을 해석한다 | 후위 표기식 계산 |
| Iterator | 내부를 몰라도 순서대로 꺼낸다 | 책장 순회 |
| Mediator | 중재자를 통해서만 소통한다 | 채팅방 |
| Memento | 상태를 저장하고 되돌린다 | 게임 세이브/로드 |
| Observer | 변경을 구독자에게 자동 알린다 | 채널 구독 |
| State | 상태별 행동을 객체로 분리한다 | 주문 상태 |
| Strategy | 알고리즘을 갈아 끼운다 | 할인 정책 |
| Template Method | 뼈대는 부모, 세부는 자식 | 데이터 처리 |
| Visitor | 구조는 두고 기능을 밖에서 추가한다 | 도형 넓이/설명 |

## 실행 방법

IDE에서 각 `XxxDemo`의 `main`을 실행하거나, 터미널에서 아래처럼 실행합니다.

```bash
# 프로젝트 루트에서 전체 컴파일
javac -d out $(find src/main/java/com/example/demo/designpattern -name "*.java")

# 예: 싱글톤 실행
java -cp out com.example.demo.designpattern.creational.singleton.SingletonDemo
```

## 폴더 구조

```
designpattern/
├── creational/   singleton, factorymethod, abstractfactory, builder, prototype
├── structural/   adapter, bridge, composite, decorator, facade, flyweight, proxy
└── behavioral/   chainofresponsibility, command, interpreter, iterator, mediator,
                  memento, observer, state, strategy, templatemethod, visitor
```
