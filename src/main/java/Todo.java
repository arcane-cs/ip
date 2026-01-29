public class Todo extends Task{
    public Todo(String task) {
        super(task);
    }

    public String serialize() {
        return "T|" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
