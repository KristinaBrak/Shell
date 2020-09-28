package shell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import command.Command;
import command.EnterCommand;
import command.ListCommand;
// import command.ListCommand;
// import command.StopCommand;
import command.StopCommand;

public class ShellImp implements Shell {
    private String currentDirectory;
    private Map<String, Command> listOfCommands;

    public ShellImp(String currentDirectory) {
        this.currentDirectory = currentDirectory;

        listOfCommands = new HashMap<String, Command>();
        listOfCommands.put("stop", new StopCommand());
        listOfCommands.put("enter", new EnterCommand());
        listOfCommands.put("list", new ListCommand());

    }

    @Override
    public void run() {

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("directory>");
            String input = scan.nextLine().toString();

            try {
                handleInput(input);
            } catch (NoSuchElementException e) {
                sendErrorMessage(input);
            }

        }
        // scan.close();
    }

    private void handleInput(String input) throws NoSuchElementException {
        List<String> arguments = getArguments(input);

        Command command = getCommand(arguments.get(0));

        arguments.remove(0);
        command.start(arguments);
    }

    private List<String> getArguments(String input) throws NoSuchElementException {
        String[] commandData = input.trim().toLowerCase().split(" ");
        if (commandData.length == 0) {
            throw new NoSuchElementException();
        }
        List<String> arguments = Stream.of(commandData).map(String::trim).collect(Collectors.toList());
        return arguments;
    }

    private Command getCommand(String commandName) throws NoSuchElementException {
        return listOfCommands.entrySet().stream().filter(entry -> entry.getKey().equals(commandName)).findFirst().get()
                .getValue();
    }

    private void sendErrorMessage(String incorrectCommand) {
        System.out.println("Bad request: " + incorrectCommand);
    }

}
