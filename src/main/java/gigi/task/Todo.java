package gigi.task;

/**
 * Represents a simple todo task without any date or time constraints.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with a description.
     * @param task The description of the task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructs a Todo task with a description and initial tags.
     * @param task The description of the task.
     * @param tags A comma-separated string of tags.
     */
    public Todo(String task, String tags) {
        super(task);
        for (String tag: tags.split(", ")) {
            addTag(tag);
        }
    }

    /**
     * Converts the task into a simplified string format suitable for file storage.
     * The format typically includes the task type, completion status, description,
     * and any relevant dates, separated by a specific delimiter (e.g., "|").
     *
     * @return A formatted string representing the task for persistent storage.
     */
    public String serialize() {
        return "T|" + super.toString() + "|" + getTag();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n\tTags: " + getTag();
    }
}
