package gigi.task;

public class Todo extends Task{
    public Todo(String task) {
        super(task);
    }

    /**
     * Converts the task into a simplified string format suitable for file storage.
     * The format typically includes the task type, completion status, description,
     * and any relevant dates, separated by a specific delimiter (e.g., "|").
     *
     * @return A formatted string representing the task for persistent storage.
     */
    public String serialize() {
        return "T|" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
