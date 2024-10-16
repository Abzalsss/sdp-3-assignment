import java.util.ArrayList;
import java.util.List;

interface MyIterator<T> {
    boolean hasNext();
    T next();
}

class TaskIterator {
    private String name;

    public TaskIterator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class TaskIIterator implements MyIterator<TaskIterator> {
    private List<TaskIterator> tasks;
    private int position = 0;

    public TaskIIterator(List<TaskIterator> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean hasNext() {
        return position < tasks.size();
    }

    @Override
    public TaskIterator next() {
        return tasks.get(position++);
    }
}

class TaskCollection {
    private List<TaskIterator> tasks = new ArrayList<>();

    public void addTask(TaskIterator task) {
        tasks.add(task);
    }

    public MyIterator<TaskIterator> iterator() {
        return new TaskIIterator(tasks);
    }
}

public class Iterator {
    public static void main(String[] args) {
        TaskCollection taskCollection = new TaskCollection();

        taskCollection.addTask(new TaskIterator("Task 1"));
        taskCollection.addTask(new TaskIterator("Task 2"));
        taskCollection.addTask(new TaskIterator("Task 3"));

        MyIterator<TaskIterator> iterator = taskCollection.iterator();

        while (iterator.hasNext()) {
            TaskIterator task = iterator.next();
            System.out.println("Processing task: " + task.getName());
        }
    }
}
