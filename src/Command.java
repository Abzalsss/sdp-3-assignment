interface cmd {
    void execute();
}

class TaskCommand {
    private String name;

    public TaskCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class PrintCommand implements cmd {
    private TaskCommand task;

    public PrintCommand(TaskCommand task) {
        this.task = task;
    }

    @Override
    public void execute() {
        System.out.println("Printing task: " + task.getName());
    }
}

class SaveCommand implements cmd {
    private TaskCommand task;

    public SaveCommand(TaskCommand task) {
        this.task = task;
    }

    @Override
    public void execute() {
        System.out.println("Saving task: " + task.getName());
    }
}

class DeleteCommand implements cmd {
    private TaskCommand task;

    public DeleteCommand(TaskCommand task) {
        this.task = task;
    }

    @Override
    public void execute() {
        System.out.println("Deleting task: " + task.getName());
    }
}

class CallCommand {
    private cmd command;

    public void setCommand(cmd command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

public class Command {
    public static void main(String[] args) {
        TaskCommand task = new TaskCommand("Task 1");

        cmd print = new PrintCommand(task);
        cmd save = new SaveCommand(task);
        cmd delete = new DeleteCommand(task);

        CallCommand call = new CallCommand();

        call.setCommand(print);
        call.executeCommand();

        call.setCommand(save);
        call.executeCommand();

        call.setCommand(delete);
        call.executeCommand();
    }
}
