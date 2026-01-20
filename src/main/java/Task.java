public class Task {
    boolean isDone;
    String task;

    public Task (String task) {
        this.isDone = false;
        this.task = task;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
}
