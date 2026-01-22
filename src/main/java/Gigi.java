import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Gigi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        String logo = "  ________.__       .__ \n" +
                " /  _____/|__| ____ |__|\n" +
                "/   \\  ___|  |/ ___\\|  |\n" +
                "\\    \\_\\  \\  / /_/  >  |\n" +
                " \\______  /__\\___  /|__|\n" +
                "        \\/  /_____/     ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to you see you again soon!");
                scanner.close();
                return;
            } else if(input.equals("list")) {
                System.out.println("Here's your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t"+ (i+1) + ". " + list.get(i));
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
                        list.get(Integer.parseInt(arguments)-1).markDone();
                        System.out.println("I have marked the task as done:");
                        System.out.println("\t"+list.get(Integer.parseInt(arguments)-1));
                    } else if (command.equals("unmark")) {
                        list.get(Integer.parseInt(arguments)-1).unmarkDone();
                        System.out.println("I have unmarked the task as done:");
                        System.out.println("\t"+list.get(Integer.parseInt(arguments)-1));
                    } else if (command.equals("delete")) {
                        Task temp = list.get(Integer.parseInt(arguments)-1);
                        System.out.println("I have removed the following task:");
                        System.out.println("\t"+temp);
                        list.remove(Integer.parseInt(arguments)-1);
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
                        System.out.println("\t"+ (i+1) + ". " + list.get(i));
                    }
                }
            }
        }
    }
}
