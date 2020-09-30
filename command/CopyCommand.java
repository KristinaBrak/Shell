
package command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import shell.Directory;

public class CopyCommand implements Command {
    private Directory directory;

    public CopyCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) throws NoSuchElementException {
        if (arguments.size() != 2) {
            throw new NoSuchElementException("Incorrect number of arguments");
        }
        copyFile(arguments);

    }

    private void copyFile(List<String> arguments) throws NoSuchElementException {
        File fileFrom = new File(directory.get() + File.separator + arguments.get(0));
        File fileTo = new File(directory.get() + File.separator + arguments.get(1));
        try {
            Files.copy(fileFrom.toPath(), fileTo.toPath());
        } catch (IOException e) {
            throw new NoSuchElementException("Cannot copy " + fileFrom.getName() + " to " + fileTo.getName());
        }

    }

}
