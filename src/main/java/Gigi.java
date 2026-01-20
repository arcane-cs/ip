import java.util.Scanner;

public class Gigi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
            } else {
                System.out.println(input);
            }
        }
    }
}
