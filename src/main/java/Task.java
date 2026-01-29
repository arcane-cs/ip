public abstract class Task {
    boolean isDone;
    String task;

    public Task (String task) {
        this.isDone = false;
        this.task = task;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public abstract String serialize();

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
}
