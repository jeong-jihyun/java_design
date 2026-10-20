package com.example.demo.designpattern.behavioral.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [커맨드 패턴] 요청(행동)을 객체로 만들어서 저장, 실행, 취소할 수 있게 하는 패턴
 * 예) 텍스트 편집기의 쓰기 + 실행 취소(Undo)
 */
class TextEditor {
    private final StringBuilder text = new StringBuilder();

    void append(String s) { text.append(s); }
    void deleteLast(int count) { text.setLength(text.length() - count); }
    String getText() { return text.toString(); }
}

interface Command {
    void execute();
    void undo();
}

class WriteCommand implements Command {
    private final TextEditor editor;
    private final String added;

    WriteCommand(TextEditor editor, String added) { this.editor = editor; this.added = added; }

    public void execute() { editor.append(added); }
    public void undo() { editor.deleteLast(added.length()); }
}

/** 실행한 명령을 스택에 쌓아두었다가 되돌린다 */
class CommandHistory {
    private final Deque<Command> stack = new ArrayDeque<>();

    void run(Command c) {
        c.execute();
        stack.push(c);
    }

    void undo() {
        if (!stack.isEmpty()) stack.pop().undo();
    }
}

public class CommandDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CommandHistory history = new CommandHistory();

        history.run(new WriteCommand(editor, "Hello"));
        history.run(new WriteCommand(editor, ", World"));
        System.out.println("현재: " + editor.getText());

        history.undo();
        System.out.println("undo 1회: " + editor.getText());

        history.undo();
        System.out.println("undo 2회: [" + editor.getText() + "]");
    }
}
