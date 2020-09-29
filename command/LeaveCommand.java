package command;

import java.util.List;
import java.util.NoSuchElementException;

import shell.Directory;

public class LeaveCommand implements Command {
    private Directory directory;

    public LeaveCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) {
        leave();
    }

    private void leave() throws NoSuchElementException {
        directory.remove();
    }

}
