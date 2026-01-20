import java.util.ArrayList;
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
                    System.out.println(i+1 + ". " + list.get(i));
                }
            } else {
                String[] split = input.split(" ");
                if (split[0].equals("mark")) {
                    list.get(Integer.parseInt(split[1])-1).markDone();
                    System.out.println("I have marked the task as done:");
                    System.out.println( list.get(Integer.parseInt(split[1])-1));
                } else if (split[0].equals("unmark")) {
                    list.get(Integer.parseInt(split[1])-1).unmarkDone();
                    System.out.println("I have unmarked the task as done:");
                    System.out.println( list.get(Integer.parseInt(split[1])-1));
                } else {
                    list.add(new Task(input));
                    System.out.println("added: " + input);
                }
            }
        }
    }
}
