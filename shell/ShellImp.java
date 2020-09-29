
package shell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import command.Command;
import command.DeleteCommand;
import command.EnterCommand;
import command.LeaveCommand;
import command.ListCommand;

import command.StopCommand;

public class ShellImp implements Shell {
    private Directory directory;
    private Map<String, Command> listOfCommands;

    public ShellImp() {
        // final String currentDirectory = System.getProperty("user.dir");
        final String currentDirectory = "/home/kristina/Desktop";
        this.directory = new Directory(currentDirectory);

        listOfCommands = new HashMap<String, Command>();
        listOfCommands.put("stop", new StopCommand());
        listOfCommands.put("enter", new EnterCommand(directory));
        listOfCommands.put("list", new ListCommand(directory));
        listOfCommands.put("leave", new LeaveCommand(directory));
        listOfCommands.put("delete", new DeleteCommand(directory));

    }

    @Override
    public void run() {

        Scanner scan = new Scanner(System.in);
        while (true) {

            System.out.print(directory.get() + ">");
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
        String[] commandData = input.trim().split(" ");
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
