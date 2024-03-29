package terminal;

import javafx.application.Application;

import java.io.Console;
import java.io.IOException;

public class TerminalMain  {
    public static void main(String[] args) throws IOException {
        Console console = System.console();

        if (console == null) {
            System.out.println("Console is not supported");
            System.exit(1);
        }

        String name = console.readLine("What's your name? ");
        String age = console.readLine("How old are you? ");
        String city = console.readLine("Where do you live? ");

        console.format("%s, a %s year-old man is living in %s", name, age, city);
    }
}
