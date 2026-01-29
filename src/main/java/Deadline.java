public class Deadline extends Task{
    String by;

    public Deadline(String task, String by){
        super(task);
        this.by = by;
    }

    public String serialize() {
        return "D|" + super.toString() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.by + ")";
    }
}
