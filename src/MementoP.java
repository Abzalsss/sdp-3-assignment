class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class TextEditor {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Memento save() {
        return new Memento(text);
    }

    public void restore(Memento memento) {
        text = memento.getState();
    }
}

class History {
    private java.util.Stack<Memento> history = new java.util.Stack<>();

    public void save(Memento memento) {
        history.push(memento);
    }

    public Memento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}

public class MementoP {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        editor.setText("Hello");
        history.save(editor.save());

        editor.setText("Hello, World");
        history.save(editor.save());

        editor.setText(", World!!!");

        System.out.println("Current Text: " + editor.getText());
        editor.restore(history.undo());
        System.out.println("After Undo: " + editor.getText());
        editor.restore(history.undo());
        System.out.println("After Second Undo: " + editor.getText());
    }
}
