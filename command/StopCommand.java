package command;

import java.util.List;
import java.util.Scanner;

public class StopCommand implements Command {
    private Scanner scanner;

    public StopCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start(List<String> arguments) {
        closeScanner();
        stopProgram();
    }

    private void closeScanner() {
        scanner.close();
    }

    private void stopProgram() {
        System.exit(0);
    }

}
