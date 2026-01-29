import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class Gigi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        File file = new File("data/gigi.txt");
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            for (String line : Files.readAllLines(file.toPath())) {
                String[] component = line.split("\\|");
                boolean isDone = component[1].startsWith("[X]");
                component[1] = component[1].substring(4);
                switch (component[0]) {
                    case "D" -> {
                        if (component.length > 3) {
                            throw new GigiException("Invalid Task from Saved Data");
                        }
                        list.add(new Deadline(component[1], component[2]));
                    }
                    case "E" -> {
                        if (component.length > 4) {
                            throw new GigiException("Invalid Task from Saved Data");
                        }
                        list.add(new Event(component[1], component[2], component[3]));
                    }
                    case "T" -> {
                        if (component.length > 2) {
                            throw new GigiException("Invalid Task from Saved Data");
                        }
                        list.add(new Todo(component[1]));
                    }
                    default -> throw new GigiException("Invalid Task from Saved Data");
                }
                if (isDone) {
                    list.getLast().markDone();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occured while reading data: " + e.getMessage());
        } catch (GigiException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Task from Saved Data");
        }

        String logo = "  ________.__       .__ \n" +
                " /  _____/|__| ____ |__|\n" +
                "/   \\  ___|  |/ ___\\|  |\n" +
                "\\    \\_\\  \\  / /_/  >  |\n" +
                " \\______  /__\\___  /|__|\n" +
                "        \\/  /_____/     ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to you see you again soon!");
                scanner.close();
                return;
            } else if (input.equals("list")) {
                System.out.println("Here's your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + list.get(i));
                }
            } else {
                try {
                    String[] split = input.split(" ", 2);
                    String command = split[0];
                    if (split.length < 2) {
                        throw new GigiException("Please give a valid command!");
                    }
                    String arguments = split[1];
                    if (command.equals("mark")) {
                        list.get(Integer.parseInt(arguments) - 1).markDone();
                        System.out.println("I have marked the task as done:");
                        System.out.println("\t" + list.get(Integer.parseInt(arguments) - 1));
                    } else if (command.equals("unmark")) {
                        list.get(Integer.parseInt(arguments) - 1).unmarkDone();
                        System.out.println("I have unmarked the task as done:");
                        System.out.println("\t" + list.get(Integer.parseInt(arguments) - 1));
                    } else if (command.equals("delete")) {
                        Task temp = list.get(Integer.parseInt(arguments) - 1);
                        System.out.println("I have removed the following task:");
                        System.out.println("\t" + temp);
                        list.remove(Integer.parseInt(arguments) - 1);
                    } else {
                        Task newTask;
                        if (command.equals("deadline")) {
                            String[] a = arguments.split("/by", 2);
                            if (a.length < 2) {
                                throw new GigiException("Provide a 'by' for your deadline!");
                            }
                            newTask = new Deadline(a[0], a[1]);
                        } else if (command.equals("event")) {
                            String[] a = arguments.split("/from", 2);
                            String description = a[0];
                            if (a.length < 2) {
                                throw new GigiException("Provide a 'from' for your event!");
                            }
                            a = a[1].split("/to", 2);
                            if (a.length < 2) {
                                throw new GigiException("Provide a 'to' for your event!");
                            }
                            newTask = new Event(description, a[0], a[1]);
                        } else if (command.equals("todo")) {
                            newTask = new Todo(arguments);
                        } else {
                            System.out.println("Invalid command :(");
                            continue;
                        }
                        list.add(newTask);
                        System.out.println("added: \n\t" + newTask);
                        System.out.println("There are now " + list.size() + " tasks in the list.");

                    }
                } catch (GigiException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Give a valid index!");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + list.get(i));
                    }
                }
                saveTasks(file, list);
            }
        }
    }

    /**
     * Saves the current list of tasks to local storage.
     *
     * @param file  The File object of the file
     * @param tasks The list of tasks to be serialized
     */
    public static void saveTasks(File file, List<Task> tasks) {

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(task.serialize()+"\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving data: " + e.getMessage());
        }
    }
}