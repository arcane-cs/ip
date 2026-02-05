package gigi.parser;

import gigi.GigiException;
import gigi.task.Deadline;
import gigi.task.Task;
import gigi.task.TaskList;
import gigi.task.Todo;
import gigi.task.Event;
import gigi.ui.Ui;

public class Parser {
    /**
     * Parses the user input and executes the corresponding command.
     * This method interprets the command word and arguments, modifies the task list
     * accordingly, and interacts with the UI to provide feedback to the user.
     *
     * @param input The full raw string input provided by the user.
     * @param tasks The TaskList object that will be modified or queried.
     * @param ui The Ui object used to display success or error messages to the user.
     * @throws GigiException If the command is invalid or the arguments are malformed.
     */
    public static String parse(String input, TaskList tasks, Ui ui) throws GigiException {
        try {
            String[] split = input.split(" ", 2);
            String command = split[0];

            if (command.equals("bye")) {
                return ui.showMessage("Bye. Hope to you see you again soon!");
            } else if (command.equals("hello")) {
                return ui.showWelcome();
            } else if (command.equals("list")) {
                return ui.showMessage("Here's your list:\n" + tasks.printList());
            } else {
                if (split.length < 2) {
                    throw new GigiException("Please give a valid command!");
                }
                String arguments = split[1];
                if (command.equals("mark")) {
                    tasks.markTask(Integer.parseInt(arguments) - 1);
                    return ui.showMessage("I have marked the task as done:\n" + "\t" + tasks.printTask(Integer.parseInt(arguments) - 1));
                } else if (command.equals("unmark")) {
                    tasks.unmarkTask(Integer.parseInt(arguments) - 1);
                    return ui.showMessage("I have unmarked the task as done:\n" + "\t" + tasks.printTask(Integer.parseInt(arguments) - 1));
                } else if (command.equals("delete")) {
                    String response = ui.showMessage("I have removed the following task:\n" + "\t" + tasks.printTask(Integer.parseInt(arguments) - 1));
                    tasks.deleteTask(Integer.parseInt(arguments) - 1);
                    return response;
                } else if (command.equals("find")) {
                    String result = tasks.findString(arguments);
                    if (result.isEmpty()){
                        return ui.showMessage("No matches found");
                    } else {
                        return "Here you go:\n" + ui.showMessage(result);
                    }
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
                        return ui.showMessage("Invalid command :(");
                    }
                    tasks.addTask(newTask);
                    return ui.showMessage("added: \n\t" + newTask + "\n" + "There are now " + tasks.size() + " tasks in the list.");
                }
            }
        } catch (GigiException e) {
            return ui.showMessage(e.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showMessage("Give a valid index!\n" + tasks.printList());
        }
    }
}