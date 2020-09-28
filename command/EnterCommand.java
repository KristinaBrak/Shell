package command;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import shell.Directory;

public class EnterCommand implements Command {
    private Directory directory;

    public EnterCommand(Directory directory) {
        this.directory = directory;
    }

    @Override
    public void start(List<String> arguments) throws NoSuchElementException {
        if (arguments.size() > 1) {
            throw new NoSuchElementException();
        } else {
            changeDirectory(arguments.get(0));
        }
    }

    private void changeDirectory(String directoryName) throws NoSuchElementException {
        if (!isDirectoryValid(directoryName)) {
            throw new NoSuchElementException();
        }
        directory.add(directoryName);
    }

    private boolean isDirectoryValid(String directoryName) throws NoSuchElementException {
        File currentDirectory = new File(directory.get());
        File[] files = currentDirectory.listFiles();

        if (files == null) {
            throw new NoSuchElementException();
        }

        boolean isDirectory = Stream.of(files).filter(File::isDirectory)
                .anyMatch(dir -> dir.getName().equals(directoryName));

        return isDirectory;
    }

}
