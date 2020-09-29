package command;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

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
            throw new NoSuchElementException("Wrong number of argument ");
        }
        copyFile(arguments.get(0), arguments.get(1));
    }

    private void copyFile(String originalFileName, String copyDirName) {
        String[] directories = copyDirName.split(File.separator);
        int lastPosition = directories.length - 1;
        String copyFileName = directories[lastPosition].trim();

        String newDirectory = changeDirectory(directories);
        // createFile(newDirectory, copyFileName);
    }

    private String changeDirectory(String[] copyToDirectories) {
        // String[] currentDirectories = directory.get().split("/");
        String currentDirectory = this.directory.get();
        // String matchingDirectories = "";
        // for (String current : currentDirectories) {
        // for (String copy : copyToDirectories) {
        // if (current.equals(copy)) {
        // matchingDirectories = current;
        // }
        // }
        // }
        try {
            String matchingDir = Stream.of(copyToDirectories).filter(current -> currentDirectory.contains(current))
                    .collect(Collectors.joining(File.separator));
            System.out.println(matchingDir);
        } catch (NullPointerException e) {

        }
        return null;
    }

    private void createFile(String directoryTo, String copy) {

    }
}
