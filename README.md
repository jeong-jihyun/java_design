# java_design

**GoF 23가지 디자인 패턴을 순수 Java 예제로 정리한 학습 저장소**입니다.
패턴마다 `XxxDemo.java` 한 파일에 `main`이 있어 바로 실행해 볼 수 있습니다. (패턴별 한 줄 설명표는 [`designpattern/README.md`](src/main/java/com/example/demo/designpattern/README.md))

## 한눈에 보기

| 분류 | 패턴 수 | 패턴 |
| --- | --- | --- |
| 생성 (Creational) | 5 | Singleton, Factory Method, Abstract Factory, Builder, Prototype |
| 구조 (Structural) | 7 | Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy |
| 행위 (Behavioral) | 11 | Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor |

## 실행 방법 (Spring 없이, JDK만 있으면 됨)

```bash
javac -d out $(find src/main/java/com/example/demo/designpattern -name "*.java")
java -cp out com.example.demo.designpattern.creational.singleton.SingletonDemo
```

패키지 경로는 `com.example.demo.designpattern.{creational|structural|behavioral}.{패턴이름}.{패턴이름}Demo` 형식입니다.
23개 파일 모두 위 방법으로 컴파일되고, 오류 없이 실행되어 결과가 출력되는 것을 확인했습니다. (JDK 21 기준)

## 폴더 구조

```
java_design/
├── build.gradle, settings.gradle, gradlew   # Gradle 설정 (Spring Boot 틀)
└── src/
    ├── main/java/com/example/demo/
    │   ├── DemoApplication.java             # Spring Boot 시작점 (패턴 예제와는 무관)
    │   └── designpattern/                   # 디자인 패턴 23개 + 설명 README
    ├── main/resources/application.properties # MySQL 접속 설정
    └── test/java/.../DemoApplicationTests.java
```

> 디자인 패턴 예제는 Spring이나 DB와 무관합니다. 반면 `DemoApplication`을 Gradle로 띄우려면 아래 환경이 필요합니다.

## Spring Boot 앱으로 실행할 때 (선택)

| 항목 | 내용 |
| --- | --- |
| 빌드 | Gradle, Java 17 toolchain, Spring Boot `4.0.0-SNAPSHOT` |
| 구성 | JPA + MySQL 커넥터 + springdoc(Swagger UI) + devtools |
| DB | MySQL이 떠 있어야 하며, 접속 정보는 환경변수로 줌 |

| 환경변수 | 설명 | 기본값 |
| --- | --- | --- |
| `DB_URL` | JDBC 주소 | `jdbc:mysql://localhost:3306/mysql?...` |
| `DB_USERNAME` | DB 사용자 | `root` |
| `DB_PASSWORD` | DB 비밀번호 | 없음 (필수) |

```bash
export DB_PASSWORD=...
./gradlew bootRun
```

## 개선 내역 (2026-10)

| 구분 | 문제 | 수정 내용 |
| --- | --- | --- |
| 비밀 값 노출 | `application.properties`에 MySQL root 비밀번호가 그대로 들어 있었음 | 환경변수(`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`)로 교체 |
| README | 저장소 첫 화면에 설명이 없었음 | 루트 README 추가 |

## ⚠️ 보안 안내

이전 커밋 기록에 **DB 비밀번호가 남아 있습니다.** 파일에서는 지웠지만 기록에는 남으므로, 같은 비밀번호를 다른 곳에서도 쓰고 있다면 **변경**을 권장합니다.

## 확인하지 못한 것 / 주의할 점

- 이 작업 환경에서는 Maven Central에 접속할 수 없어 **Gradle 빌드와 Spring Boot 앱 실행, 테스트는 하지 못했습니다.**
- `build.gradle`이 Spring Boot **`4.0.0-SNAPSHOT`** 과 스냅샷 저장소를 쓰는데, 스냅샷은 시간이 지나면 내려받을 수 없게 될 수 있습니다. 빌드가 실패하면 정식 버전으로 올리세요.
- `mysql:mysql-connector-java` 좌표는 더 이상 쓰이지 않는 이름입니다. 최신은 `com.mysql:mysql-connector-j`입니다.
