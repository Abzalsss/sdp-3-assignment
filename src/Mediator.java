interface mdtr {
    void send(TaskMed task, HandlerMed handler);
}

class TaskMed {
    private String name;

    public TaskMed(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class HandlerMed {
    protected mdtr mediator;

    public HandlerMed(mdtr mediator) {
        this.mediator = mediator;
    }

    public abstract void handleMedRequest(TaskMed task);
}

class CertainHandler extends HandlerMed {
    public CertainHandler(mdtr mediator) {
        super(mediator);
    }

    @Override
    public void handleMedRequest(TaskMed task) {
        System.out.println("CertainHandler processing: " + task.getName());
        mediator.send(task, this);
    }
}

class AnotherHandler extends HandlerMed {
    public AnotherHandler(mdtr mediator) {
        super(mediator);
    }

    @Override
    public void handleMedRequest(TaskMed task) {
        System.out.println("AnotherHandler processing: " + task.getName());
        mediator.send(task, this);
    }
}

class TaskMediator implements mdtr {
    @Override
    public void send(TaskMed task, HandlerMed handler) {
        System.out.println("Mediator coordinating: " + task.getName() + " from " + handler.getClass().getSimpleName());
    }
}

public class Mediator {
    public static void main(String[] args) {
        mdtr mediator = new TaskMediator();

        HandlerMed certainHandler = new CertainHandler(mediator);
        HandlerMed anotherHandler = new AnotherHandler(mediator);

        TaskMed task1 = new TaskMed("Task 1");
        TaskMed task2 = new TaskMed("Task 2");

        certainHandler.handleMedRequest(task1);
        anotherHandler.handleMedRequest(task2);
    }
}
