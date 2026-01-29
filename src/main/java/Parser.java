public class Parser {
    public static void parse(String input, TaskList tasks, Ui ui, Storage storage) throws GigiException {
        try {
            String[] split = input.split(" ", 2);
            String command = split[0];

            if (command.equals("bye")) {
                ui.showMessage("Bye. Hope to you see you again soon!");
            } else if (command.equals("list")) {
                ui.showMessage("Here's your list:");
                ui.showMessage(tasks.printList());
            } else {
                if (split.length < 2) {
                    throw new GigiException("Please give a valid command!");
                }
                String arguments = split[1];
                if (command.equals("mark")) {
                    tasks.markTask(Integer.parseInt(arguments) - 1);
                    ui.showMessage("I have marked the task as done:");
                    ui.showMessage("\t" + tasks.printTask(Integer.parseInt(arguments) - 1));
                } else if (command.equals("unmark")) {
                    tasks.unmarkTask(Integer.parseInt(arguments) - 1);
                    ui.showMessage("I have unmarked the task as done:");
                    ui.showMessage("\t" + tasks.printTask(Integer.parseInt(arguments) - 1));
                } else if (command.equals("delete")) {
                    ui.showMessage("I have removed the following task:");
                    ui.showMessage("\t" + tasks.printTask(Integer.parseInt(arguments) - 1));
                    tasks.deleteTask(Integer.parseInt(arguments) - 1);
                } else {
                    Task newTask;
                    if (command.equals("deadline")) {
                        String[] deadlineParts = arguments.split("/by", 2);
                        if (deadlineParts.length < 2) {
                            throw new GigiException("Provide a 'by' for your deadline!");
                        }
                        newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                    } else if (command.equals("event")) {
                        String[] eventParts = arguments.split("/from", 2);
                        String description = eventParts[0];
                        if (eventParts.length < 2) {
                            throw new GigiException("Provide a 'from' for your event!");
                        }
                        eventParts = eventParts[1].split("/to", 2);
                        if (eventParts.length < 2) {
                            throw new GigiException("Provide a 'to' for your event!");
                        }
                        newTask = new Event(description, eventParts[0], eventParts[1]);
                    } else if (command.equals("todo")) {
                        newTask = new Todo(arguments);
                    } else {
                        ui.showMessage("Invalid command :(");
                        return;
                    }
                    tasks.addTask(newTask);
                    ui.showMessage("added: \n\t" + newTask);
                    ui.showMessage("There are now " + tasks.size() + " tasks in the list.");
                }
            }
        } catch (GigiException e) {
            ui.showMessage(e.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.showMessage("Give a valid index!");
            tasks.printList();
        }
    }
}