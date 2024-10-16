class TaskChain {
    enum Priority { Low, Medium, High }
    private Priority priority;
    private String name;

    public TaskChain(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }
}

abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handleRequest(TaskChain task);
}

class LowPH extends Handler {
    public void handleRequest(TaskChain task) {
        if (task.getPriority() == TaskChain.Priority.Low) {
            System.out.println("Low priority task: " + task.getName());
        } else if (next != null) {
            next.handleRequest(task);
        }
    }
}

class MedPH extends Handler {
    public void handleRequest(TaskChain task) {
        if (task.getPriority() == TaskChain.Priority.Medium) {
            System.out.println("Medium priority task: " + task.getName());
        } else if (next != null) {
            next.handleRequest(task);
        }
    }
}

class HighPH extends Handler {
    public void handleRequest(TaskChain task) {
        if (task.getPriority() == TaskChain.Priority.High) {
            System.out.println("High priority task: " + task.getName());
        } else if (next != null) {
            next.handleRequest(task);
        }
    }
}

class Chain {
    public static void main(String[] args) {
        Handler low = new LowPH();
        Handler medium = new MedPH();
        Handler high = new HighPH();

        low.setNext(medium);
        medium.setNext(high);
        high.setNext(low);

        TaskChain task1 = new TaskChain("Task 1", TaskChain.Priority.Low);
        TaskChain task2 = new TaskChain("Task 2", TaskChain.Priority.Medium);
        TaskChain task3 = new TaskChain("Task 3", TaskChain.Priority.High);

        low.handleRequest(task1);
        low.handleRequest(task2);
        low.handleRequest(task3);
    }
}
