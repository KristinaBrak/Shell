package command;

import java.util.List;

import command.Command;

public class EnterCommand implements Command {

    public EnterCommand() {
    }

    public String changeDirectory(String currentPath, String newPath) {
        String[] paths = currentPath.split(">");

        currentPath = paths[0].trim() + "/";
        return currentPath + newPath + " >";
    }

    @Override
    public void start(List<String> arguments) {
        // TODO Auto-generated method stub

    }

}
